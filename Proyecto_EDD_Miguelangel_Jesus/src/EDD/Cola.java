/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Miguel
 */
public class Cola<T> {
    private Nodo<T> pFront; //nodo apuntador al primero
    private int size; //tamaño de la lista
    private Nodo <T> pBack;
    
    public Cola() {
        this.pFront = null;
        this.size = 0;
        this.pBack=null;
    }
    
    //Funcion para verificar si la cola esta vacia
    public boolean esVacio(){
        return this.pFront == null;
    }
    
    //Agrega un elemento a la cola
    public void Encolar(T dato){
        Nodo <T> pNew = new Nodo(dato);
        if(esVacio()){
            pFront = pNew;
            pBack= pNew;
        }else{
            pBack.setPnext(pNew);
            pBack=pNew;
        }
        size++;
    }
    public void Destructor(){
        int size=0;
        this.pFront = null;
        this.pBack=null;
    }
    
    public T Desencolar(){
        if((esVacio())){
           System.out.println("Esta vacia la cola");
        return null;}
        T dato=(T) pFront.getDato();
        pFront=pFront.getPnext();
        if(pFront==null){
            this.pBack=null;
        }size--;
        return dato;
    }

    /**
     * @return the pFront
     */
    public Nodo getpFront() {
        return pFront;
    }

    /**
     * @param pFront the pFront to set
     */
    public void setpFront(Nodo pFront) {
        this.pFront = pFront;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the pBack
     */
    public Nodo getpBack() {
        return pBack;
    }

    /**
     * @param pBack the pBack to set
     */
    public void setpBack(Nodo pBack) {
        this.pBack = pBack;
    }
    }
    

