package com.indianapp.fixmyspeaker;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button playstp;
    private Boolean isPlaying;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));

        isPlaying = false;
        playstp = findViewById(R.id.play_stop);
        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("audio", "raw", getPackageName()));
        AudioManager am =
                (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final int originalVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);


        playstp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isPlaying) {


                    am.setStreamVolume(
                            AudioManager.STREAM_MUSIC,
                            am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                            0);
                    Animation sgAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.avatar_anim);
                    playstp.startAnimation(sgAnimation);

                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);


                    isPlaying = true;
                    playstp.setText("STOP");
                } else {
                    playstp.setAnimation(null);
                    am.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume, 0);
                    mediaPlayer.stop();
                    mediaPlayer.prepareAsync();
                    isPlaying = false;
                    playstp.setText("START");
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.release();
    }
}
