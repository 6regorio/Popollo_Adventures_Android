package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ActivityCombate extends AppCompatActivity {
    ImageView imagenHeroeCombate, imagenEnemigoCombate;
    TextView saludHeroeCombate, manaHeroeCombate, saludEnemigoCombate, manaEnemigoCombate;
    ProgressBar barraSaludHeroe, barraManaHeroe, barraSaludEnemigo, barraManaEnemigo;
    ListView combatLog;
    ArrayAdapter<String> adapterLog;
    ArrayList<String> logsLines;
    Button botonAtaque, botonDefensa, botonHabilidad, botonObjeto, botonHabilidad1, botonHabilidad2, botonHabilidad3, botonObjeto1, botonObjeto2, botonObjeto3, botonVolverHabilidades, botonVolverObjetos;
    LinearLayout cajaBotonesPrincipales, cajaBotonesHabilidades, cajaBotonesObjetos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Eliminando barra de estado
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_combate);

        cajaBotonesPrincipales = findViewById(R.id.LinearLayoutBotonesPrincipales);
        cajaBotonesHabilidades = findViewById(R.id.LinearLayoutHabilidades);
        cajaBotonesObjetos = findViewById(R.id.LinearLayoutObjetos);

        botonAtaque = findViewById(R.id.btnAtacar);
        botonDefensa = findViewById(R.id.btnDefender);
        botonHabilidad = findViewById(R.id.btnHabilidades);
        botonObjeto = findViewById(R.id.btnObjetos);
        botonHabilidad1 = findViewById(R.id.btnHabilidad1);
        botonHabilidad2 = findViewById(R.id.btnHabilidad2);
        botonHabilidad3 = findViewById(R.id.btnHabilidad3);
        botonVolverHabilidades = findViewById(R.id.btnVolverHabilidades);
        botonObjeto1 = findViewById(R.id.btnObjeto1);
        botonObjeto2 = findViewById(R.id.btnObjeto2);
        botonObjeto3 = findViewById(R.id.btnObjeto3);
        botonVolverObjetos = findViewById(R.id.btnVolverObjetos);

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

        combatLog = findViewById(R.id.listViewCombatLog);
        logsLines = new ArrayList<String>();
        adapterLog = new ArrayAdapter<String>(this, R.layout.log_adapter, logsLines);
        combatLog.setAdapter(adapterLog);
        combatLog.setEnabled(false);

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

    public void comandoAtacar(View view) {
        barraSaludEnemigo.setProgress(barraSaludEnemigo.getProgress() - 10);
        saludEnemigoCombate.setText(barraSaludEnemigo.getProgress()+"/"+barraSaludEnemigo.getMax());
        logsLines.add("Recibe 10");
        adapterLog.notifyDataSetChanged();
    }

    public void comandoDefender(View view) {
        barraSaludEnemigo.setProgress(barraSaludEnemigo.getProgress() - 20);
        saludEnemigoCombate.setText(barraSaludEnemigo.getProgress()+"/"+barraSaludEnemigo.getMax());
        logsLines.add("Recibe 20");
        adapterLog.notifyDataSetChanged();
    }

    public void comandoHabilidades(View view) {
        cajaBotonesHabilidades.setVisibility(View.VISIBLE);
        cajaBotonesPrincipales.setVisibility(View.GONE);
    }

    public void comandoObjetos(View view) {
        cajaBotonesObjetos.setVisibility(View.VISIBLE);
        cajaBotonesPrincipales.setVisibility(View.GONE);
    }

    public void comandoVolver(View view) {
        cajaBotonesPrincipales.setVisibility(View.VISIBLE);
        cajaBotonesHabilidades.setVisibility(View.GONE);
        cajaBotonesObjetos.setVisibility(View.GONE);
    }

}