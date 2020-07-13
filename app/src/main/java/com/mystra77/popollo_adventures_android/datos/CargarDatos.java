package com.mystra77.popollo_adventures_android.datos;

import com.mystra77.popollo_adventures_android.R;
import com.mystra77.popollo_adventures_android.clases.Enemigo;
import com.mystra77.popollo_adventures_android.clases.Habilidad;
import com.mystra77.popollo_adventures_android.clases.Heroe;
import com.mystra77.popollo_adventures_android.clases.Objeto;
import com.mystra77.popollo_adventures_android.database.MyOpenHelper;

import java.util.ArrayList;

public class CargarDatos {

    public CargarDatos() {

    }

    public Heroe nuevoHeroe() {
        //CARGANDO HEROE
        //Habilidades Heroe
        ArrayList<Habilidad> habilidadesHeroe = new ArrayList<Habilidad>();
        habilidadesHeroe.add(new Habilidad("Llamarada", 2, 4, true));
        habilidadesHeroe.add(new Habilidad("Flecha Helada", 3, 6, true));
        habilidadesHeroe.add(new Habilidad("Circulo Sanador", 5, 6, false));
        //Objetos Heroe
        ArrayList<Objeto> objetosHeroe = new ArrayList<Objeto>();
        objetosHeroe.add(new Objeto("Estrella Ninja", 25, 2, true, 150));
        objetosHeroe.add(new Objeto("Barril Explosivo", 60, 1, true, 400));
        objetosHeroe.add(new Objeto("Pocion de Mana", 30, 2, false, 250));
        //Atributos Heroe
        Heroe heroe = new Heroe("Popollo", 500, 1, 0, 0,
                0, 30, 30, 20, 20, 10,
                5, 5, 5, habilidadesHeroe, objetosHeroe,
                R.drawable.combat_popollo, R.drawable.combat_popollo_death);
        return heroe;
    }

    public Enemigo cargarPoring() {
        ArrayList<Habilidad> habilidadesPoring = new ArrayList<Habilidad>();
        habilidadesPoring.add(new Habilidad("Pedo magico", 2, 4, true));
        habilidadesPoring.add(new Habilidad("Tirar jellopy", 3, 6, true));
        Enemigo poring = new Enemigo("Poring", 20, 20, 10,
                10, 10, 5, 5, 5, habilidadesPoring,
                150, 10, R.drawable.combat_poi, R.drawable.combat_poi_death);
        return poring;
    }

    public Enemigo cargarNigromante() {
        ArrayList<Habilidad> habilidadesNigromante = new ArrayList<Habilidad>();
        habilidadesNigromante.add(new Habilidad("Lanzar maldicion", 3, 4, true));
        habilidadesNigromante.add(new Habilidad("Flecha acida", 4, 6, true));
        Enemigo nigromante = new Enemigo("Nigromante", 40, 40, 20,
                20, 15, 5, 5, 5, habilidadesNigromante,
                200, 30, R.drawable.combat_nigromante, R.drawable.combat_nigromante_death);
        return nigromante;
    }

    public Enemigo cargarGolem() {
        ArrayList<Habilidad> habilidadesGolem = new ArrayList<Habilidad>();
        habilidadesGolem.add(new Habilidad("Mina magica", 3, 4, true));
        habilidadesGolem.add(new Habilidad("Llamarada", 4, 6, true));
        Enemigo golem = new Enemigo("Golem", 60, 60, 14,
                14, 20, 5, 6, 8, habilidadesGolem,
                300, 45, R.drawable.combat_dlc, R.drawable.combat_dlc_death);
        return golem;
    }

    public Enemigo cargarGoblin() {
        ArrayList<Habilidad> habilidadesGoblin = new ArrayList<Habilidad>();
        habilidadesGoblin.add(new Habilidad("Lanza envenenada", 3, 4, true));
        habilidadesGoblin.add(new Habilidad("Flecha venenosa", 4, 6, true));
        Enemigo goblin = new Enemigo("Goblin", 70, 70, 15,
                15, 18, 5, 8, 6, habilidadesGoblin,
                400, 60, R.drawable.combat_update, R.drawable.combat_update_death);
        return goblin;
    }

    public Enemigo cargarDeviling() {
        ArrayList<Habilidad> habilidadesPoring = new ArrayList<Habilidad>();
        habilidadesPoring.add(new Habilidad("Pedo magico", 3, 4, true));
        habilidadesPoring.add(new Habilidad("Tirar jellopy", 4, 6, true));
        Enemigo deviling = new Enemigo("Deviling", 100, 100, 24,
                24, 20, 5, 8, 7, habilidadesPoring,
                600, 70, R.drawable.combat_deviling, R.drawable.combat_deviling_death);
        return deviling;
    }

    public Enemigo cargarPulpoi() {
        ArrayList<Habilidad> habilidadesPulpoi = new ArrayList<Habilidad>();
        habilidadesPulpoi.add(new Habilidad("Cosquillas", 5, 6, true));
        habilidadesPulpoi.add(new Habilidad("Mirada viciosa", 6, 8, true));
        Enemigo pulpoi = new Enemigo("Pulpoi", 200, 200, 30,
                30, 30, 5, 12, 15, habilidadesPulpoi,
                1000, 150, R.drawable.combat_pulpoi, R.drawable.combat_pulpoi_death);
        return pulpoi;
    }
}