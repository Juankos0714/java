import javax.swing.JOptionPane;
public class CalculadoraPolacaInversaApp {
    public static void main(String[] args) {
        double num1 = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el primer numero"));
        String operacion = JOptionPane.showInputDialog(null, "Ingresa el operador");
        double num2 = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el segundo numero"));
        double resultado=0;
        switch (operacion){
            case "suma":
                resultado = num1 + num2;
                break;
            case "resta":
                resultado = num1 - num2;
                break;
            case "division":
                resultado = num1/num2;
                break;
            case "multiplicacion":
                resultado = num1*num2;
                break;
            case "modulo":
                resultado = num1%num2;
                break;

        }
        JOptionPane.showMessageDialog(null,"El resultado de la "+operacion+"es "+resultado);

    }
}


