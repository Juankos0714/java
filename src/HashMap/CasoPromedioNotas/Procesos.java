package HashMap.CasoPromedioNotas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Procesos {
    Scanner sc = new Scanner(System.in);
    ModeloDatos miData;
    ArrayList<String> listEstudiantes;
    int cantGanan = 0, cantRecuperan = 0, cantPierden = 0;

    public Procesos() {
        miData = new ModeloDatos();
        iniciar();
    }

    public void iniciar() {
        String continuar;

        do {
            ingresarDatos();
            System.out.print("¿Desea ingresar otro estudiante? (s/n): ");
            continuar = sc.nextLine().toLowerCase();
        } while (continuar.equals("s"));

        miData.imprimirMapa();
        sumaTotalNotas();
        calcularPromedioFinal();
        calcularEstados();
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

        for (int i = 1; i <= 3; i++) {
            double nota = validarNotas(i);
            listEstudiantes.add(String.valueOf(nota));
        }

        miData.guardarDatos(doc, listEstudiantes);
    }

    public double validarNotas(int numNota) {
        double nota;
        do {
            System.out.print("Nota " + numNota + " (0-5): ");
            nota = Double.parseDouble(sc.nextLine());
        } while (nota < 0 || nota > 5);
        return nota;
    }

    public void sumaTotalNotas() {
        double suma = 0;
        int totalNotas = 0;

        HashMap<String, ArrayList<String>> mapa = miData.getMapaEstudiantes();
        for (String doc : mapa.keySet()) {
            ArrayList<String> datos = mapa.get(doc);
            for (int i = 3; i <= 5; i++) {
                suma += Double.parseDouble(datos.get(i));
                totalNotas++;
            }
        }

        System.out.println("Suma total de notas: " + suma);
        System.out.println("Promedio general de notas: " + (suma / totalNotas));
    }

    public void calcularPromedioFinal() {
        HashMap<String, ArrayList<String>> mapa = miData.getMapaEstudiantes();

        for (String doc : mapa.keySet()) {
            ArrayList<String> datos = mapa.get(doc);
            double nota1 = Double.parseDouble(datos.get(3));
            double nota2 = Double.parseDouble(datos.get(4));
            double nota3 = Double.parseDouble(datos.get(5));
            double promedio = (nota1 + nota2 + nota3) / 3;

            System.out.println("Estudiante: " + datos.get(1) + " - Promedio: " + promedio);

            if (promedio >= 3.5) {
                cantGanan++;
            } else if (promedio >= 2.5) {
                cantRecuperan++;
            } else {
                cantPierden++;
            }
        }
    }

    public void calcularEstados() {
        System.out.println("Cantidad de estudiantes que ganan: " + cantGanan);
        System.out.println("Cantidad de estudiantes que pueden recuperar: " + cantRecuperan);
        System.out.println("Cantidad de estudiantes que pierden: " + cantPierden);
    }
}

