package com.example.final_android_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_android_project.model.Chessplayer;
import com.example.final_android_project.utils.DatabaseHandler;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class EnteringDataActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 999;
    private Button add_picture_button;
    private Button add;
    private ImageView imageView;
    private EditText firstName;
    private EditText lastName;
    private EditText eloRating;
    private TextView dateBirth;
    private TextView dateDeath;
    private EditText yearsChampion;
    private EditText country;
    private DatePickerDialog.OnDateSetListener mDateSetListenerBirth;
    private DatePickerDialog.OnDateSetListener mDateSetListenerDeath;






    DatabaseHandler db= new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entering_data);
        initialise();

        CreateListeners();



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);

            }
            else{
                Toast.makeText(getApplicationContext(),"You don't have permissions to access file location!",Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }






    public void initialise()
    {
        add = findViewById(R.id.entering_activity_add);
        imageView = findViewById(R.id.entering_activity_imageView);
        add_picture_button = findViewById(R.id.entering_activity_add_picture_button);
        firstName = findViewById(R.id.entering_activity_firstName);
        lastName =  findViewById(R.id.entering_activity_lastName);
        eloRating = findViewById(R.id.entering_activity_eloRating);
        dateBirth  =  findViewById(R.id.entering_activity_date_birth);
        dateDeath =  findViewById(R.id.entering_activity_date_death);
        yearsChampion = findViewById(R.id.entering_activity_yearsChampion);
        country = findViewById(R.id.entering_activity_country);
    }
public void CreateListeners()
{

    dateBirth.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new
                    DatePickerDialog(EnteringDataActivity.this,
                    android.R.style.Widget_Holo_ActionBar,
                    mDateSetListenerBirth,
                    year,month,day);
           dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    });
    mDateSetListenerBirth = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            month = month +1;
            dateBirth.setText(year + "/" + month + "/" + day);
        }
    };

    dateDeath.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new
                    DatePickerDialog(EnteringDataActivity.this,
                    android.R.style.Widget_Holo_ActionBar,
                    mDateSetListenerDeath,
                    year,month,day);
           dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    });

    mDateSetListenerDeath = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            month = month +1;
            dateBirth.setText(year + "/" + month + "/" + day);
        }
    };

    add_picture_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityCompat.requestPermissions(EnteringDataActivity.this ,new String[]
                    {Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
        }
    });
    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityCompat.requestPermissions(EnteringDataActivity.this ,new String[]
                    {Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
        }
    });

    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // imageView  firstName lastName eloRating dateBirth dateDeath yearsChampion country

            String fName = firstName.getText().toString();
            String lName = lastName.getText().toString();
            int elo = Integer.parseInt(eloRating.getText().toString());
            String dBirth = dateBirth.getText().toString();
            String dDeath = dateDeath.getText().toString();
            String yearChamp = yearsChampion.getText().toString();
            String count = country.getText().toString();
            byte[] img = imageViewToByte(imageView);
            addPlayer(new Chessplayer(fName,lName,elo,dBirth,dDeath,yearChamp,count,img));
        }
    });

}
    public  void addPlayer(Chessplayer pl){

        db.addChessplayer(pl);
    }
    private byte[] imageViewToByte(ImageView image)
    {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream  = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte [] byteArray = stream.toByteArray();
        return byteArray;

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
