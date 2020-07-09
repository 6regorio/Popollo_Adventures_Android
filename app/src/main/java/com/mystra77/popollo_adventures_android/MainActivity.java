package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private MediaPlayer musicaFondo;
    private MediaPlayer sonidoBoton;
    private int lengthMusic;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Eliminando barra de estado
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        musicaFondo = MediaPlayer.create(this, R.raw.fondo_inicio);
        musicaFondo.setLooping(true);
        musicaFondo.start();
        sonidoBoton = MediaPlayer.create(this, R.raw.sonido_login);
        handler = new Handler();
    }

    public void irAPrincipal(View view) {
        sonidoBoton.start();
        handler.postDelayed(new Runnable() {
            public void run() {
                intent = new Intent(MainActivity.this, ActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        }, 600);
    }

    public void continuarPartida(View view) {
        sonidoBoton.start();
        handler.postDelayed(new Runnable() {
            public void run() {
                intent = new Intent(MainActivity.this, ActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        }, 600);
    }

    public void salir(View view) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.cerrarJuego)
                .setCancelable(false)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
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
        if (musicaFondo != null) {
            musicaFondo.stop();
            musicaFondo.release();
        }
        if (sonidoBoton != null) {
            sonidoBoton.stop();
            sonidoBoton.release();
        }
    }
}