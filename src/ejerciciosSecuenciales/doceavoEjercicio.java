import javax.swing.JOptionPane;

public class doceavoEjercicio {

    public static void main(String[] args) {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de la primera nota"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de la segunda nota"));
        double c = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de la tercera nota"));
        double resultado = (a+b+c)/3;
        String msj ="El promedio es "+resultado;
        JOptionPane.showMessageDialog(null,msj);



    }

}