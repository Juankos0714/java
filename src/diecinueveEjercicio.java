import javax.swing.JOptionPane;

public class diecinueveEjercicio {

    public static void main(String[] args) {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de a"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de b"));
        double resultado = a+(b*b)*a;
        String msj ="El valor de x es "+resultado;
        JOptionPane.showMessageDialog(null,msj);



    }

}