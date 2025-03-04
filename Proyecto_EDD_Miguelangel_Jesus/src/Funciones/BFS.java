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
public class BFS {
    private boolean visitado [][];
    private Cola<Casilla> cola;
    private int movFila [];
    private static Grafo grafo;

    public BFS(Grafo grafo){
        BFS.grafo=grafo;
        this.visitado=new boolean[grafo.cantidadVertices()][grafo.cantidadVertices()];
        this.cola=new Cola<>();
    }
    
    public int intColumnas(String columna){
        return (columna.toUpperCase().charAt(0))-'A';
    }

    public void inicializar(int filaInicio,String columnaInicio){
        Nodo nodo=grafo.getVertices().getpFirst();
        Casilla aux=(Casilla)nodo.getDato();
        int filas=grafo.cantidadVertices();
        if(filas>0){
            String columnas=aux.getColumna();
        }
        int numeroColumnas=intColumnas(columnaInicio);
    
        //TODO:Funcion verificar mina
        //TODO:FUNCION ESTA revelada
        //TODO Funcion revelar
        //TODO Funcion minas cercanas no expadir 

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
            cola.Encolar(casillaInicio);}
        else{
            System.out.println("Casilla de inicio no exite");
        }
    
        while(!cola.esVacio()){
            Casilla casillaActual=cola.Desencolar();
            int filaActual=casillaActual.getFila();
            int columnaActual=Integer.parseInt(casillaActual.getColumna());

            for (int i=0;i<8;i++){
                int nuevaFila=filaActual+movFila[i];
                int nuevaColumna=columnaActual+movColumnas[i];
                String nombreVecino=(char)('A'+nuevaColumna)+
                String.valueOf(nuevaFila);

                Casilla casillaVecina=grafo.buscar(nombreVecino);

                if(casillaVecina!=null && !visitado[nuevaFila][nuevaColumna]){
                    visitado[nuevaFila][nuevaColumna]=true;
                    cola.Encolar(casillaVecina);
                    {

                            //Un if{} TODO:FUNCION ESTA revelada  y //TODO:Funcion verificar mina
                            //Un if{}  TODO NumMinas cercanas  
                    }
                }
            }
        }
    }
}

//public  void main(String[] args) {
//       Grafo grafo=new Grafo();
//       Casilla casillaA0=new Casilla("A",0);
//       Casilla casillaB1=new Casilla("B",1);
//       Casilla casillaC2=new Casilla("C",2);      
//       grafo.agregarCasilla(casillaA0);
//       grafo.agregarCasilla(casillaB1);
//       grafo.agregarCasilla(casillaC2);
//
//       BFS bfs=new BFS(grafo);
//        bfs.inicializar(0, "A");
//
//}

