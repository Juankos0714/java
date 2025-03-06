package ejrciciosEntregables;


import javax.swing.*;

public class metodosJava {
    public static int pedirNumeroPositivo(String mensaje) {
        int numero;
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(mensaje);

                numero = Integer.parseInt(input);

                if (numero >= 0) {
                    return numero;
                } else {
                    JOptionPane.showMessageDialog(null, "No se permiten números negativos. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Ingresa solo números enteros.");
            }
        }
    }
}

