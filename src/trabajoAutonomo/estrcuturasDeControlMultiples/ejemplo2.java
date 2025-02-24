package trabajoAutonomo.estrcuturasDeControlMultiples;

public class ejemplo2 {
    public static void main(String[] args) {
        int opcion = 2;

        switch (opcion) {
            case 1:
                System.out.println("Seleccionaste la 1: Ver saldo");
                break;
            case 2:
                System.out.println("Seleccionaste la 2: retirar dinero");
                break;
            case 3:
                System.out.println("Seleccionaste la 3: deposito");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
}

