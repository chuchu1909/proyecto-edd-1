/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

/**
 *Esta clase consiste en algunas funciones extras para ayudar en validaciones
 * @author Miguel
 * @version: 9/03/2025/A
 */
public class Helpers {
    /**
 * Valida si una cadena de texto contiene solo números.
 * 
 * @param num Cadena de texto a validar.
 * @return {@code true} si la cadena contiene solo dígitos del 0 al 9, de lo contrario {@code false}.
 */
    private boolean validarnumeros(String num){
        return num.matches("[0-9]*");
    }
    /**
 * Convierte una cadena de texto en un número entero si es válida.
 * 
 * @param numero Cadena de texto que representa un número.
 * @return El número convertido si es válido, de lo contrario {@code -1}.
 */
    public int convertirNumero(String numero){
        if(validarnumeros(numero)== true){
            int num = Integer.parseInt(numero);
            return num;
        }else{
            return -1;
        }
    }
    
    
}
