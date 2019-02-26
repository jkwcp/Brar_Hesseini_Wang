package ca.bcit.brar_hesseini_wang;


/*Things to do, layouts and design*/
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryDetailsActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private String[] details;
    private Country tempCountry = new Country();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        String countryName = (String) getIntent().getExtras().get("country");
        final String reqUrl = "https://restcountries.eu/rest/v2/name/" + countryName;



        final TextView nameView = findViewById(R.id.name);
        final TextView capitalView = findViewById(R.id.capital);
        final TextView regionView = findViewById(R.id.region);
        final TextView populationView = findViewById(R.id.population);
        final TextView areaView = findViewById(R.id.area);
        final TextView bordersView = findViewById(R.id.borders);

        new AsyncTask(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                pDialog = new ProgressDialog(CountryDetailsActivity.this);
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

                        JSONObject c = jsonList.getJSONObject(0);

                        String temp = c.getString("name");
                        tempCountry.set_area(c.getString("area"));
                        tempCountry.set_name(c.getString("name"));
                        tempCountry.set_borders(c.getString("borders"));
                        tempCountry.set_capital(c.getString("capital"));
                        tempCountry.set_flag(c.getString("flag"));
                        tempCountry.set_region(c.getString("region"));
                        tempCountry.set_population(c.getString("populaion"));


                        return temp;

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

                nameView.setText(tempCountry.get_name());
                capitalView.setText(tempCountry.get_capital());
                regionView.setText(tempCountry.get_region());
                populationView.setText(tempCountry.get_population());
                areaView.setText(tempCountry.get_area());
                bordersView.setText(tempCountry.get_borders());


            }
        }.execute();

    }



}
