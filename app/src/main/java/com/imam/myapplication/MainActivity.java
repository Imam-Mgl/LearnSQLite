package com.imam.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;
    private ArrayList ids, names, phoneNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(this);
        ids = new ArrayList();
        names = new ArrayList();
        phoneNumbers = new ArrayList();
        contactToArray();

        RecyclerView recyclerView = findViewById(R.id.contactRV);
        recyclerView.setAdapter(new ContactsAdapter(this, ids, names, phoneNumbers));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void createActivity(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    private void contactToArray(){
        Cursor cursor = dataBaseHelper.readAllContacts();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Database is empaty", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                ids.add(cursor.getString(0));
                names.add(cursor.getString(1));
                phoneNumbers.add(cursor.getString(2));

            }
        }
    }
}