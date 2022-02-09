package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListButton();
        initMapButton();
        initSettingsButton();
        initToggleButton();
        setForEditing(false);
        initTextChangedEvents();
        initSaveButton();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            initContact(extras.getInt("contactId"));
        }
        else {
            currentContact = new Contact();
        }

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openRateSuperMarket();
            }
        });
    }

    private void initListButton() {
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initMapButton() {
        ImageButton ibList = findViewById(R.id.imageButtonMap);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactMapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initSettingsButton() {
        ImageButton ibList = findViewById(R.id.imageButtonSettings);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactSettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initToggleButton() {
        final ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
        editToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setForEditing(editToggle.isChecked());
            }
        });
    }

    //Code to enable the Data Entry Form which passes true if the toggle button is enabled
    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.editMarketName);
        EditText editAddress = findViewById(R.id.editMarketAddress);
        EditText editCity = findViewById(R.id.editMarketCity);
        EditText editState = findViewById(R.id.editMarketState);
        EditText editZipCode = findViewById(R.id.editMarketZipcode);
        Button buttonSave = findViewById(R.id.button2);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipCode.setEnabled(enabled);
        buttonSave.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
        else{
            ScrollView s = findViewById(R.id.scrollView);
            s.fullScroll(ScrollView.FOCUS_UP);
        }
    }

    //Intent method for initializing RateSuperMarket button
    public void openRateSuperMarket() {
        Intent intent = new Intent(this, RateSuperMarket.class);
        startActivity(intent);
    }

    //TextChanged Event method
    private void initTextChangedEvents() {
        final EditText etContactName = findViewById(R.id.editMarketName);
        etContactName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setContactName(etContactName.getText().toString());
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
                currentContact.setStreetAddress(etStreetAddress.getText().toString());
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
                currentContact.setCity(etCity.getText().toString());
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
                currentContact.setState(etState.getText().toString());
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
                currentContact.setZipCode(etZipcode.getText().toString());
            }
        });
    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.button2);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                hideKeyboard();
                ContactDataSource ds = new ContactDataSource(MainActivity.this);
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

    //Initialize Contact information
    private void initContact(int id) {

        ContactDataSource ds = new ContactDataSource(MainActivity.this);
        try {
            ds.open();
            currentContact = ds.getSpecificContact(id);
            ds.close();
        }
        catch (Exception e) {
            Toast.makeText(this, "Load Contact Failed", Toast.LENGTH_LONG).show();
        }

        EditText editName = findViewById(R.id.editMarketName);
        EditText editAddress = findViewById(R.id.editMarketAddress);
        EditText editCity = findViewById(R.id.editMarketCity);
        EditText editState = findViewById(R.id.editMarketState);
        EditText editZipCode = findViewById(R.id.editMarketZipcode);


        editName.setText(currentContact.getContactName());
        editAddress.setText(currentContact.getStreetAddress());
        editCity.setText(currentContact.getCity());
        editState.setText(currentContact.getState());
        editZipCode.setText(currentContact.getZipCode());

    }
}