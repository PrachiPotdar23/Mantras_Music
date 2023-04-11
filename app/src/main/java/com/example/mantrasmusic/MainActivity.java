package com.example.mantrasmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;


import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    TextView startTime=(TextView) findViewById(R.id.startTime);
    TextView endTime=(TextView) findViewById(R.id.endTime) ;
    String start=startTime.getText().toString();
    String end=endTime.getText().toString();

    AudioManager audioManager;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setSelected(true);


        // for playing the music

        player = MediaPlayer.create(this, R.raw.music);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        SeekBar seekVol = findViewById(R.id.seekVol);
        seekVol.setMax(maxVol);
        seekVol.setProgress(curVol);

        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar seekProg = findViewById(R.id.seekProg);
        seekProg.setMax(player.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekProg.setProgress(player.getCurrentPosition());
            }
        }, 0, 900);

        seekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button OpenBottomSheet = findViewById(R.id.open_bottom_sheet);

        OpenBottomSheet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        BotttomSheet botttomSheet=new BotttomSheet();
                        botttomSheet.show(getSupportFragmentManager(),
                                "ModalBottomSheet");
                    }
                });

    }

    public void play(View view) {
        BotttomSheet b=new BotttomSheet();
        long end_time=b.setTime();

        new CountDownTimer(end_time, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                startTime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

                if(player.isPlaying()==false){
                    player.start();
                }
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                startTime.setText("00:00:00");
            }
        }.start();
        //player.start();

    }
    public void stop(View view){
        player.stop();
    }
}





