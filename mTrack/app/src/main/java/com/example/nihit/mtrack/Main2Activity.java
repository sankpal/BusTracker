package com.example.nihit.mtrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

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

public class Main2Activity extends AppCompatActivity {
    String data1,json_string,demo;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;
    TextView tv,tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //tv1=(TextView)findViewById(R.id.txt_demo);
        listView = (ListView)findViewById(R.id.listView);
        contactAdapter = new ContactAdapter(this,R.layout.row_layout);
        listView.setAdapter(contactAdapter);
        data1 = getIntent().getExtras().getString("data");
        try {
            jsonObject = new JSONObject(data1);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count=0;
            String id,time,route_name;
            while (count<jsonArray.length())
            {
                JSONObject jo = jsonArray.getJSONObject(count);
                    id = jo.getString("id");
                    time = jo.getString("time");
                    route_name = jo.getString("route_name");
                    Info info = new Info(id,time,route_name);
                    contactAdapter.add(info);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               TextView v=(TextView)findViewById(R.id.id);
               //Toast.makeText(getApplicationContext(),parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),v.getText().toString(),Toast.LENGTH_SHORT).show();


                String route_id="4";
                //String time="06:30";
                String type="search";


                /*BackgroundTask backgroundtask = new BackgroundTask(this);
                backgroundtask.execute(type,route_id,time);*/

                BackgroundTask backgroundtask = new BackgroundTask(this);
                backgroundtask.execute(type,route_id);

            }
        });

        demo = getIntent().getExtras().getString("demo");

    }

    class BackgroundTask extends AsyncTask<String ,Void,String> {
        AdapterView.OnItemClickListener context;

        BackgroundTask(AdapterView.OnItemClickListener ctx){
            context=ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type=params[0];
            String login_url="http://nihit.comli.com/route1.php";
            if (type.equals("search")){
                try {
                    String routeid=params[1];
                    //String tme=params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("route_id","UTF-8")+"="+URLEncoder.encode(routeid,"UTF-8");

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
            Intent intent = new Intent("com.example.nihit.mtrack.Details");
            intent.putExtra("data",data1);
            intent.putExtra("demo1",demo);
            startActivity(intent);





        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }
}
