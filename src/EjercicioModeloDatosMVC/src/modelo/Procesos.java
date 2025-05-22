package EjercicioModeloDatosMVC.src.modelo;

import EjercicioModeloDatosMVC.src.controlador.Coordinador;
import modelo.dto.OperadorDTO;

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
public class Procesos {
	
	private Coordinador miCoordinador;
	

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador=miCoordinador;
	}
	
	public Procesos() {
		System.out.println("Crea un objeto");
	}
	
	public void calcularSueldoNuevo(OperadorDTO operador) {
		
		double por=0;
		
		if (operador.getSueldo()<500 && operador.getAntiguedad()>=10) {
			por=0.2;
		}else if(operador.getSueldo()<500 && operador.getAntiguedad()<10) {
			por=0.05;
		}else if(operador.getSueldo()>=500){
			por=0;
		}
		
		double aumento=operador.getSueldo()*por;
		double sueldoNuevo=operador.getSueldo()+aumento;
		
		operador.setSueldoNuevo(sueldoNuevo);
		operador.setAumento(por*100);
		
	}
	

}
