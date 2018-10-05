package com.example.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

import static com.example.feelsbook.MainActivity.FILENAME;
import static com.example.feelsbook.feels.Counts;

//import static com.example.feelsbook.feels.Counts;


public class countfeels extends AppCompatActivity {
    //ArrayList<feels>Counts;
    private TextView count_joy;
    private TextView count_love;
    private TextView count_fear;
    private TextView count_sadness;
    private TextView count_anger;
    private TextView count_surprise;
    //loadFromeFile2()
    int joy_=0;
    int love_=0;
    int anger_=0;
    int sadness_=0;
    int fear_=0;
    int surprise_=0;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        count_joy = (TextView) findViewById(R.id.textView4);

        count_love = (TextView) findViewById(R.id.textView5);
        count_fear = (TextView) findViewById(R.id.textView6);
        count_sadness = (TextView) findViewById(R.id.textView7);
        count_anger = (TextView) findViewById(R.id.textView8);
        count_surprise = (TextView) findViewById(R.id.textView9);

        //count each emotions
        for (i=0 ; i<Counts.size();i++) {
            String countsname = Counts.get(i).getName();
            if (countsname.matches("joy")) {
                joy_++;
            } else if (countsname.matches("love")) {
                love_++;
            } else if (countsname.matches("anger")) {
                anger_++;
            } else if (countsname.matches("fear")) {
                fear_++;
            } else if (countsname.matches("sadness")) {
                sadness_++;
            } else if (countsname.matches("surprise")) {
                surprise_++;
            }

        }

        count_joy.setText("joy:" + joy_);
        count_love.setText("love:" + love_);
        count_fear.setText("fear:" + fear_);
        count_sadness.setText("sadness" + sadness_);
        count_anger.setText("anger" + anger_);
        count_surprise.setText("surprise" + surprise_);
    }

}

