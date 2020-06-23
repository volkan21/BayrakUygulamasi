package com.example.bayrakuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView textviewwelcome;
    private Button buttonstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textviewwelcome=findViewById(R.id.textviewwelcome);
        buttonstart=findViewById(R.id.buttonstart);
        kopyala();
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,QuizActivity.class));

            }
        });
    }
    public void  kopyala() {
        DatabaseCopyHelper copy=new DatabaseCopyHelper(this);
        try {
            copy.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        copy.openDataBase();
    }
}