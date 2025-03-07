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

/**
 *
 * @author Miguel
 */
public class GuardarCSV {
    public static void guardarPartida(JButton[][] botonesTablero, File archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            // Escribir las cabeceras del archivo CSV
            writer.write("Fila,Columna,EsMina,CantidadMinasAdyacentes,MarcadaConBandera\n");

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
                        int cantidadMinasAdyacentes = casilla.cantidadMinasAdy();
                        boolean estaMarcada = casilla.isEstaMarcada();

                        // Escribir la fila de datos en el archivo CSV
                        writer.write(fila + "," + columna + "," + esMina + "," + cantidadMinasAdyacentes + "," + estaMarcada + "\n");
                    }
                }
            }

            // Informar al usuario que la partida ha sido guardada
            System.out.println("Partida guardada correctamente en " + archivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar la partida");
        }
    }

}
