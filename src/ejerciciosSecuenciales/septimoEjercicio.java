import javax.swing.JOptionPane;

public class septimoEjercicio {

    public static void main(String[] args) {
        double diaHospitalizacion =100000;
        double numerodias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su numero de dias de hospitalizacion"));
        double costoTratamiento = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo de su tratamiento"));
        double costoMedicamento = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo de sus medicamentos"));
        double costoTotal = (diaHospitalizacion*numerodias)+costoTratamiento+costoMedicamento;
        String msj ="El costo total es "+costoTotal;
        JOptionPane.showMessageDialog(null,msj);



    }

}
