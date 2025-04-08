package HashMap.EjercicioEnClase2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ModeloDatos {
    Scanner sc = new Scanner(System.in);
    HashMap<String, ArrayList<String>> mapEstudiantes;

    public ModeloDatos() {
        mapEstudiantes = new HashMap<>();
    }

    public void guardarDatos(String documento, ArrayList<String> datosEstudiante) {
        mapEstudiantes.put(documento, datosEstudiante);
    }

    public ArrayList<String> consultarEstudiante(String documento) {
        return mapEstudiantes.get(documento);
    }
    public ArrayList<String> modificarEstudiante(String documento) {
        return mapEstudiantes.put(documento, ArrayList<sc.nextLine()>) ;

    }

    public HashMap<String, ArrayList<String>> getMapaEstudiantes() {
        return mapEstudiantes;
    }

    public void imprimirMapa() {
        for (String documento : mapEstudiantes.keySet()) {
            ArrayList<String> datos = mapEstudiantes.get(documento);
            System.out.println("Documento: " + documento);
            System.out.println("Nombre: " + datos.get(1));
            System.out.println("Materia: " + datos.get(2));
            System.out.println("Notas: " + datos.get(3) + ", " + datos.get(4) + ", " + datos.get(5));
            System.out.println("---------------");
        }
    }
}

