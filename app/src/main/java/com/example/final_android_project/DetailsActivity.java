package com.example.final_android_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.final_android_project.model.Chessplayer;
import com.example.final_android_project.utils.DatabaseHandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class DetailsActivity extends AppCompatActivity {
public EditText firstName;
public EditText lastName;
public EditText eloRating;
public TextView dateB;
public TextView dateD;
public ImageView image;
public EditText yearsChampion;
public EditText country;
public Button editButton;
public Button leaveButton;
    DatabaseHandler db;

int id  = 0;
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
        db = new DatabaseHandler(DetailsActivity.this);

        if(bundle != null) {
            id = Integer.parseInt(bundle.getString("id"));
        }
        final Chessplayer  chessplayer = db.getChessplayer(id);
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
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                int elo = Integer.parseInt(eloRating.getText().toString());
                String dBirth = dateB.getText().toString();
                String dDeath = dateD.getText().toString();
                String yearChamp = yearsChampion.getText().toString();
                String count = country.getText().toString();
                byte[] img = imageViewToByte(image);
                updatePlayer(new Chessplayer(chessplayer.getId(),fName,lName,elo,dBirth,dDeath,yearChamp,count,img));
            }
        });

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leave();
            }
        });

    }
    public void leave()
    {Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("letChoose","Show All");
        startActivity(intent);
    }


    public int updatePlayer(Chessplayer chessplayer)
    {
        return db.updateChessplayer(chessplayer);
    }

    private byte[] imageViewToByte(ImageView image)
    {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream  = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte [] byteArray = stream.toByteArray();
        return byteArray;

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
