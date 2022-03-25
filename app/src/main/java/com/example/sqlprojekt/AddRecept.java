package com.example.sqlprojekt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class AddRecept extends AppCompatActivity {

    EditText txtname, txtresources, txtprocess;
    Button confirmRecept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recept);

        txtname = findViewById(R.id.editReceptName);
        txtresources = findViewById(R.id.editReceptResources);
        txtprocess = findViewById(R.id.editReceptProcess);
        confirmRecept = findViewById(R.id.btnConfirmAddRecept);

        confirmRecept.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                insertData();


            }


        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                Intent ht1 = new Intent(AddRecept.this, MainActivity.class);
                startActivity(ht1);

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void insertData() {

        String nazev = txtname.getText().toString().trim();
        String suroviny = txtresources.getText().toString().trim();
        String postup = txtprocess.getText().toString().trim();



        if (nazev.isEmpty()) {
            Toast.makeText(this, "Zadej název", Toast.LENGTH_SHORT).show();
            return;
        } else if (suroviny.isEmpty()) {
            Toast.makeText(this, "Zadej Suroviny", Toast.LENGTH_SHORT).show();
            return;
        } else if (postup.isEmpty()) {
            Toast.makeText(this, "Zadej Postup", Toast.LENGTH_SHORT).show();
            return;
        }

        else{


            StringRequest request = new StringRequest(Request.Method.POST, "https://sqlprojekt.000webhostapp.com/insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data vložena")) {
                                Toast.makeText(AddRecept.this, "Data vložena", Toast.LENGTH_SHORT).show();

                                Intent ht1 = new Intent(AddRecept.this, MainActivity.class);
                                startActivity(ht1);
                            }
                            else{
                                Toast.makeText(AddRecept.this, "chybička", Toast.LENGTH_SHORT).show();

                            }
                        }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddRecept.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("nazev",nazev);
                    params.put("suroviny",suroviny);
                    params.put("postup",postup);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddRecept.this);
            requestQueue.add(request);

    }
}




}
