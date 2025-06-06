package MVC_IMC.modelo.dao;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.conexion.ConexionBD;
import MVC_IMC.modelo.dto.PersonaDTO;
import TrabajoComboBox.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private Coordinador miCoordinador;
    private ConexionBD miConexionBD;

    public PersonaDAO() {
        this.miConexionBD = new ConexionBD();
    }

    public String registrarPersona(PersonaDTO persona) {
        String resultado = "";
        Connection connection = null;
        Conexion conexion = new Conexion();
        PreparedStatement preStatement = null;
        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "INSERT INTO personas (documento, nombre, edad) VALUES (?, ?, ?,)";
                System.out.println(consulta);

                preStatement = connection.prepareStatement(consulta);
                preStatement.setString(1, persona.getDocumento());
                preStatement.setString(2, persona.getNombre());
                preStatement.setInt(3, persona.getEdad());


                preStatement.execute();
                resultado = "Persona registrada exitosamente";
            } else {
                resultado = "No se pudo conectar a la base de datos";
            }
        } catch (SQLException e) {
            System.out.println("No se pudo registrar el dato: " + e.getMessage());
            resultado = "Error al registrar la persona: " + e.getMessage();
        } finally {
            try {
                if (preStatement != null) preStatement.close();
                if (connection != null) connection.close();
                conexion.desconectar();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return resultado;
    }

    public PersonaDTO consultarPersonaPorDocumento(String documento) {
        Connection connection = null;
        Conexion conexion = new Conexion();
        PreparedStatement statement = null;
        ResultSet result = null;
        PersonaDTO persona = null;

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "SELECT * FROM personas WHERE documento = ?";
                statement = connection.prepareStatement(consulta);
                statement.setString(1, documento);
                result = statement.executeQuery();

                if (result.next()) {
                    persona = new PersonaDTO();
                    persona.setDocumento(result.getString("documento"));
                    persona.setNombre(result.getString("nombre"));
                    persona.setEdad(result.getInt("edad"));

                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta del usuario: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                conexion.desconectar();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return persona;
    }
}

public ArrayList<PersonaDTO> consultarPersonas() {
    ArrayList<PersonaDTO> listaPersonas = new ArrayList<>();
    Connection connection = null;
    Conexion conexion = new Conexion();
    PreparedStatement statement = null;
    ResultSet result = null;

    try {
        connection = conexion.getConnection();
        if (connection != null) {
            String consulta = "SELECT * FROM persona ORDER BY nombre";
            statement = connection.prepareStatement(consulta);
            result = statement.executeQuery();

            while (result.next()) {
                PersonaDTO persona = new PersonaDTO();
                persona.setDocumento(result.getString("documento"));
                persona.setNombre(result.getString("nombre"));
                persona.setEdad(result.getInt("edad"));

                listaPersonas.add(persona);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error en la consulta de personas: " + e.getMessage());
    } finally {
        try {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            conexion.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
    return listaPersonas;
}

    public boolean eliminarPersona(String documento) {
        Connection connection = null;
        Conexion conexion = new Conexion();
        PreparedStatement statement = null;
        ResultSet result = null;
        PersonaDTO persona = null;

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "SELECT * FROM personas WHERE documento = ?";
                statement = connection.prepareStatement(consulta);
                statement.setString(1, documento);
                result = statement.executeQuery();

                if (result.next()) {
                    persona = new PersonaDTO();
                    persona.setDocumento(result.getString("documento"));
                    persona.setNombre(result.getString("nombre"));
                    persona.setEdad(result.getInt("edad"));

                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta del usuario: " + e.getMessage());
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                conexion.desconectar();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
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