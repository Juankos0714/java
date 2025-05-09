package InterfacesGraficas.PagoEmpleado;

import javax.swing.JOptionPane;
public class Persona {
    private String documento;
    private String nombre;
    private double salarioActual;
    private int categoria;
    private double bonificacion;
    private double descuento;
    private double valorPago;
    public void llenarDatos() {
        nombre=JOptionPane.showInputDialog("Ingrese el nombre");
        documento=JOptionPane.showInputDialog("Ingrese el documento");
        salarioActual=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salarioActual"));
        categoria=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la categoria"));
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getSalarioActual() {
        return salarioActual;
    }
    public void setSalarioActual(double salarioActual) {
        this.salarioActual = salarioActual;
    }
    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public double getBonificacion() {
        return bonificacion;
    }
    public void setBonificacion(double bonificacion) {
        this.bonificacion = bonificacion;
    }
    public double getDescuento() {
        return descuento;
    }
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    public double getValorPago() {
        return valorPago;
    }
    public void setValorpago(double valorPago) {
        this.valorPago = valorPago;
    }
    @Override
    public String toString() {
        return "DATOS PERSONA -> [documento=" + documento + ", nombre=" + nombre + ", salarioActual=" + salarioActual
                + ", categoria=" + categoria + ", bonificacion=" + bonificacion + ", descuento=" + descuento
                + ", valorpago=" + valorPago + "]";
    }
}

