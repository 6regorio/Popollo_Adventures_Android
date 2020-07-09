package com.mystra77.popollo_adventures_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mystra77.popollo_adventures_android.clases.Heroe;
import com.mystra77.popollo_adventures_android.datos.CargarDatos;

import java.util.Random;


public class ActivityPrincipal extends AppCompatActivity {
    private Heroe heroe;
    private Intent intent;
    private CargarDatos cargarDatos;
    private ImageView imagenMapaFondo;
    private TextView textoEstadisticas, textoExperiencia, textoDineroReputacion, textoMensajePrincipal;
    private LinearLayout cajaMensajePrincipal;
    private Button botonPasarMensaje, botonCreditos, botonCombateAleatorio, botonGuardarPartida,
            botonAreaDescanso, botonSalir;
    private Handler handler;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private MediaPlayer sonidoMovimiento, sonidoHuida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Eliminando barra de estado
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_principal);

        imagenMapaFondo = findViewById(R.id.imageViewMapa);
        textoEstadisticas = findViewById(R.id.textViewEstaditicas);
        textoExperiencia = findViewById(R.id.textViewExperiencia);
        textoDineroReputacion = findViewById(R.id.textViewOroReputacion);
        cajaMensajePrincipal = findViewById(R.id.layoutMensajePrincipal);
        textoMensajePrincipal = findViewById(R.id.textViewMensajePrincipal);
        botonCombateAleatorio = findViewById(R.id.btnCombateAleatorio);
        botonAreaDescanso = findViewById(R.id.btnDescanso);
        botonGuardarPartida = findViewById(R.id.btnGuardar);
        botonSalir = findViewById(R.id.btnInicio);
        botonPasarMensaje = findViewById(R.id.btnSalirMensajePrincipal);
        botonCreditos = findViewById(R.id.btnCreditos);

        sonidoMovimiento = MediaPlayer.create(this, R.raw.sonido_mover);
        sonidoHuida = MediaPlayer.create(this, R.raw.sonido_retirada);

        //Cargando Heroe
        cargarDatos = new CargarDatos();
        heroe = (Heroe) getIntent().getSerializableExtra("heroe");
        if (heroe == null) {
            heroe = cargarDatos.cargarHeroe();
        }

        textoExperiencia.setText("Nivel: " + heroe.getNivel() + "\n" +
                heroe.getExperiencia() + "/" + (heroe.getNivel() * 30));

        textoEstadisticas.setText("Salud: " + heroe.getSalud() + "/" + heroe.getSaludMaxima() + "\n" +
                "Mana: " + heroe.getMana() + "/" + heroe.getManaMaximo() + "\n" +
                "Fuerza: " + heroe.getFuerza() + "\n" +
                "Magia: " + heroe.getMagia() + "\n" +
                "Defensa: " + heroe.getDefensa() + "\n" +
                "Agilidad: " + heroe.getAgilidad());

        textoDineroReputacion.setText("Oro: " + heroe.getDinero() + "\nReputacion: " + heroe.getReputacion());
        handler = new Handler();
        movimientoMapa();
    }

    public void combatir(View view) {
        desactivarBotones();
        int aleatorio = 0;
        if (heroe.getExplorar() >= 5 && heroe.getExplorar() < 9) {
            aleatorio = new Random().nextInt(2);
        } else if (heroe.getExplorar() >= 9 && heroe.getExplorar() < 12) {
            aleatorio = new Random().nextInt(3);
        } else if (heroe.getExplorar() >= 12 && heroe.getExplorar() < 16) {
            aleatorio = new Random().nextInt(4);
        } else if (heroe.getExplorar() >= 16) {
            aleatorio = new Random().nextInt(5);
        }
        showAlert(R.string.entrarEnCombate, 1, aleatorio);
    }


    public void irADescanso(View view) {
        desactivarBotones();
        //intent = new Intent(this, ActivityCombate.class);
        //intent.putExtra("heroe", heroe);
        //startActivity(intent);
        //finish();
    }

    public void irAGuardar(View view) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.guardarPArtida)
                .setCancelable(false)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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

    public void salirAlMenu(View view) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.irAMenuPrincipal)
                .setCancelable(false)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ActivityPrincipal.this, MainActivity.class);
                        intent.removeExtra("heroe");
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

    public void movimientoMapa() {
        heroe.getExplorar();
        if (heroe.getExplorar() == 0) {
            imagenMapaFondo.setImageResource(R.drawable.mapa);
            textoMensajePrincipal.setText("Una malvada criatura está robando toda la comida.\n\n "
                    + "Un voraz pollo de granja se alza entre todos los habitantes del reino "
                    + "para hacer frente al vil enemigo que le priva de sus chuletitas.\n\n "
                    + "Tres aliados le acompañarán por el camino, aunque cada uno tiene una"
                    + " opinión distinta de lo que nuestro héroe debiera hacer.\n\n"
                    + "*Pulsa en cualquier lugar del mapa para avanzar*\n "
                    + "*Guarda partida siempre que puedas y utiliza la tienda sabiamente*\n ");
            botonPasarMensaje.setVisibility(View.VISIBLE);
            cajaMensajePrincipal.setVisibility(View.VISIBLE);
            imagenMapaFondo.setEnabled(false);
            botonSalir.setEnabled(false);
            botonCombateAleatorio.setEnabled(false);
            botonAreaDescanso.setEnabled(false);
            botonGuardarPartida.setEnabled(false);
            textoEstadisticas.setVisibility(View.INVISIBLE);
            textoExperiencia.setVisibility(View.INVISIBLE);
            textoDineroReputacion.setVisibility(View.INVISIBLE);
        }
        if (heroe.getExplorar() == 2) {
            imagenMapaFondo.setImageResource(R.drawable.mapa2);
        }
        if (heroe.getExplorar() == 3) {
            imagenMapaFondo.setImageResource(R.drawable.mapa3);
        }
        if (heroe.getExplorar() == 4) {
            imagenMapaFondo.setImageResource(R.drawable.mapa4);
        }
        if (heroe.getExplorar() == 5) {
            imagenMapaFondo.setImageResource(R.drawable.mapa5);
        }
        if (heroe.getExplorar() == 6) {
            imagenMapaFondo.setImageResource(R.drawable.mapa6);
        }
        if (heroe.getExplorar() == 7) {
            imagenMapaFondo.setImageResource(R.drawable.mapa7);
        }
        if (heroe.getExplorar() == 8) {
            imagenMapaFondo.setImageResource(R.drawable.mapa8);
        }
        if (heroe.getExplorar() == 9) {
            imagenMapaFondo.setImageResource(R.drawable.mapa9);
        }
        if (heroe.getExplorar() == 10) {
            imagenMapaFondo.setImageResource(R.drawable.mapa10);
        }
        if (heroe.getExplorar() == 11) {
            imagenMapaFondo.setImageResource(R.drawable.mapa11);
        }
        if (heroe.getExplorar() == 12) {
            imagenMapaFondo.setImageResource(R.drawable.mapa12);
        }
        if (heroe.getExplorar() == 13) {
            imagenMapaFondo.setImageResource(R.drawable.mapa13);
        }
        if (heroe.getExplorar() == 14) {
            imagenMapaFondo.setImageResource(R.drawable.mapa14);
        }
        if (heroe.getExplorar() == 15) {
            imagenMapaFondo.setImageResource(R.drawable.mapa15);
        }
        if (heroe.getExplorar() == 16) {
            imagenMapaFondo.setImageResource(R.drawable.mapa16);
        }
        if (heroe.getExplorar() == 17) {
            imagenMapaFondo.setImageResource(R.drawable.mapa17);
        }
        if (heroe.getExplorar() == 18) {
            imagenMapaFondo.setImageResource(R.drawable.mapa18);
        }
        if (heroe.getExplorar() == 19) {
            imagenMapaFondo.setImageResource(R.drawable.mapa19);
        }
        if (heroe.getExplorar() == 20) {
            imagenMapaFondo.setImageResource(R.drawable.mapa19);
            cajaMensajePrincipal.setVisibility(View.VISIBLE);
            botonCreditos.setVisibility(View.VISIBLE);
            botonGuardarPartida.setVisibility(View.INVISIBLE);
            botonCombateAleatorio.setVisibility(View.INVISIBLE);
            botonAreaDescanso.setVisibility(View.INVISIBLE);
            botonSalir.setVisibility(View.INVISIBLE);
            textoDineroReputacion.setVisibility(View.INVISIBLE);
            textoExperiencia.setVisibility(View.INVISIBLE);
            textoEstadisticas.setVisibility(View.INVISIBLE);
            textoMensajePrincipal.setText("¡Popollo ha conseguido derrotar al infame Pulpoi!\n\n" +
                    "Todos en el reino recordarán la hazaña y cantarán odas en tu honor," +
                    " pero ahora lo más importante es... Que a Popollo le ruge el estómago.");

        }
    }

    public void avanzarCasilla(View view) {
        sonidoMovimiento.start();
        new AlertDialog.Builder(this)
                .setMessage(R.string.avanzar)
                .setCancelable(false)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        dialog.dismiss();
                        heroe.setExplorar(heroe.getExplorar() + 1);
                        if (heroe.getExplorar() == 1) {
                            botonSalir.setEnabled(true);
                            imagenMapaFondo.setImageResource(R.drawable.mapa1);
                            showAlert(R.string.primerPaso, 0, 0);
                        }
                        if (heroe.getExplorar() == 2) {
                            showAlert(R.string.entrarEnCombate, 1, 0);
                        }
                        if (heroe.getExplorar() == 3) {

                        }
                        if (heroe.getExplorar() == 4) {
                            showAlert(R.string.entrarEnTienda, 2, 0);
                        }
                        if (heroe.getExplorar() == 5) {
                            if (heroe.getNivel() >= 2) {
                                showAlert(R.string.entrarEnCombate, 1, 1);
                            } else {
                                //Ventana.comenzarSonido(sonidoNoLevel);
                                sonidoHuida.start();
                                showAlert(R.string.bajoNivel, 0, 0);
                                heroe.setExplorar(heroe.getExplorar() - 2);
                                movimientoMapa();
                            }
                        }

                        if (heroe.getExplorar() == 6) {

                        }
                        if (heroe.getExplorar() == 7) {
                            showAlert(R.string.entrarEnTienda, 2, 0);
                        }
                        if (heroe.getExplorar() == 8) {

                        }
                        if (heroe.getExplorar() == 9) {
                            showAlert(R.string.entrarEnCombate, 1, 2);
                        }
                        if (heroe.getExplorar() == 10) {

                        }
                        if (heroe.getExplorar() == 11) {
                            showAlert(R.string.entrarEnTienda, 2, 0);
                        }
                        if (heroe.getExplorar() == 12) {
                            if (heroe.getNivel() >= 4) {
                                showAlert(R.string.entrarEnCombate, 1, 3);
                            } else {
                                sonidoHuida.start();
                                //Ventana.comenzarSonido(sonidoNoLevel);
                                showAlert(R.string.bajoNivel, 0, 0);
                                heroe.setExplorar(heroe.getExplorar() - 2);
                                movimientoMapa();
                            }

                        }
                        if (heroe.getExplorar() == 13) {

                        }
                        if (heroe.getExplorar() == 14) {
                            showAlert(R.string.entrarEnTienda, 2, 0);
                        }
                        if (heroe.getExplorar() == 15) {

                        }
                        if (heroe.getExplorar() == 16) {
                            showAlert(R.string.entrarEnCombate, 1, 4);
                        }

                        if (heroe.getExplorar() == 17) {

                        }
                        if (heroe.getExplorar() == 18) {
                            showAlert(R.string.entrarEnTienda, 2, 0);
                        }
                        if (heroe.getExplorar() == 19) {
                            if (heroe.getNivel() >= 7) {
                                showAlert(R.string.entrarEnCombate, 1, 5);
                            } else {
                                sonidoHuida.start();
                                //Ventana.comenzarSonido(sonidoNoLevel);
                                showAlert(R.string.bajoNivel, 0, 0);
                                heroe.setExplorar(heroe.getExplorar() - 2);
                                movimientoMapa();
                            }
                        }
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
        /*
        if (ventana.heroe.getExplorar() == 3) {
            Ventana.origenADestino(ventana, "principal", "evento", 0);
        }
        if (ventana.heroe.getExplorar() == 6) {
            Ventana.origenADestino(ventana, "principal", "evento", 1);
        }

        if (ventana.heroe.getExplorar() == 8) {
            Ventana.origenADestino(ventana, "principal", "afinidad", 1);
        }

        if (ventana.heroe.getExplorar() == 10) {
            Ventana.origenADestino(ventana, "principal", "evento", 2);
        }
        if (ventana.heroe.getExplorar() == 13) {
            Ventana.origenADestino(ventana, "principal", "evento", 3);
        }

        if (ventana.heroe.getExplorar() == 15) {
            Ventana.origenADestino(ventana, "principal", "afinidad", 1);
        }

        if (ventana.heroe.getExplorar() == 17) {
            Ventana.origenADestino(ventana, "principal", "evento", 4);
        }


         */

    }

    public void pasarMensaje(View view) {
        cajaMensajePrincipal.setVisibility(View.GONE);
        textoExperiencia.setVisibility(View.VISIBLE);
        textoEstadisticas.setVisibility(View.VISIBLE);
        textoDineroReputacion.setVisibility(View.VISIBLE);
        botonSalir.setVisibility(View.VISIBLE);
        imagenMapaFondo.setEnabled(true);
    }

    public void irACreditos(View view) {
    }

    /**
     * @param mensaje
     * @param lugar         0 Solo mensaje, 1 Combate 2 Tienda 3 Area descanso 4 Eventos 5 Evento Afinidad
     * @param numeroCombate
     */
    public void showAlert(int mensaje, int lugar, final int numeroCombate) {
        imagenMapaFondo.setEnabled(false);
        builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje);
        dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        if (lugar == 0) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    imagenMapaFondo.setEnabled(true);
                    dialog.dismiss();
                }
            }, 2000);
        }
        if (lugar == 1) {
            sonidoMovimiento.start();
            handler.postDelayed(new Runnable() {
                public void run() {
                    intent = new Intent(ActivityPrincipal.this, ActivityCombate.class);
                    intent.putExtra("heroe", heroe);
                    intent.putExtra("seleccionEnemigo", numeroCombate);
                    startActivity(intent);
                    dialog.dismiss();
                    finish();
                }
            }, 2000);
        }
        if (lugar == 2) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    intent = new Intent(ActivityPrincipal.this, ActivityCombate.class);
                    intent.putExtra("heroe", heroe);
                    intent.putExtra("seleccionEnemigo", numeroCombate);
                    startActivity(intent);
                    dialog.dismiss();
                    finish();
                }
            }, 2000);
        }
        if (lugar == 3) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    //intent = new Intent(ActivityPrincipal.this, ActivityCombate.class);
                    //intent.putExtra("heroe", heroe);
                    //startActivity(intent);
                    dialog.dismiss();
                    //finish();
                }
            }, 2000);
        }
        if (lugar == 4) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    //intent = new Intent(ActivityPrincipal.this, ActivityCombate.class);
                    //intent.putExtra("heroe", heroe);
                    //startActivity(intent);
                    dialog.dismiss();
                    //finish();
                }
            }, 2000);
        }
        if (lugar == 5) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    //intent = new Intent(ActivityPrincipal.this, ActivityCombate.class);
                    //intent.putExtra("heroe", heroe);
                    //startActivity(intent);
                    dialog.dismiss();
                    //finish();
                }
            }, 2000);
        }
    }

    public void desactivarBotones() {
        botonCombateAleatorio.setEnabled(false);
        botonGuardarPartida.setEnabled(false);
        botonAreaDescanso.setEnabled(false);
        botonSalir.setEnabled(false);
    }

    public void onDestroy() {
        super.onDestroy();
        if (sonidoMovimiento != null) {
            sonidoMovimiento.stop();
            sonidoMovimiento.release();
        }
        if (sonidoHuida != null) {
            sonidoHuida.stop();
            sonidoHuida.release();
        }
    }

    @Override
    public void onBackPressed() {
    }
}