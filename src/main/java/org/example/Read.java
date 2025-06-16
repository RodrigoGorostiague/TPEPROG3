package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Read {
    private int piezasTotales;
    private List<Maquina> maquinas = new ArrayList<>();

    public void cargarDesdeArchivo(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            if ((linea = br.readLine()) != null) {
                piezasTotales = Integer.parseInt(linea.trim());
            }
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    int piezas = Integer.parseInt(partes[1].trim());
                    maquinas.add(new Maquina(nombre, piezas));
                }
            }
        }
    }

    public int getPiezasTotales() {
        return piezasTotales;
    }

    public List<Maquina> getMaquinas() {
        return maquinas;
    }
}
