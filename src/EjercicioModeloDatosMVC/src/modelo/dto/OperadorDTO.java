package modelo.dto;

public class OperadorDTO {
	
	private String documento;
	private String nombre;
	private double sueldo;
	private int antiguedad;
	private double sueldoNuevo;
	private double aumento;

	public OperadorDTO() {
		nombre="Default";
		documento="xxxxx";
	}
	
	

	public OperadorDTO(String documento, String nombre, double sueldo, int antiguedad, double sueldoNuevo,
			double aumento) {
		this.documento = documento;
		this.nombre = nombre;
		this.sueldo = sueldo;
		this.antiguedad = antiguedad;
		this.sueldoNuevo = sueldoNuevo;
		this.aumento = aumento;
	}



	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public double getSueldoNuevo() {
		return sueldoNuevo;
	}

	public void setSueldoNuevo(double sueldoNuevo) {
		this.sueldoNuevo = sueldoNuevo;
	}

	public double getAumento() {
		return aumento;
	}

	public void setAumento(double aumento) {
		this.aumento = aumento;
	}
	
	
	@Override
	public String toString() {
		return "Operador [documento=" + documento + ", nombre=" + nombre + ", sueldo=" + sueldo + ", antiguedad="
				+ antiguedad + ", sueldoNuevo=" + sueldoNuevo + ", aumento=" + aumento + "%]";
	}
	
}
