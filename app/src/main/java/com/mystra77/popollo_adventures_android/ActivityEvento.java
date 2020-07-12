package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mystra77.popollo_adventures_android.clases.Heroe;

public class ActivityEvento extends AppCompatActivity {
    private Heroe heroe;
    private int numeroEvento, lengthMusic;
    private MediaPlayer sonidoFondo, sonidoSeleccion;
    private TextView textoEvento;
    private Button botonEvento1, botonEvento2, botonEvento3, botonEventoContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_evento);

        heroe = (Heroe) getIntent().getSerializableExtra("heroe");
        numeroEvento = (int) getIntent().getSerializableExtra("seleccionEvento");

        sonidoFondo = MediaPlayer.create(this, R.raw.fondo_evento);
        sonidoFondo.setLooping(true);
        sonidoFondo.setVolume(0.7f, 0.7f);
        sonidoFondo.start();
        sonidoSeleccion = MediaPlayer.create(this, R.raw.sonido_evento);

        textoEvento = findViewById(R.id.textViewEvento);
        botonEvento1 = findViewById(R.id.btnEvento1);
        botonEvento2 = findViewById(R.id.btnEvento2);
        botonEvento3 = findViewById(R.id.btnEvento3);
        botonEventoContinuar = findViewById(R.id.btnEventoContinuar);

        seleccionEvento();
    }

    public void seleccionEvento(){
        if (numeroEvento == 0){
            textoEvento.setText("Encuentras un hombre tirado en el camino y por su aspecto no parece" +
                    " que este gozando de buena salud. ¿Que decides hacer?");
            botonEvento1.setText("Pasar de largo");
            botonEvento2.setText("Ayudarle");
            botonEvento3.setText("Robar sus pocas pertenencias");
        }
        if (numeroEvento == 1){
            textoEvento.setText("Escuchas a lo lejos el sonido de una multitud gritando, " +
                    "puedes observar como un enorme monstruo se acerca a ellos.");
            botonEvento1.setText("Pasar de largo");
            botonEvento2.setText("Atacar al monstruo");
            botonEvento3.setText("Aprovechar el momento de confusión y robar a los aldeanos");
        }
        if (numeroEvento == 2){
            textoEvento.setText("Cerca de unas ruinas encuentras un Golem persiguiendo a una jovenzuela."
                    + " Al lado de ambos puedes ver un cofre que llama mucho tu atención.");
            botonEvento1.setText("Pasar de largo");
            botonEvento2.setText("Te enfrentas al Golem para salvar a la joven");
            botonEvento3.setText("Aprovechas para abrir el cofre y largarte de allí");
        }
        if (numeroEvento == 3){
            textoEvento.setText("Encuentras una madriguera de crías Poring llena de gemas.\n"
                    + "Dentro un pequeño poring te mira con entusiasmo.");
            botonEvento1.setText("Pasar de largo");
            botonEvento2.setText("Saquear el nido");
            botonEvento3.setText("Te paras y lo acaricias");

        }
        if (numeroEvento == 4){
            textoEvento.setText("Escuchas una voz que proviene del fondo de un lago cercano, " +
                    "esta te avisa del reto que te espera mas adelante y te ofrece un deseo " +
                    "por el esfuerzo realizado hasta ahora.");
            botonEvento1.setText("Aumentar Salud");
            botonEvento2.setText("Aumentar Mana");
            botonEvento3.setText("Aumentar Fuerza");
        }
    }

    public void opcionEvento1(View view) {
        desactivarBotones();
        sonidoSeleccion.start();
        if (numeroEvento == 0){
            textoEvento.setText("El hombre te observa tristemente mientras te alejas.");
        }
        if (numeroEvento == 1){
            textoEvento.setText("Sigues tu camino hasta que los gritos dejan de escucharse.");
        }
        if (numeroEvento == 2){
            textoEvento.setText("Sigues tu camino dejando atrás a la pobre muchacha.");
        }
        if (numeroEvento == 3){
            textoEvento.setText("Sigues tu camino dejando atras la madriguera.");
        }
        if (numeroEvento == 4){
            textoEvento.setText("Una luz aparece del centro del lago y te impacta directamente en el corazón.\n\n"
                    + "* La salud máxima aumenta 35 puntos *");
            heroe.setSaludMaxima(heroe.getSaludMaxima() + 35);
            heroe.setSalud(heroe.getSalud() + 35);
        }
    }

    public void opcionEvento2(View view) {
        sonidoSeleccion.start();
        if (numeroEvento == 0){
            desactivarBotones();
            textoEvento.setText("Te acercas al hombre y le ayudas a levantarse, te comenta que hace "
                    + "días que no ha podido comer.\n\n"
                    + "Le ofreces un poco de tu comida y habláis amistosamente durante un rato.\n\n"
                    + "* Restableces todos los puntos de salud *");
            heroe.setSalud(heroe.getSaludMaxima());
            heroe.setReputacion(heroe.getReputacion() + 25);
        }
        if (numeroEvento == 1){
            heroe.setReputacion(heroe.getReputacion() + 25);
            comenzarCombate(1);
        }
        if (numeroEvento == 2){
            heroe.setReputacion(heroe.getReputacion() + 25);
            comenzarCombate(2);
        }
        if (numeroEvento == 3){
            heroe.setReputacion(heroe.getReputacion() - 25);
            comenzarCombate(4);
        }
        if (numeroEvento == 4){
            desactivarBotones();
            textoEvento.setText("Una luz aparece del centro del lago y te impacta directamente en el corazón.\n\n"
                    + "* El mana máximo aumenta 10 puntos *");
            heroe.setManaMaximo(heroe.getManaMaximo() + 10);
            heroe.setMana(heroe.getMana() + 10);
        }
    }

    public void opcionEvento3(View view) {
        desactivarBotones();
        sonidoSeleccion.start();
        if (numeroEvento == 0){
            textoEvento.setText("Buscas en sus bolsillos mientras intenta defenderse aunque no tiene " +
                    "fuerzas para ello.\n\n"
                    + "* Recibes 500 monedas de oro *\n\n"
                    + "* El atributo agilidad aumenta 1 punto *");
            heroe.setAgilidad(heroe.getAgilidad() + 1);
            heroe.setDinero(heroe.getDinero() + 500);
            heroe.setReputacion(heroe.getReputacion() - 25);
        }
        if (numeroEvento == 1){
            textoEvento.setText("Aprovechas el alboroto causado por el ataque del monstruo y saqueas " +
                    "todo lo que puedes.\n\n* Recibes 1000 monedas de oro *\n\n"
                    + "* El atributo agilidad aumenta 1 punto *");
            heroe.setDinero(heroe.getDinero() + 1000);
            heroe.setAgilidad(heroe.getAgilidad() + 1);
            heroe.setReputacion(heroe.getReputacion() - 25);
        }
        if (numeroEvento == 2){
            textoEvento.setText("Abres el cofre y encuentras una gran cantidad de monedas.\n\n"
                    + "* Recibes 1500 monedas de oro *");
            heroe.setDinero(heroe.getDinero() + 1500);
            heroe.setReputacion(heroe.getReputacion() - 25);
        }
        if (numeroEvento == 3){
            textoEvento.setText("El pequeño poring empieza a darte mimitos y notas una gran " +
                    "calidez en tu corazón.\n\n"
                    + "* El atributo magia aumenta 2 puntos *");
            heroe.setMagia(heroe.getMagia() + 2);
            heroe.setReputacion(heroe.getReputacion() + 25);
        }
        if (numeroEvento == 4){
            textoEvento.setText("Una luz aparece del centro del lago y te impacta directamente en el corazón.\n\n"
                    + "* El atributo fuerza aumenta 10 puntos *");
            heroe.setFuerza(heroe.getFuerza() + 10);
        }
    }

    public void desactivarBotones(){
        botonEventoContinuar.setVisibility(View.VISIBLE);
        botonEvento1.setVisibility(View.GONE);
        botonEvento2.setVisibility(View.GONE);
        botonEvento3.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sonidoFondo.pause();
        lengthMusic = sonidoFondo.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sonidoFondo.seekTo(lengthMusic);
        sonidoFondo.start();
        sonidoFondo.setLooping(true);
    }

    public void onDestroy() {
        super.onDestroy();
        if (sonidoFondo != null) {
            sonidoFondo.stop();
            sonidoFondo.release();
        }
        if (sonidoSeleccion != null) {
            sonidoSeleccion.stop();
            sonidoSeleccion.release();
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void continuarEvento(View view) {
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

    public void comenzarCombate(final int numeroCombate){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.entrarEnCombate);
        final Dialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        final Intent intent = new Intent(this, ActivityCombate.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
                intent.putExtra("heroe", heroe);
                intent.putExtra("seleccionEnemigo", numeroCombate);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}