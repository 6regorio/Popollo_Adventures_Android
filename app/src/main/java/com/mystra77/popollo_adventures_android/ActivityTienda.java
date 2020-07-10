package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import javax.net.ssl.HandshakeCompletedEvent;

public class ActivityTienda extends AppCompatActivity {
    private Button comprarObjeto1, comprarObjeto2, comprarObjeto3, botonFuerza, botonMagia,
            botonDefensa, botonAgilidad;
    private TextView dineroTienda;
    private Heroe heroe;
    private MediaPlayer sonidoCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tienda);

        comprarObjeto1 = findViewById(R.id.btnObjeto1);
        comprarObjeto2 = findViewById(R.id.btnObjeto2);
        comprarObjeto3 = findViewById(R.id.btnObjeto3);
        botonFuerza = findViewById(R.id.btnFuerza);
        botonMagia = findViewById(R.id.btnMagia);
        botonDefensa = findViewById(R.id.btnDefensa);
        botonAgilidad = findViewById(R.id.btnAgilidad);
        dineroTienda = findViewById(R.id.textViewDineroTienda);

        heroe = (Heroe) getIntent().getSerializableExtra("heroe");
        sonidoCompra = MediaPlayer.create(this, R.raw.sonido_comprar);
        activarCompras();
    }



    public void salirDeTienda(View view) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.salirDeLaTienda)
                .setCancelable(false)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showAlert(R.string.mensajeSalirTienda);
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

    public void showAlert(int mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje);
        final Dialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
                Intent intent = new Intent(ActivityTienda.this, ActivityPrincipal.class);
                intent.putExtra("heroe", heroe);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    public void activarCompras(){
        dineroTienda.setText("ORO: " + heroe.getDinero());
        comprarObjeto1.setText(" (" + heroe.getObjetosArray().get(0).getCantidad() + ") " + heroe.getObjetosArray().get(0).getNombre() +
                " +1 - Precio: " + heroe.getObjetosArray().get(0).getPrecio());
        comprarObjeto2.setText(" (" + heroe.getObjetosArray().get(1).getCantidad() + ") " + heroe.getObjetosArray().get(1).getNombre() +
                " +1 - Precio: " + heroe.getObjetosArray().get(1).getPrecio());
        comprarObjeto3.setText(" (" + heroe.getObjetosArray().get(2).getCantidad() + ") " + heroe.getObjetosArray().get(2).getNombre() +
                " +1 - Precio: " + heroe.getObjetosArray().get(2).getPrecio());
        botonFuerza.setText(" (" + heroe.getFuerza() + ") Fuerza +5 - Precio: 750");
        botonMagia.setText(" (" + heroe.getMagia() + ") Magia +1 - Precio: 1000");
        botonDefensa.setText(" (" + heroe.getDefensa() + ") Defensa +2 - Precio: 750");
        botonAgilidad.setText(" (" + heroe.getAgilidad() + ") Agilidad +2 - Precio: 750");
        if(heroe.getDinero() < 150){
            comprarObjeto1.setEnabled(false);
        }
        if(heroe.getDinero() < 250){
            comprarObjeto3.setEnabled(false);
        }
        if(heroe.getDinero() < 400){
            comprarObjeto2.setEnabled(false);
        }
        if (heroe.getDinero() < 750){
            botonFuerza.setEnabled(false);
            botonDefensa.setEnabled(false);
            botonAgilidad.setEnabled(false);
        }
        if (heroe.getDinero() < 1000){
            botonMagia.setEnabled(false);
        }
    }

    public void mensajeAgradecimiento() {
        sonidoCompra.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.mensajeAgradecimientoCompra);
        final Dialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        activarCompras();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, 1000);
    }

    public void comprarObjeto1(View view) {
        heroe.setDinero(heroe.getDinero() - 150);
        heroe.getObjetosArray().get(0).setCantidad(heroe.getObjetosArray().get(0).getCantidad() + 1);
        mensajeAgradecimiento();
    }

    public void comprarObjeto2(View view) {
        heroe.setDinero(heroe.getDinero() - 400);
        heroe.getObjetosArray().get(1).setCantidad(heroe.getObjetosArray().get(1).getCantidad() + 1);
        mensajeAgradecimiento();
    }

    public void comprarObjeto3(View view) {
        heroe.setDinero(heroe.getDinero() - 250);
        heroe.getObjetosArray().get(2).setCantidad(heroe.getObjetosArray().get(2).getCantidad() + 1);
        mensajeAgradecimiento();
    }

    public void mejorarFuerza(View view){
        heroe.setDinero(heroe.getDinero() - 750);
        heroe.setFuerza(heroe.getFuerza() + 5);
        mensajeAgradecimiento();
    }

    public void mejorarMagia(View view){
        heroe.setDinero(heroe.getDinero() - 1000);
        heroe.setMagia(heroe.getMagia() + 1);
        mensajeAgradecimiento();
    }

    public void mejorarDefensa(View view){
        heroe.setDinero(heroe.getDinero() - 750);
        heroe.setDefensa(heroe.getDefensa() + 2);
        mensajeAgradecimiento();
    }


    public void mejorarAgilidad(View view){
        heroe.setDinero(heroe.getDinero() - 750);
        heroe.setAgilidad(heroe.getAgilidad() + 2);
        mensajeAgradecimiento();
    }

    @Override
    public void onBackPressed() {
    }

    public void onDestroy() {
        super.onDestroy();
        if (sonidoCompra != null) {
            sonidoCompra.stop();
            sonidoCompra.release();
        }
    }
}