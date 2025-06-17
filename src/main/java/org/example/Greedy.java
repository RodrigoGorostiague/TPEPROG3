package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Greedy {
    private List<Maquina> mejorSolucion = null;
    private int estadosGenerados;

    public List<Maquina> greedySolucion(List<Maquina> maquinas, int piezasAConstruir) {
        mejorSolucion = new ArrayList<Maquina>();
        estadosGenerados = 0;
        return greedyPrivado(maquinas, piezasAConstruir);
    }

    private List<Maquina> greedyPrivado(List<Maquina> maquinas, int piezasAConstruir) {
        List<Maquina> solucion = new ArrayList<>();

        for (Maquina maquina : maquinas) {
            estadosGenerados++;
            int piezasPorEncendido = maquina.getPiezas();
            int it = piezasAConstruir / piezasPorEncendido;
            for (int i = 0; i < it; i++) {
                solucion.add(maquina);
                piezasAConstruir -= piezasPorEncendido;
            }
            if (piezasAConstruir == 0) break;
        }

        if (piezasAConstruir == 0) {
            mejorSolucion.addAll(solucion);
            return mejorSolucion;
        } else {
            return null;
        }
    }
}
