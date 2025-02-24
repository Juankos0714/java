package trabajoAutonomo.constantes;


enum Dias {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
}

public class ejemplo3 {
    public static void main(String[] args) {
        Dias diaActual = Dias.VIERNES;

        if (diaActual == Dias.VIERNES) {
            System.out.println("¡Es viernes! Fin de semana cerca.");
        } else {
            System.out.println("Hoy es " + diaActual);
        }
    }
}

