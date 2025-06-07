package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Backtracking {
    private List<Maquina> mejorSolucion = null;
    int estadosGenerados;

    public List<Maquina> back(List<Maquina> maquinas, int piezasAConstruir) {
        estadosGenerados = 0;
        List<Maquina> maquinasActuales = new ArrayList<>();
        backSolucion(maquinas, piezasAConstruir, maquinasActuales, 0);
        return mejorSolucion;
    }

    private void backSolucion(List<Maquina> maquinas, int piezasAConstruir, List<Maquina> maquinasActuales, int piezasCreadas) {
        estadosGenerados++;
        if (piezasCreadas == piezasAConstruir) { // condicion de corte
            // Si es una solución válida, y es mejor (menos máquinas), la guardo
            if (mejorSolucion == null || maquinasActuales.size() < mejorSolucion.size()) {
                mejorSolucion = new ArrayList<>(maquinasActuales);
                return;
            }
            return;
        }

        for (Maquina maquina : maquinas) {
            int piezas = maquina.getPiezas();

            if (piezasCreadas + piezas <= piezasAConstruir ) {
               if (mejorSolucion != null && maquinasActuales.size() + 1 >= mejorSolucion.size())
                    continue;

                maquinasActuales.add(maquina);
                backSolucion(maquinas, piezasAConstruir, maquinasActuales, piezasCreadas + piezas);
                maquinasActuales.remove(maquinasActuales.size() - 1); // backtrack
            }
        }
    }
}
