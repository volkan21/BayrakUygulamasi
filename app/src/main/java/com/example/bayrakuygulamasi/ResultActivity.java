package com.example.bayrakuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView textviewsonuc,textviewyuzdesonuc;
    private Button buttontekraroyna;
    private Integer dogrusayac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textviewsonuc=findViewById(R.id.textviewsonuc);
        textviewyuzdesonuc=findViewById(R.id.textviewyuzdesonuc);
        buttontekraroyna=findViewById(R.id.buttontekraroyna);
        dogrusayac=getIntent().getIntExtra("dogrusayac",0);
        textviewsonuc.setText(dogrusayac+":Doğru"+(5-dogrusayac)+":Yanlış" );
        textviewyuzdesonuc.setText("%"+((dogrusayac*100)/5)+":Başarı");
        buttontekraroyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this,QuizActivity.class));
                finish();

            }
        });
    }
}