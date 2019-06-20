package com.maxwell.sreebagalamukhitv;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

List<VideosModel> videosModelList=new ArrayList<>();
VideosModel videosModel;
String language="";
TextView tv_language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewVideos);
        tv_language=(TextView)findViewById(R.id.text_selected_language);

        if(getIntent()!=null){
            language=getIntent().getStringExtra("Language");
            tv_language.setText("Videos - "+language);
        }

        videosListing();
    }
    public void backPressed(View view){
        onBackPressed();
    }
    public void videosListing(){
        RequestQueue queue = Volley.newRequestQueue(VideosActivity.this);

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, StringConstants.mainUrl + StringConstants.videoUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.d("Response",response);
                try {
                    JSONObject jsonObject=new JSONObject(response.trim());

                    if(jsonObject.has("videos")){

                        videosModelList=new ArrayList<>();
                        JSONArray videosArray=jsonObject.getJSONArray("videos");
                        for(int i=0;i<videosArray.length();i++){
                            JSONObject object=videosArray.getJSONObject(i);
                            videosModel=new VideosModel();
                            videosModel.setTitle(object.getString("title"));
                            videosModel.setVideoUrl(object.getString("embed_code"));
                            videosModelList.add(videosModel);
                        }
                    }


                    mAdapter=new VideosAdapter(getApplicationContext(),videosModelList);
                    LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(horizontalLayoutManager1);
                    recyclerView.setAdapter(mAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                String errormessage=StringConstants.ErrorMessage(error);
                //showAlertDialog(errormessage);
                //This code is executed if there is an error.
                if(errormessage.matches("Connection TimeOut! Please check your internet connection.")){
                   videosListing();

                }else {

                }
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put(StringConstants.inputLanguage, language);

                // MyData.put(StringConstants.inputMobile,"");//Add the data you'd like to send to the server.
                return MyData;
            }
        };

// add it to the RequestQueue
        queue.add(MyStringRequest);


    }
    public static class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder>{

        List<VideosModel> videosModelList1;
        Context context;

        public VideosAdapter(Context mcontext, List<VideosModel> videosModelList){
            this.context=mcontext;
            this.videosModelList1 =videosModelList;

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View listItem= layoutInflater.inflate(R.layout.layout_videos_row, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            final VideosModel videosModel= videosModelList1.get(i);
            viewHolder.tv_title.setText(videosModel.getTitle());
            viewHolder.layout_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i=new Intent(context,PlayVideoActivity.class);
                    i.putExtra("VideoUrl",videosModel.getVideoUrl());
                    context.startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return videosModelList1.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_title;
            private static final int RECOVERY_REQUEST = 1;
            RelativeLayout layout_video;
            public ViewHolder(View itemView) {
                super(itemView);

                this.tv_title =(TextView)itemView.findViewById(R.id.text_title);
                this.layout_video=(RelativeLayout)itemView.findViewById(R.id.layout_play_video);



            }
        }

    }

}
