package estructurasDeDatos;

import javax.swing.*;

public class EjemploSumaArreglos {
    public static void main(String[] args){
        int n=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamano"));
        int[] a=new int[n];
        int[] b=new int[a.length];
        int c[]=new int[n];

        for(int i =0;i<a.length;i++){
            a[i]=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de a en la posicion "+i));
            System.out.println(a[i]+"|");
        }
        System.out.println();
        for(int i =0;i<b.length;i++){
            b[i]=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de a en la posicion "+i));
            System.out.println(b[i]+"|");


        }
        System.out.println();
        for (int i = 0; i < c.length; i++) {
            c[i]=a[i]+b[i];
            System.out.println(c[i]+"|");


        }
    }



}