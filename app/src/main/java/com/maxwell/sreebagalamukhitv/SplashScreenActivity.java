package com.maxwell.sreebagalamukhitv;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class SplashScreenActivity extends AppCompatActivity {

    VideoView videoView;
    Window window;
    String currentVersion;
    String TAG="FireBaseResponse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_splash_screen);


        videoView = (VideoView) findViewById(R.id.videoView);
        try {
            currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(isNetworkAvailable()){
            new Thread(new Runnable() {
                @Override
                public void run() {


                    try {
                        Thread.sleep(2500);
                        new GetVersionCode().execute();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }).start();
        }else {
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo);
            videoView.setVideoURI(video);

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    startNextActivity();
                }
            });

            videoView.start();
        }


    }

    private void startNextActivity() {
        if (isFinishing())
            return;
        startActivity(new Intent(SplashScreenActivity.this, HomePageActivity.class));
        finish();
    }

    public void skipView(View view){

        startNextActivity();

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    class GetVersionCode extends AsyncTask<Void, String, String> {

        @Override

        protected String doInBackground(Void... voids) {

            String newVersion = null;

            try {
                Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + SplashScreenActivity.this.getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get();
                if (document != null) {
                    Elements element = document.getElementsContainingOwnText("Current Version");
                    for (Element ele : element) {
                        if (ele.siblingElements() != null) {
                            Elements sibElemets = ele.siblingElements();
                            for (Element sibElemet : sibElemets) {
                                newVersion = sibElemet.text();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }catch (NumberFormatException e1){
                e1.printStackTrace();
            }
            return newVersion;

        }

        @Override
        protected void onPostExecute(String onlineVersion) {

            super.onPostExecute(onlineVersion);

            if (onlineVersion != null && !onlineVersion.isEmpty()) {

                if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
                    //show anything
                    Intent intent = new Intent(SplashScreenActivity.this, UpdateAppActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo);
                    videoView.setVideoURI(video);

                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            startNextActivity();
                        }
                    });

                    videoView.start();
                }
            }

            Log.d("updateNewVersion", "Current version " + currentVersion + "playstore version " + onlineVersion);
        }
    }

    public void sendRegistrationToken(){

    }

}
