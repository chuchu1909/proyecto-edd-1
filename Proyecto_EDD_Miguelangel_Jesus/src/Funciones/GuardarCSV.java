/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import ClasesPrincipales.Casilla;
import static Interfaces.Inicio.buscaMinaApp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *Carga un grafo a partir de una representación en formato CSV.
 * @author Miguel
 * @version: 9/03/2025/A
 */
public class GuardarCSV {
    /**
 * Guarda el estado actual de la partida en un archivo CSV.
 * 
 * @param botonesTablero Matriz de botones que representa el tablero del juego.
 * @param archivo Archivo en el que se guardará la partida.
 */
    
    public static void guardarPartida(JButton[][] botonesTablero, File archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            // Escribir las cabeceras del archivo CSV
            writer.write("Fila,Columna,EsMina,MarcadaConBandera,EstaRevelada\n");

            // Recorrer el tablero de botones
            for (int i = 0; i < botonesTablero.length; i++) {
                for (int j = 0; j < botonesTablero[i].length; j++) {
                    JButton btn = botonesTablero[i][j];

                    // Obtener las coordenadas del botón
                    String[] coordenadas = btn.getName().split(",");
                    int fila = Integer.parseInt(coordenadas[0]);
                    String columna = coordenadas[1];

                    // Obtener los datos de la casilla
                    Casilla casilla = buscaMinaApp.getGrafo().buscar(columna + fila);

                    // Si la casilla no es nula, guardamos la información
                    if (casilla != null) {
                        boolean esMina = casilla.isMina();
                        boolean estaMarcada = casilla.isEstaMarcada();
                        boolean estaRevelada = casilla.estaRevelada();  // Nueva variable para saber si está revelada

                        // Escribir la fila de datos en el archivo CSV
                        writer.write(fila + "," + columna + "," + esMina + "," + estaMarcada + "," + estaRevelada + "\n");
                    }
                }
            }

            // Informar al usuario que la partida ha sido guardada
            JOptionPane.showMessageDialog(null, "Partida guardada correctamente en " + archivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar la partida");
        }
    }
}


