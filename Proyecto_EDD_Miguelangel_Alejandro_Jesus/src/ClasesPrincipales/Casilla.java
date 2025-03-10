/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesPrincipales;

import EDD.Lista;
import EDD.Nodo;

/**
 *La clase Casilla es basicamente el vertice de el grafo. En este caso contiene todos los metodos que son utiles para los vertices como :
 * 
 * 
 * Buscar las minas adyacentes, cantidad de minas ady, imprimir ady, etc. Basicamente contiene metodos que facilitan informacion de 
 * las casillas.
 * @author Miguel
 * @version: 9/03/2025/A 
 */
public class Casilla {
    // Campos de la clase 
    private int numVertice;
    private String columna;
    private int fila;
    private boolean mina;
    private boolean estaMarcada;
    private boolean revelada;
    private Lista adyacentes;
    /**
     * Inicializar el contructor de la Casilla
     * @param columna es un string que va a contener las letras de las casillas
     * @param fila es un int que va a contener los numeros de las casillas
     */

    public Casilla( String columna,int fila) {
        this.columna = columna;
        this.fila = fila;
        this.mina = false;
        this.estaMarcada = false;
        this.revelada = false;
        this.adyacentes = new Lista();
        int numVertice;
    }// fin del constructor 
    
    

/**
 * Obtiene la fila de la casilla en el tablero.
 *
 * @return el número de fila de la casilla.
 */
        public int getFila() {
        return fila;
    }
/**
 * Establece la fila de la casilla en el tablero.
 *
 * @param fila el número de fila que se asignará a la casilla.
 */
    public void setFila(int fila) {
        this.fila = fila;
    }
    /**
 * Verifica si la casilla contiene una mina.
 *
 * @return {@code true} si la casilla contiene una mina, {@code false} en caso contrario.
 */

    public boolean isMina() {
        return mina;
    }
/**
 * Asigna si la casilla contiene una mina o no.
 *
 * @param mina {@code true} si la casilla tendrá una mina, {@code false} en caso contrario.
 */
    public void setMina(boolean mina) {
        this.mina = mina;
    }
/**
 * Verifica si la casilla está marcada por el jugador.
 *
 * @return {@code true} si la casilla está marcada, {@code false} en caso contrario.
 */
    public boolean isEstaMarcada() {
        return estaMarcada;
    }
/**
 * Establece si la casilla está marcada por el jugador.
 *
 * @param estaMarcada {@code true} si la casilla debe marcarse, {@code false} en caso contrario.
 */
    public void setEstaMarcada(boolean estaMarcada) {
        this.estaMarcada = estaMarcada;
    }
    /**
 * Verifica si la casilla ha sido revelada.
 *
 * @return {@code true} si la casilla ha sido revelada, {@code false} en caso contrario.
 */

    public boolean estaRevelada() {
        return revelada;
    }
    /**
 * Establece si la casilla ha sido revelada.
 *
 * @param revelada {@code true} si la casilla debe revelarse, {@code false} en caso contrario.
 */

    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }
/**
 * Obtiene la lista de casillas adyacentes a esta casilla.
 *
 * @return una lista de casillas adyacentes.
 */
    public Lista getAdyacentes() {
        return adyacentes;
    }
    /**
 * Establece la lista de casillas adyacentes a esta casilla.
 *
     * @param adyacetes
 */

    public void setAdyacentes(Lista adyacetes) {
        this.adyacentes = adyacetes;
    }
/**
 * Obtiene la columna en la que se encuentra la casilla.
 *
 * @return una cadena que representa la columna de la casilla.
 */
    public String getColumna() {
        return columna;
    }
/**
 * Establece la columna en la que se encuentra la casilla.
 *
 * @param columna la columna que se asignará a la casilla.
 */
    public void setColumna(String columna) {
        this.columna = columna;
    }
    /**
 * Obtiene el número de vértice asignado a la casilla en el grafo.
 *
 * @return el número de vértice de la casilla.
 */
    
    public int getNumVertice() {
        return numVertice;
    }
