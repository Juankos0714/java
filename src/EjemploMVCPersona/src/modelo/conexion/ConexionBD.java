package EjemploMVCPersona.src.modelo.conexion;

import EjemploMVCPersona.src.controlador.Coordinador;
import EjemploMVCPersona.src.modelo.dto.PersonaDTO;

import java.util.HashMap;


public class ConexionBD {

    public static HashMap<String, PersonaDTO> personasMap;
    private Coordinador miCoordinador;

    public ConexionBD() {
        personasMap=new HashMap<String, PersonaDTO>();
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }


}



