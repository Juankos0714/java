import javax.swing.JOptionPane;

public class treceavoEjercicio {

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre completo");
        int identificacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero del documento"));
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su edad"));
        String profesion = JOptionPane.showInputDialog("Ingrese su profesion");
        String msj ="Datos ingresados:\nNombre Completo: "+nombre+"\nDocumento: "+identificacion+"\nEdad= "+edad+"\nProfesión: "+profesion+"\n\nBienvenido!!!";
        JOptionPane.showMessageDialog(null,msj);



    }

}