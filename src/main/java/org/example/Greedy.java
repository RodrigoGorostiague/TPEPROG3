package org.example;

import java.util.ArrayList;
import java.util.List;

public class Greedy {
    private static List<Maquina> mejorSolucion = null;
    private static int estadosGenerados = 0;

    public static List<Maquina> Greedy(List<Maquina> maquinas, int piezasAConstruir) {
        mejorSolucion = new ArrayList<Maquina>();
        estadosGenerados = 0;
        greedyPrivado(maquinas, piezasAConstruir);
        return mejorSolucion;
    }
    public static List<Maquina> greedyPrivado(List<Maquina> maquinas, int piezasAConstruir) {
        List<Maquina> solucion = new ArrayList<>();

        for (Maquina maquina : maquinas) {
            int piezasPorEncendido = maquina.getPiezas();
            int it = piezasAConstruir / piezasPorEncendido;
            for (int i = 0; i < it; i++) {
                solucion.add(maquina);
                piezasAConstruir -= piezasPorEncendido;
            }

            if (piezasAConstruir == 0) break;
        }

        if (piezasAConstruir == 0) {
            return solucion;
        } else {
            return null;
        }
    }


}
