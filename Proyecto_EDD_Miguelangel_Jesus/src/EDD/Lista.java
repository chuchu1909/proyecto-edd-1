/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import javax.swing.JOptionPane;

/**
 *La clase lista  es donde se manipulan los nodos de una lista con diferentes funciones 
 * @author Miguel
 * @version: 9/03/2025/A
 */
public class Lista {
    // inicializar parametros
    private Nodo pFirst; //nodo apuntador al primero
    private int size; //tamaño de la lista
    
    //Constructor de la clase Lista
    public Lista() {
        this.pFirst = null;
        this.size = 0; 
    }// fin de el constructor 
    
    /**
     * Obtiene el primer nodo de la lista.
     * 
     * @return El primer nodo de la lista.
     */

    public Nodo getpFirst() {
        return pFirst;
    }
    /**
     * Establece el primer nodo de la lista.
     * 
     * @param pFirst El nuevo primer nodo de la lista.
     */

    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }
    /**
     * Obtiene el tamaño de la lista.
     * 
     * @return El tamaño de la lista.
     */

    public int getSize() {
        return size;
    }
/**
     * Establece el tamaño de la lista.
     * 
     * @param size El nuevo tamaño de la lista.
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    //Primitivas
    
    /**
     * Verifica si la lista está vacía.
     * 
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean EsVacio(){
        return this.pFirst == null;
    }
    
    /**
     * Vacía la lista, eliminando todos sus elementos.
     */
    public void vaciar(){
        this.pFirst = null;
        this.size = 0;   
    }
    
   
    
    


    /**
     * Esta funcion consiste en insertar al final de la lista un dato cualquiera
     * @param dato El dato pasado por parametro es de cualquier tipo por eso el object 
     */
    public void InsertarFinal(Object dato){
        Nodo pNew = new Nodo(dato);
        if(EsVacio()){
            pFirst = pNew;
        }else{
            Nodo aux = pFirst;
            while (aux.getPnext() != null){
                aux = aux.getPnext();
            }
            aux.setPnext(pNew);
        }
        size++;
    }
    
    
    
    
    /**
     * Dependiendo de la referencia pasada por parametro se inserta en su siguiente el valor 
     * @param ref la referencia de tipo object se utliza como parametro a recorrer en la lista
     * @param valor el valor se inserta despues de el parametro en la lista 
     */
    public void insertarPorReferencia(Object ref,Object valor){
        
        Nodo nuevo = new Nodo();
        nuevo.setDato(valor);
        
        if (!EsVacio()) {
            if (buscar(ref)) {
                Nodo aux = pFirst;
                // Recorre la lista hasta llegar al nodo de referencia.
                while (aux.getDato() != ref) {
                    aux = aux.getPnext();
                }
                // Crea un respaldo de la continuación de la lista.
                Nodo siguiente = aux.getPnext();
                // Enlaza el nuevo nodo despues del nodo de referencia.
                aux.setPnext(nuevo);
                // Une la continuacion de la lista al nuevo nodo.
                nuevo.setPnext(siguiente);
            
                size++;
            }
        }
    }
    
    
    /**
     * Muestra los elementos de la lista en un cuadro de diálogo.
     */
    public void mostrar(){
        if (!EsVacio()){
            Nodo aux = pFirst;
            String expresion = "";
            while(aux != null){
               expresion = expresion + aux.getDato().toString() + "\n";
               aux = aux.getPnext();
            }
            JOptionPane.showMessageDialog(null,expresion);
            
        }else{
            JOptionPane.showMessageDialog(null, "La lista esta vacia");
        }
    }
    
    
    
    
    /**
     * Elimina un nodo de la lista según su referencia.
     * 
     * @param referencia El objeto a eliminar de la lista.
     */
    public void EliminarPorReferencia(Object referencia){

        if (buscar(referencia)) {
            if (pFirst.getDato() == referencia) {
                pFirst = pFirst.getPnext();
            } else{
                Nodo aux = pFirst;
                while(aux.getPnext().getDato() != referencia){
                    aux = aux.getPnext();
                }
                Nodo siguiente = aux.getPnext().getPnext();
                aux.setPnext(siguiente);  
            }
            size--;
        }
    }
    
    
    /**
     * Obtiene el valor de un nodo en una posición determinada.
     * 
     * @param posicion La posición del nodo en la lista.
     * @return El valor del nodo en la posición dada o null si la posición es inválida.
     */
    public Object getValor(int posicion){

        if(posicion>=0 && posicion<size){
            
            if (posicion == 0) {
                return pFirst.getDato();
            }else{
                Nodo aux = pFirst;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getPnext();
                }
                return aux.getDato();
            }
        }
        return null;
    }
    
      
    /**
     * Busca un elemento en la lista.
     * 
     * @param referencia El objeto a buscar.
     * @return true si el elemento se encuentra en la lista, false en caso contrario.
     */
    public boolean buscar(Object referencia){
        Nodo aux = pFirst;
        boolean encontrado = false;
        while(aux != null && encontrado != true){
            if (referencia == aux.getDato()){ 
                encontrado = true;
            }
            else{
                aux = aux.getPnext();
            }
        }
        return encontrado;
    }
    
    /**
     * Elimina todos los elementos de la lista y la vacía.
     */
    public void eliminar(){
        pFirst = null;
        size = 0;
    }
     /**
     * Inserta las letras A-J en la lista como nombres de columnas.
     */
    public void nombrarColumnas(){
        this.InsertarFinal("A");
        this.InsertarFinal("B");
        this.InsertarFinal("C");
        this.InsertarFinal("D");
        this.InsertarFinal("E");
        this.InsertarFinal("F");
        this.InsertarFinal("G");
        this.InsertarFinal("H");
        this.InsertarFinal("I");
        this.InsertarFinal("J");
    
    }
    /**
     * Obtiene el valor de un nodo en la posición especificada.
     * 
     * @param index Índice del nodo a obtener.
     * @return El valor del nodo en la posición dada o null si la posición es inválida.
     */
    public Object get(int index){    
        if(index>=0 && index< size){
            Nodo aux=pFirst;
            for(int i=0;i<index;i++){
            aux=aux.getPnext();
            }
        return aux.getDato();
        }else{
            return null;
        }}
}
