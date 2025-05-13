package InterfacesGraficas.PagoEmpleado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModeloDeDatos {
    // Estructuras de datos para almacenar empleados
    private ArrayList<Persona> personasList;
    private HashMap<String, Persona> personasMap;

    public ModeloDeDatos() {
        // Initialize with initial capacity for better performance if expected size is known
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

    public Persona consultarPersona(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return null;
        }

        Persona persona = personasMap.get(documento.trim());

        return persona != null ? copyPersona(persona) : null;
    }

    public String imprimirListaPersonas() {
        StringBuilder msj = new StringBuilder("DATOS EMPLEADOS\n");

        if (personasMap.isEmpty()) {
            return msj.toString();
        }

        personasList.stream()
                .sorted((e1, e2) -> e1.getNombre().compareToIgnoreCase(e2.getNombre()))
                .forEach(persona -> {
                    msj.append("Nombre: ").append(persona.getNombre())
                            .append(", Documento: ").append(persona.getDocumento())
                            .append(", Categoría: ").append(persona.getCategoria())
                            .append(", Salario: ").append(formatMoney(persona.getSalarioActual()))
                            .append("\n");
                    msj.append("Bonificación: ").append(formatPorcentaje(persona.getBonificacion()))
                            .append(", Descuento: ").append(formatPorcentaje(persona.getDescuento()))
                            .append(", Valor a pagar: ").append(formatMoney(persona.getValorPago()))
                            .append("\n\n");
                });

        return msj.toString();
    }

    private String formatMoney(double valor) {
        return String.format("$%.2f", valor);
    }

    private String formatPorcentaje(double valor) {
        return String.format("%.2f%%", valor * 100);
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