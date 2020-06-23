package com.example.bayrakuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
    private TextView textviewdogru,textviewyanlıs,textviewsorusayisi;
    private ImageView imagebayrak;
    private Button buttona,buttonb,buttonc,buttond;
    private VeriTabani vt;
    private ArrayList<Bayraklar>sorularlist;
    private ArrayList<Bayraklar> yanlıssecenklerliste;

    private Bayraklar dogrusoru;

    private int dogrusayac=0;
    private int yanlıssayac=0;

    private int sorusayac=0;

    private HashSet<Bayraklar> seceneklerkarıstırmalist=new HashSet<>();

    private ArrayList<Bayraklar> seceneklerliste=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vt=new VeriTabani(this);
        setContentView(R.layout.activity_quiz);
        textviewdogru=findViewById(R.id.textviewdogru);
        textviewyanlıs=findViewById(R.id.textviewyanlıs);
        textviewsorusayisi=findViewById(R.id.textviewsorusayi);
        imagebayrak=findViewById(R.id.imagebayrak);
        buttona=findViewById(R.id.buttona);
        buttonb=findViewById(R.id.buttonb);
        buttonc=findViewById(R.id.buttonc);
        buttond=findViewById(R.id.buttond);


        sorularlist=new BayraklarDao().rastgele5getir(vt);
        soruyukle();

        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogrukontrol(buttona);
                sayackontrol();




            }
        });
        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogrukontrol(buttonb);
                sayackontrol();



            }
        });
        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogrukontrol(buttonc);
                sayackontrol();



            }
        });
        buttond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogrukontrol(buttond);
                sayackontrol();



            }
        });
    }
    public void soruyukle() {
        textviewsorusayisi.setText((sorusayac+1)+".SORU");

        //soru yuklendıgı an dogru soruyu almam lazım
        dogrusoru=sorularlist.get(sorusayac);

        yanlıssecenklerliste=new BayraklarDao().yanlıssecenekgetır(vt,dogrusoru.getBayrak_id());

        imagebayrak.setImageResource(getResources().getIdentifier(dogrusoru.getBayrak_resim(),"drawable",getPackageName()));

      seceneklerkarıstırmalist.clear();

        seceneklerkarıstırmalist.add(dogrusoru);

        seceneklerkarıstırmalist.add(yanlıssecenklerliste.get(0));
        seceneklerkarıstırmalist.add(yanlıssecenklerliste.get(1));
        seceneklerkarıstırmalist.add(yanlıssecenklerliste.get(2));

        seceneklerliste.clear();

        for (Bayraklar b:seceneklerkarıstırmalist) {
            seceneklerliste.add(b);

        }

        buttona.setText(seceneklerliste.get(0).getBayrak_ad());
        buttonb.setText(seceneklerliste.get(1).getBayrak_ad());
        buttonc.setText(seceneklerliste.get(2).getBayrak_ad());
        buttond.setText(seceneklerliste.get(3).getBayrak_ad());


    }

    public void dogrukontrol(Button button){

        String buttonyazi=button.getText().toString();

        String dogrucevap=dogrusoru.getBayrak_ad();
        if (buttonyazi.equals(dogrucevap)) {
            dogrusayac++;

        } else {
            yanlıssayac++;

        }
        textviewdogru.setText("Doğru:"+dogrusayac);
        textviewyanlıs.setText("Yanlış:"+yanlıssayac);



    }

    public void sayackontrol(){
        sorusayac++;
        if (sorusayac !=5)  {
            soruyukle();

        } else {
          Intent yeni=  new Intent(QuizActivity.this,ResultActivity.class);
          yeni.putExtra("dogrusayac",dogrusayac);
            startActivity(yeni);
            finish();


        }
    }

}