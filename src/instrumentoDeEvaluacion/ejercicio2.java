package instrumentoDeEvaluacion;

import javax.swing.*;

public class ejercicio2 {
    public static void main(String arg[]) {
        int salir =0;
        do {
            double base = metodoEjercicio1D.pedirNumeroPositivoDouble("Ingrese la base del triangulo");
            double altura = metodoEjercicio1D.pedirNumeroPositivoDouble("Ingrese la altura del triangulo");
            double area = (base*altura)/2;
            if(area>=200){
                JOptionPane.showMessageDialog(null, "Es una gran area");
            }else{
                JOptionPane.showMessageDialog(null, "El area es de "+area);
            }
            salir = metodoEjercicio1.pedirNumeroPositivo("Ingrese 1 para salir");
        }while(salir!=1);

    }
}
