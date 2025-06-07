package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Servicio {
    public static void main(String[] args) {
        List<Maquina> maquinas = new ArrayList<>();

        //TODO obtencion de maquinas y piezas a contruir a travez de un archivo

        maquinas.add(new Maquina("M1", 7));
        maquinas.add(new Maquina("M2", 3));
        maquinas.add(new Maquina("M3", 4));
        maquinas.add(new Maquina("M4", 1));

        int piezasObjetivo = 20;

        Backtracking bt = new Backtracking();
        List<Maquina> solucion = bt.back(maquinas, piezasObjetivo);

        if (solucion != null) {
            System.out.println("🔧 Solución obtenida: secuencia de máquinas:");
            int contadorPiezas = 0;
            for (Maquina m : solucion) {
                System.out.println("- " + m.getNombre() + " (piezas: " + m.getPiezas() + ")");
                contadorPiezas+= m.getPiezas();
            }

            System.out.println("\n📊 Resumen:");
            System.out.println("Total piezas producidas: " + contadorPiezas);
            System.out.println("Cantidad de máquinas utilizadas: " + solucion.size());
            System.out.println("Costo (estados generados): " + bt.getEstadosGenerados());
        } else {
            System.out.println("❌ No se encontró una combinación válida.");
        }
    }
}