package TallerMetodos.Ejercicio1;

import javax.swing.*;

public class Principal {
    public void main (String[] args){
        int close=1;
        do {
            int cedula = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su numero de cedula"));
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"));
            String nombre = JOptionPane.showInputDialog("Ingrese su numero de cedula");
            close = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 para cerrar el ciclo"));
        }while(close!=0);
    }
}
