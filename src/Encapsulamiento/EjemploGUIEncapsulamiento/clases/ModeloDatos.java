package Encapsulamiento.EjemploGUIEncapsulamiento.clases;
import java.util.HashMap;
import Encapsulamiento.EjemploGUIEncapsulamiento.entidades.Estudiante;

import javax.print.Doc;

public class ModeloDatos {
    HashMap<String, Estudiante> estudiantesMap;
    public ModeloDatos() {
        estudiantesMap=new HashMap<String, Estudiante>();
    }
    public String registrarEstudiante(Estudiante est) {
        if (!estudiantesMap.containsKey(est.getDocumento())) {
            estudiantesMap.put(est.getDocumento(), est);
            return "ok";
        }else {
            return "Estudiante existente";
        }
    }
    public Estudiante consultaEstudiante(String Documento) {
        Estudiante estTemporal=null;
        if (estudiantesMap.containsKey(Documento)) {
            estTemporal=estudiantesMap.get(Documento);
        }
        return estTemporal;
    }
    public String imprimirListaEstudiantes() {
        String msj="DATOS ESTUDIANTES\n";
        for (Estudiante estudiante : estudiantesMap.values()) {
            msj+="Nombre: "+estudiante.getNombre()+", Materia: "+estudiante.getMateria()+"\n";
            msj+="Nota1: "+estudiante.getNota1()+", Nota2: "+estudiante.getNota2()+", ";
            msj+="Nota3: "+estudiante.getNota3()+", Promedio:"+estudiante.getPromedio()+"\n\n";
        }
        System.out.println(msj);
        return msj;
    }
    public void eliminarEstudiante(String Documento){
        Estudiante estTemporal=null;
        if (estudiantesMap.containsKey(Documento)) {
            estTemporal=estudiantesMap.remove(Documento);
        }


    }
    public String actualizarEstudiante(Estudiante est) {
        if (estudiantesMap.containsKey(est.getDocumento())) {
            estudiantesMap.put(est.getDocumento(), est);
            return "ok";
        } else {
            return "Estudiante inexistente";
        }
    }
}