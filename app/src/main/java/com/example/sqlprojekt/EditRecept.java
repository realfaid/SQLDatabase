package com.example.sqlprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class EditRecept extends AppCompatActivity
{
    int position;
    String id;
    EditText edNazev, edSuroviny, edPostup;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_recept);

        edNazev = findViewById(R.id.editNazev);
        edSuroviny = findViewById(R.id.editSuroviny);
        edPostup = findViewById(R.id.editPostup);




        Intent intent = getIntent();
       position = intent.getExtras().getInt("position");
        id = MainActivity.receptArrayList.get(position).getId();

        edNazev.setText(MainActivity.receptArrayList.get(position).getNazev());
        edSuroviny.setText(MainActivity.receptArrayList.get(position).getSuroviny());
        edPostup.setText(MainActivity.receptArrayList.get(position).getPostup());
    }

    public void smazData(View v){
        smazatData(id);
        Intent ht1 = new Intent( EditRecept.this, MainActivity.class);
        startActivity(ht1);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                Intent ht1 = new Intent(EditRecept.this, MainActivity.class);
                startActivity(ht1);

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateData(View v){

        String novyNazev = edNazev.getText().toString();
        String noveSuroviny = edSuroviny.getText().toString();
        String novyPostup = edPostup.getText().toString();




        StringRequest request = new StringRequest(Request.Method.POST, "https://sqlprojekt.000webhostapp.com/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data se aktualizovala")){
                            Toast.makeText(EditRecept.this, "Data se úspěšně aktualizovala", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditRecept.this, MainActivity.class));
                        finish();
                        }
                        else{

                            Toast.makeText(EditRecept.this, "Chyba, data se neaktualizovala", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditRecept.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("id", id);
                params.put("nazev",novyNazev);
                params.put("suroviny",noveSuroviny);
                params.put("postup",novyPostup);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(EditRecept.this);
        requestQueue.add(request);


    }



    public void smazatData(final String id1){
        StringRequest request = new StringRequest(Request.Method.POST, "https://sqlprojekt.000webhostapp.com/delete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            if(response.equalsIgnoreCase("uspech")){
                                Toast.makeText(EditRecept.this, "Data se úspěšně smazala", Toast.LENGTH_SHORT).show();

                                }
                            else{

                                Toast.makeText(EditRecept.this, "Chyba, data se nesmazala", Toast.LENGTH_SHORT).show();
                            }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditRecept.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{

                Map<String, String> params = new HashMap<String,String>();
                params.put("id", id1);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }

}
