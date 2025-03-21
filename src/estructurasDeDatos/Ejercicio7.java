package estructurasDeDatos;

import javax.swing.*;

public class Ejercicio7 {
    public static void main(String[] args){

        int i=0;
        int tamaño = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos números desea ingresar?"));
        int[] numbers = new int[tamaño];


        do {
            if (i < tamaño) {
                numbers[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dato entero"));
                i++;
            } else {
                JOptionPane.showMessageDialog(null, "¡El array está lleno! No se pueden agregar más datos.");
                break;
            }
        } while (JOptionPane.showConfirmDialog(null, "¿Desea agregar otro dato?", "Repetir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        for (int y=0; y < i;y++){
            System.out.println(numbers[y]);
        }


    }
}
