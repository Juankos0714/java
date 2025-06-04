package TrabajoComboBox.dao;

import TrabajoComboBox.conexion.Conexion;
import TrabajoComboBox.dto.PersonaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PersonaDAO {
    public String registrarPersona(PersonaDTO persona) throws SQLException {
        String resultado="";
        Connection connection=null;
        Conexion conexion=new Conexion();
        PreparedStatement preStatement=null;
        connection=conexion.getConnection();
        if (connection!=null) {
            String consulta="INSERT INTO empleado (documento,nombre,edad)" +
                    " VALUES (?,?,?)";
            System.out.println(consulta);
            try {
                preStatement = connection.prepareStatement(consulta);
                preStatement.setString(1, persona.getDocumento());
                preStatement.setString(2,persona.getNombre());
                preStatement.setInt(3, persona.getEdad());
                preStatement.execute();
                resultado="ok";
            } catch (SQLException e) {
                System.out.println("No se pudo registrar el dato: "+e.getMessage());
                resultado="error";
            }finally {
                preStatement.close();
                connection.close();
                conexion.desconectar();
            }
        }else {
            System.out.println("No conecta!");
        }
        return resultado;
    }
    public PersonaDTO consultarPersonaPorDocumento(String documento) {
        Connection connection=null;
        Conexion miConexion=new Conexion();
        PreparedStatement statement=null;
        ResultSet result=null;
        PersonaDTO miUsuario=null;
        connection=miConexion.getConnection();
        String consulta="SELECT * FROM empleado where documento= ? ";
        try {
            if (connection!=null) {
                statement=connection.prepareStatement(consulta);
                statement.setString(1, documento);
                result=statement.executeQuery();
                if(result.next()==true){
                    miUsuario=new PersonaDTO();
                    miUsuario.setDocumento(result.getString("documento"));
                    miUsuario.setNombre(result.getString("nombre"));
                    miUsuario.setEdad(result.getInt("edad"));
                }
            }else{
                miUsuario=null;
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta del usuario: "+e.getMessage());
        }finally {
            miConexion.desconectar();
        }
        return miUsuario;
    }
}
