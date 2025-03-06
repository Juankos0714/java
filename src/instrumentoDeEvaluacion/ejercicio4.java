package instrumentoDeEvaluacion;

public class ejercicio4 {
    public static void main(String arg[]) {
        double nomina =10000000;
        while(nomina!=0){
            String  msj= """
                    Tabla de opciones
                    1. Si desea retirar
                    2. Si desea enviar dinero a su contacto de emergencia
                    ¡El programa solo se termina cuando su valor de nomina queda en cero!
                    """;
            int opcion = metodoEjercicio1.pedirNumeroPositivo(msj);
            switch (opcion){
                case 1:
                    double retiro=metodoEjercicio1.pedirNumeroPositivo("Ingrese el valor a retirar");
                    nomina-=retiro;
                break;
                case 2:
                    double transferencia=metodoEjercicio1.pedirNumeroPositivo("Ingrese el valor a transferir");
                    nomina-=transferencia;
                break;


            }
        }
    }
}
