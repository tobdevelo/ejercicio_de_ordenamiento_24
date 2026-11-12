// Clase principal para ejecutar las pruebas y comparar el rendimiento de los tres algoritmos
public class Main {

    public static void main(String[] args) {
        // Definimos diferentes tamaños de entrada para probar cómo crece el tiempo y las comparaciones
        int[] tamanosPrueba = {100, 10_000, 1_000_000};

        // Calentamiento breve de Java (JVM) para que las primeras mediciones de tiempo sean justas
        int[] calentamiento = BuscadorSecuenciaOrdenada.generarArregloOrdenado(1000);
        BuscadorSecuenciaOrdenada.busquedaLineal(calentamiento, 500);
        BuscadorSecuenciaOrdenada.busquedaBinaria(calentamiento, 500);
        BuscadorSecuenciaOrdenada.jumpSearch(calentamiento, 500);

        // Recorremos cada tamaño configurado
        for (int n : tamanosPrueba) {
            // Generamos el arreglo ordenado de tamaño N
            int[] datos = BuscadorSecuenciaOrdenada.generarArregloOrdenado(n);

            System.out.println("=========================================================================");
            System.out.println("PRUEBAS CON ARREGLO ORDENADO DE TAMANO N = " + n);
            System.out.println("=========================================================================");

            // Definimos 4 casos de prueba clave:
            // 1. Al inicio (mejor caso)
            // 2. En el centro
            // 3. Al final (peor caso para búsqueda lineal)
            // 4. Un número que no existe (-5)
            int[] valoresABuscar = {
                    datos[0],          // Inicio
                    datos[n / 2],      // Centro
                    datos[n - 1],      // Final
                    -5                 // Inexistente
            };
            String[] nombresCasos = {"Inicio", "Centro", "Final", "Inexistente"};

            // Ejecutamos los 3 algoritmos sobre los mismos datos para cada caso
            for (int i = 0; i < valoresABuscar.length; i++) {
                int objetivo = valoresABuscar[i];
                System.out.printf("\nCaso [%s] -> Buscando el numero: %d%n", nombresCasos[i], objetivo);

                ResultadoBusqueda resLineal = BuscadorSecuenciaOrdenada.busquedaLineal(datos, objetivo);
                ResultadoBusqueda resBinaria = BuscadorSecuenciaOrdenada.busquedaBinaria(datos, objetivo);
                ResultadoBusqueda resJump = BuscadorSecuenciaOrdenada.jumpSearch(datos, objetivo);

                mostrarFila("Lineal", resLineal);
                mostrarFila("Binaria", resBinaria);
                mostrarFila("Jump Search", resJump);
            }
            System.out.println();
        }
    }

    // Método auxiliar para imprimir los resultados alineados en consola
    private static void mostrarFila(String algoritmo, ResultadoBusqueda resultado) {
        System.out.printf("  %-12s | Posicion: %-8d | Comparaciones: %-8d | Tiempo: %d ns%n",
                algoritmo,
                resultado.getIndice(),
                resultado.getComparaciones(),
                resultado.getTiempoNano());
    }
}