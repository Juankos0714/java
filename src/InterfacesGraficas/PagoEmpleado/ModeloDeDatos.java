package InterfacesGraficas.PagoEmpleado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModeloDeDatos {
    private ArrayList<Persona> personasList;
    private HashMap<String, Persona> personasMap;

    public ModeloDeDatos() {
        personasList = new ArrayList<Persona>();
        personasMap = new HashMap<String, Persona>();
    }

    public String registrarPersona(Persona persona) {
        validatePersona(persona);

        String documento = persona.getDocumento().trim();
        if (personasMap.containsKey(documento)) {
            return "Empleado existente";
        }

        personasMap.put(documento, copyPersona(persona));
        personasList.add(copyPersona(persona));
        return "ok";
    }

    public boolean eliminarPersona(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return false;
        }

        Persona persona = personasMap.remove(documento.trim());
        if (persona != null) {
            personasList.removeIf(p -> p.getDocumento().equals(documento.trim()));
            return true;
        }
        return false;
    }

    public String actualizarPersona(Persona persona) {
        validatePersona(persona);

        String documento = persona.getDocumento().trim();
        if (!personasMap.containsKey(documento)) {
            return "Empleado inexistente";
        }

        personasList.removeIf(p -> p.getDocumento().equals(documento));

        personasMap.put(documento, copyPersona(persona));
        personasList.add(copyPersona(persona));
        return "ok";
    }

    private void validatePersona(Persona persona) {
        if (persona == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo");
        }

        if (persona.getDocumento() == null || persona.getDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("El documento del empleado es obligatorio");
        }
    }

    private Persona copyPersona(Persona original) {
        Persona copia = new Persona();
        copia.setNombre(original.getNombre());
        copia.setDocumento(original.getDocumento());
        copia.setSalarioActual(original.getSalarioActual());
        copia.setCategoria(original.getCategoria());
        copia.setBonificacion(original.getBonificacion());
        copia.setDescuento(original.getDescuento());
        copia.setValorpago(original.getValorPago());
        return copia;
    }

    public Map<String, Persona> getPersonasMap() {
        return Collections.unmodifiableMap(personasMap);
    }

    public ArrayList<Persona> getPersonasList() {
        return new ArrayList<>(personasList);
    }

    public int getNumeroPersonas() {
        return personasMap.size();
    }

    public double getValorTotalPago() {
        return personasList.stream()
                .mapToDouble(Persona::getValorPago)
                .sum();
    }
}