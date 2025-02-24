package trabajoAutonomo.estrcuturasDeControlMultiples;

public class ejemplo1 {

    public static void main(String[] args) {
        int calificacion = 50;

        if (calificacion >= 45) {
            System.out.println("Superior");
        } else if (calificacion >= 40) {
            System.out.println("Alto");
        } else if (calificacion >= 30) {
            System.out.println("Basico");
        } else if (calificacion >= 20) {
            System.out.println("Bajo");
        } else {
            System.out.println("Insuficiente");
        }
    }
}

