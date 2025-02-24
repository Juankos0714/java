package trabajoAutonomo.estrcuturasDeControl;

import javax.swing.JOptionPane;
public class ejemplo2 {

    public static void main(String[] args) {
        double costoTrat = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el costo del tratamiento"));
        double numDias = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el numero de dias de tratamiento"));
        double costoMedica = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el costo de los medicamentos"));


        double valorDias=numDias*100000;
        double costoTotal=costoTrat+costoMedica+valorDias;
        String Tipo = JOptionPane.showInputDialog(null, "Ingresa Si se encuentra subsidiado") ;
        if(Tipo=="si"){
            costoTotal=costoTotal-(costoTotal*0.1);
        }else{
            costoTotal=costoTotal-(costoTotal*0.05);
        }

        String msj = "El costo es "+costoTotal;



        JOptionPane.showMessageDialog(null,msj);

    }
}
