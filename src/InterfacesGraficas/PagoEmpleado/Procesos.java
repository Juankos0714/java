package InterfacesGraficas.PagoEmpleado;

import javax.swing.JOptionPane;

public class Procesos {
    private double valorTotalPago = 0;
    private ModeloDeDatos miModeloDatos;

    public Procesos() {
        miModeloDatos = new ModeloDeDatos();
    }

    public void pedirDatos() {
        Persona persona = new Persona();
        persona.llenarDatos();
        calcularBonificacionDescuento(persona);
        calcularPago(persona);
        System.out.println(persona);
        miModeloDatos.registrarPersona(persona);
    }

    public double calcularPago(Persona persona) {
        double pago = persona.getSalarioActual() +
                (persona.getSalarioActual() * persona.getBonificacion()) -
                (persona.getSalarioActual() * persona.getDescuento());
        persona.setValorpago(pago);
        valorTotalPago = valorTotalPago + pago;
        System.out.println("Hola " + persona.getNombre() + " El pago final es: " + pago);
        return pago;
    }

    public void calcularBonificacionDescuento(Persona persona) {
        switch (persona.getCategoria()) {
            case 1:
                persona.setBonificacion(0.15);
                persona.setDescuento(0.02);
                break;
            case 2:
                persona.setBonificacion(0.10);
                persona.setDescuento(0.015);
                break;
            case 3:
                persona.setBonificacion(0.08);
                persona.setDescuento(0.01);
                break;
            case 4:
                persona.setBonificacion(0.0);
                persona.setDescuento(0.0);
                break;
            default:
                System.out.println("La categoria no existe");
                persona.setBonificacion(0.0);
                persona.setDescuento(0.0);
                break;
        }
    }
    public ModeloDeDatos getModeloDeDatos() {
        return miModeloDatos;
    }
}

