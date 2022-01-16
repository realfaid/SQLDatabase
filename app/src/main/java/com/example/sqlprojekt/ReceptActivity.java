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
int position, id;

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
        detailNazev.setText("Název:"+MainActivity.receptArrayList.get(position).getNazev());
        detailSuroviny.setText("Suroviny:"+MainActivity.receptArrayList.get(position).getSuroviny());
        detailPostup.setText("Postup:"+MainActivity.receptArrayList.get(position).getPostup());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailNazev.setMovementMethod(new ScrollingMovementMethod());

    }

    public void upravit(View v){
       setContentView(R.layout.edit_recept);

    }

    public void smazData(View v){
        smazatData(id2);
        Intent ht1 = new Intent( ReceptActivity.this, MainActivity.class);
        startActivity(ht1);


    }



    public void smazatData(final String id1){
        StringRequest request = new StringRequest(Request.Method.POST, "https://sqlprojekt.000webhostapp.com/delete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("uspech")){
                            Toast.makeText(ReceptActivity.this, "Data se úspěšně smazala", Toast.LENGTH_SHORT).show();

                        }
                        else{

                            Toast.makeText(ReceptActivity.this, "Chyba, data se nesmazala", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReceptActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String,String>();
                params.put("id", id1);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }


}
