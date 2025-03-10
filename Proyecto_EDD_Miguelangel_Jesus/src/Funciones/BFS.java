/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import ClasesPrincipales.Casilla;
import EDD.Cola;
import EDD.Grafo;
import EDD.Nodo;

/**
 *
 * @author aleja
 */
public class  BFS {
    private static boolean visitado [][];/*Matriz para marcar las casillas 
    que ya fueron visitadas*/ 
    private Cola<Casilla> cola; /*La cola nos sirve para manejar las casilla
    mientras b&uacutesca*/
    private int movFila [];/*Movimientos posibles de fila*/
    private int movColumnas[];/*Movimientos posibles de columna*/
    private Grafo grafo;/*Grafo para representar el tablero*/

    //Clase constructor de BFS
    public BFS(Grafo grafo){
        /*Se le asigna el grafo*/
        this.grafo=grafo;
        /*Se inicializa la matriz 'visitado' con el tamaño*/
        this.visitado=new boolean[grafo.cantidadVertices()]
        [grafo.cantidadVertices()];
        /*Se inicializa la col*/
        this.cola=new Cola<>();
    }
    
    /*M&eacutetodo de convertir las columnas a numeros.Las columnas estan
    letras*/
    public  int intColumnas(String columna){
        return (columna.toUpperCase().charAt(0))-'A';
    }

    /*M&eacutetodo para realizar la b&uacutesqueda y poder barrer las casillas*/
    public  void Barrer(int filaInicio,String columnaInicio){
        /*Accede al primer nodo de la lista de v&eacute de grafo*/
        Nodo nodo=grafo.getVertices().getpFirst();
        
        /*Lo convierte a una casilla*/
        Casilla aux=(Casilla)nodo.getDato();
        
        /*Obtiene la cantidad de filas en el grafo*/
        int filas=grafo.cantidadVertices();
        
        /*Se llama el m*&eacutetodo para convertir letras a numeros*/
        int numeroColumnas=intColumnas(columnaInicio);
    
        /*Se crea la cola*/
        Cola<Casilla> cola=new Cola();
        /*Se crea el nombre de la casilla de inico*/
        String nombreInicio=columnaInicio+ filaInicio;
        /*Se us&aacute el m&eacutetodo buscar de grafos para conseguir la 
        casilla de inicio*/
        Casilla casillaInicio=grafo.buscar(nombreInicio);
        
        
        int [] movFila={-1,-1,-1,0,0,1,1,1};
        //////Muestra los diferentes movimientos que puede
        ///tener las filas.Los-1 son arriba los 0 es que no se mueve y 1 abajo

        int [] movColumnas={-1,0,1,-1,1,-1,0,1};
        //////Muestra los diferentes movimientos que puede
        ///tener las columnas.Los-1 son izquierda los 0 es que no se mueve y 1 abajo
    
        if(casillaInicio !=null){
            if(casillaInicio.isMina()==true){
               System.out.println("Perdiste");/*caso de si te encuentras 
               una mina*/
               return;
           }
            /*Primera casilla que se procesa durante la b&uacutequeda.
            Taacutembien es primera que sedescubre */
            cola.Encolar(casillaInicio);}
        else{
            System.out.println("Casilla de inicio no exite");/*Caso si no tocas el tablero*/
            return;
        }
        /*Verifica que la cola no este vac&iacutea*/
        while(!cola.esVacio()){
            Casilla casillaActual=cola.Desencolar();
            /*Se obtiene la posici&oacuten de la casilla actual*/
            int filaActual=casillaActual.getFila();
            int columnaActual=casillaActual.getColumna().charAt(0)-'A';
            /*Se verifica que no haya sido visitada*/
            if(!visitado[filaActual][columnaActual]){
                /*Se marca como visitada*/
                visitado[filaActual][columnaActual]=true;
                /*Se obtiene las minas cercanas y se revela la casilla*/
                int numMinasCercanas=casillaActual.cantidadMinasAdy();
                casillaActual.Revelar();   
                
                //Detiene la funci&oacuten si hay un pista
                if(numMinasCercanas>0){
                    continue;
                }

                /*Recorre las 8 posibles direcci&oacute en las que se puede 
                mover la casilla actual*/
                for (int i=0;i<8;i++){
                    int nuevaFila=filaActual+movFila[i];/*Calcula la nueva 
                    fila*/
                    int nuevaColumna=columnaActual+movColumnas[i];/*Calcula la 
                    nueva columna*/

                    //Verifica que la casilla se encuentre dentro de los limites 
                    if(nuevaFila>=0 && nuevaFila<filas){
                        if (nuevaColumna>=0 && nuevaColumna<numeroColumnas){
                            /*Crea el nombre de la casilla vecina*/
                            String nombreVecino=(char)('A'+nuevaColumna)+
                            String.valueOf(nuevaFila);

                            /*Se us&aacute el m&eacutetodo buscar de grafos 
                            para conseguir la casilla vecina*/
                            Casilla casillaVecina=grafo.buscar(nombreVecino);

                            /*Verifca que exita una casilla vecina y que 
                            la casilla vecina no haya sido visitada */
                            if(casillaVecina!=null && !visitado[nuevaFila][nuevaColumna]){
                                /*Minas cercanas a la casilla vecina*/
                                int numMinascercanas= casillaVecina.cantidadMinasAdy();
                                /*Se verifica que la casilla vecina no sea una mina
                                y que la casilla vecina no tiene minas cerca*/
                                casillaVecina.Revelar();
                                /*Se revela la casilla vecina si no tienen minas 
                                y no ha sido visitada*/
                                if(!casillaVecina.isMina() && numMinascercanas==0){
                                    /*Se encola para ser procesada */
                                    cola.Encolar(casillaVecina);
                                       }
                            }
                        }
                    }
                }
            }
        }
    }
}

    

   

