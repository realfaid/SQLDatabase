package com.example.sqlprojekt;

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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


public class MainActivity extends AppCompatActivity {

    EditText name, resources, process;
    Button confirmRecept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editReceptName);
        resources = findViewById(R.id.editReceptResources);
        process = findViewById(R.id.editReceptProcess);
        confirmRecept = findViewById(R.id.btnConfirmAddRecept);
    }

public void AddRecept(View v){

    Intent ht1 = new Intent(MainActivity.this, AddRecept.class);
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
                //Intent ht1 = new Intent(MainActivity.this, MainActivity.class);
                //startActivity(ht1);
                setContentView(R.layout.activity_main);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }
