package TallerMetodos.Ejercicio2;

import javax.swing.*;

public class Metodos2 {
    public void loop(){
        int close=1;
        do {

            Validacion(SolicitarDatos());

            close = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 para cerrar el ciclo"));
        }while(close!=0);
    }
    public int SolicitarDatos(){
        int num = Integer.parseInt(JOptionPane.showInputDialog("Ingresa un numero mayor a 10 para imprimir"));
        return num;
    }
    public void Validacion(int num){

        if(num>10){
            System.out.println(num);
        }else{
            JOptionPane.showMessageDialog(null,"EL numero es menor a 10");
        }
    }


}
