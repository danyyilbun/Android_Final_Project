package com.example.final_android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class ChoosePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);



    }













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
