package EjercicioModeloDatosMVC.src.data;

import java.util.HashMap;

import EjercicioModeloDatosMVC.src.controlador.Coordinador;
import modelo.dto.MascotaDTO;
import modelo.dto.OperadorDTO;


public class ModeloDatos {
	
	public HashMap<String, OperadorDTO> mapaOperadores;
	public HashMap<String, MascotaDTO> mapaMascotas;
	
	private Coordinador miCoordinador;
	

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador=miCoordinador;
	}

	
	public ModeloDatos() {
		mapaOperadores=new HashMap<String, OperadorDTO>();
		mapaMascotas=new HashMap<String, MascotaDTO>();
	}

	
}
