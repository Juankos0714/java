package TallerMetodos.Ejercicio3;

public class Metodos3 {
    public int random(){
        int min = 1;
        int max = 100;
        int aleatorio = (int) (Math.random() * (max - min + 1) + min);
        System.out.println("Número entre 1 y 100: " + aleatorio);
        return aleatorio;
    }
}
