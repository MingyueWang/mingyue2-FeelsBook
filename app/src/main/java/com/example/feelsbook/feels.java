package com.example.feelsbook;

import java.util.ArrayList;
import java.util.Date;


import static com.example.feelsbook.writefeels.joy;
import static com.example.feelsbook.writefeels.love;
import static com.example.feelsbook.writefeels.fear;
import static com.example.feelsbook.writefeels.sadness;
import static com.example.feelsbook.writefeels.anger;
import static com.example.feelsbook.writefeels.surprise;
public class feels {
    public static ArrayList<feels> Counts = new ArrayList<feels>();

    private String feeling;                    //name of emotion
    private Date date;                      //date when modification applied

    private String comment;                 //user input comments. It is ok to be empty

    /*Construction and getter and setter*/
    public feels(String name,String comment){
        this.feeling = name;
        this.date = new Date();
        this.comment = comment;


    }



    public String getName() {
        return feeling;
    }

    public void setName(String name) {
        this.feeling = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    //Used to display information on the main activity page.
    @Override
    public String toString(){
        return "Emontion: " + feeling + "\nTime:" + date.toString() + "\nComment: " + comment;
    }
}

