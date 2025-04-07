package estructurasDeDatos;

import javax.swing.*;

public class EjercicioNotas {
    public static void main(String[] args){
        int n = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de estudiantes"));
        String[] nombres = new String[n];
        double[] notas = new double[nombres.length];
        String[] materia = new String[nombres.length];
        double[] edad = new double[nombres.length];




    }
    public static void SolicitarDatos(double[] notas, String[] nombres, String[]materia, double [] edad, int n ){
        int opc= 1,i=0;

        do {

            nombres[i] = JOptionPane.showInputDialog("Ingrese su nombre");
            notas[i] = EjercicioNotas.Validacion("Ingrese su nota final")  ;
            materia[i] = JOptionPane.showInputDialog("Ingrese su materia");
            edad[i] = EjercicioNotas.Validacion("Ingrese su edad")  ;
            opc = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 para cerrar el ciclo"));
            i++;
        }while(i<n);


    }
    public static double SumaValores(double[] notas){
        double suma=0;
        for (double nota : notas) {
            suma+=nota;
        }
        return suma;
    }
    public static double PromNotas(double suma, int cant){
        double prom =suma/cant;
        return prom;
    }
    public static double Validacion(String msj) {
        msj = "";

        double entrada;
        do {
            entrada = Double.parseDouble(JOptionPane.showInputDialog(msj));
            if (entrada < 0 && entrada > 5) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor valido");
            }
        } while (entrada>  0 && entrada > 5);
        return entrada;

    }



}
