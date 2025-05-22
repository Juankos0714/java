package EjercicioModeloDatosMVC.src.controlador;


import EjercicioModeloDatosMVC.src.data.ModeloDatos;
import EjercicioModeloDatosMVC.src.modelo.dao.MascotaDAO;
import EjercicioModeloDatosMVC.src.modelo.dao.OperadorDAO;
import EjercicioModeloDatosMVC.src.vistas.RegistroMascotaGUI;
import EjercicioModeloDatosMVC.src.vistas.RegistroOperadorGUI;
import InterfacesGraficas.IMC.Procesos;

public class Principal {

	/*
	 * 4. De un operario se conoce su sueldo y los años de antigüedad. 
	 * Se pide crear un programa que lea los datos de entrada e informe: 
		-Si el sueldo es inferior a 500 y su antigüedad es igual 
		o superior a 10 años, otorgarle un aumento del 20%, mostrar el 
		sueldo a pagar. 
		-Si el sueldo es inferior a 500 pero su antigüedad es menor 
		a 10 años, otorgarle un aumento de 5%. 
		-Si el sueldo es mayor o igual a 500 mostrar el sueldo en
		 pantalla sin cambios.
	 */
	public static void main(String[] args) {
			
		//Instancias de las clases unicas
		Coordinador miCoordinador=new Coordinador();
		ModeloDatos miModeloDatos=new ModeloDatos();
		Procesos miProcesos=new Procesos();
		OperadorDAO miOperadorDAO=new OperadorDAO();
		MascotaDAO miMascotaDAO=new MascotaDAO();
		RegistroOperadorGUI miVentanaRegistroOperador=new RegistroOperadorGUI();
		RegistroMascotaGUI miVentanaMascotaGUI=new RegistroMascotaGUI();
		
		//Relaciones entre el coordinador y las clases
		miCoordinador.setMiModeloDatos(miModeloDatos);
		miCoordinador.setMiProcesos(miProcesos);
		miCoordinador.setMiOperadorDAO(miOperadorDAO);
		miCoordinador.setMiMascotaDAO(miMascotaDAO);
		miCoordinador.setMiVentanaRegistroOperador(miVentanaRegistroOperador);
		miCoordinador.setRegistroMascota(miVentanaMascotaGUI);
		
		//Relaciones entre las clases y el coordinador
		miModeloDatos.setCoordinador(miCoordinador);
		miProcesos.setCoordinador(miCoordinador);
		miOperadorDAO.setCoordinador(miCoordinador);
		miMascotaDAO.setCoordinador(miCoordinador);
		miVentanaRegistroOperador.setCoordinador(miCoordinador);
		miVentanaMascotaGUI.setCoordinador(miCoordinador);
		
		//inicio del sistema
		miCoordinador.mostrarVentana(1);
		
		
		
	}

}
