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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mystra77.popollo_adventures_android.clases.Heroe;

public class ActivityAfinidad extends AppCompatActivity {
    private Heroe heroe;
    private MediaPlayer musicaFondo, sonido, recibirPremio;
    private LinearLayout cajaPremio;
    private TextView textoPremio;
    private ImageView imagenFondo;
    private int lengthMusic;
    private CountDownTimer cTimer;
    private int segundos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_afinidad);

        cajaPremio = findViewById(R.id.cajaEventoEspecial);
        textoPremio = findViewById(R.id.textViewEventoEspecial);
        imagenFondo = findViewById(R.id.imageViewFondoAfinidad);
        cTimer = null;
        segundos = 0;

        heroe = (Heroe) getIntent().getSerializableExtra("heroe");

        musicaFondo = MediaPlayer.create(this, R.raw.fondo_evento);
        musicaFondo.setLooping(true);
        musicaFondo.setVolume(0.7f, 0.7f);
        musicaFondo.start();

        sonido = MediaPlayer.create(this, R.raw.sonido_evento);
        recibirPremio = MediaPlayer.create(this, R.raw.sonido_premio);

        startTimer();
    }


    public void seleccionarEvento() {
        if (heroe.getReputacion() > 25 && heroe.getReputacion() <= 50) {
            evento(1);
        } else if (heroe.getReputacion() > 50) {
            evento(2);
        } else if (heroe.getReputacion() <= 25 && heroe.getReputacion() >= -25) {
            evento(3);
        } else if (heroe.getReputacion() < -25 && heroe.getReputacion() >= -50) {
            evento(4);
        } else {
            evento(5);
        }
    }

    public void evento(int numero) {
        if (numero == 1) {
            if (segundos == 4) {
                imagenFondo.setImageResource(R.drawable.legal_1);
            }
            if (segundos == 9) {
                imagenFondo.setImageResource(R.drawable.caotico_2);
            }
            if (segundos == 14) {
                imagenFondo.setImageResource(R.drawable.legal_3);
            }
            if (segundos == 19) {
                imagenFondo.setImageResource(R.drawable.legal_4);
            }
            if (segundos == 24) {
                imagenFondo.setImageResource(R.drawable.afinidad);
                cajaPremio.setVisibility(View.VISIBLE);
                textoPremio.setText("* Recibes 1000 monedas de oro *" +
                        "\n\n* La salud máxima aumenta 15 puntos *");
                heroe.setSaludMaxima(heroe.getSaludMaxima() + 15);
                heroe.setSalud(heroe.getSalud() + 15);
                heroe.setDinero(heroe.getDinero() + 1000);
                recibirPremio.start();
            }
        }
        if (numero == 2) {
            if (segundos == 4) {
                imagenFondo.setImageResource(R.drawable.super_legal_1);
            }
            if (segundos == 9) {
                imagenFondo.setImageResource(R.drawable.caotico_2);
            }
            if (segundos == 14) {
                imagenFondo.setImageResource(R.drawable.super_legal_3);
            }
            if (segundos == 19) {
                imagenFondo.setImageResource(R.drawable.legal_4);
            }
            if (segundos == 24) {
                imagenFondo.setImageResource(R.drawable.afinidad);
                cajaPremio.setVisibility(View.VISIBLE);
                textoPremio.setText("* Recibes 2000 monedas de oro *\n\n* " +
                        "La salud máxima aumenta 30 puntos *\n\n* " +
                        "El atributo defensa aumenta 5 puntos *");
                heroe.setSaludMaxima(heroe.getSaludMaxima() + 30);
                heroe.setSalud(heroe.getSalud() + 30);
                heroe.setDinero(heroe.getDinero() + 2000);
                heroe.setDefensa(heroe.getDefensa() + 5);
                recibirPremio.start();
            }
        }
        if (numero == 3) {
            if (segundos == 4) {
                imagenFondo.setImageResource(R.drawable.neutral_1);
            }
            if (segundos == 9) {
                imagenFondo.setImageResource(R.drawable.neutral_2);
            }
            if (segundos == 14) {
                imagenFondo.setImageResource(R.drawable.neutral_3);
            }
            if (segundos == 19) {
                imagenFondo.setImageResource(R.drawable.neutral_4);
            }
            if (segundos == 24) {
                imagenFondo.setImageResource(R.drawable.afinidad);
                cajaPremio.setVisibility(View.VISIBLE);
                textoPremio.setText("* La salud máxima aumenta 10 puntos *\n\n* " +
                        "El mana máximo aumenta 5 puntos *\n\n" +
                        "* El atributo fuerza aumenta 5 puntos *");
                heroe.setSaludMaxima(heroe.getSaludMaxima() + 10);
                heroe.setSalud(heroe.getSalud() + 10);
                heroe.setManaMaximo(heroe.getManaMaximo() + 5);
                heroe.setMana(heroe.getMana() + 5);
                heroe.setFuerza(heroe.getFuerza() + 5);
                recibirPremio.start();
            }
        }
        if (numero == 4) {
            if (segundos == 4) {
                imagenFondo.setImageResource(R.drawable.caotico_1);
            }
            if (segundos == 7) {
                imagenFondo.setImageResource(R.drawable.caotico_2);
            }
            if (segundos == 11) {
                imagenFondo.setImageResource(R.drawable.caotico_3);
            }
            if (segundos == 15) {
                imagenFondo.setImageResource(R.drawable.caotico_4);
            }
            if (segundos == 19) {
                imagenFondo.setImageResource(R.drawable.afinidad);
                cajaPremio.setVisibility(View.VISIBLE);
                textoPremio.setText("* Recibes 1000 monedas de oro *\n\n" +
                        "* El mana máximo aumenta 10 puntos *");
                heroe.setManaMaximo(heroe.getManaMaximo() + 10);
                heroe.setMana(heroe.getMana() + 10);
                heroe.setDinero(heroe.getDinero() + 1000);
                recibirPremio.start();
            }
        }
        if (numero == 5) {
            if (segundos == 4) {
                imagenFondo.setImageResource(R.drawable.super_caotico_1);
            }
            if (segundos == 7) {
                imagenFondo.setImageResource(R.drawable.super_caotico_2);
            }
            if (segundos == 11) {
                imagenFondo.setImageResource(R.drawable.super_caotico_3);
            }
            if (segundos == 15) {
                imagenFondo.setImageResource(R.drawable.caotico_4);
            }
            if (segundos == 19) {
                imagenFondo.setImageResource(R.drawable.afinidad);
                cajaPremio.setVisibility(View.VISIBLE);
                textoPremio.setText("* Recibes 1500 monedas de oro *\n\n" +
                        "* El mana máximo aumenta 10 puntos *\n\n" +
                        "* El atributo magia aumenta 1 punto *");
                heroe.setManaMaximo(heroe.getManaMaximo() + 10);
                heroe.setMana(heroe.getMana() + 10);
                heroe.setMagia(heroe.getMagia() + 1);
                heroe.setDinero(heroe.getDinero() + 1500);
                recibirPremio.start();
            }
        }
    }

    public void salirEventoEspecial(View view) {
        mensajeSalirEvento();
    }

    public void mensajeSalirEvento() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.salirEvento);
        final Dialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        final Intent intent = new Intent(this, ActivityPrincipal.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
                intent.putExtra("heroe", heroe);
                startActivity(intent);
                finish();
            }
        }, 2000);
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
        if (sonido != null) {
            sonido.stop();
            sonido.release();
        }

        if (recibirPremio != null) {
            recibirPremio.stop();
            recibirPremio.release();
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
                seleccionarEvento();
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

