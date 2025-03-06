package instrumentoDeEvaluacion;

import javax.swing.*;

public class metodoEjercicio1 {
    public static int pedirNumeroPositivo(String mensaje) {
        int numero;
        while (true) {
                String input = JOptionPane.showInputDialog(mensaje);

                numero = Integer.parseInt(input);

                if (numero >= 0) {
                    return numero;
                } else {
                    JOptionPane.showMessageDialog(null, "No se permiten números negativos. Inténtalo de nuevo.");
                }
        }
    }
}


