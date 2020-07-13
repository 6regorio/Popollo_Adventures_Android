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

public class ActivityDescanso extends AppCompatActivity {
    private Heroe heroe;
    private TextView dineroDescanso;
    private Button botonMediaCuracion, botonFullCuracion;
    private MediaPlayer sonidoCuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_descanso);

        dineroDescanso = findViewById(R.id.textViewDineroDescanso);
        botonMediaCuracion = findViewById(R.id.btnMediaCuracion);
        botonFullCuracion = findViewById(R.id.btnCuracionCompleta);

        sonidoCuracion = MediaPlayer.create(this, R.raw.sonido_curacion);
        heroe = (Heroe) getIntent().getSerializableExtra("heroe");
        mostrarDinero();

    }

    public void mostrarDinero(){
        dineroDescanso.setText("ORO: " + heroe.getDinero());
        if (heroe.getDinero() < 250){
            botonMediaCuracion.setEnabled(false);
        }
        if(heroe.getDinero() < 500){
            botonFullCuracion.setEnabled(false);
        }
    }

    public void curarHeridas(View view) {
        heroe.setDinero(heroe.getDinero() - 250);
        heroe.setSalud(heroe.getSaludMaxima());
        mensajeCuracion(R.string.curacionRecibida);
    }

    public void curacionCompleta(View view) {
        heroe.setDinero(heroe.getDinero() - 500);
        heroe.setSalud(heroe.getSaludMaxima());
        heroe.setMana(heroe.getManaMaximo());
        mensajeCuracion(R.string.curacionRecibida);
    }

    public void mensajeCuracion(int mensaje) {
        mostrarDinero();
        sonidoCuracion.start();
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
            }
        }, 2000);
    }

    public void salirDescanso(View view) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.salirDelDescanso)
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
                Intent intent = new Intent(ActivityDescanso.this, ActivityPrincipal.class);
                intent.putExtra("heroe", heroe);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

}