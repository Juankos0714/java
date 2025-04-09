package HashMap.EjercicioEnClase2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Procesos {
    Scanner sc = new Scanner(System.in);
    // Corregir el tipo al ModeloDatos correcto
    ModeloDatos miData;
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
            System.out.print("Menu: 1. para consultar estudiantes registrados\n2.Para Consultar estudiantes por cedula\n3.Para modificar el nombre de un estudiante\n");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    miData.imprimirMapa();
                    break;
                case 2:
                    System.out.print("Ingrese el documento a consultar: ");
                    String documento = sc.nextLine();
                    ArrayList<String> datosEstudiante = miData.consultarEstudiante(documento);

                    if (datosEstudiante != null) {
                        System.out.println("Documento: " + documento);
                        System.out.println("Nombre: " + datosEstudiante.get(1));
                        System.out.println("Materia: " + datosEstudiante.get(2));
                        if (datosEstudiante.size() > 3) {
                            System.out.println("Notas: " + datosEstudiante.get(3) + ", " +
                                    datosEstudiante.get(4) + ", " + datosEstudiante.get(5));
                        }
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el documento del estudiante a modificar: ");
                    String docModificar = sc.nextLine();
                    miData.modificarEstudiante(docModificar);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            System.out.print("¿Desea realizar otra operación? (s/n): ");
            continuar = sc.nextLine().toLowerCase();

            if (continuar.equals("s")) {
                System.out.print("¿Desea ingresar un nuevo estudiante? (s/n): ");
                String ingresarNuevo = sc.nextLine().toLowerCase();
                if (ingresarNuevo.equals("s")) {
                    ingresarDatos();
                }
            }

        } while (continuar.equals("s"));
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

        System.out.print("Nota 1: ");
        listEstudiantes.add(sc.nextLine());

        System.out.print("Nota 2: ");
        listEstudiantes.add(sc.nextLine());

        System.out.print("Nota 3: ");
        listEstudiantes.add(sc.nextLine());

        miData.guardarDatos(doc, listEstudiantes);
        System.out.println("Estudiante registrado correctamente.");
    }
}
