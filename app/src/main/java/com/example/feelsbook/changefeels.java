package com.example.feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import static com.example.feelsbook.MainActivity.FILENAME;

import static com.example.feelsbook.feels.Counts;

public class changefeels extends AppCompatActivity {
    private EditText emotioned;
    private TextView emotionlist;
    private TextView date_list;
    private EditText date_ed;
    private TextView comment_list;
    private EditText comment_ed;
    private Button savebutton;
    private Button delete;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Bundle bundle = getIntent().getExtras();
        emotioned = (EditText) findViewById(R.id.editText3);
        //Intent intent = getIntent();


        // Capture the layout's TextView and set the string as its text
        //emotioned.setText(bundle.getString("feelsname"));

        //date_ed=(EditText) findViewById(R.id.editText8);
        comment_ed = (EditText) findViewById(R.id.editText5);
        savebutton = (Button) findViewById(R.id.button);
        delete = (Button) findViewById(R.id.button3);
        position = (bundle.getInt("position"));
        Intent intent = getIntent();


        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edcomment = comment_ed.getText().toString();
                String edfeeling = emotioned.getText().toString();
                //String eddate = date_ed.getText().toString();

                Counts.get(position).setComment(edcomment);
                Counts.get(position).setName(edfeeling);
                //Counts.get(position).setDate(eddate);

                saveInFile();
                Toast.makeText(changefeels.this, "Saved", Toast.LENGTH_LONG).show();
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edfeeling = emotioned.getText().toString();
                if (edfeeling.matches("joy") ) {
                   writefeels.joy--;

                }if (edfeeling.matches("love") ) {
                    writefeels.love--;

                }if (edfeeling.matches("sadness") ) {
                    writefeels.sadness--;

                    finish();
                }if (edfeeling.matches("fear") ) {
                    writefeels.fear--;
                    Counts.remove(position);

                }if (edfeeling.matches("anger") ) {
                    writefeels. anger--;
                    Counts.remove(position);

                }if (edfeeling.matches("surprise") ) {
                    writefeels.surprise--;


                }Counts.remove(position);
                saveInFile();
                Toast.makeText(changefeels.this, "Deleted", Toast.LENGTH_LONG).show();
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
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
