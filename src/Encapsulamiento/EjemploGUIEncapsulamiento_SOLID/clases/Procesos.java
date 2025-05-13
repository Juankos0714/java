package Encapsulamiento.EjemploGUIEncapsulamiento_SOLID.clases;

import Encapsulamiento.EjemploGUIEncapsulamiento.entidades.Estudiante;

public class Procesos {
    /**
     * Calcula el promedio de las notas que recibe como
     * parametro y llama al metodo validar para garantizar
     * que se encuentran en el rango definido.
     * @param n1 nota 1 del estudiante
     * @param n2 nota 2 del estudiante
     * @param n3 nota 3 del estudiante
     * @return retorna el promedio calculado o -1 indicando que hay un error para validarlo luego
     */
    public double calcularPromedio(Estudiante e) {
        System.out.println("Estamos en Procesos");
        double prom=-1;
        double n1=e.getNota1();
        double n2=e.getNota2();
        double n3=e.getNota3();
        if(validar(n1,n2,n3)==true) {
            prom=(n1+n2+n3)/3;
        }
        return prom;
    }
    /**
     * Valida que las notas ingresadas estén en el rango
     * definido entre 0 y 5, retorna verdad o falso en caso
     * de que no supere la validación para permitir o
     * impedir que se realice el calculo.
     * @param n1 nota 1 del estudiante
     * @param n2 nota 2 del estudiante
     * @param n3 nota 3 del estudiante
     * @return true o false
     */
    public boolean validar(double n1,double n2,double n3) {
        if (n1>=0 && n1<=5 && n2>=0 && n2<=5 && n3>=0 && n3<=5) {
            return true;
        }
        return false;
    }


}

