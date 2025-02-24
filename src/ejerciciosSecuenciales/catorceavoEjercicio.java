import javax.swing.JOptionPane;

public class catorceavoEjercicio {

    public static void main(String[] args) {
        double base = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la base del triangulo"));
        double altura = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la altura del trinagulo"));
        double areaDelTriangulo =(base*altura)/2;
        String msj ="El area total es "+areaDelTriangulo;
        JOptionPane.showMessageDialog(null,msj);




    }

}