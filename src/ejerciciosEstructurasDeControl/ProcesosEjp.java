package ejerciciosEstructurasDeControl;

import javax.swing.JOptionPane; // Importamos la clase JOptionPane del paquete javax.swing

/**
 * Clase Procesos donde se solicita información sobre una persona
 * y se calcula su edad a partir de su fecha de nacimiento.
 * @author CHENAO
 */
public class ProcesosEjp {

    // Definimos las variables que vamos a usar
    int anio, dia, mes;
    int anioActual, mesActual, diaActual;

    /**
     * Inicia la ejecución del programa. Solicita la cantidad de veces que se
     * desea realizar la operación y llama a los métodos correspondientes para
     * solicitar información y calcular la edad.
     */
    public void iniciar() {

        // Pedimos la cantidad de veces que el usuario quiere ejecutar el programa
        int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de veces"));

        // Usamos un ciclo for para repetir el proceso de solicitar los datos y calcular la edad la cantidad de veces que indicó el usuario
        for (int i = 0; i < cant; i++) {
            solicitarDatos();
            calcularEdad();
        }
    }

    /**
     * Solicita la fecha de nacimiento de la persona y calcula su edad.
     */
    public void solicitarDatos() {

        // Definimos la fecha actual en variables para poder calcular la edad
        anioActual = 2023;
        mesActual = 3;
        diaActual = 7;

        // Pedimos al usuario el año, mes y día de su fecha de nacimiento
        int anioNacimiento = validarNegativos("Ingrese el año de nacimiento");
        int mesNacimiento = validarMeses();
        int diaNacimiento = validarDias();

        // Calculamos la edad restando la fecha de nacimiento a la fecha actual
        anio = anioActual - anioNacimiento;
        mes = mesActual - mesNacimiento;
        dia = diaActual - diaNacimiento;
    }

    /**
     * Solicita el mes de nacimiento de la persona y valida que sea un valor
     * válido (entre 1 y 12).
     * @return El mes de nacimiento validado.
     */
    public int validarMeses() {

        int mes = 0;

        // Usamos un ciclo do-while para pedirle al usuario el mes hasta que ingrese un valor válido
        do {
            mes = validarNegativos("Ingrese el Mes de nacimiento");
            if (mes == 0 || mes > 12) {
                System.out.println("Mes Incorrecto");
            }
        } while (mes == 0 || mes > 12);

        return mes;
    }

    /**
     * Solicita el día de nacimiento de la persona y valida que sea un valor
     * válido (entre 1 y 31).
     * @return El día de nacimiento validado.
     */
    public int validarDias() {

        int dia = 0;

        // Usamos un ciclo do-while para pedirle al usuario el día hasta que ingrese un valor válido
        do {
            dia = validarNegativos("Ingrese el Dia de nacimiento");
            if (dia == 0 || dia > 31) {
                System.out.println("Dia Incorrecto");
            }
        } while (dia == 0 || dia > 31);

        return dia;
    }

    /**
     * Calcula la edad de la persona a partir de la fecha de nacimiento y
     * muestra un mensaje indicando si es mayor o menor de edad.
     */
    public void calcularEdad() {

        if (anio > 18) {
            System.out.println("Usted es mayor de edad");
        } else if (anio == 18 && mes > 0) {
            System.out.println("Usted es mayor de edad");
        } else if (anio == 18 && mes == 0 && dia >= 0) {
            System.out.println("Usted es mayor de edad");
        } else {
            System.out.println("Usted es menor de edad");
        }
    }

    /**
     * 	Método que solicita un número al usuario a través de una ventana de diálogo de entrada y
     * verifica que el número ingresado no sea negativo.
     *
     * @param mensaje mensaje que se mostrará al usuario en la ventana de diálogo de entrada.
     * @return el número ingresado por el usuario sin ser negativo.
     */
    public int validarNegativos(String mensaje) {

        int dato;

        // Usamos un ciclo do-while para pedirle al usuario el dato hasta que ingrese un valor válido
        do {
            dato=Integer.parseInt(JOptionPane.
                    showInputDialog(mensaje));
            if (dato<0) {
                System.out.println("No puede ser datos negativos");
            }

        } while (dato<0);

        return dato;

    }

}
