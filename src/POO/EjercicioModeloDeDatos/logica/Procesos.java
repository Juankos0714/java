package POO.EjercicioModeloDeDatos.logica;

public class Procesos {
    public void calcularSueldo(Operador operador){
        System.out.println("Calcula el sueldo");
        System.out.println(operador);
        operador.setSueldoNuevo(5000);
        operador.setAumento(6);
        ;
    }
}
