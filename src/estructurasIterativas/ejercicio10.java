package estructurasIterativas;

import javax.swing.*;

public class ejercicio10 {
    public static void main(String[] args) {
        int multiplo = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero de la tabla que deseas ver"));
        int inc = 1;
        int resultado = 0;
        while(inc<=10){
            resultado = multiplo*inc;
            System.out.println("El resultado de "+multiplo+" x "+inc+" es igual a "+resultado);
            inc++;
        }
    }
}
