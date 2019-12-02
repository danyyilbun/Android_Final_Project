package com.example.final_android_project.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.final_android_project.model.Chessplayer;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

        public DatabaseHandler(Context context) {
            super(context, util.DATABASE_NAME, null, util.DATABASE_VERSION);

        }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String CREATE_CONTACT_TABLE = " CREATE TABLE " + util.TABLE_NAME + " ( "
                    + util.KEY_ID("chessplayer") + " INTEGER PRIMARY KEY , " +
                    util.KEY_NAME[0] + " TEXT , " //firstname
                    +
                    util.KEY_NAME[1] + " TEXT , " //lastname
                    +
                    util.KEY_NAME[2] + "  INTEGER , " //eloRating
                    +
                    util.KEY_NAME[3] + " TEXT , " // dateOfBirth
                    +
                    util.KEY_NAME[4] + " TEXT , " // dateOfDeath
                    +
                    util.KEY_NAME[5] + " TEXT , " // yearsChampion
                    +
                     util.KEY_NAME[6] + " TEXT"// country
                         +
                    util.KEY_NAME[7] + " BLOB " //image
                    +");";
        Log.d("MySQL", "onCreate: " + CREATE_CONTACT_TABLE);
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
    }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS " + util.TABLE_NAME);
            db.execSQL(DROP_TABLE, new String[]{util.TABLE_NAME});

            onCreate(db);

        }
    public void addChessplayer(Chessplayer chessplayer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(util.KEY_NAME[0], chessplayer.getFirstName() );


        db.insert(util.TABLE_NAME, null, values);
        db.close();
    }
    public Chessplayer getChessplayer(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();


        Cursor cursor = db.query(util.TABLE_NAME, new String[]{util.KEY_ID("chessplayer"),
                        util.KEY_NAME[0],util.KEY_NAME[1],util.KEY_NAME[2],
                        util.KEY_NAME[3],util.KEY_NAME[4],util.KEY_NAME[5],
                        util.KEY_NAME[6], util.KEY_NAME[7]},
                util.KEY_ID("chessplayer") + "=?",
                new String[]{String.valueOf(id)},
                null,null,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
           Chessplayer chessplayer = new Chessplayer(
                   Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                  cursor.getString(2),
            Integer.parseInt(cursor.getString(3)),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                   cursor.getBlob(8));
           db.close();
            return chessplayer;
        }
        db.close();
        return null;
    }
    public List<Chessplayer> getAllChessplayer()
    {
        List <Chessplayer> chessplayerList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll,null);

        if(cursor.moveToFirst())
        {
            do{

                Chessplayer chessplayer = new Chessplayer(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getBlob(8)
                );
               chessplayerList.add(chessplayer);
            }
            while(cursor.moveToNext());

        }
            db.close();
        return chessplayerList;
    }


    public void deleteChessplayer(Chessplayer chessplayer)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(util.TABLE_NAME, util.KEY_ID("chessplayer") +
                        "=?",
                new String[]{String.valueOf(chessplayer.getId())} );
        db.close();
    }
    public int updateChessplayer(Chessplayer chessplayer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(util.KEY_NAME[0], chessplayer.getFirstName() );
        values.put(util.KEY_NAME[1], chessplayer.getLastName() );
        values.put(util.KEY_NAME[2], chessplayer.getEloRating() );
        values.put(util.KEY_NAME[3], chessplayer.getDateOfBirth() );
        values.put(util.KEY_NAME[4], chessplayer.getDateOfDeath() );
        values.put(util.KEY_NAME[5], chessplayer.getYearsChampion() );
        values.put(util.KEY_NAME[6], chessplayer.getCountry() );
        values.put(util.KEY_NAME[7], chessplayer.getImage() );
        db.close();
        return db.update(util.TABLE_NAME,  values,  util.KEY_ID("chessplayer") +
                        "=?",
                new String[]{String.valueOf(chessplayer.getId())} );

    }
    public int getChessplayerCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();

      String countQuery = "SELECT * FROM " + util.TABLE_NAME;
       Cursor cursor = db.rawQuery( countQuery , null);
       int cnt =  cursor.getCount();
       db.close();
       return cnt;

    }
}
