package ejerciciosEstructurasDeControl.CondicionalesAnidadas;

public class ejercicio2 {


        public static double calcularY(double x) {
            if (x < -5) {
                return -x;
            } else if (x >= -5 && x <= 2) {
                return Math.pow(x, 2) + 3;
            } else if (x > 2 && x < 5) {
                return x;
            } else {
                return 10;
            }
        }

        public static void main(String[] args) {

            double[] valoresDeX = {-6, -5, 0, 3, 5, 6};
            for (double x : valoresDeX) {
                System.out.println("Para x = " + x + ", y = " + calcularY(x));
            }
        }
    }


