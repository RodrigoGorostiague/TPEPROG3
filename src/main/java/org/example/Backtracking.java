package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



/**
 * Clase que implementa una búsqueda con backtracking para encontrar la combinación óptima
 * de máquinas necesarias para construir una cantidad exacta de piezas.
 *
 * <p>Esta implementación considera que las máquinas están ordenadas en la fábrica de mayor a menor
 * producción (cantidad de piezas por máquina), y utiliza técnicas de poda para mejorar el rendimiento
 * y evitar explorar combinaciones que no pueden superar la mejor solución encontrada.</p>
 *
 * <p>La búsqueda recursiva explora combinaciones evitando repeticiones gracias a un índice que indica
 * desde qué máquina se puede seguir seleccionando, y calcula un límite inferior de máquinas necesarias
 * para podar ramas que no pueden mejorar la mejor solución.</p>
 *
 * <p>El objetivo es encontrar la combinación con la menor cantidad de máquinas cuyo total de piezas
 * construidas sea exactamente igual al solicitado.</p>
 *
 * <p>El atributo {@code estadosGenerados} permite medir la cantidad de nodos explorados en la búsqueda,
 * lo que puede ser útil para análisis o optimización.</p>
 */
@Getter
@Setter
public class Backtracking {
    private List<Maquina> mejorSolucion = null;
    int estadosGenerados;

    /**
     * Método público que inicia la búsqueda de la combinación óptima de máquinas
     * para construir la cantidad exacta de piezas requerida.
     *
     * Este método:
     * - Inicializa el contador de estados generados (para análisis o métricas).
     * - Limpia cualquier mejor solución previa guardada.
     * - Prepara una lista vacía para ir acumulando la combinación actual durante la búsqueda.
     * - Llama al método recursivo {@code backSolucion} para comenzar la exploración con backtracking.
     *
     * @param maquinas La fábrica que contiene la lista de máquinas ordenadas descendentemente por producción.
     * @param piezasAConstruir Cantidad total exacta de piezas que se deben construir.
     * @return La lista óptima de máquinas (en cantidad mínima) cuya suma de producción es exactamente igual a {@code piezasAConstruir}.
     *         Retorna {@code null} si no existe combinación que cumpla la cantidad requerida.
     */

    public List<Maquina> back(Fabrica maquinas, int piezasAConstruir) {
        estadosGenerados = 0;
        mejorSolucion = null;
        List<Maquina> maquinasActuales = new ArrayList<>();
        backSolucion(maquinas, piezasAConstruir, maquinasActuales, 0, 0);
        return mejorSolucion;
    }
    /**
     * Método recursivo que busca la combinación óptima de máquinas para construir
     * la cantidad deseada de piezas usando backtracking con poda eficiente.
     *
     * Estrategia:
     * - Se exploran las máquinas disponibles ordenadas de mayor a menor producción.
     * - Para evitar combinaciones repetidas, se usa un índice `start` para que
     *   cada llamada recursiva solo considere máquinas desde esa posición en adelante.
     * - La poda principal calcula el mínimo número posible de máquinas necesarias
     *   para completar la producción restante asumiendo que sólo se usará la máquina
     *   más productiva (la primera en la lista ordenada).
     * - Si la solución parcial actual, más ese mínimo estimado, no puede mejorar la mejor
     *   solución encontrada hasta el momento (en términos de menor cantidad de máquinas),
     *   se abandona esa rama de búsqueda (poda).
     * - Para cada máquina, se intenta usarla desde 1 vez hasta el máximo número posible
     *   sin exceder la producción requerida.
     * - Si durante esta iteración se detecta que la cantidad de máquinas usadas ya
     *   supera o iguala la mejor solución encontrada, se corta la exploración de esa rama.
     *
     * Este método garantiza que:
     * - No se exploran soluciones que no puedan mejorar la mejor actual.
     * - No se generan combinaciones redundantes gracias al índice `start`.
     * - Se encuentra una solución óptima en cantidad mínima de máquinas para la producción.
     *
     * @param maquinas La fábrica que contiene la lista de máquinas ordenadas descendentemente por producción.
     * @param piezasAConstruir Cantidad total de piezas que se deben construir.
     * @param maquinasActuales Lista temporal con la combinación actual de máquinas en exploración.
     * @param piezasCreadas Cantidad de piezas acumuladas hasta la llamada actual.
     * @param start Índice desde donde se comienzan a considerar máquinas para evitar repeticiones.
     */
    private void backSolucion(Fabrica maquinas, int piezasAConstruir, List<Maquina> maquinasActuales, int piezasCreadas, int start) {
        estadosGenerados++;
        if (piezasCreadas == piezasAConstruir) {
            if (mejorSolucion == null || maquinasActuales.size() < mejorSolucion.size()) {
                mejorSolucion = new ArrayList<>(maquinasActuales);
            }
            return;
        }
        if (piezasCreadas > piezasAConstruir) {
            return;
        }

        List<Maquina> lista = maquinas.getMaquinas();

        for (int idx = start; idx < lista.size(); idx++) {
            Maquina maquina = lista.get(idx);
            int piezas = maquina.getPiezas();
            int maxUsos = (piezasAConstruir - piezasCreadas) / piezas;
            for (int i = 1; i <= maxUsos; i++) {
                if (mejorSolucion != null && maquinasActuales.size() + i >= mejorSolucion.size()) {
                    break;
                }
                for (int j = 0; j < i; j++) {
                    maquinasActuales.add(maquina);
                }
                backSolucion(maquinas, piezasAConstruir, maquinasActuales, piezasCreadas + i * piezas, idx);
                for (int j = 0; j < i; j++) {
                    maquinasActuales.remove(maquinasActuales.size() - 1);
                }
            }
        }
    }

}
