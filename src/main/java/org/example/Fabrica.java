package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Fabrica implements Iterable<Maquina>{
    private final List<Maquina> maquinas = new ArrayList<>();

    public void add(Maquina maquina) {
        int index = 0;
        while (index < maquinas.size() && maquina.getPiezas() < maquinas.get(index).getPiezas()) {
            index++;
        }
        maquinas.add(index, maquina);
    }

    @Override
    public Iterator<Maquina> iterator() {
        return maquinas.iterator();
    }
}
