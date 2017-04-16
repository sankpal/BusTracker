package com.example.nihit.mtrack;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String data1;
    Button button;
    EditText time1,Source1,Destination1;
    String json_string;
    String src,dst,time;
    String s1,d1;
   // AutoCompleteTextView Source1,Destination1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Source1= (AutoCompleteTextView) findViewById(R.id.source);
        String [] stops={"Shantinagar","ITI","Raigad colony","Sambhaji Nagar","Padmaraje","Shahu Maidan","Shivaji Chowk","Laxmipuri","CBS","Kadamwadi"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,stops);
        Source1.setThreshold(1);
        Source1.setAdapter(adapter);

        Destination1= (AutoCompleteTextView) findViewById(R.id.destination);
        Destination1.setThreshold(1);
        Destination1.setAdapter(adapter);*/

        Source1=(EditText) findViewById(R.id.source);
        toSource();
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            //Source1.setText(bundle.getString("source"));
            s1=bundle.getString("source");

        }
        Source1.setText(s1);

        Destination1=(EditText) findViewById(R.id.destination);
        toDestination();
        Bundle bundle1=getIntent().getExtras();
        if(bundle1!=null){
            Destination1.setText(bundle.getString("destination"));
            /*d1=bundle.getString("destination");
            Destination1.setText(d1);*/
        }

        time1 = (EditText)findViewById(R.id.time);


        toMaps();

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sh=getSharedPreferences("file",MODE_PRIVATE);
        SharedPreferences.Editor myedit=sh.edit();
        myedit.putString("k1",s1);
        myedit.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh=getSharedPreferences("file",MODE_PRIVATE);
        String source=sh.getString("k1","");
        Source1.setText(source);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_contact) {
            Intent intent5=new Intent("com.example.nihit.mtrack.Contact");
            startActivity(intent5);

        } else if (id == R.id.nav_feedback) {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=santase.radefffactory")));
            }catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=santase.radefffactory")));
            }

        } else if (id == R.id.nav_rate) {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=santase.radefffactory")));
            }catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=santase.radefffactory")));
            }

        } else if (id == R.id.nav_upgrade) {
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=santase.radefffactory")));
            }catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=santase.radefffactory")));
            }

        } else if (id == R.id.nav_terms) {

        } else if (id == R.id.nav_share) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String shareBody = "Your body here";
            String shareSub = "Your subject here";
            myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(myIntent,"Share using"));

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  void toSource(){
        Source1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.nihit.mtrack.Source");
                        startActivity(intent);
                    }
                }


        );
    }

    public  void toDestination(){
        Destination1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent3 = new Intent("com.example.nihit.mtrack.Destination");
                        startActivity(intent3);
                    }
                }


        );
    }

    public void toMaps(){

        button = (Button)findViewById(R.id.btn_stops);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent("com.example.nihit.mtrack.MapsActivity");
                        startActivity(intent1);
                    }
                }


        );


    }

    public  void onSearch(View view){

        src=Source1.getText().toString();
        dst=Destination1.getText().toString();
        time=time1.getText().toString();
        String type="search";

        BackgroundTask backgroundtask = new BackgroundTask(this);
        backgroundtask.execute(type,src,dst,time);

    }




    class BackgroundTask extends AsyncTask<String ,Void,String> {
        Context context;
        AlertDialog alertDialog;
        Button button;
        BackgroundTask(Context ctx){
            context=ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type=params[0];
            String login_url="http://nihit.comli.com/main1.php";
            if (type.equals("search")){
                try {
                    String src=params[1];
                    String dst=params[2];
                    String tme=params[3];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("src","UTF-8")+"="+URLEncoder.encode(src,"UTF-8")+"&"+
                            URLEncoder.encode("dst","UTF-8")+"="+URLEncoder.encode(dst,"UTF-8")+"&"+
                            URLEncoder.encode("tme","UTF-8")+"="+URLEncoder.encode(tme,"UTF-8");
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


            //TextView textView = (TextView) findViewById(R.id.textView1);
            //textView.setText(result);
            data1=result;
            Intent intent2 = new Intent("com.example.nihit.mtrack.Main2Activity");
            intent2.putExtra("data",data1);
            intent2.putExtra("demo",src);
            startActivity(intent2);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }






}
