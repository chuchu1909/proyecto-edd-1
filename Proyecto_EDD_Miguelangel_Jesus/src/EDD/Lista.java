/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class Lista {
    private Nodo pFirst; //nodo apuntador al primero
    private int size; //tamaño de la lista
    
    //Constructor de la clase Lista
    public Lista() {
        this.pFirst = null;
        this.size = 0; 
    }
    
    //Metodos get y set para los atrubutos

    public Nodo getpFirst() {
        return pFirst;
    }

    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    //Primitivas
    
    //Funcion para verificar si la lista es vacia
    public boolean EsVacio(){
        return this.pFirst == null;
    }
    
    //Metodo que vacia la lista
    public void vaciar(){
        this.pFirst = null;
        this.size = 0;   
    }
    
   
    
    


    //Metodo para insertar al final
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
    
    
    
    
    //Metodo para Insertar por referencia
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
    
    
    //Metodo para obtener el valor de un nodo en una determinada posición
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
    
      
    // Funcion para buscar un elemento en la lista
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
    
    //Destructor
    public void eliminar(){
        pFirst = null;
        size = 0;
    }
    
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
