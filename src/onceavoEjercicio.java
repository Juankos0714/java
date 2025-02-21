import javax.swing.JOptionPane;

public class onceavoEjercicio {

    public static void main(String[] args) {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de a"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de b"));
        double c = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de c"));
        double resultado = (a+b+c)*2;
        String msj ="El resultado es "+resultado;
        JOptionPane.showMessageDialog(null,msj);



    }

}