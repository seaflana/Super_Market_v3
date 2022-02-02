package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class RateSuperMarket extends AppCompatActivity {

    Button saveButton;
    Button mainButton;
    RatingBar liquorRating;
    RatingBar produceRating;
    RatingBar meatRating;
    RatingBar cheeseRating;
    RatingBar easeRating;
    TextView rateAverage;
    private SuperMarket currentRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratemarket);

        mainButton = (Button) findViewById(R.id.button3);
        mainButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        //RatingBars and Button set-up for RateSuperMarket class
        liquorRating = findViewById(R.id.liquorRating);
        produceRating = findViewById(R.id.produceRating);
        meatRating = findViewById(R.id.meatRating);
        cheeseRating = findViewById(R.id.cheeseRating);
        easeRating = findViewById(R.id.easeRating);
        saveButton = findViewById(R.id.button4);
        mainButton = findViewById(R.id.button3);
        rateAverage = findViewById(R.id.textView9);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float avg = liquorRating.getRating() + produceRating.getRating() +
                        meatRating.getRating() + cheeseRating.getRating() +
                        easeRating.getRating();
                avg = avg/5;
                rateAverage.setText("" + avg);
            }
        });
    }

    //Intent method for initializing RateSuperMarket button
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}