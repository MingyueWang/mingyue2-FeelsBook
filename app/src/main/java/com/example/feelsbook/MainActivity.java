package com.example.feelsbook;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.example.feelsbook.feels.Counts;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "file.sav";
    private ListView emotionlist;
    private TextView totalCounts;


    ArrayList<feels> feelsList;
    ArrayAdapter<feels> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadFromFile();
        emotionlist= (ListView) findViewById(R.id.listview1);
        final ArrayAdapter adapter = new ArrayAdapter<feels>(this, android.R.layout.simple_list_item_1, Counts);
        emotionlist.setAdapter(adapter);
        Button emotionButton = (Button) findViewById(R.id.button2);
        Button allcounts = (Button) findViewById(R.id.button4);
        //click the button to go to another acativity to go to countsfeels
        allcounts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                saveInFile();
                Intent intent = new Intent(MainActivity.this, countfeels.class);
                startActivity(intent);


            }
                                       });
        //go to writefeels acativity
        emotionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, writefeels.class);

                startActivity(intent);



            }
        });
        //listview to help change and delete
        emotionlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view, int pos, long arg3){
                Intent intent = new Intent(MainActivity.this, changefeels.class);


                intent.putExtra("feelname", Counts.get(pos).getName());
                intent.putExtra("feelsdate", Counts.get(pos).getDate());
                intent.putExtra("feelscomment", Counts.get(pos).getComment());
                intent.putExtra("position", pos);
                startActivityForResult(intent,1);

            }
        });

    }


    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        emotionlist= (ListView) findViewById(R.id.listview1);
        final ArrayAdapter adapter = new ArrayAdapter<feels>(this, android.R.layout.simple_list_item_1, Counts);
        emotionlist.setAdapter(adapter);

    }


        public void loadFromFile(){
        try{

            FileInputStream fis = openFileInput(FILENAME );
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<feels>>(){}.getType();
            Counts = gson.fromJson(in, listType);
        }catch (FileNotFoundException e){
            Counts = new ArrayList<feels>();
        }
    }

    private void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(Counts, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
