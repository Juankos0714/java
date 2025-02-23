import javax.swing.JOptionPane;

public class veintitresavoEjercicio {

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog(null, "Ingresa su nombre");
        int identidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su numero de identificacion"));
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto de su salario actual"));
        double horasExtras = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad de horas extras realizadas"));
        double nuevoSalario1 = (horasExtras*2000)+salario;
        double descuento = salario*0.1;
        double nuevoSalario2 = nuevoSalario1-descuento;
        String msj = "El salario de "+nombre+" es de "+nuevoSalario2+"\ncon la cedula "+identidad+"\ncon un descuento de "+descuento+"\nun salario base de "+salario;
        JOptionPane.showMessageDialog(null,msj);



    }

}
