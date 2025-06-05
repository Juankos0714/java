package TrabajoComboBox.dao;

import TrabajoComboBox.conexion.Conexion;
import TrabajoComboBox.vo.PersonaVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonaDAO {

    public String registrarPersona(PersonaVo persona) {
        String resultado = "";
        Connection connection = null;
        Conexion conexion = new Conexion();
        PreparedStatement preStatement = null;

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "INSERT INTO persona (documento, nombre, edad) VALUES (?, ?, ?)";
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

    public ArrayList<PersonaVo> consultarPersonas() {
        ArrayList<PersonaVo> listaPersonas = new ArrayList<>();
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
                    PersonaVo persona = new PersonaVo();
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

    public PersonaVo consultarPersonaPorDocumento(String documento) {
        Connection connection = null;
        Conexion conexion = new Conexion();
        PreparedStatement statement = null;
        ResultSet result = null;
        PersonaVo persona = null;

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "SELECT * FROM persona WHERE documento = ?";
                statement = connection.prepareStatement(consulta);
                statement.setString(1, documento);
                result = statement.executeQuery();

                if (result.next()) {
                    persona = new PersonaVo();
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