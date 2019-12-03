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

import com.example.final_android_project.model.Chessplayer;
import com.example.final_android_project.utils.DatabaseHandler;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class ChoosePlayerActivity extends AppCompatActivity {
    public EditText year;
    public EditText rating;
    public EditText show_name;
    public Button showAll;
    public Button accept;
    DatabaseHandler db = new DatabaseHandler(ChoosePlayerActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);

    year = findViewById(R.id.activity_choose_player_demonstrate_for_year);
    rating = findViewById(R.id.activity_choose_player_demonstrate_demonstrate_for_rating);
    show_name = findViewById(R.id.activity_choose_player_demonstrate_show_by_name);
    showAll = findViewById(R.id.activity_choose_player_demonstrate_show_all);
    accept = findViewById(R.id.activity_choose_player_submit);

	 Bundle bundle = getIntent().getExtras();
	 if(bundle != null)
	 {
		 if(bundle.getString("message") != null)
		 {leave();}
	 }
    showAll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        leave();
        }
    });


    accept.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String condition = "";
        if(year.getText() == null && rating.getText() == null  && show_name == null)
            {
            condition = " TRUE";

            }
            if(year.getText() != null && rating.getText() != null  && show_name == null)
            {
            condition = " dateOfBirth = " + year.getText().toString() + " OR dateOfDeath =" +
                    year.getText().toString() + " and eloRating =" + rating.getText().toString();

            }
            if(year.getText() == null && rating.getText() == null  && show_name != null)
            {
				condition = " firstName =%" + show_name.getText().toString() + "% "
						+"or lastName =%" + show_name.getText().toString() + "% ";

            }
            if(year.getText() != null && rating.getText() == null  && show_name != null)
            {

                condition = " dateOfBirth = " + year.getText().toString() + " OR dateOfDeath =" +
                        year.getText().toString() + " and firstName =%" + show_name.getText().toString() + "% "
						+"or lastName =%" + show_name.getText().toString() + "% ";
            }

            if(year.getText() != null && rating.getText() == null  && show_name == null)
            {

			condition = " dateOfBirth = " + year.getText().toString() + " OR dateOfDeath =" +
                        year.getText().toString() ;
            }
            if(year.getText() != null && rating.getText() != null  && show_name != null)
            {
			condition = " dateOfBirth = " + year.getText().toString() + " OR dateOfDeath =" +
                        year.getText().toString() 
						+" and eloRating =" + rating.getText().toString() +
						" and firstName =%" + show_name.getText().toString() + "% "
						+"or lastName =%" + show_name.getText().toString() + "% ";

            }
        sendData(condition);
        }
    });

    }
    public void sendData(String condition)
    {
        Intent intent = new Intent(this, MainActivity.class);
        Gson gson = new Gson();
        Chessplayer [] chessplayers = (Chessplayer[]) db.getChessplayerByCondition(condition).toArray();
        String json = gson.toJson(chessplayers, Chessplayer.class);
        intent.putExtra("list",json);
        startActivityForResult(intent,1);
    }
    public void leave()
    {Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("letChoose","Show All");
        startActivity(intent);
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
