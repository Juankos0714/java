package InterfacesGraficas.PagoEmpleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaUsuario extends JFrame {
    private JTextField txtCategoria, txtDocumento, txtNombre, txtSalario;
    private JButton btnCalcular, btnLimpiar, btnConsultaIndividual, btnLista, btnEliminar, btnActualizar;
    private JLabel lblResultado;

    private static final int CAT_MIN = 1;
    private static final int CAT_MAX = 4;

    private static final Color COLOR_ERROR = Color.RED;
    private static final Color COLOR_SUCCESS = Color.BLUE;
    private static final Color COLOR_DEFAULT = Color.BLACK;

    private final Procesos misProcesos;

    public VentanaUsuario() {
        misProcesos = new Procesos();

        setTitle("Pago Empleados");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        iniciarComponentes();
        pack();
    }

    private void iniciarComponentes() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("SISTEMA CONTROL DE PAGOS EMPLEADOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.PLAIN, 22));
        titlePanel.add(lblTitulo, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(5, 5, 5, 5);
        formGbc.anchor = GridBagConstraints.WEST;

        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formPanel.add(new JLabel("Nombre:"), formGbc);

        formGbc.gridx = 1;
        formGbc.gridy = 0;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtNombre = new JTextField(20);
        formPanel.add(txtNombre, formGbc);

        formGbc.gridx = 2;
        formGbc.gridy = 0;
        formGbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Documento:"), formGbc);

        formGbc.gridx = 3;
        formGbc.gridy = 0;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtDocumento = new JTextField(15);
        formPanel.add(txtDocumento, formGbc);

        formGbc.gridx = 0;
        formGbc.gridy = 1;
        formGbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Salario Actual:"), formGbc);

        formGbc.gridx = 1;
        formGbc.gridy = 1;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtSalario = new JTextField(10);
        formPanel.add(txtSalario, formGbc);

        formGbc.gridx = 2;
        formGbc.gridy = 1;
        formGbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Categoría:"), formGbc);

        formGbc.gridx = 3;
        formGbc.gridy = 1;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtCategoria = new JTextField(5);
        formPanel.add(txtCategoria, formGbc);

        JPanel resultPanel = new JPanel(new BorderLayout());
        lblResultado = new JLabel("Resultado:");
        resultPanel.add(lblResultado, BorderLayout.WEST);


        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        btnCalcular = createButton("Calcular", e -> calcularPago());
        btnLimpiar = createButton("Limpiar", e -> limpiar());
        btnEliminar = createButton("Eliminar", e -> eliminarPersona());
        btnActualizar = createButton("Actualizar", e -> actualizarPersona());

        buttonsPanel.add(btnCalcular);
        buttonsPanel.add(btnLimpiar);
        buttonsPanel.add(btnEliminar);
        buttonsPanel.add(btnActualizar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(titlePanel, gbc);

        gbc.gridy = 1;
        mainPanel.add(formPanel, gbc);

        gbc.gridy = 2;
        mainPanel.add(resultPanel, gbc);

        gbc.gridy = 3;
        mainPanel.add(buttonsPanel, gbc);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        containerPanel.add(mainPanel, BorderLayout.CENTER);

        add(containerPanel);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private int parseAndValidateCat(String catStr) {
        int categoria = Integer.parseInt(catStr);
        if (categoria < CAT_MIN || categoria > CAT_MAX) {
            throw new IllegalArgumentException("La categoría debe estar entre " + CAT_MIN + " y " + CAT_MAX);
        }
        return categoria;
    }

    private double parseAndValidateSal(String salStr) {
        double salario = Double.parseDouble(salStr);
        if (salario < 0) {
            throw new IllegalArgumentException("El salario debe ser positivo");
        }
        return salario;
    }

    private Persona obtenerDatosFormulario() {
        try {
            int categoria = parseAndValidateCat(txtCategoria.getText());
            double salario = parseAndValidateSal(txtSalario.getText());

            Persona persona = new Persona();
            persona.setNombre(txtNombre.getText().trim());
            persona.setDocumento(txtDocumento.getText().trim());
            persona.setCategoria(categoria);
            persona.setSalarioActual(salario);
            return persona;
        } catch (NumberFormatException e) {
            mostrarError("El salario y la categoría deben ser números válidos");
            return null;
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
            return null;
        }
    }

    private void calcularPago() {
        try {
            if (!validateRequiredFields()) {
                return;
            }

            Persona persona = obtenerDatosFormulario();
            if (persona == null) {
                return;
            }

            misProcesos.calcularBonificacionDescuento(persona);
            double pago = misProcesos.calcularPago(persona);

            String resultado = misProcesos.getModeloDeDatos().registrarPersona(persona);
            if (!"ok".equals(resultado)) {
                mostrarError(resultado);
                return;
            }

            displayResult(persona);
        } catch (Exception ex) {
            mostrarError("Error al calcular: " + ex.getMessage());
        }
    }

    private void eliminarPersona() {
        String documento = txtDocumento.getText().trim();
        if (documento.isEmpty()) {
            mostrarError("Ingrese un documento para eliminar");
            return;
        }

        boolean eliminado = misProcesos.getModeloDeDatos().eliminarPersona(documento);
        if (eliminado) {
            lblResultado.setText("Empleado eliminado con éxito");
            lblResultado.setForeground(COLOR_SUCCESS);
            limpiar();
        } else {
            mostrarError("No se encontró un empleado con el documento: " + documento);
        }
    }

    private void actualizarPersona() {
        try {
            if (!validateRequiredFields()) {
                return;
            }

            Persona persona = obtenerDatosFormulario();
            if (persona == null) {
                return;
            }

            misProcesos.calcularBonificacionDescuento(persona);
            double pago = misProcesos.calcularPago(persona);

            String resultado = misProcesos.getModeloDeDatos().actualizarPersona(persona);
            if (!"ok".equals(resultado)) {
                mostrarError(resultado);
                return;
            }

            lblResultado.setText("Empleado actualizado con éxito. Pago: $" + String.format("%.2f", persona.getValorPago()));
            lblResultado.setForeground(COLOR_SUCCESS);
        } catch (Exception ex) {
            mostrarError("Error al actualizar: " + ex.getMessage());
        }
    }

    private void displayResult(Persona persona) {
        String message = "Resultado: Hola " + persona.getNombre() + ", su pago es: $" +
                String.format("%.2f", persona.getValorPago());
        lblResultado.setText(message);
        lblResultado.setForeground(COLOR_SUCCESS);
    }

    private boolean validateRequiredFields() {
        if (isEmptyField(txtNombre) || isEmptyField(txtCategoria) || isEmptyField(txtDocumento) ||
                isEmptyField(txtSalario)) {
            mostrarError("Todos los campos son obligatorios");
            return false;
        }
        return true;
    }

    private boolean isEmptyField(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    private void mostrarError(String message) {
        lblResultado.setText(message);
        lblResultado.setForeground(COLOR_ERROR);
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void limpiar() {
        txtNombre.setText("");
        txtDocumento.setText("");
        txtSalario.setText("");
        txtCategoria.setText("");
        lblResultado.setText("Resultado:");
        lblResultado.setForeground(COLOR_DEFAULT);
        txtNombre.requestFocus();
    }
}