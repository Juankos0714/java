package MVC_IMC.vista;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.dto.PersonaDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConsultaIndividual extends JDialog implements ActionListener {

    private Coordinador miCoordinador;
    private JLabel lblTitulo, lblNombre;
    private JTextField txtNombre;
    private JButton btnBuscar, btnCerrar;
    private JTextArea areaResultado;

    public VentanaConsultaIndividual(VentanaPrincipal ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        setTitle("Consulta Individual");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        lblTitulo = new JLabel("Consulta Individual");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 102, 204));

        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(0, 102, 204));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(204, 0, 0));
        btnCerrar.setForeground(Color.WHITE);

        areaResultado = new JTextArea(12, 40);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaResultado.setBackground(new Color(245, 245, 245));
        areaResultado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelSuperior.add(lblTitulo, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 1;
        panelSuperior.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelSuperior.add(txtNombre, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnBuscar);
        panelBotones.add(btnCerrar);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelSuperior.add(panelBotones, gbc);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
    }

    private void setupListeners() {
        btnBuscar.addActionListener(this);
        btnCerrar.addActionListener(this);
        txtNombre.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar || e.getSource() == txtNombre) {
            buscarPersona();
        } else if (e.getSource() == btnCerrar) {
            dispose();
        }
    }

    private void buscarPersona() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return;
        }

        PersonaDTO persona = miCoordinador.consultarPersona(nombre);

        if (persona != null) {
            StringBuilder resultado = new StringBuilder();
            resultado.append("PERSONA ENCONTRADA\n");
            resultado.append("==============================\n\n");
            resultado.append("Nombre: ").append(persona.getNombre()).append("\n");
            resultado.append("Edad: ").append(persona.getEdad()).append(" años\n\n");

            if (persona.getPeso() > 0 && persona.getTalla() > 0) {
                resultado.append(String.format("Peso: %.1f kg\n", persona.getPeso()));
                resultado.append(String.format("Talla: %.2f m\n", persona.getTalla()));
                resultado.append(String.format("IMC: %.2f\n\n", persona.getImc()));
                resultado.append("Estado: ").append(persona.getEstado()).append("\n\n");
                resultado.append("Recomendación:\n").append(persona.getMensaje());
            } else {
                resultado.append("IMC: No calculado\n\n");
                resultado.append("Para calcular el IMC, use la opción\n'Calcular IMC' del menú principal.");
            }

            areaResultado.setText(resultado.toString());
        } else {
            areaResultado.setText("PERSONA NO ENCONTRADA\n" +
                    "==============================\n\n" +
                    "No existe una persona con el nombre:\n\"" + nombre + "\"\n\n" +
                    "Verifique la escritura o registre\nla persona primero.");

            JOptionPane.showMessageDialog(this,
                    "Persona no encontrada: \"" + nombre + "\"",
                    "Búsqueda",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        areaResultado.setCaretPosition(0);
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}