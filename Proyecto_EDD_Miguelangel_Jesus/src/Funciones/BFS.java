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
    private static boolean visitado [][];
    private Cola<Casilla> cola;
    private int movFila [];
    private Grafo grafo;

    public BFS(Grafo grafo){
        this.grafo=grafo;
        this.visitado=new boolean[grafo.cantidadVertices()][grafo.cantidadVertices()];
        this.cola=new Cola<>();
    }
    
    public  int intColumnas(String columna){
        return (columna.toUpperCase().charAt(0))-'A';
    }

    public  void Barrer(int filaInicio,String columnaInicio){
        Nodo nodo=grafo.getVertices().getpFirst();
        Casilla aux=(Casilla)nodo.getDato();
        int filas=grafo.cantidadVertices();
        if(filas>0){
            String columnas=aux.getColumna();
        }
        int numeroColumnas=intColumnas(columnaInicio);
    
        Cola<Casilla> cola=new Cola();
        String nombreInicio=columnaInicio+ filaInicio;
        Casilla casillaInicio=grafo.buscar(nombreInicio);
        
        
        int [] movFila={-1,-1,-1,0,0,1,1,1};
        //////Muestra los diferentes movimientos que puede
        ///tener las filas.Los-1 son arriba los 0 es que no se mueve y 1 abajo

        int [] movColumnas={-1,0,1,-1,1,-1,0,1};
        //////Muestra los diferentes movimientos que puede
        ///tener las columnas.Los-1 son izquierda los 0 es que no se mueve y 1 abajo
    
        if(casillaInicio !=null){
            if(casillaInicio.isMina()==true){
               System.out.println("Perdiste");
               return;
           }
            cola.Encolar(casillaInicio);}
        else{
            System.out.println("Casilla de inicio no exite");
            return;
        }
    
        while(!cola.esVacio()){
            Casilla casillaActual=cola.Desencolar();
            int filaActual=casillaActual.getFila();
            int columnaActual=casillaActual.getColumna().charAt(0)-'A';
            if(!visitado[filaActual][columnaActual]){
                visitado[filaActual][columnaActual]=true;
                int numMinasCercanas=casillaActual.cantidadMinasAdy();
                casillaActual.Revelar();              
                //Detiene la funcion si hay un pista
                if(numMinasCercanas>0){
                    continue;
                }

                for (int i=0;i<8;i++){
                    int nuevaFila=filaActual+movFila[i];
                    int nuevaColumna=columnaActual+movColumnas[i];

                    //Verifica que la casilla se encuentre dentro de los limites 
                    if(nuevaFila>=0 && nuevaFila<filas){
                        if (nuevaColumna>=0 && nuevaColumna<numeroColumnas){
                            String nombreVecino=(char)('A'+nuevaColumna)+
                            String.valueOf(nuevaFila);

                            Casilla casillaVecina=grafo.buscar(nombreVecino);

                            if(casillaVecina!=null && !visitado[nuevaFila][nuevaColumna]){
                                int numMinascercanas= casillaVecina.cantidadMinasAdy();
                                if(!casillaVecina.isMina() && numMinascercanas==0){
                                    cola.Encolar(casillaVecina);
                                    //visitado[nuevaFila][nuevaColumna]=true;
//                                    if(!casillaVecina.estaRevelada()){
//                                       casillaVecina.Revelar();
//                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

    

   

