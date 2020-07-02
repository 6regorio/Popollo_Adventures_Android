package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ActivityCombate extends AppCompatActivity {
    ImageView imagenHeroeCombate, imagenEnemigoCombate;
    TextView saludHeroeCombate, manaHeroeCombate, saludEnemigoCombate, manaEnemigoCombate;
    ProgressBar barraSaludHeroe, barraManaHeroe, barraSaludEnemigo, barraManaEnemigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Eliminando barra de estado
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_combate);

        imagenHeroeCombate = findViewById(R.id.imagenHeroe);
        imagenEnemigoCombate = findViewById(R.id.imagenEnemigo);
        saludHeroeCombate = findViewById(R.id.textViewSaludHeroe);
        manaHeroeCombate = findViewById(R.id.textViewManaHeroe);
        barraSaludHeroe = findViewById(R.id.progressBarSaludHeroe);
        barraManaHeroe = findViewById(R.id.progressBarManaHeroe);
        saludEnemigoCombate = findViewById(R.id.textViewSaludEnemigo);
        manaEnemigoCombate = findViewById(R.id.textViewManaEnemigo);
        barraSaludEnemigo = findViewById(R.id.progressBarSaludEnemigo);
        barraManaEnemigo = findViewById(R.id.progressBarManaEnemigo);

        Glide.with(this).load(R.drawable.combat_popollo).into(imagenHeroeCombate);
        Glide.with(this).load(R.drawable.combat_poi).into(imagenEnemigoCombate);

        barraSaludHeroe.setMax(100);
        barraSaludHeroe.setProgress(100);
        saludHeroeCombate.setText(barraSaludHeroe.getProgress() + "/" + barraSaludHeroe.getMax());
        barraManaHeroe.setMax(100);
        barraManaHeroe.setProgress(100);
        manaHeroeCombate.setText(barraSaludHeroe.getProgress() + "/" + barraSaludHeroe.getMax());

        barraSaludEnemigo.setMax(100);
        barraSaludEnemigo.setProgress(100);
        saludEnemigoCombate.setText(barraSaludHeroe.getProgress() + "/" + barraSaludHeroe.getMax());
        barraManaEnemigo.setMax(100);
        barraManaEnemigo.setProgress(100);
        manaEnemigoCombate.setText(barraSaludEnemigo.getProgress() + "/" + barraSaludEnemigo.getMax());
    }
}