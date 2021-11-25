package com.example.heavenlyminded;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button button;

    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> event_id, event_type;
    CustomAdapter customAdapter;

    //NavBar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hooks
        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.addEventButton);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        // Navigation Bar
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
        event_id = new ArrayList<>();
        event_type = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, event_id, event_type);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public void storeDataInArrays(){
        Cursor cursor = myDatabaseHelper.readData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                event_id.add(cursor.getString(0));
                event_type.add(cursor.getString(1));
            }
        }
    }
}