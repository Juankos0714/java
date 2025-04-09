package HashTable;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SistemaAutenticacionHashtable {

    // Hashtable para almacenar usuarios y contraseñas
    private static Hashtable<String, String> usuarios = new Hashtable<>();

    // Hashtable para almacenar intentos de inicio de sesión fallidos
    private static Hashtable<String, Integer> intentosFallidos = new Hashtable<>();

    // Máximo número de intentos fallidos permitidos
    private static final int MAX_INTENTOS = 3;

    public static void main(String[] args) {
        // Agregar algunos usuarios iniciales
        usuarios.put("admin", "admin123");
        usuarios.put("usuario1", "pass123");
        usuarios.put("usuario2", "clave456");

        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Simular múltiples hilos de ejecución para acceso concurrente
        ExecutorService executor = Executors.newFixedThreadPool(3);

        do {
            System.out.println("\n--- SISTEMA DE AUTENTICACIÓN ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar nuevo usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Mostrar todos los usuarios");
            System.out.println("5. Simular acceso concurrente");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    // Iniciar sesión
                    System.out.print("Usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String password = scanner.nextLine();

                    if (autenticar(usuario, password)) {
                        System.out.println("Inicio de sesión exitoso. ¡Bienvenido " + usuario + "!");
                        // Resetear intentos fallidos
                        intentosFallidos.remove(usuario);
                    } else {
                        System.out.println("Credenciales incorrectas.");

                        // Incrementar contador de intentos fallidos
                        int intentos = intentosFallidos.getOrDefault(usuario, 0) + 1;
                        intentosFallidos.put(usuario, intentos);

                        if (intentos >= MAX_INTENTOS) {
                            System.out.println("Usuario bloqueado por demasiados intentos fallidos.");
                        } else {
                            System.out.println("Intentos restantes: " + (MAX_INTENTOS - intentos));
                        }
                    }
                    break;

                case 2:
                    // Registrar nuevo usuario
                    System.out.print("Nuevo usuario: ");
                    String nuevoUsuario = scanner.nextLine();
                    if (usuarios.containsKey(nuevoUsuario)) {
                        System.out.println("El usuario ya existe.");
                    } else {
                        System.out.print("Contraseña: ");
                        String nuevaPass = scanner.nextLine();
                        usuarios.put(nuevoUsuario, nuevaPass);
                        System.out.println("Usuario registrado correctamente.");
                    }
                    break;

                case 3:
                    // Eliminar usuario
                    System.out.print("Usuario a eliminar: ");
                    String eliminarUsuario = scanner.nextLine();
                    if (usuarios.containsKey(eliminarUsuario)) {
                        usuarios.remove(eliminarUsuario);
                        intentosFallidos.remove(eliminarUsuario);
                        System.out.println("Usuario eliminado correctamente.");
                    } else {
                        System.out.println("El usuario no existe.");
                    }
                    break;

                case 4:
                    // Mostrar todos los usuarios
                    System.out.println("\nUsuarios registrados:");
                    for (String user : usuarios.keySet()) {
                        String estado = intentosFallidos.getOrDefault(user, 0) >= MAX_INTENTOS ?
                                "BLOQUEADO" : "ACTIVO";
                        System.out.println("- " + user + " (" + estado + ")");
                    }
                    break;

                case 5:
                    // Simular acceso concurrente
                    System.out.println("Simulando 10 operaciones concurrentes...");
                    for (int i = 0; i < 10; i++) {
                        final int index = i;
                        executor.execute(() -> {
                            // Alternando entre registrar y autenticar
                            if (index % 2 == 0) {
                                String tempUser = "usuarioConcurrente" + index;
                                usuarios.put(tempUser, "pass" + index);
                                System.out.println("Hilo " + index + " registró: " + tempUser);
                            } else {
                                String randomUser = "usuario" + (index % 3);
                                String pass = usuarios.getOrDefault(randomUser, "");
                                boolean success = !pass.isEmpty();
                                System.out.println("Hilo " + index + " intentó autenticar: " +
                                        randomUser + " - " + (success ? "Éxito" : "Falló"));
                            }
                        });
                    }
                    System.out.println("Operaciones concurrentes en ejecución...");
                    break;

                case 6:
                    System.out.println("¡Hasta pronto!");
                    executor.shutdown();
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 6);

        scanner.close();
    }

    private static boolean autenticar(String usuario, String password) {
        // Verificar si el usuario está bloqueado
        if (intentosFallidos.getOrDefault(usuario, 0) >= MAX_INTENTOS) {
            return false;
        }

        // Verificar credenciales
        String storedPassword = usuarios.get(usuario);
        return storedPassword != null && storedPassword.equals(password);
    }
}