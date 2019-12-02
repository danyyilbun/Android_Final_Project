package com.example.final_android_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.final_android_project.model.Chessplayer;
import com.example.final_android_project.utils.DatabaseHandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
public TextView firstName;
public TextView lastName;
public TextView eloRating;
public TextView dateB;
public TextView dateD;
public ImageView image;
public TextView yearsChampion;
public TextView country;
public Button editButton;
public Button leaveButton;


int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        firstName = findViewById(R.id.main_activity_details_firstName);
        lastName = findViewById(R.id.main_activity_details_lastName);
        eloRating = findViewById(R.id.main_activity_details_eloRating);
        dateB = findViewById(R.id.main_activity_details_dateBirth);
        dateD = findViewById(R.id.main_activity_details_dateDeath);
        image = findViewById(R.id.main_activity_details_imageView);
        yearsChampion = findViewById(R.id.main_activity_details_yearsChampion);
        country = findViewById(R.id.main_activity_details_country);
        editButton = findViewById(R.id.editButton);
        leaveButton = findViewById(R.id.leaveButton);


        Bundle bundle = getIntent().getExtras();
        DatabaseHandler db = new DatabaseHandler(DetailsActivity.this);

        if(bundle != null) {
            id = Integer.parseInt(bundle.getString("id"));
        }
        Chessplayer chessplayer = db.getChessplayer(id);
        firstName.setText(chessplayer.getFirstName());
        lastName.setText(chessplayer.getLastName());
        eloRating.setText(chessplayer.getEloRating());
        dateB.setText(chessplayer.getDateOfBirth());
        dateD.setText(chessplayer.getDateOfDeath());
        Bitmap bitmap = BitmapFactory.decodeByteArray(chessplayer.getImage(), 0, chessplayer.getImage().length);
        image.setImageBitmap(bitmap);
        yearsChampion.setText(chessplayer.getYearsChampion());
        country.setText(chessplayer.getCountry());



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("message back","From the second Activity");
                //Send something back if it's OK to do so.
                setResult(RESULT_OK, intent);
                finish();
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
