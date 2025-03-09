/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import ClasesPrincipales.Casilla;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class Grafo {
    private Lista vertices;

    public Grafo() {
        this.vertices = new Lista();
    }
    
    public Lista getVertices() {
        return vertices;
    }

    public void setVertices(Lista vertices) {
        this.vertices = vertices;
    }
    
    public int cantidadVertices(){
        return this.vertices.getSize();
    }
    
    public boolean esVacio() {
        return this.vertices.EsVacio();
    }
    
    public Casilla buscar(String nombreCasilla) {
        if (!this.esVacio()) {
            Nodo aux = this.vertices.getpFirst();
            while (aux != null) {
                Casilla casillaActual = (Casilla) aux.getDato();
                if (casillaActual.getNombre().equalsIgnoreCase(nombreCasilla)) {
                    return casillaActual;
                }
                aux = aux.getPnext();
            }
            return null;
        }
        return null;
    }
    
    public void insertarCasilla(String columna, int fila) {
        String nombreCasilla = columna + fila;
        if (this.buscar(nombreCasilla) == null) {
            Casilla casilla = new Casilla(columna, fila);
            casilla.setNumVertice(this.vertices.getSize());
            this.vertices.InsertarFinal(casilla);
        }
    }
    
    public void agregarAdyacencia(String casilla1, String casilla2) {
        if (this.buscar(casilla1) != null && this.buscar(casilla2) != null) {
            Casilla casillaInicio = this.buscar(casilla1);
            Casilla casillaFin = this.buscar(casilla2);
            
            if(!casillaInicio.buscarAdy(casilla2)){
                casillaInicio.agregarAdy(casillaFin);
            }
            
            if(!casillaFin.buscarAdy(casilla1)){
                casillaFin.agregarAdy(casillaInicio);
            }
           
        } else {
            if (this.buscar(casilla1) == null && this.buscar(casilla2) != null) {
                JOptionPane.showMessageDialog(null, "La estacion de inicio no existe.");
            } else if (this.buscar(casilla1) != null && this.buscar(casilla2) == null) {
                JOptionPane.showMessageDialog(null, "La estacion de llegada no existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Ninguna de las estaciones existe.");
            }
        }
    }
    
    public void eliminarAdyacente(String casilla1, String casilla2) {
        if (this.buscar(casilla1) != null && this.buscar(casilla2) != null) {
            Casilla casillaInicio = this.buscar(casilla1);
            Casilla casillaFin = this.buscar(casilla2);

            if (casillaInicio.getAdyacentes().buscar(casillaFin)) {
                casillaInicio.getAdyacentes().EliminarPorReferencia(casillaFin);
                casillaFin.getAdyacentes().EliminarPorReferencia(casillaInicio);

                JOptionPane.showMessageDialog(null, "Conexion eliminada con exito");
            } else {
                JOptionPane.showMessageDialog(null, "No son adyacentes.");
            }

        } else {
            if (this.buscar(casilla1) == null && this.buscar(casilla2) != null) {
                JOptionPane.showMessageDialog(null, "La estacion de inicio no existe.");
            } else if (this.buscar(casilla1) != null && this.buscar(casilla2) == null) {
                JOptionPane.showMessageDialog(null, "La estacion de llegada no existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Ninguna de las estaciones existe.");
            }
        }
    }
    
    public void getAdyacentes(String nombreCasilla) {
        if (this.buscar(nombreCasilla) != null) {
            Casilla casilla = this.buscar(nombreCasilla);
            JOptionPane.showMessageDialog(null, casilla.imprimirAdy());
        } else {
            JOptionPane.showMessageDialog(null, "La estacion ya existe.");
        }
    }

    public boolean estanConectados(String casilla1, String casilla2) {
        if (this.buscar(casilla1) != null && this.buscar(casilla2) != null) {
            Casilla estacionInicio = this.buscar(casilla1);
            Casilla estacionFinal = this.buscar(casilla2);

            if (estacionInicio.getAdyacentes().buscar(estacionFinal)) {
                JOptionPane.showMessageDialog(null, "Si estan conectadas");
                return true;
            } else {

                JOptionPane.showMessageDialog(null, "No son adyacentes.");
                return false;
            }

        } else {
            if (this.buscar(casilla1) == null && this.buscar(casilla2) != null) {
                JOptionPane.showMessageDialog(null, "La estacion de inicio no existe.");
            } else if (this.buscar(casilla1) != null && this.buscar(casilla2) == null) {
                JOptionPane.showMessageDialog(null, "La estacion de llegada no existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Ninguna de las estaciones existe.");
            }

            return false;
        }
    }
    
    public int verticesMarcados(){
        int count = 0;
        for (int i = 0; i < this.vertices.getSize(); i++) {
            Casilla casillaActual = (Casilla) this.vertices.getValor(i);
            if(casillaActual.isEstaMarcada()){
                count++;
            }
        }
        
        return count;
    }
    
    public int cantidadMinas(){
        int count = 0;
        for (int i = 0; i < this.vertices.getSize(); i++) {
            Casilla casillaActual = (Casilla) this.vertices.getValor(i);
            if(casillaActual.isMina()){
                count++;
            }
        }
        
        return count;
    }

    public void destruir() {
        this.vertices = new Lista();
    }

    @Override
    public String toString() {
        if (!this.esVacio()) {
            String estacionesStr = "";
            Nodo aux = this.vertices.getpFirst();
            while (aux.getPnext()!= null) {
                Casilla estacionActual = (Casilla) aux.getDato();
                estacionesStr += estacionActual.getNombre() + " ---> " + estacionActual.imprimirAdy()+ "\n";
                aux = aux.getPnext();
            }
            Casilla estacionActual = (Casilla) aux.getDato();
            estacionesStr += estacionActual.getNombre() + " ---> " + estacionActual.imprimirAdy();
            return estacionesStr;
        } else {
            return "Grafo vacion";
        }
    }
    
    
    
}
