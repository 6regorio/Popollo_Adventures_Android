package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mystra77.popollo_adventures_android.clases.Heroe;
import com.mystra77.popollo_adventures_android.clases.Personaje;
import com.mystra77.popollo_adventures_android.datos.CargarDatos;

import java.util.Random;


public class ActivityPrincipal extends AppCompatActivity {
    private Heroe heroe;
    private Intent intent;
    private CargarDatos cargarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Eliminando barra de estado
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_principal);

        //Cargando Heroe
        cargarDatos = new CargarDatos();
        heroe = (Heroe) getIntent().getSerializableExtra("heroe");
        if (heroe == null) {
            heroe = cargarDatos.cargarHeroe();
        }
    }

    public void combatir(View view) {
        int aleatorio = 0;
        intent = new Intent(this, ActivityCombate.class);
        intent.putExtra("heroe", heroe);
        if (heroe.getExplorar() >= 5 && heroe.getExplorar() < 9) {
            aleatorio = new Random().nextInt(2);
        } else if (heroe.getExplorar() >= 9 && heroe.getExplorar() < 12) {
            aleatorio = new Random().nextInt(3);
        } else if (heroe.getExplorar() >= 12 && heroe.getExplorar() < 16) {
            aleatorio = new Random().nextInt(4);
        } else if (heroe.getExplorar() >= 16) {
            aleatorio = new Random().nextInt(5);
        }
            intent.putExtra("seleccionEnemigo", aleatorio);
            startActivity(intent);
            finish();
    }

    public void salirAlMenu(View view) {
        final Intent intent = new Intent(this, MainActivity.class);
        new AlertDialog.Builder(this)
                .setMessage(R.string.irAMenuPrincipal)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        moveTaskToBack(true);
                        finish();
                        startActivity(intent);
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

}