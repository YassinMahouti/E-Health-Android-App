package be.ehb.LoginMockup.ui.corona;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.widget.TextView;

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

public class CoronaStatsAPI extends AsyncTask<Void , Void , Void> {
    String data = "";
    String dataParsed = "";
    String singleParsedObj = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://thevirustracker.com/free-api?global=stats");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {

                line = bufferedReader.readLine();
                data += line;
            }
            try {
                JSONObject JO = new JSONObject(data);
                JSONArray JA = JO.getJSONArray("results");
                for (int i = 0; i < JA.length(); i++) {
                    JSONObject obj = JA.getJSONObject(i);

                    singleParsedObj = "Total Recovered People in the world:" + obj.get("total_recovered") + "\n" +
                            "Total Unresolved COVID-19 cases: " + obj.get("total_unresolved") + "\n" +
                            "Total of Deaths worldwide" + obj.get("total_deaths") + "\n" +
                            "Total of New COVID-19 Cases today worldwide" + obj.get("total_new_cases_today") + "\n" +
                            "Total of New Deaths today worldwide" + obj.get("total_new_deaths_today") + "\n" +
                            "Total active cases worldwide" + obj.get("total_active_cases") + "\n" +
                            "Total of serious cases worldwide" + obj.get("total_serious_cases") + "\n" +
                            "Total of Affected countries" + obj.get("total_affected_countries") + "\n";

                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }


            System.out.println(singleParsedObj);
        } catch (IOException ex) {
            ex.printStackTrace();



        } return null;
    }
        @Override
        protected void onPostExecute (Void aVoid){
            super.onPostExecute(aVoid);
            // wegschrijven naar txtView

            UserCoronaDetails.data_api.setText(this.singleParsedObj);

        }



}
