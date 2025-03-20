package TallerMetodos.Ejercicio8;

import javax.swing.*;

public class Metodos8 {
    public void loop() {
        do {
            int estrato = obtenerEstrato();
            String estadoCivil = obtenerEstadoCivil();
            int numeroMaterias = obtenerNumeroMaterias();

            int cargoFijo = obtenerCargoFijo(estrato, estadoCivil);
            int valorMateria = obtenerValorMateria(estrato, estadoCivil);

            int valorMatricula = calcularMatricula(cargoFijo, valorMateria, numeroMaterias);

            JOptionPane.showMessageDialog(null, "El valor de la matrícula es: $" + valorMatricula);

        } while (JOptionPane.showConfirmDialog(null, "¿Desea calcular otra matrícula?", "Repetir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
    public static int obtenerEstrato() {
        int estrato;
        do {
            String input = JOptionPane.showInputDialog("Ingrese el estrato (1, 2 o 3):");
            estrato = Integer.parseInt(input);
        } while (estrato < 1 || estrato > 3);
        return estrato;
    }

    public static String obtenerEstadoCivil() {
        String estadoCivil;
        do {
            estadoCivil = JOptionPane.showInputDialog("Ingrese el estado civil (Casado, Separado, Viudo, Soltero):");
        } while (!estadoCivil.equalsIgnoreCase("Casado") &&
                !estadoCivil.equalsIgnoreCase("Separado") &&
                !estadoCivil.equalsIgnoreCase("Viudo") &&
                !estadoCivil.equalsIgnoreCase("Soltero"));
        return estadoCivil;
    }

    public static int obtenerNumeroMaterias() {
        String input = JOptionPane.showInputDialog("Ingrese el número de materias:");
        return Integer.parseInt(input);
    }

    public static int obtenerCargoFijo(int estrato, String estadoCivil) {
        switch (estrato) {
            case 1:
                if (estadoCivil.equalsIgnoreCase("Casado")) return 15000;
                if (estadoCivil.equalsIgnoreCase("Separado")) return 30000;
                if (estadoCivil.equalsIgnoreCase("Viudo")) return 20000;
                if (estadoCivil.equalsIgnoreCase("Soltero")) return 25000;
                break;
            case 2:
                if (estadoCivil.equalsIgnoreCase("Casado")) return 25000;
                if (estadoCivil.equalsIgnoreCase("Separado")) return 40000;
                if (estadoCivil.equalsIgnoreCase("Viudo")) return 30000;
                if (estadoCivil.equalsIgnoreCase("Soltero")) return 35000;
                break;
            case 3:
                if (estadoCivil.equalsIgnoreCase("Casado")) return 50000;
                if (estadoCivil.equalsIgnoreCase("Separado")) return 80000;
                if (estadoCivil.equalsIgnoreCase("Viudo")) return 65000;
                if (estadoCivil.equalsIgnoreCase("Soltero")) return 70000;
                break;
        }
        return 0;
    }

    public static int obtenerValorMateria(int estrato, String estadoCivil) {
        switch (estrato) {
            case 1:
                if (estadoCivil.equalsIgnoreCase("Casado")) return 10000;
                if (estadoCivil.equalsIgnoreCase("Separado")) return 15000;
                if (estadoCivil.equalsIgnoreCase("Viudo")) return 12000;
                if (estadoCivil.equalsIgnoreCase("Soltero")) return 12000;
                break;
            case 2:
                if (estadoCivil.equalsIgnoreCase("Casado")) return 15000;
                if (estadoCivil.equalsIgnoreCase("Separado")) return 20000;
                if (estadoCivil.equalsIgnoreCase("Viudo")) return 18000;
                if (estadoCivil.equalsIgnoreCase("Soltero")) return 18000;
                break;
            case 3:
                if (estadoCivil.equalsIgnoreCase("Casado")) return 30000;
                if (estadoCivil.equalsIgnoreCase("Separado")) return 40000;
                if (estadoCivil.equalsIgnoreCase("Viudo")) return 35000;
                if (estadoCivil.equalsIgnoreCase("Soltero")) return 35000;
                break;
        }
        return 0;
    }

    public static int calcularMatricula(int cargoFijo, int valorMateria, int numeroMaterias) {
        return cargoFijo + (valorMateria * numeroMaterias);
    }
}

