package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar seek;
    TextView txt;
    CountDownTimer count;
    boolean bool = false;
    Button btn ;
    public void fin(){
        txt.setText("0:30");
        seek.setProgress(30);
        seek.setEnabled(true);
        count.cancel();
        btn.setText("Go");
        bool = false;
    }
    public void myFun(View view) {
        if(bool){
            fin();
        }else {
            bool = true;
            seek.setEnabled(false);
            btn.setText("Stop");
            count = new CountDownTimer(seek.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    reuse((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer media = MediaPlayer.create(getApplicationContext(), R.raw.roar);
                    media.start();
                    fin();
                }
            }.start();
        }
    }
    public void reuse(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft -(minutes*60);
        String str=Integer.toString(seconds);
        if (seconds<10) {
            str="0"+str;
        }
        txt.setText(Integer.toString(minutes)+":"+str);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.timeText);
        seek = (SeekBar)findViewById(R.id.seekBar3);
        btn = (Button)findViewById(R.id.button3);
        seek.setMax(600);
        seek.setProgress(30);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                reuse(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
