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
    Contact currentContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratemarket);
        currentContact = new Contact();
        initRatingChangedListener();
        initSaveRatingButton();



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
    }


    private void initRatingChangedListener() {
        final RatingBar lr = findViewById(R.id.liquorRating);
        lr.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                final int lr = ratingBar.getNumStars();
                currentContact.setLiquorRating(lr);
            }
        });

        final RatingBar pr = findViewById(R.id.produceRating);
        pr.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                final int pr = ratingBar.getNumStars();
                currentContact.setProductRating(pr);
            }
        });

        final RatingBar mr = findViewById(R.id.meatRating);
        mr.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                final int mr = ratingBar.getNumStars();
                currentContact.setMeatRating(mr);
            }
        });

        final RatingBar cr = findViewById(R.id.cheeseRating);
        cr.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                final int cr = ratingBar.getNumStars();
                currentContact.setCheeseRating(cr);
            }
        });

        final RatingBar er = findViewById(R.id.easeRating);
        er.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                final int er = ratingBar.getNumStars();
                currentContact.setEaseRating(er);
            }
        });
    }


    private void initSaveRatingButton() {
        Button saveButton = findViewById(R.id.button4);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get average rating
                float avg = liquorRating.getRating() + produceRating.getRating() +
                        meatRating.getRating() + cheeseRating.getRating() +
                        easeRating.getRating();
                avg = avg / 5;
                rateAverage.setText("" + avg);

                //Insert rating into Contact Object
                boolean wasSuccessful;
                ContactDataSource ds = new ContactDataSource(RateSuperMarket.this);
                try {
                    ds.open();

                    if (currentContact.getContactID() == -1) {
                        wasSuccessful = ds.insertContact(currentContact);

                        if (wasSuccessful) {
                            int newId = ds.getLastContactID();
                            currentContact.setContactID(newId);
                        }
                    }
                    else {
                        wasSuccessful = ds.updateContact(currentContact);
                    }
                    ds.close();
                }
                catch (Exception e) {
                    wasSuccessful = false;
                }

                if (wasSuccessful) {
                    System.out.println("Successful");
                }
            }
        });
    }

    //Intent method for returning to MainActivity
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
//    //Code to enable Data Entry for Rating Form
//    private void setForEditing(boolean enabled) {
//
//    }
//}