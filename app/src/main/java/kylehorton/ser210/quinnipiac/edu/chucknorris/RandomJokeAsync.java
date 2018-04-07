package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This is part of backend of the app.  It retrieves the data from the API and parses it using the JSON libraries.
 */

public class RandomJokeAsync extends AsyncTask<Void, Void, String> {

    String data = "";
    String joke = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection connection = null;
        URL url = null;

        // try catch block to receive the API data
        try {
            url = new URL("https://matchilling-chuck-norris-jokes-v1.p.mashape.com/jokes/random?mashape-key=J6MWOEOA8Jmshuu1yzdAszSfcbSMp1uhKuhjsna2IqKdjtZVrR");
            connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            // reads in the text as a string
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            // using the JSon libraries to parse the strings read in above
            JSONObject jsonObject = new JSONObject(data);
            joke = jsonObject.getString("value");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return joke;
    }


    // execute on call
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
