package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase que implementa una solución greedy para el problema de seleccionar
 * máquinas que permitan construir una cantidad exacta de piezas.
 *
 * <p>El algoritmo voraz selecciona máquinas en orden, empezando por la que produce
 * mayor cantidad de piezas, y añade tantas unidades de esa máquina como sea posible
 * sin exceder la cantidad requerida. Luego pasa a la siguiente máquina y así sucesivamente,
 * hasta alcanzar la cantidad exacta o agotar las opciones.</p>
 *
 * <p>Esta estrategia es rápida y simple, pero no garantiza encontrar la solución óptima
 * (mínima cantidad de máquinas), sino una solución factible si existe con esta heurística.</p>
 *
 * <p>El atributo {@code estadosGenerados} cuenta la cantidad de máquinas consideradas,
 * útil para análisis de rendimiento.</p>
 */
@Getter
@Setter
public class Greedy {
    private List<Maquina> mejorSolucion = null;
    private int estadosGenerados;


    /**
     * Método público que inicia la búsqueda voraz de la solución.
     *
     * Inicializa las variables internas y llama al método privado
     * que implementa la lógica voraz.
     *
     * @param maquinas Lista de máquinas ordenadas descendentemente por producción.
     * @param piezasAConstruir Cantidad total exacta de piezas que se deben construir.
     * @return Una lista con la combinación de máquinas seleccionadas cuya suma
     *         de producción es exactamente igual a {@code piezasAConstruir}.
     *         Retorna {@code null} si no se puede alcanzar la cantidad exacta con esta heurística.
     */
    public List<Maquina> greedySolucion(Fabrica maquinas, int piezasAConstruir) {
        mejorSolucion = new ArrayList<Maquina>();
        estadosGenerados = 0;
        return greedyPrivado(maquinas, piezasAConstruir);
    }
    /**
     * Método privado que implementa la lógica del algoritmo voraz (greedy) para seleccionar
     * máquinas que permitan construir exactamente la cantidad requerida de piezas.
     *
     * <p><strong>Estrategia:</strong></p>
     * <ul>
     *   <li>Se asume que la lista de máquinas está ordenada en forma descendente por
     *       la cantidad de piezas que produce cada una.</li>
     *   <li>El método intenta usar primero la máquina que produce más piezas para
     *       cubrir la mayor parte posible de la demanda.</li>
     *   <li>Calcula cuántas veces se puede usar la máquina actual sin exceder la cantidad
     *       de piezas restantes por construir (división entera).</li>
     *   <li>Omite directamente máquinas que producen más piezas que las que restan construir,
     *       optimizando la cantidad de estados generados.</li>
     *   <li>Añade repetidamente esa máquina a la solución hasta cubrir esa cantidad,
     *       luego pasa a la siguiente y repite el proceso.</li>
     * </ul>
     *
     * <p><strong>Corte o “poda” implícita:</strong></p>
     * <ul>
     *   <li>Cuando la cantidad de piezas a construir llega a cero, se detiene inmediatamente
     *       la búsqueda porque se alcanzó una solución válida.</li>
     *   <li>Si al terminar la iteración sobre todas las máquinas la cantidad no es cero,
     *       significa que no se pudo obtener una combinación exacta con esta estrategia,
     *       y por lo tanto retorna {@code null}.</li>
     * </ul>
     *
     * <p><strong>Limitaciones:</strong></p>
     * <ul>
     *   <li>Esta estrategia no garantiza encontrar la solución óptima (mínima cantidad
     *       de máquinas), ya que siempre prioriza la máquina más productiva sin
     *       explorar combinaciones alternativas.</li>
     *   <li>No reutiliza soluciones previas ni explora múltiples caminos,
     *       pero es muy eficiente en tiempo y útil para obtener soluciones rápidas.</li>
     * </ul>
     *
     * @param maquinas Lista de máquinas ordenadas descendentemente por piezas producidas.
     * @param piezasAConstruir Cantidad exacta de piezas que se desea construir.
     * @return Lista con la combinación de máquinas seleccionadas cuya suma de producción
     *         es exactamente igual a {@code piezasAConstruir}, o {@code null} si no existe
     *         una solución con esta estrategia voraz.
     */
    private List<Maquina> greedyPrivado(Fabrica maquinas, int piezasAConstruir) {
        List<Maquina> solucion = new ArrayList<>();

        for (Maquina maquina : maquinas) {
            estadosGenerados++;
            int piezasPorEncendido = maquina.getPiezas();

            if (piezasPorEncendido > piezasAConstruir) continue;

            int usos = piezasAConstruir / piezasPorEncendido;
            for (int i = 0; i < usos; i++) {
                solucion.add(maquina);
                piezasAConstruir -= piezasPorEncendido;
            }

            if (piezasAConstruir == 0) break;
        }

        return (piezasAConstruir == 0) ? solucion : null;
    }
}
