package ejerciciosEstructurasDeControl;

import javax.swing.*;

public class ejercicio4 {
    public static void main(String[] args) {
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto de su salario actual"));
        double salario2 = (salario*0.25)+salario;
        String msj = "El salario nuevo es " +salario2;
        JOptionPane.showMessageDialog(null,msj);




    }
}
