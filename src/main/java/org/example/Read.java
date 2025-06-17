package org.example;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Read {
    private int piezasTotales;
    private List<Maquina> maquinas;
    private String rutaArchivo;

    public Read(String rutaArchivo) throws IOException {
        this.rutaArchivo = rutaArchivo;
        cargarDesdeArchivo();
        this.maquinas = new ArrayList<>();
    }

    public void cargarDesdeArchivo() throws IOException {
        /*try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
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
        }*/
        // Obtengo una lista con las lineas del archivo
        // lines.get(0) tiene la primer linea del archivo
        // lines.get(1) tiene la segunda linea del archivo... y así
        ArrayList<String[]> lines = this.readContent(this.rutaArchivo);

        for (String[] line: lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            String nombre = line[0].trim();
            int piezas = Integer.parseInt(line[1].trim());
            // cómo guardo estos datos??
            // Aca instanciar lo que necesiten en base a los datos leidos
            if(this.maquinas != null)
                this.maquinas.add(new Maquina(nombre, piezas));
        }

    }

    private ArrayList<String[]> readContent(String path) {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(";"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
        return lines;
    }
}
