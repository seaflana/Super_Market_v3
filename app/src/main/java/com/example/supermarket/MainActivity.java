package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ScrollView;
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
        initSaveButton();
        initTextChangedEvents();

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openRateSuperMarket();
            }
        });
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
                hideKeyboard();
                boolean wasSuccessful;
                ContactDataSource ds = new ContactDataSource(MainActivity.this);
                try {
                    ds.open();

                    if (currentMarket.getMarketID() == -1) {
                        wasSuccessful = ds.insertContact(currentMarket);

                        if (wasSuccessful) {
                            int newId = ds.getLastContactID();
                            currentMarket.setMarketID(newId);
                        }
                    }
                    else {
                        wasSuccessful = ds.updateContact(currentMarket);
                    }
                    ds.close();
                }
                catch (Exception e) {
                    wasSuccessful = false;
                }

               if (wasSuccessful) {
                    setForEditing(false);
                }
            }
        });
    }

    //Partial hideKeyboard() Method
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editMarketName);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        EditText editAddress = findViewById(R.id.editMarketAddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
        EditText editCity = findViewById(R.id.editMarketCity);
        imm.hideSoftInputFromWindow(editCity.getWindowToken(), 0);
        EditText editState = findViewById(R.id.editMarketState);
        imm.hideSoftInputFromWindow(editState.getWindowToken(), 0);
        EditText editZipcode = findViewById(R.id.editMarketZipcode);
        imm.hideSoftInputFromWindow(editZipcode.getWindowToken(), 0);
    }

    //Code to enable the Data Entry Form
    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.editMarketName);
        EditText editAddress = findViewById(R.id.editMarketAddress);
        EditText editCity = findViewById(R.id.editMarketCity);
        EditText editState = findViewById(R.id.editMarketState);
        EditText editZipCode = findViewById(R.id.editMarketZipcode);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipCode.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
        else{
            ScrollView s = findViewById(R.id.scrollView);
            s.fullScroll(ScrollView.FOCUS_UP);
        }
    }
}