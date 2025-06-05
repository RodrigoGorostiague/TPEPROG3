package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Maquina implements Comparable<Maquina> {
    private String nombre;
    private int piezas;
    
    @Override
    public int compareTo(Maquina o) {
        return this.nombre.compareTo(o.nombre);
    }
}