/**
 * Establece el número de vértice de la casilla en el grafo.
 *
 * @param numVertice el número de vértice que se asignará.
 */
    public void setNumVertice(int numVertice) {
        this.numVertice = numVertice;
    }
    /**
 * Obtiene el nombre de la casilla, que es una combinación de la columna y la fila.
 *
 * @return una cadena representando el nombre de la casilla (ejemplo: "A3").
 */

    public String getNombre(){
        return this.columna + this.fila;
    }
    
    /**
 * Obtiene la cantidad de casillas adyacentes a esta casilla.
 *
 * @return el número de casillas adyacentes.
 */
     public int cantidadAdy() {
        return this.adyacentes.getSize();
    }
     
     /**
 * Agrega una casilla a la lista de adyacentes.
 *
 * @param casilla la casilla que se agregará como adyacente.
 */

    public void agregarAdy(Casilla casilla) {
        this.adyacentes.InsertarFinal(casilla);
    }
    /**
     * Esta funcion busca las casillas ady de una casilla en especifico 
     * @param casilla este parametro es el nombre de la casilla completo en string
     * @return un booleano es lo que retorna para saber si se encuentra o no 
     */
    public boolean buscarAdy(String casilla) {
        boolean encontrado = false;
        if (!this.adyacentes.EsVacio()) {
            for (int i = 0; i < this.adyacentes.getSize(); i++) {
                Casilla casillaActual = (Casilla) this.adyacentes.getValor(i);
                if (casillaActual.getNombre().equalsIgnoreCase(casilla)) {
                    encontrado = true;
                    break;
                }
            }
        }
        return encontrado;
    }
/**
 * Esta funcion solo retorna una cantidad de minas ady 
 * @return retorna el numero de minas ady de una casilla
 */
    public int cantidadMinasAdy() {
        int count = 0;
        for (int i = 0; i < this.adyacentes.getSize(); i++) {
            Casilla casillaActual = (Casilla) this.adyacentes.getValor(i);
            if(casillaActual.isMina()){
                count++;
            }
        }
        return count;
    }
/**
 * Imprime en forma de un string las ady de una casilla 
 * @return string con todas las ady de una casilla 
 */
    public String imprimirAdy() {
        if (!this.adyacentes.EsVacio()) {
            Nodo aux = this.adyacentes.getpFirst();
            String adyacentesStr = "";
            while (aux.getPnext() != null) {
                Casilla casillaActual = (Casilla) aux.getDato();
                adyacentesStr += casillaActual.getNombre() + " ---> ";

                aux = aux.getPnext();
            }
            Casilla casillaActual = (Casilla) aux.getDato();
            adyacentesStr += casillaActual.getNombre();

            return adyacentesStr;
        }

        return "No tiene adyacentes";
    }
/**
 * Devuelve una representación en cadena de si la casilla está marcada.
 *
 * @return "Si" si la casilla está marcada, "No" en caso contrario.
 */
    public String marcadaStr() {
        if (this.estaMarcada) {
            return "Si";
        }

        return "No";
    }
/**
 * Devuelve una representación en cadena de si la casilla contiene una mina.
 *
 * @return "Si" si la casilla contiene una mina, "No" en caso contrario.
 */
    public String minaStr() {
        if (this.mina) {
            return "Si";
        }

        return "No";
    }
/**
 * Devuelve una representación en cadena de si la casilla ha sido revelada.
 *
 * @return "Si" si la casilla ha sido revelada, "No" en caso contrario.
 */
    public String barridaStr() {
        if (this.revelada) {
            return "Si";
        }

        return "No";
    }
    
/**
 * Revela la casilla, cambiando su estado a revelada.
 */
    public void revelar() {
        this.revelada = true;
    }
    
    
   
    @Override
    /**
     * Una funcion para poder modificar a gusto los parametros de la clase
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNombre: ").append(this.getNombre());
        sb.append("\nMina: ").append(this.minaStr());
        sb.append("\nMarcada: ").append(this.marcadaStr());
        sb.append("\nBarrida: ").append(this.barridaStr());
        sb.append("\nAdyacentes: ").append(this.imprimirAdy());
        return sb.toString();
    } 
    
    
}
