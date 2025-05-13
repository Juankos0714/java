package POO.EjercicioModeloDeDatos.data;

import Encapsulamiento.EjemploGUIEncapsulamiento.clases.ModeloDatos;
import POO.EjercicioModeloDeDatos.logica.Operador;

import java.util.HashMap;

public class ModeloDeDatos {
    HashMap<String, Operador>mapaOperadores;
    public ModeloDeDatos() {
        mapaOperadores=new HashMap<String,Operador>();


    }
    public void registrarOperador(Operador op){
        mapaOperadores.put(op.getDocumento(),op);
        System.out.println("operador registrado: "+op.getNombre());

    }
    public void consultaOperador(String documento){}
}
