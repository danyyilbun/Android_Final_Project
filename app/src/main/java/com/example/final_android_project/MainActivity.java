package com.example.final_android_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_android_project.adapter.RecyclerViewAdapter;
import com.example.final_android_project.model.Chessplayer;
import com.example.final_android_project.utils.DatabaseHandler;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private List<Chessplayer> contactArrayList;
   private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView  = findViewById(R.id.recyclerView);
        if(getIntent().getExtras() != null){
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("list") != null)
        {
            Gson gson = new Gson();
            contactArrayList = Arrays.asList(gson.fromJson(bundle.getString("list"), Chessplayer[].class));
        }}
        else {
            DatabaseHandler db = new DatabaseHandler(MainActivity.this);
            contactArrayList = db.getAllChessplayer();
        }
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {

            case R.id.menuitem_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}

    /**
     *
     * @param menuItem
     */
       public void  EnterPlayerClicked(MenuItem menuItem)
       {
        Intent intent = new Intent(this, EnteringDataActivity.class);
        startActivity(intent);
       }
       /**
     *
     * @param menuItem
     */
    public void  ChooseConditionClicked(MenuItem menuItem)
    {
        Intent intent = new Intent(this, ChoosePlayerActivity.class);
        intent.putExtra("letChoose","Show All");
        startActivity(intent);
    }

    /**
     *
     * @param menuItem
     */
    public void  ShowAllClicked(MenuItem menuItem)
    {
        Intent intent = new Intent(this, ChoosePlayerActivity.class);
        intent.putExtra("message","Show All");
        startActivity(intent);

    }
}
