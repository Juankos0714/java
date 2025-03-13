package ejerciciosEstructurasDeControl;

/**

 * Clase principal que contiene el método main para ejecutar el programa.
 */
public class PrincipalEjp {

    /**
     *
     * Método main que crea un objeto de la clase Procesos y llama al método iniciar.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {

        ProcesosEjp misProcesos;//crea un objeto de tipo Procesos
        misProcesos = new ProcesosEjp(); //crea la instancia del objeto misProcesos
        misProcesos.iniciar();//llama al metodo iniciar de la clase procesos

    }

}
