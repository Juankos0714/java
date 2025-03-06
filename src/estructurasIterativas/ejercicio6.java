package estructurasIterativas;

import javax.swing.*;

public class ejercicio6 {
    public static void main(String arg[]){
        int num = 1;
        do{
            num = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero a imprimir"));
            System.out.println(num);
        }
        while(num!=0);
    }
}
