package com.example.sqlprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText name, resources, process;
    Button confirmRecept;
    ListView hlavniListView;
    ReceptAdapter adapter;
    public static ArrayList<Recept> receptArrayList = new ArrayList<>();
    String url = "https://sqlprojekt.000webhostapp.com/retrieve.php";
    Recept recept;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editReceptName);
        resources = findViewById(R.id.editReceptResources);
        process = findViewById(R.id.editReceptProcess);
        confirmRecept = findViewById(R.id.btnConfirmAddRecept);

        hlavniListView = findViewById(R.id.myListView);
        adapter = new ReceptAdapter(this, receptArrayList);
        hlavniListView.setAdapter(adapter);

        hlavniListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ht1 = new Intent( view.getContext(), ReceptActivity.class);
                ht1.putExtra("position", position );

                view.getContext().startActivity(ht1);
            }
        });

        nacistData();
    }

    public void AddRecept(View v){

        Intent ht1 = new Intent(MainActivity.this, AddRecept.class);
        startActivity(ht1);

    }

    public void nacistData(){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        receptArrayList.clear();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("recepty");

                            if(sucess.equals("1")){

                                for(int i=0; i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String nazev = object.getString("nazev");
                                    String suroviny = object.getString("suroviny");
                                    String postup = object.getString("postup");

                                    recept = new Recept(id,nazev,suroviny,postup);
                                    receptArrayList.add(recept);
                                    adapter.notifyDataSetChanged();
                                }

                            }

                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }




    }
