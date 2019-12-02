package com.example.final_android_project.utils;

public class util {
    //database related items
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "chessplayer_db";
    public static final String TABLE_NAME = "chessplayer";
    public static final String TABLE_NAME_2 = "chessgames";
    public static final String  KEY_ID(String cl){ return cl +"_id";}
    public static final String[]  KEY_NAME= {
            "firstName",
            "lastName",
            "eloRating",
            "dateOfBirth",
            "dateOfDeath",
            "yearsChampion",
            "country",
            "image"};




}
