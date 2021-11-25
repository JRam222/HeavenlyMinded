package com.example.heavenlyminded;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddEventActivity extends AppCompatActivity {

    EditText editTextOtherEvent;
    Button addEventButton;
    Spinner spinner;
    private static final String[] event_actions = {"Shared Gospel", "Prayed", "Read Bible", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        editTextOtherEvent = findViewById(R.id.type_input);
        editTextOtherEvent.setVisibility(View.INVISIBLE);
        spinner = findViewById(R.id.dropdown_menu);
        addEventButton = findViewById(R.id.addEventButton);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEventActivity.this,
                android.R.layout.simple_spinner_item, event_actions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> spinner, View view, int pos,
                                       long id) {
                if(spinner.getItemAtPosition(pos).toString().equals("Other")) {
                    editTextOtherEvent.setVisibility(View.VISIBLE);
                }else{
                    editTextOtherEvent.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                editTextOtherEvent.setVisibility(View.INVISIBLE);
            }
        });

        addEventButton.setOnClickListener(v -> {
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddEventActivity.this);
            myDatabaseHelper.addEvent(editTextOtherEvent.getText().toString().trim());

            startActivity(new Intent(AddEventActivity.this, MainActivity.class));
        });
    }
}