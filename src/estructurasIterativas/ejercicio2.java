package estructurasIterativas;

import javax.swing.*;

public class ejercicio2 {
    public static void main(String arg[]){
        int x=0;
        int n=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero a imprimir"));
//        do{
//            System.out.println(x);
//            x++;
//        }while(x<=n);

//         while(x<=n){
//        System.out.println(x);
//          x++;
//        }
        for(;x<=n;x++){
            System.out.println(x);
        }

    }

}
