package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mystra77.popollo_adventures_android.clases.Enemigo;
import com.mystra77.popollo_adventures_android.clases.Heroe;
import com.mystra77.popollo_adventures_android.datos.CargarDatos;

import java.util.ArrayList;
import java.util.Random;

public class ActivityCombate extends AppCompatActivity {
    private ImageView imagenHeroeCombate, imagenEnemigoCombate;
    private TextView saludHeroeCombate, manaHeroeCombate, saludEnemigoCombate, manaEnemigoCombate,
            textoVictoriaDerrota, textoAtributosHeroe, textoAtributosEnemigo, textoNombres;
    private ProgressBar barraSaludHeroe, barraManaHeroe, barraSaludEnemigo, barraManaEnemigo;
    private ListView combatLog;
    private ArrayAdapter<String> adapterLog;
    private ArrayList<String> logsLines;
    private Button botonAtaque, botonHabilidad, botonObjeto, botonHabilidad1, botonHabilidad2,
            botonHabilidad3, botonObjeto1, botonObjeto2, botonObjeto3, botonVictoria, botonDerrota;
    private ConstraintLayout cajaVictoriaDerrota;
    private LinearLayout cajaBotonesPrincipales, cajaBotonesHabilidades, cajaBotonesObjetos;
    private Heroe heroe;
    private Enemigo enemigo;
    private Intent intent;
    private int seleccionEnemigo, lengthMusic;
    private CargarDatos cargarDatos;
    private Handler handler;
    private boolean turnoHeroe, activarInfoHeroe, activarInfoEnemigo;
    private MediaPlayer musicaFondo;
    private MediaPlayer sonidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Eliminando barra de estado
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_combate);

        cajaVictoriaDerrota = findViewById(R.id.layoutVictoriaDerrota);
        cajaBotonesPrincipales = findViewById(R.id.LinearLayoutBotonesPrincipales);
        cajaBotonesHabilidades = findViewById(R.id.LinearLayoutHabilidades);
        cajaBotonesObjetos = findViewById(R.id.LinearLayoutObjetos);

        botonAtaque = findViewById(R.id.btnAtacar);
        botonHabilidad = findViewById(R.id.btnHabilidades);
        botonObjeto = findViewById(R.id.btnObjetos);
        botonHabilidad1 = findViewById(R.id.btnHabilidad1);
        botonHabilidad2 = findViewById(R.id.btnHabilidad2);
        botonHabilidad3 = findViewById(R.id.btnHabilidad3);
        botonObjeto1 = findViewById(R.id.btnObjeto1);
        botonObjeto2 = findViewById(R.id.btnObjeto2);
        botonObjeto3 = findViewById(R.id.btnObjeto3);
        botonVictoria = findViewById(R.id.btnVictoria);
        botonDerrota = findViewById(R.id.btnDerrota);

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
        textoVictoriaDerrota = findViewById(R.id.textViewVictoriaDerrota);
        textoAtributosHeroe = findViewById(R.id.textViewAtributosHeroe);
        textoAtributosEnemigo = findViewById(R.id.textViewAtributosEnemigo);
        textoNombres = findViewById(R.id.textViewNombres);

        combatLog = findViewById(R.id.listViewCombatLog);
        logsLines = new ArrayList<String>();
        adapterLog = new ArrayAdapter<String>(this, R.layout.log_adapter, logsLines);
        combatLog.setAdapter(adapterLog);
        combatLog.setEnabled(false);

        cargarDatos = new CargarDatos();
        heroe = (Heroe) getIntent().getSerializableExtra("heroe");
        cargarMenuHeroeHabilidades();
        cargarMenuHeroeObjetos();
        Glide.with(this).load(heroe.getImagenCombate()).into(imagenHeroeCombate);


        seleccionEnemigo = (int) getIntent().getSerializableExtra("seleccionEnemigo");
        enemigo = encontrarEnemigo(seleccionEnemigo);
        Glide.with(this).load(enemigo.getImagenCombate()).into(imagenEnemigoCombate);

        modificarBarrasSaludMana();
        handler = new Handler();
        turnoHeroe = true;
        activarInfoHeroe = true;
        activarInfoEnemigo = true;

        textoAtributosHeroe.setText("Fuerza: " + heroe.getFuerza() + "\n" +
                "Magia: " + heroe.getMagia() + "\n" +
                "Defensa: " + heroe.getDefensa() + "\n" +
                "Agilidad: " + heroe.getAgilidad());

        textoAtributosEnemigo.setText("Fuerza: " + enemigo.getFuerza() + "\n" +
                "Magia: " + enemigo.getMagia() + "\n" +
                "Defensa: " + enemigo.getDefensa() + "\n" +
                "Agilidad: " + enemigo.getAgilidad());

        musicaFondo = MediaPlayer.create(this, R.raw.fondo_combate);
        musicaFondo.setVolume(0.5f, 0.5f);
        musicaFondo.setLooping(true);
        musicaFondo.start();
        sonidos = MediaPlayer.create(this, R.raw.sonido_ataque);

        textoNombres.setText(heroe.getNombre() + " VS " + enemigo.getNombre());
    }


    public void comandoAtacar(View view) {
        logsLines.add(heroe.atacarObjetivo(enemigo));
        turnoHeroe = false;
        handler.postDelayed(new Runnable() {
            public void run() {
                sonidoAtacar();
            }
        }, 100);
        modificarBarrasSaludMana();
        condicionVictoriaDerrota();
    }

    public void sonidoAtacar() {
        sonidos.release();
        sonidos = MediaPlayer.create(this, R.raw.sonido_ataque);
        sonidos.setVolume(0.5f, 0.5f);
        sonidos.start();
    }

    public void comandoHabilidades(View view) {
        botonHabilidad1.setEnabled(true);
        botonHabilidad2.setEnabled(true);
        botonHabilidad3.setEnabled(true);
        cajaBotonesHabilidades.setVisibility(View.VISIBLE);
        cajaBotonesPrincipales.setVisibility(View.GONE);
        if (heroe.getMana() < heroe.getHabilidadesArray().get(0).getCoste()) {
            botonHabilidad1.setEnabled(false);
        }
        if (heroe.getMana() < heroe.getHabilidadesArray().get(1).getCoste()) {
            botonHabilidad2.setEnabled(false);
        }
        if (heroe.getMana() < heroe.getHabilidadesArray().get(2).getCoste()) {
            botonHabilidad3.setEnabled(false);
        }
    }

    public void lanzarHabilidad1(View view) {
        logsLines.add(heroe.lanzarHechizo(enemigo, 0));
        turnoHeroe = false;
        handler.postDelayed(new Runnable() {
            public void run() {
                sonidoExplosion();
            }
        }, 100);
        modificarBarrasSaludMana();
        reiniciarMenu();
        condicionVictoriaDerrota();
    }

    public void lanzarHabilidad2(View view) {
        logsLines.add(heroe.lanzarHechizo(enemigo, 1));
        turnoHeroe = false;
        handler.postDelayed(new Runnable() {
            public void run() {
                sonidoExplosion();
            }
        }, 100);
        modificarBarrasSaludMana();
        reiniciarMenu();
        condicionVictoriaDerrota();
    }

    public void lanzarHabilidad3(View view) {
        logsLines.add(heroe.lanzarHechizo(enemigo, 2));
        turnoHeroe = false;
        handler.postDelayed(new Runnable() {
            public void run() {
                sonidoCuracion();
            }
        }, 100);
        modificarBarrasSaludMana();
        reiniciarMenu();
        condicionVictoriaDerrota();
    }

    public void sonidoExplosion() {
        sonidos.release();
        sonidos = MediaPlayer.create(this, R.raw.sonido_explosion);
        sonidos.start();
    }

    public void sonidoCuracion() {
        sonidos.release();
        sonidos = MediaPlayer.create(this, R.raw.sonido_curacion);
        sonidos.start();
    }

    public void comandoObjetos(View view) {
        botonObjeto1.setEnabled(true);
        botonObjeto2.setEnabled(true);
        botonObjeto3.setEnabled(true);
        cajaBotonesObjetos.setVisibility(View.VISIBLE);
        cajaBotonesPrincipales.setVisibility(View.GONE);
        if (heroe.getObjetosArray().get(0).getCantidad() == 0) {
            botonObjeto1.setEnabled(false);
        }
        if (heroe.getObjetosArray().get(1).getCantidad() == 0) {
            botonObjeto2.setEnabled(false);
        }
        if (heroe.getObjetosArray().get(2).getCantidad() == 0) {
            botonObjeto3.setEnabled(false);
        }
    }

    public void lanzarObjecto1(View view) {
        logsLines.add(heroe.lanzarObjeto(enemigo, 0));
        turnoHeroe = false;
        handler.postDelayed(new Runnable() {
            public void run() {
                sonidoExplosion();
            }
        }, 100);
        modificarBarrasSaludMana();
        cargarMenuHeroeObjetos();
        reiniciarMenu();
        condicionVictoriaDerrota();
    }

    public void lanzarObjecto2(View view) {
        logsLines.add(heroe.lanzarObjeto(enemigo, 1));
        turnoHeroe = false;
        handler.postDelayed(new Runnable() {
            public void run() {
                sonidoExplosion();
            }
        }, 100);
        modificarBarrasSaludMana();
        cargarMenuHeroeObjetos();
        reiniciarMenu();
        condicionVictoriaDerrota();
    }

    public void lanzarObjecto3(View view) {
        logsLines.add(heroe.lanzarObjeto(enemigo, 2));
        turnoHeroe = false;
        handler.postDelayed(new Runnable() {
            public void run() {
                sonidoCuracion();
            }
        }, 100);
        modificarBarrasSaludMana();
        cargarMenuHeroeObjetos();
        reiniciarMenu();
        condicionVictoriaDerrota();
    }

    public void comandoVolver(View view) {
        reiniciarMenu();
    }

    public Enemigo encontrarEnemigo(int Seleccion) {
        if (Seleccion == 0) {
            enemigo = cargarDatos.cargarPoring();
        } else if (Seleccion == 1) {
            enemigo = cargarDatos.cargarNigromante();
        } else if (Seleccion == 2) {
            enemigo = cargarDatos.cargarGolem();
        } else if (Seleccion == 3) {
            enemigo = cargarDatos.cargarGoblin();
        } else if (Seleccion == 4) {
            enemigo = cargarDatos.cargarDeviling();
        } else if (Seleccion == 5) {
            enemigo = cargarDatos.cargarPulpoi();
        }
        return enemigo;
    }

    public void modificarBarrasSaludMana() {
        barraSaludHeroe.setMax(heroe.getSaludMaxima());
        barraSaludHeroe.setProgress(heroe.getSalud());
        saludHeroeCombate.setText(heroe.getSalud() + "/" + heroe.getSaludMaxima());
        barraManaHeroe.setMax(heroe.getManaMaximo());
        barraManaHeroe.setProgress(heroe.getMana());
        manaHeroeCombate.setText(heroe.getMana() + "/" + heroe.getManaMaximo());
        barraSaludEnemigo.setMax(enemigo.getSaludMaxima());
        barraSaludEnemigo.setProgress(enemigo.getSalud());
        saludEnemigoCombate.setText(enemigo.getSalud() + "/" + enemigo.getSaludMaxima());
        barraManaEnemigo.setMax(enemigo.getManaMaximo());
        barraManaEnemigo.setProgress(enemigo.getMana());
        manaEnemigoCombate.setText(enemigo.getMana() + "/" + enemigo.getManaMaximo());
        adapterLog.notifyDataSetChanged();
    }

    public void reiniciarMenu() {
        cajaBotonesPrincipales.setVisibility(View.VISIBLE);
        cajaBotonesHabilidades.setVisibility(View.GONE);
        cajaBotonesObjetos.setVisibility(View.GONE);
    }

    public void cargarMenuHeroeHabilidades() {
        botonHabilidad1.setText(heroe.getHabilidadesArray().get(0).getNombre() + " \n "
                + "Daño: " + heroe.getHabilidadesArray().get(0).getPoder() * heroe.getMagia() + "\n"
                + "Coste: " + heroe.getHabilidadesArray().get(0).getCoste() + " PM");
        botonHabilidad2.setText(heroe.getHabilidadesArray().get(1).getNombre() + " \n "
                + "Daño: " + heroe.getHabilidadesArray().get(1).getPoder() * heroe.getMagia() + "\n"
                + "Coste: " + heroe.getHabilidadesArray().get(1).getCoste() + " PM");
        botonHabilidad3.setText(heroe.getHabilidadesArray().get(2).getNombre() + " \n "
                + "+" + heroe.getHabilidadesArray().get(2).getPoder() * heroe.getMagia() + " Salud\n"
                + "Coste: " + heroe.getHabilidadesArray().get(2).getCoste() + " PM");
    }

    public void cargarMenuHeroeObjetos() {
        botonObjeto1.setText(heroe.getObjetosArray().get(0).getNombre() + "\n"
                + "Daño: " + heroe.getObjetosArray().get(0).getPoder() + " \n"
                + "Cantidad: " + heroe.getObjetosArray().get(0).getCantidad());

        botonObjeto2.setText(heroe.getObjetosArray().get(1).getNombre() + "\n"
                + "Daño: " + heroe.getObjetosArray().get(1).getPoder() + " \n"
                + "Cantidad: " + heroe.getObjetosArray().get(1).getCantidad());

        botonObjeto3.setText(heroe.getObjetosArray().get(2).getNombre() + "\n"
                + "+" + heroe.getObjetosArray().get(2).getPoder() + " Mana\n"
                + "Cantidad: " + heroe.getObjetosArray().get(2).getCantidad());
    }

    public String turnoEnemigo() {
        String resultado = "";
        final int numeroAleatorio = new Random().nextInt(2);
        desactivarMenu();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (numeroAleatorio == 0) {
                    logsLines.add(enemigo.atacarObjetivo(heroe));
                    sonidoAtacar();
                }
                if (numeroAleatorio == 1) {
                    int numeroHabilidad = new Random().nextInt(2);
                    if (numeroHabilidad == 0) {
                        if (enemigo.getMana() >= enemigo.getHabilidadesArray().get(0).getCoste()) {
                            logsLines.add(enemigo.lanzarHechizo(heroe, 0));
                            sonidoExplosion();
                        } else {
                            logsLines.add(enemigo.getNombre() + " pierde la concentracion.");
                        }
                    } else if (numeroHabilidad == 1) {
                        if (enemigo.getMana() >= enemigo.getHabilidadesArray().get(1).getCoste()) {
                            logsLines.add(enemigo.lanzarHechizo(heroe, 1));
                            sonidoExplosion();
                        } else {
                            logsLines.add(enemigo.getNombre() + " pierde la concentracion.");
                        }
                    }
                }
                turnoHeroe = true;
                modificarBarrasSaludMana();
                activarMenu();
                condicionVictoriaDerrota();
            }
        }, 2000);
        return resultado;
    }

    public void desactivarMenu() {
        botonAtaque.setEnabled(false);
        botonHabilidad.setEnabled(false);
        botonObjeto.setEnabled(false);
    }

    public void activarMenu() {
        botonAtaque.setEnabled(true);
        botonHabilidad.setEnabled(true);
        botonObjeto.setEnabled(true);
    }

    public void condicionVictoriaDerrota() {
        if (enemigo.getSalud() <= 0) {
            musicaFondo.release();
            musicaFondo = MediaPlayer.create(this, R.raw.sonido_victoria);
            musicaFondo.setVolume(0.6f, 0.6f);
            musicaFondo.start();
            String resultado = "¡VICTORIA!\n\n" +
                    "** Recoges " + enemigo.getDinero() + " monedas de oro **\n" +
                    "** Recibes " + enemigo.getExperiencia() + " puntos de experiencia **\n";
            resultado += heroe.subirNivel(enemigo.getExperiencia());
            heroe.setDinero(heroe.getDinero() + enemigo.getDinero());
            textoVictoriaDerrota.setText(resultado);
            imagenEnemigoCombate.setImageResource(enemigo.getImagenMuerte());
            desactivarMenu();
            handler.postDelayed(new Runnable() {
                public void run() {
                    cajaBotonesPrincipales.setVisibility(View.INVISIBLE);
                    combatLog.setVisibility(View.INVISIBLE);
                    cajaVictoriaDerrota.setVisibility(View.VISIBLE);
                    botonVictoria.setVisibility(View.VISIBLE);
                }
            }, 1000);
        } else if (heroe.getSalud() <= 0) {
            musicaFondo.release();
            musicaFondo = MediaPlayer.create(this, R.raw.sonido_derrota);
            musicaFondo.setVolume(0.8f, 0.8f);
            musicaFondo.start();
            imagenHeroeCombate.setImageResource(heroe.getImagenMuerte());
            desactivarMenu();
            textoVictoriaDerrota.setText("¡DERROTA!\n\n" + " Esfuérzate más la próxima vez OwO\n");
            handler.postDelayed(new Runnable() {
                public void run() {
                    cajaBotonesPrincipales.setVisibility(View.INVISIBLE);
                    combatLog.setVisibility(View.INVISIBLE);
                    cajaVictoriaDerrota.setVisibility(View.VISIBLE);
                    botonDerrota.setVisibility(View.VISIBLE);
                }
            }, 1000);
        } else {
            if (turnoHeroe == false) {
                turnoEnemigo();
            }
        }
    }

    public void victoria(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.salirEvento);
        final Dialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        intent = new Intent(this, ActivityPrincipal.class);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
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

    public void derrota(View view) {
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

    public void mostrarInformacionHeroe(View view) {
        if (activarInfoHeroe) {
            textoAtributosHeroe.setVisibility(View.VISIBLE);
            activarInfoHeroe = false;
        } else {
            textoAtributosHeroe.setVisibility(View.INVISIBLE);
            activarInfoHeroe = true;
        }
    }


    public void mostrarInformacionEnemigo(View view) {
        if (activarInfoEnemigo) {
            textoAtributosEnemigo.setVisibility(View.VISIBLE);
            activarInfoEnemigo = false;
        } else {
            textoAtributosEnemigo.setVisibility(View.INVISIBLE);
            activarInfoEnemigo = true;
        }
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
        if (sonidos != null) {
            sonidos.stop();
            sonidos.release();
        }
    }

    @Override
    public void onBackPressed() {
    }
}