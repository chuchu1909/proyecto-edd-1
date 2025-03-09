/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import ClasesPrincipales.Casilla;
import javax.swing.JOptionPane;

/**
 *La clase Grafo  es donde se manipulan los vertices que son las casillas teniendo diferentes funciones para manipular vertices 
 * @author Miguel
 * @version: 9/03/2025/A 
 */
public class Grafo {
    // Campos de la clase
    private Lista vertices;
/**
 * Constructor  del grafo 
 */
    public Grafo() {
        this.vertices = new Lista();
    }// fin del constructor 
    /**
 * Obtiene la lista de vértices del grafo.
 *
 * @return Una lista que contiene los vértices del grafo.
 */
    public Lista getVertices() {
        return vertices;
    }
    /**
 * Establece la lista de vértices del grafo.
 *
 * @param vertices La lista de vértices a asignar al grafo.
 */

    public void setVertices(Lista vertices) {
        this.vertices = vertices;
    }
    /**
 * Devuelve la cantidad de vértices en el grafo.
 *
 * @return El número total de vértices en el grafo.
 */
    public int cantidadVertices(){
        return this.vertices.getSize();
    }
    /**
 * Verifica si el grafo está vacío.
 *
 * @return true si el grafo no contiene vértices, false en caso contrario.
 */
    public boolean esVacio() {
        return this.vertices.EsVacio();
    }
    /**
     * Buscar por el nombre de la casilla la casilla en el grafo
     * @param nombreCasilla consiste el nomnbre de la casilla en la combinacion de fila y columna
     * @return la casilla que se busco 
     */
    public Casilla buscar(String nombreCasilla) {
        if (!this.esVacio()) {
            Nodo aux = this.vertices.getpFirst();
            while (aux != null) {
                Casilla casillaActual = (Casilla) aux.getDato();
                if (casillaActual.getNombre().equalsIgnoreCase(nombreCasilla)) {
                    return casillaActual;
                }
                aux = aux.getPnext();
            }
            return null;
        }
        return null;
    }
    /**
     * Se inserta al final la casilla a traves de la columna y fila
     * @param columna el string de la columna de la casilla 
     * @param fila el int de la fila de la casilla
     */
    public void insertarCasilla(String columna, int fila) {
        String nombreCasilla = columna + fila;
        if (this.buscar(nombreCasilla) == null) {
            Casilla casilla = new Casilla(columna, fila);
            casilla.setNumVertice(this.vertices.getSize());
            this.vertices.InsertarFinal(casilla);
        }
    }
    /**
     * Simplemente se agregan las ady de dos casillas. Los parametros para que sean ady consisten en si ninguna existe o alguna de las dos no existen
     * No pueden ser agregadas como ady. Pero, si las dos llegan a existir se agrega su ady
     * @param casilla1
     * @param casilla2 
     */
    public void agregarAdyacencia(String casilla1, String casilla2) {
        if (this.buscar(casilla1) != null && this.buscar(casilla2) != null) {
            Casilla casillaInicio = this.buscar(casilla1);
            Casilla casillaFin = this.buscar(casilla2);
            
            if(!casillaInicio.buscarAdy(casilla2)){
                casillaInicio.agregarAdy(casillaFin);
            }
            
            if(!casillaFin.buscarAdy(casilla1)){
                casillaFin.agregarAdy(casillaInicio);
            }
           
        } else {
            if (this.buscar(casilla1) == null && this.buscar(casilla2) != null) {
                JOptionPane.showMessageDialog(null, "La estacion de inicio no existe.");
            } else if (this.buscar(casilla1) != null && this.buscar(casilla2) == null) {
                JOptionPane.showMessageDialog(null, "La estacion de llegada no existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Ninguna de las estaciones existe.");
            }
        }
    }
    /**
     * Sigue la misma logica de agregar su ady solo que ahor eliminando esta misma ady 
     * @param casilla1
     * @param casilla2 
     */
    public void eliminarAdyacente(String casilla1, String casilla2) {
        if (this.buscar(casilla1) != null && this.buscar(casilla2) != null) {
            Casilla casillaInicio = this.buscar(casilla1);
            Casilla casillaFin = this.buscar(casilla2);

            if (casillaInicio.getAdyacentes().buscar(casillaFin)) {
                casillaInicio.getAdyacentes().EliminarPorReferencia(casillaFin);
                casillaFin.getAdyacentes().EliminarPorReferencia(casillaInicio);

                JOptionPane.showMessageDialog(null, "Conexion eliminada con exito");
            } else {
                JOptionPane.showMessageDialog(null, "No son adyacentes.");
            }

        } else {
            if (this.buscar(casilla1) == null && this.buscar(casilla2) != null) {
                JOptionPane.showMessageDialog(null, "La estacion de inicio no existe.");
            } else if (this.buscar(casilla1) != null && this.buscar(casilla2) == null) {
                JOptionPane.showMessageDialog(null, "La estacion de llegada no existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Ninguna de las estaciones existe.");
            }
        }
    }
    /**
     * Obtiene las ady de una casilla en especifico 
     * @param nombreCasilla consiste en la combinacion de fila y columna en un string
     * 
     */
    public void getAdyacentes(String nombreCasilla) {
        if (this.buscar(nombreCasilla) != null) {
            Casilla casilla = this.buscar(nombreCasilla);
            JOptionPane.showMessageDialog(null, casilla.imprimirAdy());
        } else {
            JOptionPane.showMessageDialog(null, "La estacion ya existe.");
        }
    }
/**
 * Verifica si dos casillas están conectadas en el grafo.
 *
 * @param casilla1 Nombre de la primera casilla.
 * @param casilla2 Nombre de la segunda casilla.
 * @return true si las casillas están conectadas, false en caso contrario.
 */
    public boolean estanConectados(String casilla1, String casilla2) {
        if (this.buscar(casilla1) != null && this.buscar(casilla2) != null) {
            Casilla estacionInicio = this.buscar(casilla1);
            Casilla estacionFinal = this.buscar(casilla2);

            if (estacionInicio.getAdyacentes().buscar(estacionFinal)) {
                JOptionPane.showMessageDialog(null, "Si estan conectadas");
                return true;
            } else {

                JOptionPane.showMessageDialog(null, "No son adyacentes.");
                return false;
            }

        } else {
            if (this.buscar(casilla1) == null && this.buscar(casilla2) != null) {
                JOptionPane.showMessageDialog(null, "La estacion de inicio no existe.");
            } else if (this.buscar(casilla1) != null && this.buscar(casilla2) == null) {
                JOptionPane.showMessageDialog(null, "La estacion de llegada no existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Ninguna de las estaciones existe.");
            }

            return false;
        }
    }
    /**
 * Cuenta la cantidad de casillas que están marcadas en el grafo.
 *
 * @return El número de casillas marcadas.
 */
    public int verticesMarcados(){
        int count = 0;
        for (int i = 0; i < this.vertices.getSize(); i++) {
            Casilla casillaActual = (Casilla) this.vertices.getValor(i);
            if(casillaActual.isEstaMarcada()){
                count++;
            }
        }
        
        return count;
    }
    /**
 * Cuenta la cantidad de casillas que contienen minas en el grafo.
 *
 * @return El número de casillas con minas.
 */
    
    public int cantidadMinas(){
        int count = 0;
        for (int i = 0; i < this.vertices.getSize(); i++) {
            Casilla casillaActual = (Casilla) this.vertices.getValor(i);
            if(casillaActual.isMina()){
                count++;
            }
        }
        
        return count;
    }
/**
 * Destruye el grafo, eliminando todos sus vértices.
 */
    public void destruir() {
        this.vertices = new Lista();
    }
/**
 * Devuelve una representación en cadena del grafo, mostrando las casillas y sus adyacencias.
 *
 * @return Una cadena con la representación del grafo.
 */
    @Override
    public String toString() {
        if (!this.esVacio()) {
            String estacionesStr = "";
            Nodo aux = this.vertices.getpFirst();
            while (aux.getPnext()!= null) {
                Casilla estacionActual = (Casilla) aux.getDato();
                estacionesStr += estacionActual.getNombre() + " ---> " + estacionActual.imprimirAdy()+ "\n";
                aux = aux.getPnext();
            }
            Casilla estacionActual = (Casilla) aux.getDato();
            estacionesStr += estacionActual.getNombre() + " ---> " + estacionActual.imprimirAdy();
            return estacionesStr;
        } else {
            return "Grafo vacion";
        }
    }
    
    
    
}
