package ca.bcit.brar_hesseini_wang;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();
    public HttpHandler() {   }

    public String makeServiceCall(String reqUrl) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(reqUrl)
                .build();

        Response response = null;

        try{
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        return null;
    }
}
