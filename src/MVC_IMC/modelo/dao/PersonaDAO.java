package MVC_IMC.modelo.dao;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.conexion.ConexionBD;
import MVC_IMC.modelo.dto.PersonaDTO;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private Coordinador miCoordinador;
    private ConexionBD miConexionBD;

    public PersonaDAO() {
        this.miConexionBD = new ConexionBD();
    }

    public void registrarPersona(PersonaDTO persona) {
        if (persona != null) {
            try {
                miConexionBD.guardarPersona(persona);
                System.out.println("Persona registrada exitosamente: " + persona.getNombre());
            } catch (Exception e) {
                System.err.println("Error al registrar persona: " + e.getMessage());
            }
        } else {
            System.err.println("Error: No se puede registrar una persona nula");
        }
    }

    public PersonaDTO consultarPersona(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: Nombre no puede estar vacío");
            return null;
        }

        try {
            PersonaDTO persona = miConexionBD.buscarPersona(nombre);
            if (persona != null) {
                System.out.println("Persona encontrada: " + persona.getNombre());
            } else {
                System.out.println("Persona no encontrada: " + nombre);
            }
            return persona;
        } catch (Exception e) {
            System.err.println("Error al consultar persona: " + e.getMessage());
            return null;
        }
    }

    public List<PersonaDTO> consultarTodasLasPersonas() {
        try {
            List<PersonaDTO> listaPersonas = miConexionBD.obtenerTodasLasPersonas();
            System.out.println("Consultadas " + listaPersonas.size() + " personas de la base de datos");
            return listaPersonas;
        } catch (Exception e) {
            System.err.println("Error al consultar todas las personas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean eliminarPersona(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: Nombre no puede estar vacío");
            return false;
        }

        try {
            boolean eliminado = miConexionBD.eliminarPersona(nombre);
            if (eliminado) {
                System.out.println("Persona eliminada exitosamente: " + nombre);
            } else {
                System.out.println("No se pudo eliminar la persona: " + nombre);
            }
            return eliminado;
        } catch (Exception e) {
            System.err.println("Error al eliminar persona: " + e.getMessage());
            return false;
        }
    }

    public int obtenerTotalPersonas() {
        try {
            return miConexionBD.obtenerTotalPersonas();
        } catch (Exception e) {
            System.err.println("Error al obtener total de personas: " + e.getMessage());
            return 0;
        }
    }

    public List<PersonaDTO> consultarPersonasPorEstado(String estado) {
        List<PersonaDTO> personasFiltradas = new ArrayList<>();

        if (estado == null || estado.trim().isEmpty()) {
            System.out.println("Error: Estado no puede estar vacío");
            return personasFiltradas;
        }

        try {
            List<PersonaDTO> todasLasPersonas = miConexionBD.obtenerTodasLasPersonas();
            for (PersonaDTO persona : todasLasPersonas) {
                if (persona.getEstado() != null &&
                        persona.getEstado().equalsIgnoreCase(estado.trim())) {
                    personasFiltradas.add(persona);
                }
            }
            System.out.println("Encontradas " + personasFiltradas.size() +
                    " personas con estado: " + estado);
        } catch (Exception e) {
            System.err.println("Error al consultar personas por estado: " + e.getMessage());
        }

        return personasFiltradas;
    }

    public boolean existePersona(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        try {
            return miConexionBD.existePersona(nombre);
        } catch (Exception e) {
            System.err.println("Error al verificar existencia de persona: " + e.getMessage());
            return false;
        }
    }

    public void mostrarEstadisticas() {
        try {
            List<PersonaDTO> todasLasPersonas = consultarTodasLasPersonas();
            System.out.println("\n=== ESTADÍSTICAS DE LA BASE DE DATOS ===");
            System.out.println("Total de personas registradas: " + todasLasPersonas.size());

            java.util.Map<String, Integer> estadosCount = new java.util.HashMap<>();
            for (PersonaDTO persona : todasLasPersonas) {
                String estado = persona.getEstado();
                estadosCount.put(estado, estadosCount.getOrDefault(estado, 0) + 1);
            }

            System.out.println("\nDistribución por estados:");
            for (java.util.Map.Entry<String, Integer> entry : estadosCount.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " personas");
            }
            System.out.println("========================================\n");
        } catch (Exception e) {
            System.err.println("Error al mostrar estadísticas: " + e.getMessage());
        }
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public void setConexionBD(ConexionBD conexionBD) {
        this.miConexionBD = conexionBD;
    }

    public ConexionBD getConexionBD() {
        return miConexionBD;
    }
    public String actualizarPersona(PersonaDTO persona) {

        String resp="";
        System.out.println(persona);
        if (ConexionBD.personasMap.containsKey(persona.getNombre())) {
            ConexionBD.personasMap.put(persona.getNombre(), persona);
            resp="ok";
        } else {
            resp="error";
        }

        return resp;

    }
}