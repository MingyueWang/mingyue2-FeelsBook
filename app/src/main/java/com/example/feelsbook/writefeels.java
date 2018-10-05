package com.example.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import static android.provider.Telephony.Mms.Part.FILENAME;
import static com.example.feelsbook.feels.Counts;

public class writefeels extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private int position;
    private EditText emotion;
    private  TextView emotion_list;
    private EditText date_ed;
    private TextView commentlist;
    private EditText comment;
    private Button save_button;
    private Button delete;
    public static int joy;
    public static int love;
    public static int fear;
    public static int anger;
    public static int surprise;
    public static int sadness;
    private Button joy_new;
    private Button love_new;
    private Button fear_new;
    private Button anger_new;
    private Button sadness_new;
    private Button surprise_new;
    String ed_feeling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        emotion_list = (TextView) findViewById(R.id.textView);
        //emotion_list.setText(bundle.getString("feelsName"));
    
        //date_ed=(EditText) findViewById(R.id.editText4);
        commentlist = (TextView) findViewById(R.id.textView3);
        //commentlist.setText(bundle.getString("feelscoment"));
        comment = (EditText) findViewById(R.id.editText5);
        save_button = (Button) findViewById(R.id.button);
        delete = (Button) findViewById(R.id.button3);
        joy_new = (Button) findViewById(R.id.button7);
        love_new = (Button) findViewById(R.id.button10);
        fear_new = (Button) findViewById(R.id.button8);
        anger_new = (Button) findViewById(R.id.button6);
        sadness_new = (Button) findViewById(R.id.button9);
        surprise_new = (Button) findViewById(R.id.button5);
        //position = (bundle.getInt("position"));
        //Intent intent = getIntent();
        joy_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_feeling="joy";
                joy ++;
                //Countedmotion.get(0).injoy();
                }

                                   });
        love_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed_feeling="love";
                love++;
                //Countedmotion.get(1).inlove();

                }

        });
        fear_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed_feeling="fear";
                //
                fear++;
                //Countedmotion.get(2).infear();

                }


        });
        anger_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed_feeling="anger";
                anger++;
                //Countedmotion.get(3).inanger();

               }

        });
        sadness_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_feeling="sadness";
                //Countedmotion.get(5).insadness();
                sadness++;
                //saveInFile2();
                }

        });
        surprise_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_feeling="surprise";
                //Countedmotion.get(4).insurprise();
                surprise++;
                //saveInFile2();
                }

        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String ed_feeling = emotion.getText().toString();

               
                    String ed_comment = comment.getText().toString();
                    feels counter = new feels(ed_feeling,ed_comment);
                    Counts.add(counter);
                    saveInFile();
                    Toast.makeText(writefeels.this, "Saved", Toast.LENGTH_LONG).show();
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                
                }



                //Counts.get(position).setInitValue(eddate);
        });


    }



    private void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(MainActivity.FILENAME,
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
