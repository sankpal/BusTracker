package com.example.nihit.mtrack;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

public class Source extends AppCompatActivity {

    ListView listSource;
    EditText editText;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;
    String[] source;
    String json_string,data1;
    JSONObject jsonObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);

        editText=(EditText)findViewById(R.id.txt_search);
        listSource=(ListView)findViewById(R.id.listSource);

        initList();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    initList();
                }
                else {
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listSource.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent=new Intent(Source.this,MainActivity.class);
                intent.putExtra("source",listSource.getItemAtPosition(position).toString());
                startActivity(intent);

            }
        });

    }


    public  void initList(){

        source= new String[]{"Shantinagar","Pragati Nagar","Raigad colony","Jagtap nagar","Hanuman nagar","ITI","Sambhaji Nagar","Racecourse naka","Panyacha khajina","Nathagole","Padmaraje","Khari corner","Mirajkar tikti","Shahu Maidan","Bindu chowk","Shivaji Chowk","Maharana Pratap chowk","Laxmipuri","Venus Corner","Railway station","CBS","Kavla naka","Sadar bazaar","Shahu college","D.Y. patil","Bhosale wadi","Kadamwadi","Shivaji Park","Hotel K Tree","Star Bazar","Takala","Axis Bank","Rajarampuri 2nd Lane","Rajarampuri 9th Lane","Bai Cha Putla","Vijay Bakery","Csiber","Rajaram College","Shivaji University","Shantiniketan","Shahu Toll Naka","KIT","Uchgaon Phata","Kaneri Phata","Kaneri Wadi","BIMAT","Laxmi Mandir","Pachayat Samiti Stop","Kagal Bus Stand","Kagal","R.K. Nagar","Khadicha Ganpati","Post office","Shugarmil","Sai Colony","Postal Colony","Jarag Nagar Last stop","Jarag Nagar L.A. No.2","Baba Jarag Nagar","Jadhav Park","Balaji Park","Sasne Colony","Nirman Chowk","Hotel Vijayraj","Sinchan Bhavan","Pitali Ganapti","Post Office","Passport office","Sawarkar Chowk","Shree Colony","Dnyan Shanti Nivas","Padalkar Colony",""};
        Arrays.sort(source);
        // source= new String[]{"kadamwadi","","Raigad colony","Jagtap nagar","Hanuman nagar","ITI","Sambhaji Nagar","Racecourse naka","Panyacha khajina","Nathagole","Padmaraje","Khari corner","Mirajkar tikti","Shahu Maidan","Bindu chowk","Shivaji Chowk","Maharana Pratap chowk","Laxmipuri","Venus Corner","Railway station","CBS","Kavla naka","Sadar bazaar","Shahu college","D.Y. patil","Bhosale wadi","Kadamwadi","R.K. Nagar","Khadicha Ganpati","Jarag Nagar","Post office","Shugarmil","Shivaji Park","Hotel K Tree","Star Bazar","Takala","Axis Bank","Rajarampuri 2nd Lane","Rajarampuri 9th Lane","Bai Cha Putla","Vijay Bakery","Csiber","Rajaram College","Shivaji University","Shantiniketan","Shahu Toll Naka","KIT","Uchgaon Phata","Kaneri Phata","Kaneri Wadi","BIMAT","Laxmi Mandir","Pachayat Samiti Stop","Kagal Bus Stand","Kagal"};
        arrayList=new ArrayList<>(Arrays.asList(source));
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.item,R.id.ls_item,arrayList);
        listSource.setAdapter(arrayAdapter);


    }

    public void searchItem(String textToSearch){
        for (String item:source){
            if(!item.contains(textToSearch)){
                arrayList.remove(item);
            }
        }

        arrayAdapter.notifyDataSetChanged();
    }
}
