package com.example.sqlprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ReceptActivity extends AppCompatActivity {

TextView detailNazev, detailSuroviny, detailPostup;
int position;
String id2;
Button smazatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recept_detail);

        detailNazev = findViewById(R.id.receptDetailNazev);
        detailSuroviny = findViewById(R.id.receptDetailSuroviny);
        detailPostup = findViewById(R.id.receptDetailPostup);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        smazatBtn = findViewById(R.id.smazatBtn);


        id2 = MainActivity.receptArrayList.get(position).getId();
        detailNazev.setText(MainActivity.receptArrayList.get(position).getNazev());
        detailSuroviny.setText(MainActivity.receptArrayList.get(position).getSuroviny());
        detailPostup.setText(MainActivity.receptArrayList.get(position).getPostup());



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu); //fasfadf adf
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                Intent ht1 = new Intent(ReceptActivity.this, MainActivity.class);
                startActivity(ht1);

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void upravit(View v){
        Intent ht1 = new Intent( ReceptActivity.this, EditRecept.class);
        ht1.putExtra("position", position );
        startActivity(ht1);

    }




}
