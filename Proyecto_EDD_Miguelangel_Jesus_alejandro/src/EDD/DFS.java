/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author chuchu
 */
import ClasesPrincipales.Casilla;
import java.util.HashSet;
import java.util.Set;


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