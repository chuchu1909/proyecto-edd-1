/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *La clase Nodo simplemente para inicializar la clase 
 * @author Miguel
 * @version: 9/03/2025/A
 */
public class Nodo {
    // inicializar parametros
    private Object dato; //Variable donde se guardará el valor
     private Nodo pnext; //Variable para enlazar los nodos
    
    /**
     * Constructores del nodo 
     */
    public Nodo(){
       this.dato = null;
       this.pnext = null; 
    }
    
    /**
     * 
     *Constructor de la clase nodo si le paso solo la info
     */
    public Nodo(Object dato) {
        this.dato = dato;
        this.pnext = null;
    }
    // fin de los constructores 
    /**
     * Obtiene el dato almacenado en el nodo.
     * 
     * @return El dato almacenado en el nodo.
     */
    public Object getDato() {
        return dato;
    }
    /**
     * Establece el dato del nodo.
     * 
     * @param dato El nuevo dato a almacenar en el nodo.
     */

    public void setDato(Object dato) {
        this.dato = dato;
    }
    /**
     * Obtiene el siguiente nodo en la lista.
     * 
     * @return El siguiente nodo en la lista.
     */

    public Nodo getPnext() {
        return pnext;
    }
    /**
     * Establece el siguiente nodo en la lista.
     * 
     * @param pnext El nuevo nodo siguiente en la lista.
     */

    public void setPnext(Nodo pnext) {
        this.pnext = pnext;
    }
    
}
