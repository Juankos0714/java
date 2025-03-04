package estructurasIterativas;
import javax.swing.*;

public class ciclos {
    public static void main(String arg[]){
        int x=0;
        do{
//            System.out.println("Valor de x "+x);
            JOptionPane.showMessageDialog(null, "Valor de x "+x);
            x++;
        }while(x<=10);
    }
}
