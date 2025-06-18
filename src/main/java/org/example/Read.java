package org.example;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Getter
@Setter
public class Read {
    private int piezasTotales;
    private Fabrica fabrica;
    private String rutaArchivo;

    public Read(String rutaArchivo) throws IOException {
        this.rutaArchivo = rutaArchivo;
        cargarDesdeArchivo();
        this.fabrica = new Fabrica();
    }

    public void cargarDesdeArchivo() throws IOException {
        ArrayList<String[]> lines = this.readContent(this.rutaArchivo);
        for (String[] line: lines) {
            // Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
            if (line.length == 1){
                piezasTotales = Integer.parseInt(line[0]);
            } else {
            String nombre = line[0].trim();
            int piezas = Integer.parseInt(line[1].trim());

            if(this.fabrica != null)
                this.fabrica.add(new Maquina(nombre, piezas));
            }
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
                lines.add(line.split(","));
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
