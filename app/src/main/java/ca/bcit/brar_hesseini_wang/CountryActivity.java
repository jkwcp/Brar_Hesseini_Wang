package ca.bcit.brar_hesseini_wang;

/*Things to do, layouts and design*/
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import android.widget.Toast;
import android.app.ProgressDialog;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryActivity extends ListActivity {

    private String SERVICE_URL = "https://restcountries.eu/rest/v2/region/";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String continent = (String) getIntent().getExtras().get("continent");
        final String reqUrl = SERVICE_URL + continent;
        final ListView listCountries = getListView();

        new AsyncTask(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                pDialog = new ProgressDialog(CountryActivity.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();

            }


            @Override
            protected Object doInBackground(Object[] objects){
                HttpHandler sh = new HttpHandler();

                String jsonStr = sh.makeServiceCall(reqUrl);

                if (jsonStr != null) {
                    try {
                        JSONArray jsonList = new JSONArray(jsonStr);
                        String[] list = new String[jsonList.length()];
                        for (int i = 0; i < jsonList.length(); i++){
                            JSONObject c = jsonList.getJSONObject(i);
                            list[i] = c.getString("name");
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CountryActivity.this, android.R.layout.simple_expandable_list_item_1, list);
                        return arrayAdapter;
                    } catch (final JSONException e){
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o){
                if (pDialog.isShowing())
                    pDialog.dismiss();

                listCountries.setAdapter((ArrayAdapter)o);

            }
        }.execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String selectedCountry = l.getItemAtPosition(position).toString();
        Intent intent = new Intent(this, CountryDetailsActivity.class);
        intent.putExtra("country", (String)selectedCountry);
        startActivity(intent);
    }
}
