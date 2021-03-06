package com.example.zhangy2322.mycontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_RESULT = "com.example.myContactApp.RESULT";

    DatabaseHelper myDb;

    EditText editName;
    EditText editEmail;
    EditText editPhone;
    EditText searchName;

    Button btnAddData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText)(findViewById(R.id.editText_name));
        editPhone = (EditText)(findViewById(R.id.editText_phone));
        editEmail = (EditText)(findViewById(R.id.editText_email));
        searchName = (EditText)(findViewById(R.id.editText_searchName));

    }

    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());

        if (isInserted == true) {
            Log.d("MyContact", "Data insertion successful");

            //Create toast message to user indicating data inserted correctly
            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();

            //Clear fields
            editName.setText("");
            editPhone.setText("");
            editEmail.setText("");
        }
        else {
            Log.d("MyContact", "Data insertion unsuccessful");

            //Create toast message to user indicating data inserted incorrectly
            Toast.makeText(getApplicationContext(), "Data insertion failed :(", Toast.LENGTH_SHORT).show();

        }
    }

    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error ", "No data found in database");
            //put a Log.d message and toast
            Toast.makeText(getApplicationContext(), "No data found in database :(", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();

        //setup while loop with Cursor moveToNext method
        //append method each COL to buffer
        //use getString method
        res.moveToFirst();
        while (res.moveToNext()) {
            for (int col = 1; col <= 3; col++) {
                buffer.append(res.getString(col));
                buffer.append("\n");
            }

            buffer.append("\n");

        }

        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void searchByName(View v) {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error ", "No data found in database");
            //put a Log.d message and toast
            Toast.makeText(getApplicationContext(), "No data found in database :(", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();

        //setup while loop with Cursor moveToNext method
        //append method each COL to buffer
        //use getString method
        res.moveToFirst();
        while (res.moveToNext()) {

            if ((res.getString(1)).toLowerCase().equals(searchName.getText().toString().toLowerCase())) {


                buffer.append(res.getString(1) + "\n" + res.getString(2) + "\n" + res.getString(3));
            }
        }

        String searchResult = "Search result: \n\n" + buffer.toString();

        Intent intent = new Intent(this, SearchByNameActivity.class);

        intent.putExtra(EXTRA_RESULT, searchResult);
        startActivity(intent);



    }

}
