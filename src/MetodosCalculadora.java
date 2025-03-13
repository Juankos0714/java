import javax.swing.*;

public class MetodosCalculadora {
    double num1,num2,resultado;
    int operacion;

    public void inicio() {
        int close;
        do {

            menu();
            close = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 0 para cerrar"));

        } while (close != 0);
    }
        public int solicitarOperador(){
        String msj = """
                Elija la operacion de desea realizar:
                1.sumar
                2.resta
                3.multiplicacion
                4.division
                5.calcular modulo
                """;
        int operacion = Integer.parseInt(JOptionPane.showInputDialog(null, msj));
        return operacion;


    }

        public double validarDivision(String msj){
            double num=Double.parseDouble(JOptionPane.showInputDialog(null, msj));
            do {
                if (num == 0) {
                    JOptionPane.showMessageDialog(null, "No se puede dividir entre cero");
                    num = Double.parseDouble(JOptionPane.showInputDialog("Vuelva ingresar un valor diferente"));
                }

            }while(num==0);
            return num;
        }
        public double solitarValor(String msj){
        double num = Double.parseDouble(JOptionPane.showInputDialog(null, msj));
        return num;




       }
        public void menu(){

            operacion=  solicitarOperador();


            double resultado=0;
            String opcion ="";
            switch (operacion){
                case 1:
                    num1 = solitarValor("Ingrese el primer valor");
                    num2= solitarValor("Ingrese el segundo valor");
                    resultado = num1 + num2;
                    opcion =" suma ";
                    break;
                case 2:
                    num1 = solitarValor("Ingrese el primer valor");
                    num2= solitarValor("Ingrese el segundo valor");
                    resultado = num1 - num2;
                    opcion =" resta ";
                    break;
                case 3:
                    num1 = solitarValor("Ingrese el primer valor");
                    num2= solitarValor("Ingrese el segundo valor");
                    resultado = num1*num2;
                    opcion =" multiplicacion ";
                    break;
                case 4:
                    num1 = solitarValor("Ingrese el primer valor");
                    num2 = validarDivision("Ingrese el segundo valor");
                    resultado = num1/num2;
                    opcion =" division ";
                    break;
                case 5:
                    num1 = solitarValor("Ingrese el primer valor");
                    num2= validarDivision("Ingrese el segundo valor");
                    resultado = num1%num2;
                    opcion =" calcular modulo ";
                    break;

            }
            JOptionPane.showMessageDialog(null,"El resultado de "+opcion+"es "+resultado);

        }

}
