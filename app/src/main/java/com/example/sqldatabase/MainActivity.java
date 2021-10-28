package com.example.sqldatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void AddCategory(View view)
    {
      //  Intent AddCategory = new Intent(MainActivity.this, AddCategory.class);
       // startActivity(AddCategory);
    setContentView(R.layout.add_category);

    }

    public void AddRecept(View view)
    {
        //  Intent AddCategory = new Intent(MainActivity.this, AddCategory.class);
        // startActivity(AddCategory);
        setContentView(R.layout.add_recept);

    }
    }
