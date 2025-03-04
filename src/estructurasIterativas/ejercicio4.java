package estructurasIterativas;

import javax.swing.*;

public class ejercicio4 {
    public static void main(String arg[]){
        int x=0;
        int n=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero que desea sumar"));
        int inc=0;
        int i=1;
//        do{
//            inc++;
//            x+=inc;
//            i++;
//        }while(i<=n);

        while(i<=n){
            i++;
            inc++;
            x+=inc;
        }
//        for(;i<=n;i++){
//            inc++;
//            x+=inc;



        System.out.println(x);

    }
}