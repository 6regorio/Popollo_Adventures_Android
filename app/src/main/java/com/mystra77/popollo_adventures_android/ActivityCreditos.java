package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mystra77.popollo_adventures_android.clases.Heroe;

public class ActivityCreditos extends AppCompatActivity {
    private MediaPlayer musicaFondo, sonidoPollo;
    private TextView textNombre;
    private ImageView pollo1, pollo2, pollo3;
    private int lengthMusic;
    private CountDownTimer cTimer;

    private int segundos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_creditos);

        musicaFondo = MediaPlayer.create(this, R.raw.fondo_gatoz);
        musicaFondo.setLooping(true);
        musicaFondo.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        musicaFondo.pause();
        lengthMusic = musicaFondo.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        musicaFondo.seekTo(lengthMusic);
        musicaFondo.start();
        musicaFondo.setLooping(true);
    }

    public void onDestroy() {
        super.onDestroy();
        cancelTimer();
        if (musicaFondo != null) {
            musicaFondo.stop();
            musicaFondo.release();
        }
        if (sonidoPollo != null) {
            sonidoPollo.stop();
            sonidoPollo.release();
        }

        if (sonidoPollo != null) {
            sonidoPollo.stop();
            sonidoPollo.release();
        }
    }

    @Override
    public void onBackPressed() {
    }

    //start timer function
    public void startTimer() {
        cTimer = new CountDownTimer(25000, 1000) {
            public void onTick(long millisUntilFinished) {
                segundos++;
                if (segundos == 4){

                }
            }
            public void onFinish() {
            }
        };
        cTimer.start();
    }


    //cancel timer
    public  void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }
}