/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import ClasesPrincipales.Casilla;
import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;


/**
 *
 * @author Miguel
 */
public class DFS {
    private Grafo grafo;
    private Lista barrido; // Lista para almacenar el recorrido del barrido

    /**
     * Constructor de DFS.
     * @param grafo Grafo donde se ejecutará la búsqueda.
     */
    public DFS(Grafo grafo) {
        this.grafo = grafo;
        this.barrido = new Lista();
    }

    /**
     * Método para ejecutar DFS desde un vértice.
     * @param inicio Nombre del vértice inicial (Ejemplo: "A0").
     */
    public void ejecutarDFS(String inicio) {
        Casilla casillaInicio = grafo.buscar(inicio);
        
        if (casillaInicio == null) {
            System.out.println("La casilla no existe.");
            return;
        }

        // Si el vértice es una mina o tiene minas adyacentes, no se hace barrido
        if (casillaInicio.isMina() || casillaInicio.cantidadAdy() > 0) {
        } else {
            System.out.println("El Casilla tiene minas adyacentes, no se puede barrer.");
            return;
        }

        // Inicialización de DFS
        Lista visitados = new Lista();
        dfsRecursivo(casillaInicio, visitados);
    }

    /**
     * Método recursivo para DFS.
     * @param actual Vértice actual en el recorrido.
     * @param visitados Lista de vértices visitados.
     */
    private void dfsRecursivo(Casilla actual, Lista visitados) {
        if (actual == null) return;
        
        barrido.InsertarFinal(actual); // Guardamos el recorrido
        visitados.InsertarFinal(actual);

        // Si el vértice tiene minas adyacentes, detenemos el barrido
        if (actual.cantidadMinasAdy() > 0) {
            return;
        }

        // Recorrer los vecinos
        Nodo aux = actual.getAdyacentes().getpFirst();
        while (aux != null) {
            Casilla vecino = (Casilla) aux.getDato();
            if (!visitados.buscar(vecino) && !vecino.isMina()) {
                dfsRecursivo(vecino, visitados);
            }
            aux = aux.getPnext();
        }
    }

    /**
     * Devuelve la lista de vértices recorridos por DFS.
     * @return Lista de vértices en el recorrido.
     */
    public Lista getBarrido() {
        return barrido;
    }
}
    

