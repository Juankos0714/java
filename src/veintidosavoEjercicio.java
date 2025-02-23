import javax.swing.JOptionPane;

public class veintidosavoEjercicio {

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog(null, "Ingresa su nombre");
        int identidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su numero de identificacion"));
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto de su salario actual"));
        double horasExtras = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad de horas extras realizadas"));
        double nuevoSalario1 = (horasExtras*3000)+salario;

        String msj = "El salario de "+nombre+" es de "+nuevoSalario1;
        JOptionPane.showMessageDialog(null,msj);



    }

}