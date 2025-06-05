package TrabajoComboBox.principal;

import TrabajoComboBox.ventanas.VentanaCombo;

public class Relaciones {
    private VentanaCombo ventanaCombo;
    private Coordinador coordinador;

    public Relaciones() {
        iniciarRelaciones();
    }

    private void iniciarRelaciones() {
        ventanaCombo = new VentanaCombo();
        coordinador = new Coordinador();

        ventanaCombo.setCoordinador(coordinador);
        coordinador.setVentanaCombo(ventanaCombo);

        coordinador.mostrarVentanaCombo();
    }
}
