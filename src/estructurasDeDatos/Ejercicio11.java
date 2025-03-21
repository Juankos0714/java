package estructurasDeDatos;

import javax.swing.*;

public class Ejercicio11 {
    public static void main(String[] args){

        int i=0;
        int tamaño = 8;
        int countpar=0;
        int countinpar=0;
        int[] numbers = new int[tamaño];


        do {
            if (i < tamaño) {
                numbers[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dato entero"));
                i++;
                if(numbers[i]%2==1){
                    countinpar++;

                }else{
                    countpar++;
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡El array está lleno! No se pueden agregar más datos.");
                break;
            }
        } while (JOptionPane.showConfirmDialog(null, "¿Desea agregar otro dato?", "Repetir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        System.out.println("Numero de impares"+countinpar);
        System.out.println("Numero de pares"+countpar);


    }
}
