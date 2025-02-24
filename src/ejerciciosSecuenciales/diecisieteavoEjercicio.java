import javax.swing.JOptionPane;

public class diecisieteavoEjercicio {

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre completo");
        int identificacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero del documento"));
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"));
        String telefono = JOptionPane.showInputDialog("Ingrese su telefono");

        String profesion = JOptionPane.showInputDialog("Ingrese su profesion");
        String msj ="Datos ingresados:\nNombre Completo: "+nombre+"\nEdad: "+edad+"\nDocumento: "+identificacion+"\nProfesión: "+profesion+"\nTeléfono: "+telefono;
        JOptionPane.showMessageDialog(null,msj);



    }

}