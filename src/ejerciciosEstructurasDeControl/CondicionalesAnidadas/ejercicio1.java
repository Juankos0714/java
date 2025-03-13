package ejerciciosEstructurasDeControl.CondicionalesAnidadas;

import javax.swing.*;

public class ejercicio1 {
    public static void main(String[] args){
        double n =0;
        double conversion=0;
        String menu = """
               Tabla de opciones:
                1. Convertir de pulgadas a centímetros
                2. Convertir de centímetros a pulgadas
                3. Convertir de kilómetros a metros
                4. Convertir de metros a kilómetros.
                """;
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
        switch(opcion){
            case 1:
                n = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el numero a convertir de pulgadas a centimetros"));
                conversion = n*2.54;
                JOptionPane.showMessageDialog(null, n+" Pulgadas = "+conversion+" centimetros");
            break;
            case 2:
                n = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el numero a convertir de centimetros a pulgadas"));
                conversion = n/2.54;
                JOptionPane.showMessageDialog(null, n+" Centimetros = "+conversion+" pulgadas");
            break;
            case 3:
                n = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el numero a convertir de kilometros a metros"));
                conversion = n*1000;
                JOptionPane.showMessageDialog(null, n+" Kilometros = "+conversion+" metros");
            break;
            case 4:
                n = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el numero a convertir de metros a kilometros"));
                conversion = n/1000;
                JOptionPane.showMessageDialog(null, n+" metros = "+conversion+" kilometros");
            break;




        }

    }
}
