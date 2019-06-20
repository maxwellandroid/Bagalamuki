package com.maxwell.sreebagalamukhitv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tcking.github.com.giraffeplayer2.VideoInfo;
import tcking.github.com.giraffeplayer2.VideoView;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;
    private CoordinatorLayout mCLayout;
    VideoView videoView;
    int aspectRatio = VideoInfo.AR_ASPECT_FILL_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.video_view);
        mCLayout = (CoordinatorLayout) findViewById(R.id.co_layout);
        mContext = getApplicationContext();
        mActivity = MainActivity.this;
        getHSL();
    }

    public void getHSL() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "http://www.maxwellstreaming.com/hls/hlsurl.php",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try {
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("hls");

                            // Loop through the array elements
                            for (int i = 0; i < array.length(); i++) {
                                // Get current json object
                                JSONObject student = array.getJSONObject(i);

                                // Get the current student (json object) data
                                String sreebagalamukhidevi = student.getString("sreebagalamukhidevi");
                                videoView.setVideoPath(sreebagalamukhidevi).getPlayer().start();
                                videoView.getPlayer().aspectRatio(aspectRatio);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        Snackbar.make(
                                mCLayout,
                                "Error.",
                                Snackbar.LENGTH_LONG
                        ).show();
                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);


    }
    /*private class JsonTasks extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finaljson = buffer.toString();
                JSONObject finaljsonobject = new JSONObject(finaljson);
                JSONArray jsonArray = finaljsonobject.getJSONArray("hls");

                JSONObject jsonObject = jsonArray.getJSONObject(0);
                path = jsonObject.getString("maxwell2");

                return path;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                VideoView videoView = (VideoView) findViewById(R.id.video_view);
                videoView.setVideoPath(result).getPlayer().start();
                videoView.getPlayer().aspectRatio(aspectRatio);
            } else {
                Intent intent = new Intent(LiveTvActivity.this, EmptyActivity.class);
                startActivity(intent);
            }
        }
    }*/

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),HomePageActivity.class);
        startActivity(i);
        finish();

    }
}


