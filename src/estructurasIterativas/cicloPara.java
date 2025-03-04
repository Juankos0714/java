package estructurasIterativas;

import javax.swing.*;

public class cicloPara {
    public static void main(String arg[]){

        for(int x= 0;x<=10; x++){
//            System.out.println("Valor de x "+x);
            JOptionPane.showMessageDialog(null, "Valor de x "+x);
            x++;
        }
    }
}
