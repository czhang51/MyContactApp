package com.example.zhangy2322.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editName;
    EditText editEmail;
    EditText editPhone;

    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

    }

    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());

        if (isInserted == true) {
            Log.d("MyContact", "Data insertion successful");

            //Create toast message to user indicating data inserted correctly
            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("MyContact", "Data insertion unsuccessful");

            //Create toast message to user indicating data inserted incorrectly
            Toast.makeText(getApplicationContext(), "Data insertion failed :(", Toast.LENGTH_SHORT).show();

        }
    }



}
