package MVC_IMC.vista;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.dto.PersonaDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JDialog implements ActionListener {

    private Coordinador miCoordinador;
    private JLabel lblTitulo, lblNombre, lblEdad;
    private JTextField txtNombre, txtEdad;
    private JButton btnRegistrar, btnCancelar;

    public VentanaRegistro(VentanaPrincipal ventanaPrincipal, boolean modal) {
        super(ventanaPrincipal, modal);
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        setTitle("Registrar Persona");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        lblTitulo = new JLabel("Registro de Nueva Persona");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(0, 102, 204));

        lblNombre = new JLabel("Nombre completo:");
        txtNombre = new JTextField(20);

        lblEdad = new JLabel("Edad (años):");
        txtEdad = new JTextField(10);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(0, 153, 76));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 12));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(204, 0, 0));
        btnCancelar.setForeground(Color.WHITE);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(lblTitulo, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 1;
        panelPrincipal.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelPrincipal.add(lblEdad, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtEdad, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        btnRegistrar.addActionListener(this);
        btnCancelar.addActionListener(this);
        txtNombre.addActionListener(this);
        txtEdad.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar || e.getSource() == txtEdad) {
            procesarRegistro();
        } else if (e.getSource() == txtNombre) {
            txtEdad.requestFocus();
        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }

    private void procesarRegistro() {
        String nombre = txtNombre.getText().trim();
        String edadStr = txtEdad.getText().trim();

        if (nombre.isEmpty() || edadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Complete todos los campos.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);

            if (edad <= 0 || edad > 120) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese una edad válida (1-120 años).",
                        "Edad inválida",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (miCoordinador.existePersona(nombre)) {
                JOptionPane.showMessageDialog(this,
                        "Ya existe una persona con ese nombre.",
                        "Persona duplicada",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            PersonaDTO persona = new PersonaDTO();
            persona.setNombre(nombre);
            persona.setEdad(edad);
            persona.setPeso(0.0);
            persona.setTalla(0.0);
            persona.setImc(0.0);
            persona.setEstado("Sin calcular");
            persona.setMensaje("Debe calcular el IMC");

            miCoordinador.registrarPersona(persona);

            JOptionPane.showMessageDialog(this,
                    "¡Persona registrada exitosamente!\n\n" +
                            "Ahora puede calcular su IMC en la opción 'Calcular IMC'.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarCampos();
            dispose(); // Cerrar ventana después del registro exitoso

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "La edad debe ser un número entero.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
            txtEdad.requestFocus();
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtNombre.requestFocus();
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}