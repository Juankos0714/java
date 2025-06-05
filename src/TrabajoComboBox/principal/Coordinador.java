package TrabajoComboBox.principal;


import TrabajoComboBox.dao.PersonaDAO;
import TrabajoComboBox.ventanas.VentanaCombo;
import TrabajoComboBox.vo.PersonaVo;

public class Coordinador {
    private VentanaCombo ventanaCombo;
    private PersonaDAO personaDAO;

    public Coordinador() {
        personaDAO = new PersonaDAO();
    }

    public void setVentanaCombo(VentanaCombo ventanaCombo) {
        this.ventanaCombo = ventanaCombo;
    }

    public String registrarPersona(PersonaVo persona) {
        return personaDAO.registrarPersona(persona);
    }

    public PersonaVo consultarPersona(String documento) {
        return personaDAO.consultarPersonaPorDocumento(documento);
    }

    public void mostrarVentanaCombo() {
        if (ventanaCombo != null) {
            ventanaCombo.setVisible(true);
        }
    }
}
