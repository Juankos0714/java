package ejrciciosEntregables;

import javax.swing.*;

public class ejercicioBonificacion {
    public static void main(String[] args) {
        int menuSalario =0;

        double salario=1000000;
        double descuento=1;
        double bonificacion =1;

        do{
            String menu="""
                MENU SALARIO
                1. Para ingresar su categoria
                2. Si desea consultar el salario base
                3. Conocer su salario nuevo
              
                Por favor seleccione una opción:
                """;
            menuSalario = metodosJava.pedirNumeroPositivo(menu);

            switch (menuSalario){
                case 1:
                    int estratoObrero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa su categoria (1,2,3 o 4)"));
                    switch (estratoObrero) {
                        case 1:
                            descuento = 0.02;
                            bonificacion = 0.15;
                            break;
                        case 2:
                            descuento = 0.015;
                            bonificacion = 0.1;
                            break;
                        case 3:
                            descuento = 0.01;
                            bonificacion = 0.08;
                            break;
                        case 4:
                            descuento = 0;
                            bonificacion = 0;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "ingrese un valor valido");
                            break;
                    }
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,"Su salario base es de"+salario);
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "No corresponde a " + "un codigo valido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        }while(menuSalario!=3);

        double nuevoSalario =salario+salario*bonificacion-salario*descuento;

        String msj = ">>>>>TABLA SALARIOS<<<<<<\n _______________________________________________\n Salario Final: "+nuevoSalario;
        JOptionPane.showMessageDialog(null,msj);



    }
}