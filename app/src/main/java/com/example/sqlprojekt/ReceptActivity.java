package com.example.sqlprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ReceptActivity extends AppCompatActivity {

TextView detailNazev, detailSuroviny, detailPostup;
int position;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recept_detail);

        detailNazev = findViewById(R.id.receptDetailNazev);
        detailSuroviny = findViewById(R.id.receptDetailSuroviny);
        detailPostup = findViewById(R.id.receptDetailPostup);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        detailNazev.setText("Název:"+MainActivity.receptArrayList.get(position).getNazev());
        detailSuroviny.setText("Suroviny:"+MainActivity.receptArrayList.get(position).getSuroviny());
        detailPostup.setText("Postup:"+MainActivity.receptArrayList.get(position).getPostup());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailNazev.setMovementMethod(new ScrollingMovementMethod());

    }




}
