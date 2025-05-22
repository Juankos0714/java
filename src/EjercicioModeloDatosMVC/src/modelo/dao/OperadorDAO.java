package EjercicioModeloDatosMVC.src.modelo.dao;


import EjercicioModeloDatosMVC.src.controlador.Coordinador;
import EjercicioModeloDatosMVC.src.data.ModeloDatos;
import modelo.dto.OperadorDTO;

public class OperadorDAO {
	
	ModeloDatos miModelo;
	private Coordinador miCoordinador;
	

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador=miCoordinador;
	}
	
	public OperadorDAO() {
		miModelo=new ModeloDatos();
	}
	
	public String registrarOperador(OperadorDTO ope) {
		if (miModelo.mapaOperadores.containsKey(ope.getDocumento())==false) {
			miModelo.mapaOperadores.put(ope.getDocumento(), ope);
			return "si";
		}else {
			return "no";
		}
	}
	
	public OperadorDTO consultarOperadorPorDocumento(String documento) {
		
		OperadorDTO operaTemporal=null;
		
		if (miModelo.mapaOperadores.containsKey(documento)) {
			operaTemporal=miModelo.mapaOperadores.get(documento);
		}
		
		return operaTemporal;
		
	}
	
	public void consultarOperadorLista() {
		System.out.println("<<<<<<<<<<<<<< LISTA OPERADORES>>>>>>>>>>>>");
		System.out.println();
		
		for (OperadorDTO op : miModelo.mapaOperadores.values()) {
			System.out.println(op);
		}
	
	}
	
	public void actualizarOperador(OperadorDTO op) {
		
	}
	
	public void eliminarOperador(String documento) {
		
	}

}
