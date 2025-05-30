package Encapsulamiento.EjemploGUIEncapsulamiento_SOLID.entidades;

public class Estudiante {
    private String nombre;
    private String materia;
    private double nota1;
    private double nota2;
    private double nota3;
    private double promedio;
    private String Documento;
    public Estudiante() {
    }
    public Estudiante(String nombre, String materia, double nota1, double nota2, double nota3, double promedio,String Documento) {
        this.nombre = nombre;
        this.materia = materia;
        this.Documento = Documento;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.promedio=promedio;
    }
    public String getDocumento() {
        return Documento;
    }
    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getMateria() {
        return materia;
    }
    public void setMateria(String materia) {
        this.materia = materia;
    }
    public double getNota1() {
        return nota1;
    }
    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }
    public double getNota2() {
        return nota2;
    }
    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }
    public double getNota3() {
        return nota3;
    }
    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }
    public double getPromedio() {
        return promedio;
    }
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}



