package ejerciciosArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class EjemploArrayList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> nombres = new ArrayList<String>();
        int opcion;

        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar nombre");
            System.out.println("2. Mostrar nombres");
            System.out.println("3. Eliminar nombre");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un nombre: ");
                    String nombre = scanner.nextLine();
                    nombres.add(nombre);
                    System.out.println("Nombre agregado correctamente.");
                    break;
                case 2:
                    System.out.println("\nLista de nombres:");
                    for (int i = 0; i < nombres.size(); i++) {
                        System.out.println((i + 1) + ". " + nombres.get(i));
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el índice del nombre a eliminar: ");
                    int indice = scanner.nextInt();
                    scanner.nextLine();
                    if (indice >= 1 && indice <= nombres.size()) {
                        nombres.remove(indice - 1);
                        System.out.println("Nombre eliminado correctamente.");
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);

        scanner.close();
    }
}

