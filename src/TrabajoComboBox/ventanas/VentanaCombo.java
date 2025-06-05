package TrabajoComboBox.ventanas;

import TrabajoComboBox.dao.PersonaDAO;
import TrabajoComboBox.principal.Coordinador;
import TrabajoComboBox.vo.PersonaVo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaCombo extends JFrame implements ActionListener {

    private JTextField txtDocumento, txtNombre, txtEdad, txtDireccion;
    private JButton btnRegistrar, btnConsultar;
    private JComboBox<PersonaVo> comboPersonas;
    private JTable tablaPersonas;
    private JScrollPane scrollTabla;
    private JLabel lblSeleccionado;

    private PersonaDAO personaDAO;
    private Coordinador coordinador;

    public VentanaCombo() {
        personaDAO = new PersonaDAO();
        iniciarComponentes();
        cargarRegistros();
    }

    private void iniciarComponentes() {
        setTitle("Gestión de Personas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior para registro
        JPanel panelRegistro = new JPanel(new GridBagLayout());
        panelRegistro.setBorder(BorderFactory.createTitledBorder("Registro de Personas"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Documento
        gbc.gridx = 0; gbc.gridy = 0;
        panelRegistro.add(new JLabel("Documento:"), gbc);
        gbc.gridx = 1;
        txtDocumento = new JTextField(15);
        panelRegistro.add(txtDocumento, gbc);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        panelRegistro.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelRegistro.add(txtNombre, gbc);

        // Direccion

        gbc.gridx = 0; gbc.gridy = 3;
        panelRegistro.add(new JLabel("Direccion:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(15);
        panelRegistro.add(txtDireccion, gbc);

        // Edad
        gbc.gridx = 0; gbc.gridy = 2;
        panelRegistro.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(15);
        panelRegistro.add(txtEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(this);
        panelRegistro.add(btnRegistrar, gbc);

        gbc.gridx = 1;
        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(this);
        panelRegistro.add(btnConsultar, gbc);

        JPanel panelCentral = new JPanel(new BorderLayout());

        JPanel panelCombo = new JPanel(new GridBagLayout());
        panelCombo.setBorder(BorderFactory.createTitledBorder("Seleccionar Persona"));
        panelCombo.setPreferredSize(new Dimension(780, 80));

        GridBagConstraints gbcCombo = new GridBagConstraints();
        gbcCombo.insets = new Insets(10, 10, 10, 10);

        gbcCombo.gridx = 0; gbcCombo.gridy = 0;
        gbcCombo.anchor = GridBagConstraints.WEST;
        panelCombo.add(new JLabel("Personas:"), gbcCombo);

        gbcCombo.gridx = 1;
        gbcCombo.fill = GridBagConstraints.HORIZONTAL;
        gbcCombo.weightx = 0.4;
        comboPersonas = new JComboBox<>();
        comboPersonas.setPreferredSize(new Dimension(200, 25));
        comboPersonas.addActionListener(this);
        panelCombo.add(comboPersonas, gbcCombo);

        gbcCombo.gridx = 2;
        gbcCombo.weightx = 0.6;
        gbcCombo.insets = new Insets(10, 20, 10, 10);
        lblSeleccionado = new JLabel("Seleccione una persona");
        lblSeleccionado.setFont(new Font("Arial", Font.ITALIC, 12));
        lblSeleccionado.setForeground(Color.BLUE);
        panelCombo.add(lblSeleccionado, gbcCombo);

        String[] columnas = {"Documento", "Nombre", "Edad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        tablaPersonas = new JTable(modelo);
        scrollTabla = new JScrollPane(tablaPersonas);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Lista de Personas"));

        tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaPersonas.getColumnModel().getColumn(2).setPreferredWidth(80);

        panelCentral.add(panelCombo, BorderLayout.NORTH);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelRegistro, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            registrarPersona();
        } else if (e.getSource() == btnConsultar) {
            consultarPersona();
        } else if (e.getSource() == comboPersonas) {
            mostrarPersonaSeleccionada();
        }
    }

    private void registrarPersona() {
        try {
            String documento = txtDocumento.getText().trim();
            String nombre = txtNombre.getText().trim();
            String edadStr = txtEdad.getText().trim();
            String direccion = txtDireccion.getText().trim();

            if (documento.isEmpty() || nombre.isEmpty() || edadStr.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int edad = Integer.parseInt(edadStr);

            PersonaVo persona = new PersonaVo(documento, nombre, edad, direccion);
            String resultado = personaDAO.registrarPersona(persona);

            JOptionPane.showMessageDialog(this, resultado, "Resultado",
                    resultado.contains("Error") ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);

            if (!resultado.contains("Error")) {
                limpiarCampos();
                cargarRegistros();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarPersona() {
        String documento = txtDocumento.getText().trim();
        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un documento para consultar",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PersonaVo persona = personaDAO.consultarPersonaPorDocumento(documento);
        if (persona != null) {
            txtNombre.setText(persona.getNombre());
            txtDireccion.setText(persona.getDireccion());
            txtEdad.setText(String.valueOf(persona.getEdad()));
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró una persona con ese documento",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarRegistros() {
        llenarCombo();
        llenarTabla();
    }

    private void llenarCombo() {
        comboPersonas.removeAllItems();

        comboPersonas.addItem(new PersonaVo("", "-- Seleccione una persona --", 0));

        ArrayList<PersonaVo> listaPersonas = personaDAO.consultarPersonas();

        for (PersonaVo persona : listaPersonas) {
            comboPersonas.addItem(persona);
        }

        comboPersonas.setSelectedIndex(0);
    }

    private void llenarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tablaPersonas.getModel();
        modelo.setRowCount(0);

        ArrayList<PersonaVo> listaPersonas = personaDAO.consultarPersonas();

        for (PersonaVo persona : listaPersonas) {
            Object[] fila = {
                    persona.getDocumento(),
                    persona.getNombre(),
                    persona.getEdad(),
                    persona.getDireccion()
            };
            modelo.addRow(fila);
        }
    }

    private void mostrarPersonaSeleccionada() {
        PersonaVo personaSeleccionada = (PersonaVo) comboPersonas.getSelectedItem();
        if (personaSeleccionada != null && !personaSeleccionada.getDocumento().isEmpty()) {
            lblSeleccionado.setText("Seleccionado: " + personaSeleccionada.getNombre() +
                    " (Doc: " + personaSeleccionada.getDocumento() + ", Edad: " + personaSeleccionada.getEdad() + ")");
            lblSeleccionado.setForeground(Color.DARK_GRAY);
        } else {
            lblSeleccionado.setText("Seleccione una persona");
            lblSeleccionado.setForeground(Color.BLUE);
        }
    }

    private void limpiarCampos() {
        txtDocumento.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }
}
