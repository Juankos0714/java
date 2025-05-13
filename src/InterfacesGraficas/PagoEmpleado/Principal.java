package InterfacesGraficas.PagoEmpleado;

public class Principal {
    public static void main(String[] args) {
        Procesos miProceso = new Procesos();
        VentanaUsuario ventana = new VentanaUsuario();
        ventana.setVisible(true);
    }
}

