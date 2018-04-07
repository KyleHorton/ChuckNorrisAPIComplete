package kylehorton.ser210.quinnipiac.edu.chucknorris;

import android.os.AsyncTask;
import org.json.JSONArray;
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
 * 3/8/2018
 *
 * This is the backend of the app.  It retrieves the data from the API and parses it using the JSON libraries.
 * It takes in a search parameter before accessing the REST-API.
 */

public class SearchJokeAsync extends AsyncTask<Void, Void, String> {

    String data = "";
    String joke = "";
    String topic = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        topic = SearchActivity.topic;

        HttpURLConnection connection = null;
        URL url = null;

        // try catch block to receive the API data
        try {
            url = new URL("https://matchilling-chuck-norris-jokes-v1.p.mashape.com/jokes/search?query=" + topic + "&mashape-key=J6MWOEOA8Jmshuu1yzdAszSfcbSMp1uhKuhjsna2IqKdjtZVrR");
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
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            // access array and finds joke value
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonArrayData = jsonArray.getJSONObject(i);
                joke = jsonArrayData.getString("value");
            }

            // if the array is empty
            if (jsonArray.length() == 0){
                joke = "ERROR: Invalid Search! Please enter a different topic!";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            joke = "ERROR: Invalid Search! Please enter a topic before attempting to generate a joke!";
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

