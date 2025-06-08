package MVC_IMC.modelo.dao;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.dto.PersonaDTO;
import TrabajoComboBox.conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    public String registrarPersona(PersonaDTO persona) {
        String resultado = "";
        Connection connection = null;
        Conexion conexion = new Conexion();
        PreparedStatement preStatement = null;
        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "INSERT INTO personas (documento, nombre, edad) VALUES (?, ?, ?)";
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
    public ArrayList<PersonaDTO> consultarPersonas() {
        ArrayList<PersonaDTO> listaPersonas = new ArrayList<>();
        Connection connection = null;
        Conexion conexion = new Conexion();
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "SELECT * FROM personas ORDER BY nombre";
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
        int filasAfectadas=0;


        boolean eliminacion =false;

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "DELETE FROM personas WHERE documento = ?";
                statement = connection.prepareStatement(consulta);
                statement.setString(1, documento);
                filasAfectadas = statement.executeUpdate();
                eliminacion = (filasAfectadas > 0);

            }
        } catch (SQLException e) {
            System.out.println("Error al encontrar el usuario del usuario: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                conexion.desconectar();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return eliminacion;
    }



    public boolean existePersona(String documento) {
        Connection connection = null;
        Conexion conexion = new Conexion();
        PreparedStatement statement = null;
        ResultSet result = null;
        boolean existe=false;

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "SELECT COUNT(*) FROM personas WHERE documento = ?";
                statement = connection.prepareStatement(consulta);
                statement.setString(1, documento);
                result = statement.executeQuery();
                if (result.next()) {
                    int count = result.getInt(1);
                    existe = (count > 0);
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
        return existe;
    }

    public String actualizarPersona(PersonaDTO persona) {
        String resultado = "";
        Connection connection = null;
        Conexion conexion = new Conexion();
        PreparedStatement preStatement = null;

        try {
            connection = conexion.getConnection();
            if (connection != null) {
                String consulta = "UPDATE personas SET nombre = ?, edad = ? WHERE documento = ?";

                preStatement = connection.prepareStatement(consulta);
                preStatement.setString(1, persona.getNombre());
                preStatement.setInt(2, persona.getEdad());
                preStatement.setString(3, persona.getDocumento()); // WHERE va al final

                int filasAfectadas = preStatement.executeUpdate();
                if (filasAfectadas > 0) {
                    resultado = "Persona actualizada exitosamente";
                } else {
                    resultado = "No se encontró la persona con ese documento";
                }
            } else {
                resultado = "No se pudo conectar a la base de datos";
            }
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar: " + e.getMessage());
            resultado = "Error al actualizar la persona: " + e.getMessage();
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

    public void setCoordinador(Coordinador miCoordinador) {
    }
}



