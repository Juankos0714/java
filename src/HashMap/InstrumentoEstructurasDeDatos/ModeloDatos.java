package HashMap.InstrumentoEstructurasDeDatos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ModeloDatos {
    HashMap<String, ArrayList<String>> mapEstudiantes;

    public ModeloDatos() {
        mapEstudiantes = new HashMap<>();
    }

    public void guardarDatos(String documento, ArrayList<String> datosEstudiante) {
        mapEstudiantes.put(documento, datosEstudiante);
    }

    public ArrayList<String> consultarEstudiante(String documento) {
        return mapEstudiantes.get(documento);
    }

    public HashMap<String, ArrayList<String>> getMapaEstudiantes() {
        return mapEstudiantes;
    }

    public void imprimirMapa() {
        for (String documento : mapEstudiantes.keySet()) {
            ArrayList<String> datos = mapEstudiantes.get(documento);
            String msj = "Documento: " + documento+ "\nNombre: " + datos.get(1)+"\nMateria: "+ datos.get(2)+"\n Notas: " + datos.get(3) + ", " + datos.get(4) + ", " + datos.get(5);
            JOptionPane.showMessageDialog(null,msj);


        }
    }
}

