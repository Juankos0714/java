package MVC_IMC.modelo.conexion;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.dto.PersonaDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private String nombreBd = "MVC_IMC";
    private String usuario = "root";
    private String password = "carlosmanuel";
    private String url = "jdbc:mysql://localhost:3306/" + nombreBd + "?useUnicode=true&use"
            + "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC";

    //constructor de la clase
    public ConexionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL cargado correctamente");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, usuario, password);
            if (conn != null) {
                System.out.println("Conexión exitosa a la BD: " + nombreBd);
            } else {
                System.out.println("******************NO SE PUDO CONECTAR " + nombreBd);
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            System.out.println("Verifique que MySQL esté encendido y las credenciales sean correctas");
        }
        return conn;
    }

    public void desconectar() {

    }

    public void setCoordinador(Coordinador miCoordinador) {
    }
}

//Script de la BD
// create database MVC_IMC;
//use  MVC_IMC;
//CREATE TABLE personas (
//        documento varchar(10) not null PRIMARY KEY,
//
//nombre VARCHAR(100) NOT NULL,
//edad INT UNSIGNED NOT NULL CHECK (edad BETWEEN 0 AND 120),
//peso DECIMAL(5,2),
//talla DECIMAL(4,2),
//imc DECIMAL(5,2),
//
//estado varchar(50),
//mensaje varchar(100)
//);