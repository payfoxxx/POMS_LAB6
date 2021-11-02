package com.example.lab2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataBaseActivity extends AppCompatActivity {
    TextView dbContent;
    DataBaseManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        dbContent = findViewById(R.id.db_text_view);
        dbManager = new DataBaseManager(this);
        dbManager.open();

        Button showDbBtn = findViewById(R.id.show_db_btn);
        showDbBtn.setOnClickListener(v->showDb());
        Button clearDbBtn = findViewById(R.id.clear_db_btn);
        clearDbBtn.setOnClickListener(v->clearDb());
        Button clearScreen = findViewById(R.id.clear_screen_db_btn);
        clearScreen.setOnClickListener(v->clearScreenDb());
    }

    private void showDb() {
        dbContent.setText(dbManager.getAllAsText());
    }
    private void clearDb(){
        dbManager.deleteAll();
    }
    private void clearScreenDb(){
        dbContent.setText("");
    }
}
