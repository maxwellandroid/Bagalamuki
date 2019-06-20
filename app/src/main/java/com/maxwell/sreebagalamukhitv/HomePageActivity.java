package com.maxwell.sreebagalamukhitv;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String TAG="FireBaseResponse";
    String deviceId="";
    String regToken="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.background));
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        deviceId= Settings.Secure.getString(HomePageActivity.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        regToken=token;
                        if(!token.isEmpty())
                            sendToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                       // Toast.makeText(HomePageActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


        HomeFragment homeFragment = new HomeFragment();
        insertFragment(homeFragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void sendToken(){
        RequestQueue requestQueue = Volley.newRequestQueue(HomePageActivity.this);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, StringConstants.mainUrl + StringConstants.loginUrl+StringConstants.inputDeviceId+deviceId+StringConstants.inputRegToken+regToken, null,
                new Response.Listener<JSONObject>()
                {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        // display response

                        try {
                            if (jsonObject.has("Status")) {

                                JSONObject statusObject=jsonObject.getJSONObject("Status");
                                String statusCode=statusObject.getString("StatusCode");
                                String statusMessage=statusObject.getString("StatusMessage");

                                if(statusCode.matches("200")&&statusMessage.matches("Successfully Inserted")){
                                  // Toast.makeText(getApplicationContext(),statusMessage,Toast.LENGTH_SHORT).show();
                                }



                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage=StringConstants.ErrorMessage(error);
                        if(errorMessage.matches("Connection TimeOut! Please check your internet connection.")){
                            sendToken();
                        }

                    }
                }
        );
        requestQueue.add(getRequest);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            HomeFragment homeFragment = new HomeFragment();
            insertFragment(homeFragment);
            // Handle the camera action
        } else if (id == R.id.nav_teaser) {
           Intent i=new Intent(getApplicationContext(),TeaserActivity.class);
           startActivity(i);
        }else if (id == R.id.nav_about) {
            AboutGurujiFragment aboutGurujiFragment = new AboutGurujiFragment();
            insertFragment(aboutGurujiFragment);
        } else if (id == R.id.nav_livetv) {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_videos) {
            LanguageselectionFragment languageselectionFragment=new LanguageselectionFragment();
           insertFragment(languageselectionFragment);
        } else if (id == R.id.nav_book_now) {

           BookNowFragment bookNowFragment=new BookNowFragment();
           insertFragment(bookNowFragment);
        } else if (id == R.id.nav_contactus) {

            ContactUsFragment contactUsFragment = new ContactUsFragment();
            insertFragment(contactUsFragment);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void insertFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack("mainstack");
        transaction.commit();
    }

}
