package com.example.final_android_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChoosePlayerActivity extends AppCompatActivity {
    public EditText year;
    public EditText rating;
    public EditText show_name;
    public Button showAll;
    public Button accept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);

    year = findViewById(R.id.activity_choose_player_demonstrate_for_year);
    rating = findViewById(R.id.activity_choose_player_demonstrate_demonstrate_for_rating);
    show_name = findViewById(R.id.activity_choose_player_demonstrate_show_by_name);
    showAll = findViewById(R.id.activity_choose_player_demonstrate_show_all);
    accept = findViewById(R.id.activity_choose_player_submit);


    showAll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });


    accept.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });







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
