/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_edd_miguelangel_jesus;

import ClasesPrincipales.Casilla;
import EDD.Grafo;
import java.util.Random;

/**
 *
 * @author Miguel
 */
public class BuscaMina {
    private int n;
    private int cantidadMinas;
    public Grafo grafo;

    public BuscaMina() {
        this.n = 0;
        this.cantidadMinas = 0;
        this.grafo = new Grafo();
    }

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

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getCantidadMinas() {
        return cantidadMinas;
    }

    public void setCantidadMinas(int cantidadMinas) {
        this.cantidadMinas = cantidadMinas;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

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
    
}
