package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Servicio {
    public static void main(String[] args) throws IOException {
        //TODO obtencion de maquinas y piezas a contruir a travez de un archivo
        Read read = new Read("/home/rodaja/IdeaProjects/Prog3/TPEP3/src/main/java/org/example/maquinas.txt"); // carga el archivo
        read.cargarDesdeArchivo(); // carga las máquinas

        Fabrica maquinas = read.getFabrica(); // las obtiene

        int piezasObjetivo = read.getPiezasTotales();

        Backtracking bt = new Backtracking();
        //List<Maquina> solucion = bt.back(maquinas, piezasObjetivo);
        Greedy greedy = new Greedy();
        List<Maquina> solucion = greedy.greedySolucion(maquinas, piezasObjetivo);

        /*if (solucion != null) {
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
        }*/

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
            System.out.println("Costo (estados generados): " + greedy.getEstadosGenerados());
        } else {
            System.out.println("❌ No se encontró una combinación válida.");
        }
    }
}