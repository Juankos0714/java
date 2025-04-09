package HashMap;

import java.util.HashMap;
import java.util.Scanner;

public class GestionInventarioHashMap {
    public static void main(String[] args) {
        HashMap<String, String> productos = new HashMap<>();

        HashMap<String, Double> precios = new HashMap<>();

        productos.put("A001", "Teclado");
        productos.put("A002", "Mouse");
        productos.put("A003", "Monitor");

        precios.put("A001", 25.99);
        precios.put("A002", 15.50);
        precios.put("A003", 150.00);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE INVENTARIO ---");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Buscar un producto");
            System.out.println("3. Añadir nuevo producto");
            System.out.println("4. Eliminar un producto");
            System.out.println("5. Actualizar precio");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nProductos en inventario:");
                    for (String codigo : productos.keySet()) {
                        System.out.println("Código: " + codigo +
                                " | Producto: " + productos.get(codigo) +
                                " | Precio: $" + precios.get(codigo));
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el código del producto: ");
                    String codigoBuscar = scanner.nextLine();
                    if (productos.containsKey(codigoBuscar)) {
                        System.out.println("Producto encontrado: " + productos.get(codigoBuscar) +
                                " | Precio: $" + precios.get(codigoBuscar));
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese código del nuevo producto: ");
                    String nuevoCodigo = scanner.nextLine();
                    System.out.print("Ingrese nombre del producto: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese precio del producto: ");
                    double nuevoPrecio = scanner.nextDouble();

                    productos.put(nuevoCodigo, nuevoNombre);
                    precios.put(nuevoCodigo, nuevoPrecio);
                    System.out.println("Producto añadido correctamente.");
                    break;

                case 4:
                    System.out.print("Ingrese código del producto a eliminar: ");
                    String codigoEliminar = scanner.nextLine();
                    if (productos.containsKey(codigoEliminar)) {
                        productos.remove(codigoEliminar);
                        precios.remove(codigoEliminar);
                        System.out.println("Producto eliminado correctamente.");
                    } else {
                        System.out.println("El producto no existe.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese código del producto a actualizar: ");
                    String codigoActualizar = scanner.nextLine();
                    if (precios.containsKey(codigoActualizar)) {
                        System.out.print("Ingrese nuevo precio: ");
                        double precioActualizado = scanner.nextDouble();
                        precios.put(codigoActualizar, precioActualizado);
                        System.out.println("Precio actualizado correctamente.");
                    } else {
                        System.out.println("El producto no existe.");
                    }
                    break;

                case 6:
                    System.out.println("¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 6);

        scanner.close();
    }
}