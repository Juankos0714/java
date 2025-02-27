package ejerciciosEstructurasDeControl;

import javax.swing.*;

public class Ejercicio8 {
    public static void main(String[] args) {
        double x = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor"));;
        double valueAbsolut = x >= 0 ? x:-x;
        System.out.println(valueAbsolut);






    }
}
