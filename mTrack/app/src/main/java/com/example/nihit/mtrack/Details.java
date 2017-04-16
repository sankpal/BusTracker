package com.example.nihit.mtrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class Details extends AppCompatActivity {

    Button button;
    String json_string;
    String data1,demo1;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String bus_no,source,destination,path;
    TextView tv1,tv2,tv3,tv4;
    ListView listView;
    double lat_src,lng_src;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv1=(TextView)findViewById(R.id.txt_busNo);
        tv2=(TextView)findViewById(R.id.txt_source);
        tv3=(TextView)findViewById(R.id.txt_destination);


        demo1 = getIntent().getExtras().getString("demo1");
        data1 = getIntent().getExtras().getString("data");

        try {
            jsonObject = new JSONObject(data1);
            jsonArray = jsonObject.getJSONArray("server_response");


            int count=0;
            JSONObject jo = jsonArray.getJSONObject(count);
            bus_no = jo.getString("bus_no");
            source = jo.getString("source");
            destination = jo.getString("destination");
            path = jo.getString("path");




        } catch (JSONException e) {
            e.printStackTrace();
        }

        tv1.setText("Bus No:"+bus_no);
        tv2.setText("Source:"+source);
        tv3.setText("Destination:"+destination);
        //tv4.setText(path);



        toStops();
    }//end onCreate

    public void toStops(){
        tv4=(TextView)findViewById(R.id.txt_path);
        tv4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.nihit.mtrack.Stops");
                        intent.putExtra("data",path);
                        startActivity(intent);
                    }
                }


        );
    }

    public void toMap( View view){

        /*button = (Button)findViewById(R.id.btnLoc);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent("com.example.nihit.mtrack.Location");
                        intent2.putExtra("data",bus_no);
                        startActivity(intent2);
                    }
                }


        );*/

        String type="search";
        BackgroundTask1 backgroundtask = new BackgroundTask1(this);
        backgroundtask.execute(type,demo1);

    }//end toMap

    class BackgroundTask1 extends AsyncTask<String ,Void,String> {
        Context context;

        BackgroundTask1(Context ctx){
            context=ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type=params[0];
            String login_url="http://nihit.comli.com/distance.php";
            if (type.equals("search")){
                try {
                    String dmo=params[1];

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("source","UTF-8")+"="+URLEncoder.encode(dmo,"UTF-8");
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

            data1=result;
            Intent intent2 = new Intent("com.example.nihit.mtrack.Location");
            intent2.putExtra("data",data1);
            intent2.putExtra("demo2",bus_no);
            startActivity(intent2);





        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }

}
