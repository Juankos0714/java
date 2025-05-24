package EjemploMVCPersona.src.modelo.dao;

import EjemploMVCPersona.src.controlador.Coordinador;
import EjemploMVCPersona.src.modelo.conexion.ConexionBD;
import EjemploMVCPersona.src.modelo.dto.PersonaDTO;


import java.util.ArrayList;


public class PersonaDAO {

    private Coordinador miCoordinador;

    public String registrarPersona(PersonaDTO persona) {

        if (ConexionBD.personasMap.containsKey(persona.getDocumento())==false) {
            ConexionBD.personasMap.put(persona.getDocumento(), persona);
            return "si";
        }else {
            return "no";
        }
    }

    public PersonaDTO consultarPersonaPorDocumento(String documento) {

        if (ConexionBD.personasMap.containsKey(documento)==true) {
            return ConexionBD.personasMap.get(documento);
        }else {
            return null;
        }

    }

    public ArrayList<PersonaDTO> consultarListaPersonas(){

        ArrayList<PersonaDTO> listaPersonas=new ArrayList<PersonaDTO>();

        for (PersonaDTO persona : ConexionBD.personasMap.values()) {
            listaPersonas.add(persona);
        }

        return listaPersonas;


    }

    public String actualizarPersona(PersonaDTO persona) {

        String resp="";
        System.out.println(persona);
        if (ConexionBD.personasMap.containsKey(persona.getDocumento())) {
            ConexionBD.personasMap.put(persona.getDocumento(), persona); // reemplaza el objeto
            resp="ok";
        } else {
            resp="error";
        }

        return resp;

    }

    public String eliminarPersona(String documento) {
        if (ConexionBD.personasMap.containsKey(documento)) {
            ConexionBD.personasMap.remove(documento);
            return "ok";
        } else {
            return "error";
        }

    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }

}

