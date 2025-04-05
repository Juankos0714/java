package estructurasDeDatos;

import javax.swing.*;

public class PromedioNotas {
    public static void main(String[] args) {
        int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de estudiantes a procesar"));
        double n1=0,n2=0,n3=0;
        String[] nombres = new String[n];
        double[] notas = new double[nombres.length];
        int opcion;
        int valida = 0;
        int registro = 0;

        do {
            String menu = """
                    MENU
                    1. Registrar Información
                    2. Imprimir Lista de Estudiantes
                    3. Imprimir Notas Finales
                    4. Salir
                    Seleccione una opción:
                    """;
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    if (valida == 0) {
                        for (int i = 0; i < n; i++) {
                            nombres[i] = JOptionPane.showInputDialog("Ingrese el nombre del estudiante " + (i + 1));
                            n1 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 1 para " + nombres[i]));
                            n2 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 2 para " + nombres[i]));
                            n3 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota 3 para " + nombres[i]));
 
                            double promedio = (n1 + n2 + n3) / 3;
                            notas[i] = promedio;

                            String resultado = promedio >= 3.5 ? "Gana la materia" : "Pierde la materia";
                            JOptionPane.showMessageDialog(null, nombres[i] + " - Nota final: " + promedio + " - " + resultado);
                        }
                        valida = 1;
                        registro = 1;
                    } else {
                        int confirmacion = JOptionPane.showConfirmDialog(null, "Ya registró datos previamente. ¿Desea sobrescribirlos?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (confirmacion == JOptionPane.YES_OPTION) {
                            valida = 0;
                        }
                    }
                    break;

                case 2:
                    if (registro == 0) {
                        JOptionPane.showMessageDialog(null, "Debe registrar datos primero.");
                    } else {
                        String lista = "Lista de Estudiantes:\n";
                        for (String nombre : nombres) {
                            lista += nombre + "\n";
                        }
                        JOptionPane.showMessageDialog(null, lista);
                    }
                    break;

                case 3:
                    if (registro == 0) {
                        JOptionPane.showMessageDialog(null, "Debe registrar datos primero.");
                    } else {
                        String listaNotas = "Notas Finales:\n";
                        for (int i = 0; i < n; i++) {
                            listaNotas += nombres[i] + " - Nota final: " + notas[i] + "\n";
                        }
                        JOptionPane.showMessageDialog(null, listaNotas);
                    }
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa. Hasta luego!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }
}

