package Encapsulamiento.EjemploGUIEncapsulamiento.clases;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import Encapsulamiento.EjemploGUIEncapsulamiento.entidades.Estudiante;

/**
 * Esta clase gestiona el almacenamiento y operaciones CRUD para los estudiantes
 */
public class ModeloDatos {
    // Using final for immutable declaration
    private final Map<String, Estudiante> estudiantesMap;

    public ModeloDatos() {
        // Initialize with initial capacity for better performance if expected size is known
        // Using diamond operator for type inference
        estudiantesMap = new HashMap<>();
    }

    /**
     * Registra un nuevo estudiante en el sistema
     * @param est El estudiante a registrar
     * @return "ok" si el registro fue exitoso, mensaje de error en caso contrario
     * @throws IllegalArgumentException si el estudiante es nulo o no tiene documento
     */
    public String registrarEstudiante(Estudiante est) {
        validateEstudiante(est);

        String documento = est.getDocumento().trim();
        if (estudiantesMap.containsKey(documento)) {
            return "Estudiante existente";
        }

        // Storing a deep copy to prevent external modification
        estudiantesMap.put(documento, copyEstudiante(est));
        return "ok";
    }

    /**
     * Consulta un estudiante por su documento
     * @param documento El documento del estudiante a consultar
     * @return El estudiante encontrado o null si no existe
     */
    public Estudiante consultaEstudiante(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return null;
        }

        Estudiante estudiante = estudiantesMap.get(documento.trim());
        // Return a copy to prevent external modification
        return estudiante != null ? copyEstudiante(estudiante) : null;
    }

    /**
     * Genera un listado con la información de todos los estudiantes
     * @return String con la información formateada de todos los estudiantes
     */
    public String imprimirListaEstudiantes() {
        StringBuilder msj = new StringBuilder("DATOS ESTUDIANTES\n");

        if (estudiantesMap.isEmpty()) {
            return msj.toString();
        }

        // Sort by name for better readability
        estudiantesMap.values().stream()
                .sorted((e1, e2) -> e1.getNombre().compareToIgnoreCase(e2.getNombre()))
                .forEach(estudiante -> {
                    msj.append("Nombre: ").append(estudiante.getNombre())
                            .append(", Materia: ").append(estudiante.getMateria()).append("\n");
                    msj.append("Nota1: ").append(formatNota(estudiante.getNota1()))
                            .append(", Nota2: ").append(formatNota(estudiante.getNota2()))
                            .append(", Nota3: ").append(formatNota(estudiante.getNota3()))
                            .append(", Promedio: ").append(formatNota(estudiante.getPromedio()))
                            .append("\n\n");
                });

        return msj.toString();
    }

    /**
     * Formatea una nota para mostrarla con un máximo de 2 decimales
     */
    private String formatNota(double nota) {
        return String.format("%.2f", nota);
    }

    /**
     * Elimina un estudiante del sistema
     * @param documento El documento del estudiante a eliminar
     * @return true si se eliminó correctamente, false si no existía
     */
    public boolean eliminarEstudiante(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return false;
        }

        return estudiantesMap.remove(documento.trim()) != null;
    }

    /**
     * Actualiza la información de un estudiante existente
     * @param est El estudiante con la información actualizada
     * @return "ok" si la actualización fue exitosa, mensaje de error en caso contrario
     * @throws IllegalArgumentException si el estudiante es nulo o no tiene documento
     */
    public String actualizarEstudiante(Estudiante est) {
        validateEstudiante(est);

        String documento = est.getDocumento().trim();
        if (!estudiantesMap.containsKey(documento)) {
            return "Estudiante inexistente";
        }

        // Store a deep copy
        estudiantesMap.put(documento, copyEstudiante(est));
        return "ok";
    }

    /**
     * Valida que el objeto estudiante sea válido
     * @throws IllegalArgumentException si el estudiante es nulo o no tiene documento
     */
    private void validateEstudiante(Estudiante est) {
        if (est == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo");
        }

        if (est.getDocumento() == null || est.getDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("El documento del estudiante es obligatorio");
        }
    }

    /**
     * Crea una copia del estudiante para evitar modificaciones externas
     * @param original El estudiante original
     * @return Una copia del estudiante
     */
    private Estudiante copyEstudiante(Estudiante original) {
        Estudiante copia = new Estudiante();
        copia.setNombre(original.getNombre());
        copia.setMateria(original.getMateria());
        copia.setDocumento(original.getDocumento());
        copia.setNota1(original.getNota1());
        copia.setNota2(original.getNota2());
        copia.setNota3(original.getNota3());
        copia.setPromedio(original.getPromedio());
        return copia;
    }

    /**
     * Obtiene una vista no modificable del mapa de estudiantes
     * @return Mapa no modificable con los estudiantes
     */
    public Map<String, Estudiante> getEstudiantesMap() {
        return Collections.unmodifiableMap(estudiantesMap);
    }

    /**
     * Obtiene el número de estudiantes registrados
     * @return Cantidad de estudiantes
     */
    public int getNumeroEstudiantes() {
        return estudiantesMap.size();
    }
}
