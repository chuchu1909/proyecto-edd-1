/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesPrincipales;

import EDD.Lista;
import EDD.Nodo;

/**
 *
 * @author Miguel
 */
public class Casilla {
    private int numVertice;
    private String columna;
    private int fila;
    private boolean mina;
    private boolean estaMarcada;
    private boolean estaBarrida;
    private Lista adyacentes;

    public Casilla(String columna, int fila) {
        this.columna = columna;
        this.fila = fila;
        this.mina = false;
        this.estaMarcada = false;
        this.estaBarrida = false;
        this.adyacentes = new Lista();
        int numVertice;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public boolean isEstaMarcada() {
        return estaMarcada;
    }

    public void setEstaMarcada(boolean estaMarcada) {
        this.estaMarcada = estaMarcada;
    }

    public boolean isEstaBarrida() {
        return estaBarrida;
    }

    public void setEstaBarrida(boolean estaBarrida) {
        this.estaBarrida = estaBarrida;
    }

    public Lista getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(Lista adyacetes) {
        this.adyacentes = adyacetes;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }
    
    public int getNumVertice() {
        return numVertice;
    }

    public void setNumVertice(int numVertice) {
        this.numVertice = numVertice;
    }

    public String getNombre(){
        return this.columna + this.fila;
    }
     public int cantidadAdy() {
        return this.adyacentes.getSize();
    }

    public void agregarAdy(Casilla casilla) {
        this.adyacentes.InsertarFinal(casilla);
    }
    
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

    public int cantidadMinasAdy() {
        int count = 0;
        for (int i = 0; i < this.adyacentes.getSize(); i++) {
            Casilla casillaActual = (Casilla) this.adyacentes.getValor(i);
            if(casillaActual.mina){
                count++;
            }
        }
        return count;
    }

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

    public String marcadaStr() {
        if (this.estaMarcada) {
            return "Si";
        }

        return "No";
    }

    public String minaStr() {
        if (this.mina) {
            return "Si";
        }

        return "No";
    }
    public void Revelar() {
        revelada = true;
    }
    public String barridaStr() {
        if (this.estaBarrida) {
            return "Si";
        }

        return "No";
    }

    @Override
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
