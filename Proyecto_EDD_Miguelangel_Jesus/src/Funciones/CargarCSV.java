/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import ClasesPrincipales.Casilla;
import EDD.Grafo;

/**
 *
 * @author Miguel
 */
public class CargarCSV {
    public Grafo cargarCSV(String grafoStr){
       Grafo grafo = new Grafo();
       
       String[] lineas = grafoStr.split("\n");
 
       for (int i = 1; i < lineas.length; i++) {
           //Fila,Columna,EsMina,MarcadaConBandera,EstaRevelada
           String[] infoV = lineas[i].split(",");
           int fila = Integer.parseInt(infoV[0]);
           String columna = infoV[1];
           boolean esMina = ("true".equals(infoV[2])); 
           boolean marcadaConBandera = ("true".equals(infoV[3])); 
           boolean estaRevelada = ("true".equals(infoV[4]));
           
           grafo.insertarCasilla(columna, fila);
           String nombre = columna + infoV[0];
           
           Casilla casillaNueva = grafo.buscar(nombre);
           
           casillaNueva.setMina(esMina);
           casillaNueva.setRevelada(estaRevelada);
           casillaNueva.setEstaMarcada(marcadaConBandera);
       }
       
       return grafo;
   } 
    
}
