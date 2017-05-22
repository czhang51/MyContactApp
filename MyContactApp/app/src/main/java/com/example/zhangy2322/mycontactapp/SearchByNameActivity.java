package com.example.zhangy2322.mycontactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SearchByNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        //Get intent of activity that started this activity and extract string
        Intent intent = getIntent();
        String searchResult = intent.getStringExtra(MainActivity.EXTRA_RESULT);

        //Capture the layout's TextView and set the string as its text
        TextView textView = (TextView)(findViewById(R.id.textView_searchResult));
        textView.setText(searchResult);
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
