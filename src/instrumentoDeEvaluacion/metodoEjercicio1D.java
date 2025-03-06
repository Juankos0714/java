package instrumentoDeEvaluacion;

import javax.swing.*;

public class metodoEjercicio1D {
    public static double pedirNumeroPositivoDouble(String mensaje) {
        double doble;
        while (true) {
            String input = JOptionPane.showInputDialog(mensaje);

            doble = Double.parseDouble(input);

            if (doble >= 0) {
                return doble;
            } else {
                JOptionPane.showMessageDialog(null, "No se permiten números negativos. Inténtalo de nuevo.");
            }
        }
    }
}

