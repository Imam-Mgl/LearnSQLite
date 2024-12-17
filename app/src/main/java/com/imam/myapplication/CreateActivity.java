package com.imam.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateActivity extends AppCompatActivity {
    private EditText fullNameEditText;
    private EditText phoneNumberEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        fullNameEditText = findViewById(R.id.fullNameET);
        phoneNumberEditText = findViewById(R.id.phoneNumberET);


    }

    public void saveContact(View view) {
        String fullName = fullNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.createContacts(fullName, phoneNumber);

    }
}