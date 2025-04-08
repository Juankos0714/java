package HashMap.EjercicioEnClase2;

import HashMap.CasoPromedioNotas.ModeloDatos;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Procesos {
    Scanner sc = new Scanner(System.in);
    HashMap.CasoPromedioNotas.ModeloDatos miData;
    ArrayList<String> listEstudiantes;
    int cantGanan = 0, cantRecuperan = 0, cantPierden = 0;

    public Procesos() {
        miData = new ModeloDatos();
        iniciar();
    }

    public void iniciar() {
        String continuar;
        int opc;

        do {
            System.out.print("Menu: 1. para consultar estudiantes registrados\n2.Para <Consultar estudiantes por cedula\n3.Para modificar el nombre de un estudiante");
            opc = sc.nextInt();
            switch (opc){
                case 1:
                    miData.getMapaEstudiantes();
                break;
                case 2:
                    System.out.print("Ingrese el documento a consultar");
                    String documento = sc.nextLine() ;
                    miData.consultarEstudiante(documento);
                break;
                case 3:
                    midata.


            }
            ingresarDatos();

            System.out.print("¿Desea ingresar otro estudiante? (s/n): ");
            continuar = sc.nextLine().toLowerCase();
        } while (continuar.equals("s"));

        miData.imprimirMapa();

    }

    public void ingresarDatos() {
        listEstudiantes = new ArrayList<>();

        System.out.print("Documento: ");
        String doc = sc.nextLine();
        listEstudiantes.add(doc);

        System.out.print("Nombre: ");
        listEstudiantes.add(sc.nextLine());

        System.out.print("Materia: ");
        listEstudiantes.add(sc.nextLine());



        miData.guardarDatos(doc, listEstudiantes);
    }

}

