/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

/**
 *
 * @author Miguel
 */

import ClasesPrincipales.Casilla;
import EDD.Grafo;
import EDD.Nodo;
import java.util.Stack;
public class DFS {
 private final boolean[][] visitado; private final Grafo grafo;
 public DFS(Grafo grafo) {
    this.grafo = grafo;
    this.visitado = new boolean[grafo.cantidadVertices()][grafo.cantidadVertices()];
}

public void buscar(String nombreInicio) {
    Casilla casillaInicio = grafo.buscar(nombreInicio);
    if (casillaInicio != null) {
        realizarDFS(casillaInicio);
    } else {
        System.out.println("La casilla de inicio no existe.");
    }
}

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
}
