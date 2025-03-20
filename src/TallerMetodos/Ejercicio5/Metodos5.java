package TallerMetodos.Ejercicio5;

import javax.swing.*;

public class Metodos5 {
    public void loop() {
        int close = 0;
        do {
            int num = SolicitarDatos();
            while (!Validacion(num)) {
                num = SolicitarDatos();
            }
            close++;

        } while (close < 10);
    }

    public int SolicitarDatos() {
        return Integer.parseInt(JOptionPane.showInputDialog("Ingresa un número entre 0 y 10 para imprimir"));
    }

    public boolean Validacion(int num) {
        if (num >= 0 && num <= 10) {
            System.out.println(num);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El número no está entre 0 y 10. Intente nuevamente.");
            return false;
        }
    }
}
