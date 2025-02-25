package ejerciciosEstructurasDeControl;

import javax.swing.*;

public class Ejercicio6 {
    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog(null, "Ingresa su nombre");
        int identidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su numero de identificacion"));
        String tipoObrero = JOptionPane.showInputDialog(null, "Ingresa su categoria").toLowerCase();
        double salarioAnt = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto de su salario actual"));
        double incremento=1;
        String aumento="s";
        switch (tipoObrero){
            case "a":
                incremento=1.3;
                aumento="30%";
                break;
            case "b":
                incremento=1.2;
                aumento="20%";
                break;
            case "c":
                incremento=1.1;
                aumento="10%";
                break;
            default:JOptionPane.showMessageDialog(null,"ingrese un valor valido");
                break;


        }
        double nuevoSalario =incremento*salarioAnt ;

        String msj = ">>>>>TABLA AUMENTOS<<<<<<\n _______________________________________________\nCedula:"+identidad+",\nNombre: "+nombre+", Tipo: "+tipoObrero+", Aumento: "+aumento+", Salario Anterior: "+salarioAnt+", Salario Final: "+nuevoSalario;
        JOptionPane.showMessageDialog(null,msj);



    }
}
