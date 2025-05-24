package EjemploMVCPersona.src.controlador;


import EjemploMVCPersona.src.modelo.Procesos;
import EjemploMVCPersona.src.modelo.conexion.ConexionBD;
import EjemploMVCPersona.src.modelo.dao.PersonaDAO;
import EjemploMVCPersona.src.vista.*;

public class Relaciones {

	public Relaciones() {
		VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
			VentanaRegistro ventanaRegistro=new VentanaRegistro(ventanaPrincipal,true);
		VentanaOperaciones ventanaOperaciones=new VentanaOperaciones(ventanaRegistro,true);
		VentanaConsultaIndividual ventanaConsultaIndividual=new VentanaConsultaIndividual(ventanaPrincipal,true);
		VentanaConsultarLista ventanaConsultarLista=new VentanaConsultarLista(ventanaPrincipal,true);
		Procesos misProcesos =new Procesos();
		PersonaDAO miPersonaDAO=new PersonaDAO();
		ConexionBD miConexionBD=new ConexionBD();
		Coordinador miCoordinador =new Coordinador();

		ventanaPrincipal.setCoordinador(miCoordinador);
		ventanaRegistro.setCoordinador(miCoordinador);
		ventanaOperaciones.setCoordinador(miCoordinador);
		ventanaConsultaIndividual.setCoordinador(miCoordinador);
		ventanaConsultarLista.setCoordinador(miCoordinador);
		miPersonaDAO.setCoordinador(miCoordinador);
		miConexionBD.setCoordinador(miCoordinador);
		misProcesos.setCoordinador(miCoordinador);

		miCoordinador.setVentanaPrincipal(ventanaPrincipal);
		miCoordinador.setVentanaRegistro(ventanaRegistro);
		miCoordinador.setVentanaOperaciones(ventanaOperaciones);
		miCoordinador.setVentanaConsultaIndividual(ventanaConsultaIndividual);
		miCoordinador.setVentanaConsultarLista(ventanaConsultarLista);
		miCoordinador.setProcesos(misProcesos);
		miCoordinador.setMiPersonaDAO(miPersonaDAO);
		miCoordinador.setMiConexionBD(miConexionBD);


		// Mostrar ventana principal
		miCoordinador.mostrarVentanaPrincipal();
	}

}
