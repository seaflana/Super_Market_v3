package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private SuperMarket currentMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentMarket = new SuperMarket();


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openRateSuperMarket();
            }
        });

        initTextChangedEvents();
        initSaveButton();
    }

    //Intent method for initializing RateSuperMarket button
    public void openRateSuperMarket() {
        Intent intent = new Intent(this, RateSuperMarket.class);
        startActivity(intent);
    }

    //TextChanged Event method
    private void initTextChangedEvents() {
        final EditText etMarketName = findViewById(R.id.editMarketName);
        etMarketName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMarket.setMarketName(etMarketName.getText().toString());
            }
        });

        final EditText etStreetAddress = findViewById(R.id.editMarketAddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMarket.setStreetAddress(etStreetAddress.getText().toString());
            }
        });

        final EditText etCity = findViewById(R.id.editMarketCity);
        etCity.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMarket.setCity(etCity.getText().toString());
            }
        });

        final EditText etState = findViewById(R.id.editMarketState);
        etState.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMarket.setState(etState.getText().toString());
            }
        });

        final EditText etZipcode = findViewById(R.id.editMarketZipcode);
        etZipcode.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentMarket.setZipCode(etZipcode.getText().toString());
            }
        });
    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.button2);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                ContactDataSource ds = new ContactDataSource(MainActivity.this);
                try {
                    ds.open();

                    if (currentMarket.getMarketID() == -1) {
                        wasSuccessful = ds.insertContact(currentMarket);
                    }
                    else {
                        wasSuccessful = ds.updateContact(currentMarket);
                    }
                    ds.close();
                }
                catch (Exception e) {
                    wasSuccessful = false;
                }

//                if (wasSuccessful) {
//                    ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
//                    editToggle.toggle();
//                    setForEditing(false);
//                }
            }
        });
    }
}