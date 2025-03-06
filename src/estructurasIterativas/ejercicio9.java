package estructurasIterativas;

public class ejercicio9 {
    public static void main(String[] args) {
        int multiplo = 5;
        int inc = 1;
        int resultado = 0;
        while(inc<=10){
            resultado = multiplo*inc;
            System.out.println("El resultado de "+multiplo+" x "+inc+" es igual a "+resultado);
            inc++;
        }
    }
}
