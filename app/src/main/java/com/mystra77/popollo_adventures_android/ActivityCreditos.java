package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ActivityCreditos extends AppCompatActivity {
    private MediaPlayer musicaFondo, sonidoPollo;
    private TextView textNombre, textTitulo;
    private ImageView pollo1, pollo2, pollo3, pollo4;
    private int lengthMusic;
    private CountDownTimer cTimer;
    private Intent intent;
    private Button botonSalir;

    private int segundos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_creditos);

        textNombre = findViewById(R.id.textViewNombre);
        textTitulo = findViewById(R.id.textViewTitulo);
        botonSalir = findViewById(R.id.btnSalirCreditos);

        musicaFondo = MediaPlayer.create(this, R.raw.fondo_gatoz);
        musicaFondo.setLooping(true);
        musicaFondo.start();

        sonidoPollo = MediaPlayer.create(this, R.raw.sonido_premio);

        pollo1 = findViewById(R.id.imageViewPollo1);
        pollo2 = findViewById(R.id.imageViewPollo2);
        pollo3 = findViewById(R.id.imageViewPollo3);
        pollo4 = findViewById(R.id.imageViewPollo4);
        Glide.with(this).load(R.drawable.pollo1).into(pollo1);
        Glide.with(this).load(R.drawable.pollo1).into(pollo2);
        Glide.with(this).load(R.drawable.pollo1).into(pollo3);
        Glide.with(this).load(R.drawable.pollo2).into(pollo4);
        startTimer();
    }

    public void salirDeLosCreditos(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.salirInicio);
        final Dialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        intent = new Intent(this, MainActivity.class);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
                intent.removeExtra("heroe");
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    //start timer function
    public void startTimer() {
        cTimer = new CountDownTimer(66000, 1000) {
            public void onTick(long millisUntilFinished) {
                segundos++;
                if (segundos == 5){
                    activarTitulo("Director");
                }
                if (segundos == 7){
                    activarNombre("Iván Díaz Vera");
                    sonidoPollo.start();
                    pollo1.setVisibility(View.VISIBLE);
                }
                if (segundos == 12){
                    desactivarCreditos();
                }
                if (segundos == 16){
                    activarTitulo("Productor");
                }
                if (segundos == 18){
                    activarNombre("Iván Díaz Vera");
                    sonidoPollo.start();
                    pollo2.setVisibility(View.VISIBLE);
                }
                if (segundos == 23){
                    desactivarCreditos();
                }
                if (segundos == 27){
                    activarTitulo("Dirección Artística");
                }
                if (segundos == 29){
                    activarNombre("Ana Belén Molina González");
                    sonidoPollo.start();
                    pollo3.setVisibility(View.VISIBLE);
                }
                if (segundos == 34){
                    desactivarCreditos();
                }
                if (segundos == 36){
                    activarTitulo("");
                    activarNombre("Basado en hechos MUY reales.");
                }
                if (segundos == 41){
                    desactivarCreditos();
                }
                if (segundos == 45){
                    activarTitulo("");
                    activarNombre("Adaptación de la novela autobiográfica\n''Un pollo con mucha hambre''");
                }
                if (segundos == 51){
                    desactivarCreditos();
                }
                if (segundos == 55){
                    activarTitulo("");
                    activarNombre("Ningún pollo fue herido durante el\ndesarrollo de este juego.");
                }
                if (segundos == 61){
                    desactivarCreditos();
                }
                if (segundos == 65){
                    activarTitulo("");
                    activarNombre("Muchas gracias por jugar.\n" +
                            "Nos vemos en el siguiente juego ^_^");
                    sonidoPollo.start();
                    botonSalir.setVisibility(View.VISIBLE);
                    pollo4.setVisibility(View.VISIBLE);
                }
            }
            public void onFinish() {
            }
        };
        cTimer.start();
    }

    public void desactivarCreditos(){
        textTitulo.setVisibility(View.INVISIBLE);
        textNombre.setVisibility(View.INVISIBLE);
    }

    public void activarTitulo(String titulo){
        textTitulo.setText(titulo);
        textTitulo.setVisibility(View.VISIBLE);
    }

    public void activarNombre(String nombre){
        textNombre.setText(nombre);
        textNombre.setVisibility(View.VISIBLE);
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
    }

    @Override
    public void onBackPressed() {
    }

    //cancel timer
    public  void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }
}