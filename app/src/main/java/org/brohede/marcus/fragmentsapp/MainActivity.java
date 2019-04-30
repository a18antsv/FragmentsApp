package org.brohede.marcus.fragmentsapp;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListViewFragment.OnListFragmentInteractionListener, MountainDetailsFragment.OnFragmentInteractionListener {
    public static final List<Mountain> MOUNTAINS = new ArrayList<>();
    private ListViewFragment listViewFragment;
    private MountainDetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_listview_container) != null) {
            if(savedInstanceState == null) {
                new FetchData().execute();
            }
            listViewFragment = new ListViewFragment();
            listViewFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_listview_container, listViewFragment).commit();
        }
    }

    @Override
    public void onListFragmentInteraction(Mountain m) {
        detailsFragment = MountainDetailsFragment.newInstance(
                m.getId(),
                m.getName(),
                m.getHeight(),
                m.getLocation(),
                m.getImgURL(),
                m.getArticleURL()
        );

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.replace(R.id.fragment_details_container, detailsFragment);
        } else {
            transaction.replace(R.id.fragment_listview_container, detailsFragment);
            transaction.addToBackStack(null);
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class FetchData extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);

            try {
                MOUNTAINS.clear();
                JSONArray mountains = new JSONArray(o);

                for(int i = 0; i < mountains.length(); i++) {
                    JSONObject mountain = mountains.getJSONObject(i);
                    JSONObject auxdata = new JSONObject(mountain.getString("auxdata"));

                    Mountain m = new Mountain(
                            Integer.parseInt(mountain.getString("ID")),
                            mountain.getString("name"),
                            mountain.getInt("size"),
                            mountain.getString("location"),
                            auxdata.getString("img"),
                            auxdata.getString("url")
                    );
                    MOUNTAINS.add(m);
                }
                listViewFragment.setAdapter(MOUNTAINS);
            } catch(JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
