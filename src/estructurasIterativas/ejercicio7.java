package estructurasIterativas;

import javax.swing.*;

public class ejercicio7 {

    public static void main(String arg[]){
        double nota1;
        double nota2;
        double nota3;
        double Fnota;
        int opcion;

        do{
            nota1 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 1:"));
            nota2 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 2:"));
            nota3 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 3:"));
            Fnota = (nota1+nota2+nota3)/3;
            String mensaje = String.format("La nota final es de %.1f",Fnota);
            JOptionPane.showMessageDialog(null,mensaje);
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 para ssalir o cualquier numero para seguir"));

        }
        while(opcion!=0);
    }
}
