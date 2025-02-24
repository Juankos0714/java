package trabajoAutonomo.variableYTipoDeDatos;

public class ejemplo2 {

    public static void main(String[] args) {
        double precio = 19.99;
        int precioEntero = (int) precio;

        int cantidad = 5;
        double total = cantidad * precio;

        System.out.println("Precio con decimales: " + precio);
        System.out.println("Precio sin decimales: " + precioEntero);
        System.out.println("Total a pagar: " + total);
    }
}
