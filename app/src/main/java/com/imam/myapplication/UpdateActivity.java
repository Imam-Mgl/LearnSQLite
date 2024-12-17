package com.imam.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneNumberEditText;

    String id, name, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameEditText = findViewById(R.id.fullNameETUpdate);
        phoneNumberEditText = findViewById(R.id.phoneNumberETUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        phoneNumber = intent.getStringExtra("PHONE_NUMBER");

        nameEditText.setText(name);
        phoneNumberEditText.setText(phoneNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
            dataBaseHelper.deleteContact(id);
            Toast.makeText(this, "Contact Deleted", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateContact(View view) {
        String newName = nameEditText.getText().toString();
        String newPhoneNumber = phoneNumberEditText.getText().toString();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.updateContact(id, newName, newPhoneNumber);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}