import javax.swing.JOptionPane;

public class veinticincoavoEjercicio {

    public static void main(String[] args) {
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto de su salario actual"));
        double aumento = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el porcentaje de aumento de salario"));
        aumento = aumento/100;
        double salario2 = (salario*aumento)+salario;
        String msj = "El salario nuevo es " +salario2;
        JOptionPane.showMessageDialog(null,msj);




    }

}