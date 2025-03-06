package instrumentoDeEvaluacion;

import javax.swing.*;

public class ejericicio3 {
    public static void main(String arg[]) {
        int opcion = 0;
        do{
            String msj = """
                    Tabla de contenidos:
                    1. Ingresar un nuevo numero
                    2. Salir
                    """;
            opcion = metodoEjercicio1.pedirNumeroPositivo(msj);
            switch (opcion){
                case 1: double n = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el numero"));
                if(n<-2) {
                    JOptionPane.showMessageDialog(null,n+" Es menor que -2");
                }if (n>=-2&&n<2) {
                    JOptionPane.showMessageDialog(null,n+" Es mayor o igual que -2 pero menor que 2");
                }if(n>=2&&n<5){
                    JOptionPane.showMessageDialog(null,n+" Es mayor o igual que 2 pero menor a 5");
                }if(n>=5){
                    JOptionPane.showMessageDialog(null,n+" Es mayor o igual que 5");
                }
                break;
                case 2:
            }

        }while(opcion!=2);
    }
}
