/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_edd_miguelangel_jesus;

import ClasesPrincipales.Casilla;
import EDD.Grafo;
import java.util.Random;

/**
 *La clase BusCaminas es en donde el grafo se genera, se conectan los vertices, se resetean y agregan minas 
 * 
 * 
 * @author Miguel
 * @version: 9/03/2025/A 
 */
public class BuscaMina {
    // Campos de la clase 
    private int n;
    private int cantidadMinas;
    private Grafo grafo;
    /**
     * El constructor de la clase buscaminas 
     */

    public BuscaMina() {
        this.n = 0;
        this.cantidadMinas = 0;
        this.grafo = new Grafo();
    }// fin del constructor 
    /**
     *  Es una funcion que se utiliza para generar el grafo del tablero 
     * @param N se define este parametro como el numero nxn que el usuario elegio para poder hacer el tablero 
     * @return Retorna un grafo con el tablero generado 
     */

    public Grafo generarGrafoTablero(int N) {
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}; // Soporta hasta 10x10

        // Paso 1: Insertar todas las casillas en el grafo
        for (int fila = 0; fila < N; fila++) {
            for (int columna = 0; columna < N; columna++) {
                grafo.insertarCasilla(letras[columna] + "", fila);
            }
        }

        // Paso 2: Conectar cada casilla con sus adyacentes
        for (int fila = 0; fila < N; fila++) {
            for (int columna = 0; columna < N; columna++) {
                String actual = letras[columna] + String.valueOf(fila);

                // Posibles movimientos (izquierda, derecha, arriba, abajo y diagonales)
                int[][] movimientos = {
                    {-1, -1}, {-1, 0}, {-1, 1}, // Diagonal superior izquierda, arriba, diagonal superior derecha
                    {0, -1}, {0, 1}, // Izquierda, derecha
                    {1, -1}, {1, 0}, {1, 1} // Diagonal inferior izquierda, abajo, diagonal inferior derecha
                };

                for (int[] mov : movimientos) {
                    int nuevaFila = fila + mov[0];
                    int nuevaColumna = columna + mov[1];

                    if (nuevaFila >= 0 && nuevaFila < N && nuevaColumna >= 0 && nuevaColumna < N) {
                        String vecino = letras[nuevaColumna] + String.valueOf(nuevaFila);
                        grafo.agregarAdyacencia(actual, vecino);
                    }
                }
            }
        }
        return grafo;
    }
    /**
 * Obtiene el tamaño del tablero o la dimensión 'n'.
 *
 * @return el tamaño del tablero.
 */
    
    public int getN() {
        return n;
    }
    /**
 * Establece el tamaño del tablero o la dimensión 'n'.
 *
 * @param n el nuevo tamaño del tablero.
 */
    public void setN(int n) {
        this.n = n;
    }
    /**
 * Obtiene la cantidad de minas en el tablero.
 *
 * @return la cantidad de minas.
 */

    public int getCantidadMinas() {
        return cantidadMinas;
    }
    /**
 * Establece la cantidad de minas en el tablero.
 *
 * @param cantidadMinas la nueva cantidad de minas.
 */

    public void setCantidadMinas(int cantidadMinas) {
        this.cantidadMinas = cantidadMinas;
    }
    /**
 * Obtiene el grafo que representa la estructura del tablero.
 *
 * @return el grafo del tablero.
 */

    public Grafo getGrafo() {
        return grafo;
    }
    /**
 * Establece el grafo que representa la estructura del tablero.
 *
 * @param grafo el nuevo grafo del tablero.
 */

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }
    
    /**
 * Genera y coloca minas aleatoriamente en el tablero.
 *
 * @param cantidadMinas la cantidad de minas que se deben colocar en el tablero.
 *                      Debe estar entre 1 y la cantidad total de vértices en el grafo.
 *                      Si el valor está fuera de este rango, se muestra un mensaje de error y no se colocan minas.
 */

    public void generarMinas(int cantidadMinas) {
        int totalVertices = grafo.cantidadVertices();

        if (cantidadMinas < 1 || cantidadMinas > totalVertices) {
            System.out.println("Error: La cantidad de minas debe estar entre 1 y " + totalVertices);
            return;
        }

        Random rand = new Random(); // Generador de números aleatorios
        int minasColocadas = 0;

        while (minasColocadas < cantidadMinas) {
            int indiceAleatorio = rand.nextInt(totalVertices); // Índice aleatorio en la lista de vértices
            Casilla casillaSeleccionada = (Casilla) grafo.getVertices().getValor(indiceAleatorio);

            if (!casillaSeleccionada.isMina()) { // Si la casilla no tiene mina, se le asigna
                casillaSeleccionada.setMina(true);
                minasColocadas++;
            }
        }
    }
    /**
     * Resetea tanto el grafo como las minas y el numero del tablero.
     */

    public void resetearMinas() {
        this.grafo = new Grafo();
        this.cantidadMinas = 0;
        this.n = 0;

    }
    
    /**
 * Conecta las casillas del tablero en un grafo 
 *
 * Este método establece las conexiones entre las casillas adyacentes del tablero, 
 * representado como un grafo. Cada casilla tiene un nombre (ejemplo: "A0", "B1"). Se consideran conexiones en todas las 
 * direcciones posibles: izquierda, derecha, arriba, abajo y diagonales.
 *
 * El tablero admite tamaños de hasta 10x10, representados con letras de 'A' a 'J' 
 * en las columnas y números en las filas.
 */

    public void conectar() {
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}; // Soporta hasta 10x10
        
        for (int fila = 0; fila < this.n; fila++) {
            for (int columna = 0; columna < this.n; columna++) {
                String actual = letras[columna] + String.valueOf(fila);

                // Posibles movimientos (izquierda, derecha, arriba, abajo y diagonales)
                int[][] movimientos = {
                    {-1, -1}, {-1, 0}, {-1, 1}, // Diagonal superior izquierda, arriba, diagonal superior derecha
                    {0, -1}, {0, 1}, // Izquierda, derecha
                    {1, -1}, {1, 0}, {1, 1} // Diagonal inferior izquierda, abajo, diagonal inferior derecha
                };

                for (int[] mov : movimientos) {
                    int nuevaFila = fila + mov[0];
                    int nuevaColumna = columna + mov[1];

                    if (nuevaFila >= 0 && nuevaFila < this.n && nuevaColumna >= 0 && nuevaColumna < this.n) {
                        String vecino = letras[nuevaColumna] + String.valueOf(nuevaFila);
                        grafo.agregarAdyacencia(actual, vecino);
                        //System.out.println(actual + "," + vecino);
                    }
                }
            }
        }
    }
    
}
