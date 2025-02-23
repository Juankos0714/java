import javax.swing.JOptionPane;

public class veintisieteavoEjercicio {

    public static void main(String[] args) {
        double nota1 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota numero 1"));
        double nota2 = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota numero 2"));
        double notaT = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota de trabajos"));
        double notaExamenF = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota del examen final"));
        double notaD = (nota1*0.2)+(nota2*0.2)+(notaT*0.3)+(notaExamenF*0.3);
        String msj = "Su nota definitiva es " +notaD;
        JOptionPane.showMessageDialog(null,msj);




    }

}
