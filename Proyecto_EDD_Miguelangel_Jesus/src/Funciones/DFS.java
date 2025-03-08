/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import ClasesPrincipales.Casilla;
import EDD.Grafo;
import EDD.Lista;
import EDD.Nodo;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Miguel
 */
public class DFS {
    private final Grafo grafo;


    public DFS(Grafo grafo) {
        this.grafo = grafo;
    }

    public void realizarDFS(String nombreCasilla) {
        Casilla inicio = grafo.buscar(nombreCasilla);
        if (inicio == null) {
            return;
        }

        Set<Casilla> visitados = new HashSet<>();
        dfsRecursivo(inicio, visitados);
    }

    private void dfsRecursivo(Casilla casilla, Set<Casilla> visitados) {
        visitados.add(casilla);
        casilla.revelar();
        
        int numMinasCercanas=casilla.cantidadMinasAdy();
        if(numMinasCercanas>0 || casilla.isMina()){
        return;
        }
        Lista adyacentes = casilla.getAdyacentes();
        Nodo aux = adyacentes.getpFirst();
        while (aux != null) {
            Casilla adyacente = (Casilla) aux.getDato();
            if (!visitados.contains(adyacente)) {
                dfsRecursivo(adyacente, visitados);
            }
            aux = aux.getPnext();
        }
    }
    
}
