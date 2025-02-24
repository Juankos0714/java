package ejerciciosEstructurasDeControl;

import javax.swing.JOptionPane;
public class Ejercicio1 {
    public static void main(String[] args) {
        double num1 = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el numero"));
        double resultado;

        if(num1<0){
            resultado= num1*-1;
        }else{
            resultado=num1;
        }

        String msj = "El numero convertido es "+resultado;



        JOptionPane.showMessageDialog(null,msj);

    }
}


