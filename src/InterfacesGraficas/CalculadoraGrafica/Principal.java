package InterfacesGraficas.CalculadoraGrafica;



public class Principal {
    public static void main(String[] args) {
        Metodos miVentana = new Metodos();
        OperacionesMatematicas operaciones = new OperacionesMatematicas();
        miVentana.asignarOperaciones(operaciones);
        miVentana.setVisible(true);
    }
}

