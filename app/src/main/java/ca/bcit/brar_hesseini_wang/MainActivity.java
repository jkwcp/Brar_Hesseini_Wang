package ca.bcit.brar_hesseini_wang;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView list_continents = (ListView)findViewById(R.id.list_continents);
        list_continents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)  {
                String selectedContinent = (list_continents.getItemAtPosition(i).toString());
                Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                intent.putExtra("continent", (String)selectedContinent);
                startActivity(intent);
            }
        });
    }



}

