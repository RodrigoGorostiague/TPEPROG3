package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Servicio {
    public static void main(String[] args) throws IOException {
        //TODO obtencion de maquinas y piezas a contruir a travez de un archivo
        Read read = new Read("C:\\Users\\USUARIO\\Desktop\\TPE prog 3\\TPEPROG3\\src\\main\\java\\org\\example\\maquinas.txt"); // carga el archivo
        read.cargarDesdeArchivo(); // carga las máquinas

        Fabrica maquinas = read.getFabrica(); // las obtiene

        int piezasObjetivo = read.getPiezasTotales(); // obtiene piezas a construir

        Backtracking bt = new Backtracking();
        backtracking(maquinas, piezasObjetivo, bt); // solucion backtracking

        Greedy gr = new Greedy();
        //greedy(maquinas, piezasObjetivo, gr); // solucion greedy
    }

    public static void backtracking(Fabrica maquinas, int piezasObjetivo, Backtracking bt) {
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

    public static void greedy(Fabrica maquinas, int piezasObjetivo, Greedy greedy) {
        List<Maquina> solucion = greedy.greedySolucion(maquinas, piezasObjetivo);
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