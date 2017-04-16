package com.example.nihit.mtrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class Location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String bus_num,json_string,data1,demo2;
    JSONObject jsonObject;
    JSONArray jsonArray;
    double lat_src,lng_src,lat1,lng1;
    String bus_no;
    String response;
    String parsedDistance=" ";
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tv=(TextView)findViewById(R.id.txt);

        bus_no = getIntent().getExtras().getString("demo2");
        data1 = getIntent().getExtras().getString("data");

        try {
            jsonObject = new JSONObject(data1);
            jsonArray = jsonObject.getJSONArray("server_response");


            int count=0;
            JSONObject jo = jsonArray.getJSONObject(count);
            lat_src = jo.getDouble("latitude");
            lng_src = jo.getDouble("longitude");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Handler handler=new Handler();
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                try {
                    String type="search";
                    BackgroundTask1 backgroundtask = new BackgroundTask1(this);
                    backgroundtask.execute(type,bus_no);

                }
                catch (Exception e){

                }
            }
        };
        timer.schedule(timerTask,0,10000);

    }

    class BackgroundTask1 extends AsyncTask<String ,Void,String> {
        TimerTask context;
        AlertDialog alertDialog;
        Button button;
        BackgroundTask1(TimerTask ctx){
            context=ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type=params[0];
            String login_url="http://nihit.comli.com/location.php";
            if (type.equals("search")){
                try {
                    //String bus=params[1];

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("bus_no","UTF-8")+"="+URLEncoder.encode(bus_no,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder stringBuilder = new StringBuilder();
                    while ((json_string=bufferedReader.readLine())!=null){
                        stringBuilder.append(json_string+"\n");
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return stringBuilder.toString().trim();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;

        }

        @Override
        protected void onPreExecute() {



        }


        @Override
        protected void onPostExecute(String result) {

            try {
                jsonObject = new JSONObject(result);
                jsonArray = jsonObject.getJSONArray("server_response");


                int count=0;
                JSONObject jo = jsonArray.getJSONObject(count);
                lat1 = jo.getDouble("latitude");
                lng1 = jo.getDouble("longitude");
                bus_no = jo.getString("bus_no");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String rs=getDistance(lat_src,lng_src,lat1,lng1);
            tv.setText("Distance:"+rs);

            onMapReady(mMap);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng location = new LatLng(lat1, lng1);
        mMap.addMarker(new MarkerOptions().position(location).title(bus_no).icon(BitmapDescriptorFactory.fromResource(R.drawable.b)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }


    public String getDistance(final double lat1, final double lon1, final double lat2, final double lon2){

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    URL url = new URL("http://maps.googleapis.com/maps/api/directions/json?origin=" + lat1 + "," + lon1 + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric&mode=driving");
                    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("routes");
                    JSONObject routes = array.getJSONObject(0);
                    JSONArray legs = routes.getJSONArray("legs");
                    JSONObject steps = legs.getJSONObject(0);
                    JSONObject distance = steps.getJSONObject("distance");
                    parsedDistance = distance.getString("text");

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return parsedDistance;
    }


}



