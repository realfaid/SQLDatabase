package com.example.sqlprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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


public class EditReceptnepouzivam extends AppCompatActivity
{
    int id1, position;
    String id;
    Button smazatBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_recept);

        smazatBtn = findViewById(R.id.smazatBtn);
        Intent intent = getIntent();
       position = intent.getExtras().getInt("position");
        id = MainActivity.receptArrayList.get(position).getId();
    }

    public void smazData(View v){
        smazatData(id);

    }



    public void smazatData(final String id1){
        StringRequest request = new StringRequest(Request.Method.POST, "https://sqlprojekt.000webhostapp.com/delete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                            if(response.equalsIgnoreCase("uspech")){
                                Toast.makeText(EditReceptnepouzivam.this, "Data se úspěšně smazala", Toast.LENGTH_SHORT).show();

                                }
                            else{

                                Toast.makeText(EditReceptnepouzivam.this, "Chyba, data se nesmazala", Toast.LENGTH_SHORT).show();
                            }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditReceptnepouzivam.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
