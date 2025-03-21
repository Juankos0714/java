package estructurasDeDatos;

import javax.swing.*;

public class Ejercicio8 {
    public static void main(String[] args){

        int i=0;
        int tamaño = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos nombres desea ingresar?"));
        String[] names = new String[tamaño];
        for (int y=0; y < tamaño;y++){
            if (i < tamaño) {
                names[i] = JOptionPane.showInputDialog("Ingrese el nombreo");
                i++;
            } else {
                JOptionPane.showMessageDialog(null, "¡El array está lleno! No se pueden agregar más datos.");
                break;
            }
        }
        for (String name : names) {
            System.out.println("Nombre: " + name);
        }








        }
}
