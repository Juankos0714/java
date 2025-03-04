package estructurasIterativas;

import javax.swing.*;

public class cicloWhile {
    public static void main(String arg[]){
        int x=0;
        while(x<=10){
//            System.out.println("Valor de x "+x);
            JOptionPane.showMessageDialog(null, "Valor de x "+x);
            x++;
        };
    }
}
