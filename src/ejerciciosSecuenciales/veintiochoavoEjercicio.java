import javax.swing.JOptionPane;

public class veintiochoavoEjercicio {

    public static void main(String[] args) {
        double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto"));
        double interes = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del interes anual"));
        double plazo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el plazo"));
        double rendimiento = (monto*interes*plazo)/360;
        String msj = "Su rendimiento del CDT es de "+rendimiento;
        JOptionPane.showMessageDialog(null,msj);




    }

}
