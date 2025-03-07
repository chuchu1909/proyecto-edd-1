/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import Interfaces.Tablero;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class GuardarCSV {
    Tablero tablero;
    private String jugadas;
    
    public void guardarJuego(String Tablero) throws IOException{
        try {
        FileWriter escribir=new FileWriter(Tablero);
        escribir.write("Tablero:\n");
        escribir.write(Tablero);
        escribir.close();
            System.out.println("Su juego a sido guardado");
    }catch(IOException e){
            System.out.println("Ocurrio un error");
    }}
    
//    private void btnClick(ActionEvent e) {
//        JButton btn=(JButton)e.getSource();
//        String[] coordenada=btn.getName().split(",");
//        int posFila=Integer.parseInt(coordenada[0]);
//        String posColumna=coordenada[1];
//        JOptionPane.showMessageDialog(rootPane, posFila+","+posColumna);
//        String r=botonesTablero.toString();
//        guardarJuego(r);
//        //coordenadasLabel.setText("Fila:"+posFila+"Columna:"+posColumna);
//        
//        
//    }
}
