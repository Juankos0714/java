package ejerciciosEstructurasDeControl;

import javax.swing.*;

public class ejercicioEstrato {
    public static void main(String[] args) {
        int menuSalario =0;

        double salario=1000000;
        double descuento=1;
        String porcentaje ="s";

        do{
            String menu="""
                MENU SALARIO
                1. Para ingresar si estrato
                2. Si desea consultar el salario base
                3. Conocer su salario nuevo
              
                Por favor seleccione una opción:
                """;
            menuSalario = metodosJava.pedirNumeroPositivo(menu);

            switch (menuSalario){
                case 1:
                    int estratoObrero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa su estrato (1,2,3,4,5 o 6)"));
                    switch (estratoObrero) {
                        case 1:
                        case 2:
                            descuento = 0.02;
                            porcentaje = "2%";
                            break;
                        case 3:
                        case 4:
                            descuento = 0.04;
                            porcentaje = "4%";
                            break;
                        case 5:
                            descuento = 0.08;
                            porcentaje = "8%";
                            break;
                        case 6:
                            descuento = 0.1;
                            porcentaje = "10%";
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

        double nuevoSalario =salario-(descuento*salario);

        String msj = ">>>>>TABLA SALARIOS<<<<<<\n _______________________________________________\nPorcentaje a descontar: "+porcentaje+" Salario Final: "+nuevoSalario;
        JOptionPane.showMessageDialog(null,msj);



    }
}
