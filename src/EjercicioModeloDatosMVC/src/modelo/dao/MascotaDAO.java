package EjercicioModeloDatosMVC.src.modelo.dao;


import EjercicioModeloDatosMVC.src.controlador.Coordinador;
import EjercicioModeloDatosMVC.src.data.ModeloDatos;
import modelo.dto.MascotaDTO;

public class MascotaDAO {

	ModeloDatos miModelo;
	private Coordinador miCoordinador;
	

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador=miCoordinador;
	}
	
	public MascotaDAO() {
		miModelo=new ModeloDatos();
	}

	public String registrarMascota(MascotaDTO mascota) {
		miModelo.mapaMascotas.put(mascota.getIdMascota(), mascota);
		
		consultarListaMascotas();
		
		return "si";
	}
	
	public MascotaDTO consultarMascotaPorId(String idMascota) {
	
		return miModelo.mapaMascotas.get(idMascota);
	}
	
	public void consultarListaMascotas() {
		System.out.println(miModelo.mapaMascotas);
	}
	
	public void actualizarMascota(MascotaDTO mascota) {
		
	}
	
	public void eliminarMascota(String idMascota) {
		
	}
	
}
