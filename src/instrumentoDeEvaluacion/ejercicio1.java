package instrumentoDeEvaluacion;

import javax.swing.*;

public class ejercicio1 {
    public static void main(String arg[]) {
        double costoDiaHsopitalizacion = 100000;
        double facturaT = 0;
        int pacientes = 0;
        int opcion =0;


        do {

            String msj = """
                    Tabla de opciones:
                    1. Para Ingresar un paciente
                    2. para conocer el total de la factura
                    3. Para salir
                    """;
            opcion = metodoEjercicio1.pedirNumeroPositivo(msj);


            switch (opcion) {
                case 1:
                    double costoTratamiento = metodoEjercicio1D.pedirNumeroPositivoDouble("Ingrese el costo del tratamiento:");
                    double diasHospitalizacion = metodoEjercicio1D.pedirNumeroPositivoDouble("Ingrese el numero de dias:");
                    double costoMedicamentos = metodoEjercicio1D.pedirNumeroPositivoDouble("Ingrese el costo del medicamento:");

                    double facturaPac = (diasHospitalizacion * costoDiaHsopitalizacion) + costoMedicamentos + costoTratamiento;
                    JOptionPane.showMessageDialog(null, "El valor de la factura es " + facturaPac);
                    facturaT += facturaPac;
                    pacientes++;
                break;
                case 2:
                    JOptionPane.showMessageDialog(null, "El valor de la factura total es " + facturaT + " con un total de " + pacientes+" pacientes");
                break;
            }
        }while (opcion != 3) ;


    }
}