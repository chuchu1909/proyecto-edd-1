/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Miguel
 */
public class Nodo<T> {
    private T dato; //Variable donde se guardará el valor
     private Nodo<T> pnext; //Variable para enlazar los nodos
    
    //Constructor vacio
    public Nodo(){
       this.dato = null;
       this.pnext = null; 
    }
    
    //Constructor de la clase nodo si le paso solo la info
    public Nodo(T dato) {
        this.dato = dato;
        this.pnext = null;
    }
    

    public Object getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getPnext() {
        return pnext;
    }

    public void setPnext(Nodo pnext) {
        this.pnext = pnext;
    }
    
}
