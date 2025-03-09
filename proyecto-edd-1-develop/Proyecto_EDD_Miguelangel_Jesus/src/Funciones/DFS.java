/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import ClasesPrincipales.Casilla;
import EDD.Grafo;
import java.util.Stack;

/**
 * Clase que implementa el algoritmo de búsqueda en profundidad (DFS) en un grafo.
 * 
 *
 */
public class DFS {
    private final boolean[][] visitado; // Matriz para marcar los nodos visitados
    private final Grafo grafo; // Grafo sobre el que se realiza la búsqueda

    /**
     * Constructor de la clase DFS.
     * 
     * @param grafo El grafo en el que se realizará la búsqueda.
     */
    public DFS(Grafo grafo) {
        this.grafo = grafo;
        this.visitado = new boolean[grafo.cantidadVertices()][grafo.cantidadVertices()];
    }

    /**
     * Inicia la búsqueda en profundidad desde la casilla especificada.
     * 
     * @param nombreInicio El nombre de la casilla desde la cual iniciar la búsqueda.
     */
    public void buscar(String nombreInicio) {
        Casilla casillaInicio = grafo.buscar(nombreInicio);
        if (casillaInicio != null) {
            realizarDFS(casillaInicio);
        } else {
            System.out.println("La casilla de inicio no existe.");
        }
    }

    /**
     * Realiza la búsqueda en profundidad a partir de la casilla dada.
     * 
     * @param casilla La casilla desde la cual se inicia la búsqueda en profundidad.
     */
    private void realizarDFS(Casilla casilla) {
        Stack<Casilla> stack = new Stack<>();
        stack.push(casilla);

        while (!stack.isEmpty()) {
            Casilla actual = stack.pop();
            int filaActual = actual.getFila();
            int columnaActual = actual.getColumna().charAt(0) - 'A';

            if (!visitado[filaActual][columnaActual]) {
                visitado[filaActual][columnaActual] = true;
                System.out.println("Visitando: " + actual.getNombre());

                for (int i = 0; i < actual.cantidadAdy(); i++) {
                    Casilla vecino = (Casilla) actual.getAdyacentes().getValor(i);
                    if (!visitado[vecino.getFila()][vecino.getColumna().charAt(0) - 'A']) {
                        stack.push(vecino);
                    }
                }
            }
        }
    }

    public void realizarDFS(String nombreCasilla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}