// Clase que guarda los datos obtenidos después de ejecutar una búsqueda
public class ResultadoBusqueda {

    // Posición donde se encontró el número (-1 si no existe en el arreglo)
    private final int indice;

    // Cantidad de veces que el algoritmo comparó elementos
    private final long comparaciones;

    // Tiempo total que tardó la búsqueda en nanosegundos
    private final long tiempoNano;

    // Constructor: recibe y guarda los tres datos al finalizar la búsqueda
    public ResultadoBusqueda(int indice, long comparaciones, long tiempoNano) {
        this.indice = indice;
        this.comparaciones = comparaciones;
        this.tiempoNano = tiempoNano;
    }

    // Métodos para leer cada dato desde la clase principal (Main)
    public int getIndice() {
        return indice;
    }

    public long getComparaciones() {
        return comparaciones;
    }

    public long getTiempoNano() {
        return tiempoNano;
    }
}
