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
    private Lista barrido; // Lista que mantiene el barrido

    /*Contructor*/
    public DFS(Grafo grafo) {
        this.grafo = grafo;/*Grafo para representar el tablero*/
        this.barrido = new Lista();/*Lista de casilla barridas*/
    }

    
    
    /*M&eacutetodo buscar casilla dado un inicio*/
    public void Buscar_Casilla(String inicio) {
        Casilla casillaInicio = grafo.buscar(inicio);
        
        if (casillaInicio == null) {
            System.out.println("La casilla no existe.");
            return;
        }

        // Si el vértice es una mina o tiene minas adyacentes, no se hace barrido
        if (casillaInicio.isMina() || (casillaInicio.cantidadMinasAdy()) > 0) {
            System.out.println("La casilla tiene minas adyacentes, "
                    + "no se puede barrer.");
            return;
        }

        // Inicialización de DFS
        Lista visitados = new Lista();
        Barrer_DFS(casillaInicio, visitados);
        
        /*Imprime un mensaje para que el usuario sepa que se van a 
        revelar las casillas*/
        System.out.println("Casillas reveladas durante DFS:");
        Nodo nodo = visitados.getpFirst();//Primer nodo de la lista visitados
        //ciclo que recorre la lista
        while (nodo != null) {
            /*Obtiene la casilla  del nodo actual*/
            Casilla casilla = (Casilla) nodo.getDato();
            //Avanza al siguiente nodo de la lista
            nodo = nodo.getPnext();
        }
    }

    /*M&eacutetodo recursivo de dfs*/
    private void Barrer_DFS(Casilla actual, Lista visitados) {
        if (actual == null) return;
        
        barrido.InsertarFinal(actual); // Guardamos el barrido
        visitados.InsertarFinal(actual);/*Inserta las casilla actual a
        la lista de vistados*/
        actual.revelar();/*Revela la casilla actual*/

        // Si la casilla tiene minas adyacentes, detenemos el barrido
        if (actual.cantidadMinasAdy() > 0) {
            return;
        }

        // Recorrer los vecinos
        Nodo aux = actual.getAdyacentes().getpFirst();
        while (aux != null) {
            Casilla casilla_Vecina = (Casilla) aux.getDato();
            if (!visitados.buscar(casilla_Vecina) && !casilla_Vecina.isMina()) {
                Barrer_DFS(casilla_Vecina, visitados);
            }
            aux = aux.getPnext();
        }
    }

    
    /*Obtiene la lista de casillas barridas*/
    public Lista getBarrido() {
        return barrido;
    }
}