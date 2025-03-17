package TallerMetodos.Ejercicio6;

import javax.swing.*;

public class Metodos6 {
    public void loop() {
        int close = 1;
        do {
            validacion();


            close = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 para cerrar el ciclo"));
        } while (close != 0);
    }
    public int validacion(){
        int numEjes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ejes del vehiculo"));
        int costo =0;

        switch (numEjes){
            case 2:costo=4000;
            break;
            case 3:costo=5000;
            break;
            case 4:costo=7000;
            break;
            case 5:costo=11000;
            break;
        }
        String msj = "El costo del peaje es de "+costo;
        JOptionPane.showMessageDialog(null,msj);
        return costo;


    }
}
