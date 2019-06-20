package com.maxwell.sreebagalamukhitv;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener  {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    RelativeLayout layout_video;
    YouTubePlayer player;

    String videoUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
        layout_video=(RelativeLayout)findViewById(R.id.layout_play_video);

        if(getIntent()!=null){
            videoUrl=getIntent().getStringExtra("VideoUrl");
        }

/*
        layout_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout_video.setVisibility(View.GONE);

                player.loadVideo("iNJf7PG0p_A");
            }
        });
*/
    }
    public void backPressed(View view){
        onBackPressed();
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            player=youTubePlayer;
            layout_video.setVisibility(View.GONE);
            youTubePlayer.loadVideo(videoUrl); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format("error on playing video", errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }
}
