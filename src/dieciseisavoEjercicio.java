import javax.swing.JOptionPane;

public class dieciseisavoEjercicio {

    public static void main(String[] args) {
        double articulo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor del articulo"));
        double cantidadArticulos = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad de articulos "));
        double valorEntregado = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad de dinero entregado "));
        double cuenta = articulo*cantidadArticulos;
        double devuelta = valorEntregado-cuenta;
        String msj;
        if(devuelta>0 ){
            msj="Su devuelta es "+devuelta;
        }
        else{msj="Faltan "+devuelta;
        }


        JOptionPane.showMessageDialog(null,msj);



    }

}
