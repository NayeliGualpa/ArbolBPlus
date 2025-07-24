public class Nodo {
    int[] claves;
    Nodo[] hijos;
    int cantidad;
    boolean esHoja;
    Nodo siguienteHoja;  // enlace a la siguiente hoja (solo para hojas)

    public Nodo(int orden, boolean esHoja) {
        this.esHoja = esHoja;
        claves = new int[2 * orden];
        hijos = new Nodo[2 * orden + 1];
        cantidad = 0;
        siguienteHoja = null;
    }
}
