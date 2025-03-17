package TallerMetodos.Ejercicio4;


import javax.swing.*;

public class Metodos4 {
    public void loop() {
        int close = 1;
        do {

            ValidarPar(random());

            close = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 para cerrar el ciclo"));
        } while (close != 0);
    }
        public int random () {
            int min = 1;
            int max = 100;
            int aleatorio = (int) (Math.random() * (max - min + 1) + min);
            System.out.println("Número entre 1 y 100: " + aleatorio);
            return aleatorio;
        }
        public void ValidarPar ( int aleatorio){


            if ((aleatorio % 2) == 0) {
                System.out.println("El numero " + aleatorio + " es par");
            } else {
                System.out.println("El numero " + aleatorio + " es impar");
            }
        }

}
