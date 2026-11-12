// Clase que contiene el generador de datos y los tres algoritmos de búsqueda solicitados
public class BuscadorSecuenciaOrdenada {

    // Crea un arreglo de números enteros ordenados de menor a mayor
    public static int[] generarArregloOrdenado(int tamano) {
        int[] arreglo = new int[tamano];
        for (int i = 0; i < tamano; i++) {
            // Guardamos números pares (0, 2, 4, 6...) para asegurar que estén ordenados
            arreglo[i] = i * 2;
        }
        return arreglo;
    }

    // 1. BÚSQUEDA LINEAL: Revisa elemento por elemento desde el inicio hasta el final
    public static ResultadoBusqueda busquedaLineal(int[] arreglo, int objetivo) {
        long tiempoInicio = System.nanoTime();
        long comparaciones = 0;

        // Validar si el arreglo está vacío o nulo
        if (arreglo == null || arreglo.length == 0) {
            return new ResultadoBusqueda(-1, 0, System.nanoTime() - tiempoInicio);
        }

        // Recorremos todo el arreglo posición por posición
        for (int i = 0; i < arreglo.length; i++) {
            comparaciones++;
            if (arreglo[i] == objetivo) {
                // Encontrado: devolvemos su índice, comparaciones y tiempo
                return new ResultadoBusqueda(i, comparaciones, System.nanoTime() - tiempoInicio);
            }
            // Como sabemos que el arreglo está ordenado, si el número actual ya es mayor
            // al que buscamos, no tiene sentido seguir revisando hacia adelante
            comparaciones++;
            if (arreglo[i] > objetivo) {
                break;
            }
        }

        // Si termina el ciclo y no apareció, devolvemos -1
        return new ResultadoBusqueda(-1, comparaciones, System.nanoTime() - tiempoInicio);
    }

    // 2. BÚSQUEDA BINARIA: Divide el arreglo a la mitad en cada paso (requiere arreglo ordenado)
    public static ResultadoBusqueda busquedaBinaria(int[] arreglo, int objetivo) {
        long tiempoInicio = System.nanoTime();
        long comparaciones = 0;

        if (arreglo == null || arreglo.length == 0) {
            return new ResultadoBusqueda(-1, 0, System.nanoTime() - tiempoInicio);
        }

        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda <= derecha) {
            // Calculamos la posición central evitando desbordamiento de enteros
            int medio = izquierda + ((derecha - izquierda) >>> 1);

            comparaciones++;
            if (arreglo[medio] == objetivo) {
                return new ResultadoBusqueda(medio, comparaciones, System.nanoTime() - tiempoInicio);
            }

            comparaciones++;
            if (arreglo[medio] < objetivo) {
                // El número buscado está en la mitad derecha
                izquierda = medio + 1;
            } else {
                // El número buscado está en la mitad izquierda
                derecha = medio - 1;
            }
        }

        return new ResultadoBusqueda(-1, comparaciones, System.nanoTime() - tiempoInicio);
    }

    // 3. JUMP SEARCH (Búsqueda por saltos): Avanza en bloques de tamaño raíz cuadrada de N
    public static ResultadoBusqueda jumpSearch(int[] arreglo, int objetivo) {
        long tiempoInicio = System.nanoTime();
        long comparaciones = 0;

        if (arreglo == null || arreglo.length == 0) {
            return new ResultadoBusqueda(-1, 0, System.nanoTime() - tiempoInicio);
        }

        int n = arreglo.length;
        // Calculamos el tamaño óptimo del salto: raíz cuadrada del total de elementos
        int salto = (int) Math.floor(Math.sqrt(n));
        int anterior = 0;

        // Fase 1: Saltar de bloque en bloque hasta encontrar uno donde pueda estar el objetivo
        while (anterior < n) {
            // Evitamos salirnos del límite final del arreglo con Math.min
            int limiteBloque = Math.min(salto, n) - 1;

            comparaciones++;
            if (arreglo[limiteBloque] >= objetivo) {
                // El número debería estar dentro de este bloque, detenemos los saltos
                break;
            }

            // Avanzamos al siguiente bloque
            anterior = salto;
            salto += (int) Math.floor(Math.sqrt(n));

            if (anterior >= n) {
                return new ResultadoBusqueda(-1, comparaciones, System.nanoTime() - tiempoInicio);
            }
        }

        // Fase 2: Hacer búsqueda lineal únicamente dentro del bloque identificado
        int finBloque = Math.min(salto, n);
        for (int i = anterior; i < finBloque; i++) {
            comparaciones++;
            if (arreglo[i] == objetivo) {
                return new ResultadoBusqueda(i, comparaciones, System.nanoTime() - tiempoInicio);
            }
            comparaciones++;
            if (arreglo[i] > objetivo) {
                break;
            }
        }

        return new ResultadoBusqueda(-1, comparaciones, System.nanoTime() - tiempoInicio);
    }
}