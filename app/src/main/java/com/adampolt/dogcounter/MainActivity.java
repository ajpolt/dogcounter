package com.adampolt.dogcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int dogCount = 0;
    TextView counter;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.addDog);
        Button resetButton = findViewById(R.id.reset);
        counter = findViewById(R.id.count);

        prefs = getSharedPreferences("dogs", MODE_PRIVATE);

        dogCount = prefs.getInt("count", 0);
        counter.setText(String.valueOf(dogCount));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDogCount(dogCount + 1);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDogCount(0);
            }
        });
    }

    private void setDogCount(int count) {
        dogCount = count;
        counter.setText(String.valueOf(count));

        prefs.edit().putInt("count", count).apply();

        if(count % 10 == 0) {
            Intent congratsIntent = new Intent(this, CongratsActivity.class);
            startActivity(congratsIntent);
        }
    }
}
