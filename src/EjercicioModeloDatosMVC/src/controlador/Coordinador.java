package EjercicioModeloDatosMVC.src.controlador;


import EjercicioModeloDatosMVC.src.data.ModeloDatos;
import EjercicioModeloDatosMVC.src.modelo.dao.MascotaDAO;
import EjercicioModeloDatosMVC.src.modelo.dao.OperadorDAO;
import EjercicioModeloDatosMVC.src.vistas.RegistroMascotaGUI;
import EjercicioModeloDatosMVC.src.vistas.RegistroOperadorGUI;
import InterfacesGraficas.IMC.Procesos;

public class Coordinador {

	private ModeloDatos miModeloDatos;
	private Procesos miProcesos;
	private OperadorDAO miOperadorDAO;
	private MascotaDAO miMascotaDAO;
	private RegistroOperadorGUI miVentanaRegistroOperador;
	private RegistroMascotaGUI miVentanaMascotaGUI;
	

	public void setMiModeloDatos(ModeloDatos miModeloDatos) {
		this.miModeloDatos=miModeloDatos;
	}


	public void setMiProcesos(Procesos miProcesos) {
		this.miProcesos = miProcesos;
	}

	public void setMiOperadorDAO(OperadorDAO miOperadorDAO) {
		this.miOperadorDAO = miOperadorDAO;
	}

	public void setMiMascotaDAO(MascotaDAO miMascotaDAO) {
		this.miMascotaDAO = miMascotaDAO;
	}


	public void setMiVentanaRegistroOperador(RegistroOperadorGUI miVentanaRegistroOperador) {
		this.miVentanaRegistroOperador = miVentanaRegistroOperador;
	}
	

	public void setRegistroMascota(RegistroMascotaGUI miVentanaMascotaGUI) {
		this.miVentanaMascotaGUI=miVentanaMascotaGUI;
	}

	///////////////
	
	public void mostrarVentana(int ventana) {
		switch (ventana) {
		case 1: miVentanaRegistroOperador.setVisible(true);break;
		case 2:miVentanaMascotaGUI.setVisible(true); break;
			
		}
	}

	public void mostrarVentanaRegistro() {
		miVentanaRegistroOperador.setVisible(true);
	}


	public void mostrarVentanaMascotas() {
		miVentanaMascotaGUI.setVisible(true);
	}


	public void calcularSueldoNuevo(modelo.dto.OperadorDTO miOperador) {
		miProcesos.calcularSueldoNuevo(miOperador);
	}


	public String registrarOperador(modelo.dto.OperadorDTO miOperadorDTO) {
		return miOperadorDAO.registrarOperador(miOperadorDTO);
	}


	public modelo.dto.OperadorDTO consultarOperadorPorDocumento(String documento) {
		return miOperadorDAO.consultarOperadorPorDocumento(documento);
	}


	public void consultarOperadorLista() {
		miOperadorDAO.consultarOperadorLista();
	}

	public String registrarMascota(modelo.dto.MascotaDTO miMascotaDto) {
		return miMascotaDAO.registrarMascota(miMascotaDto);
	}

	
	
	
}
