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

    private final InterfacesGraficas.PagoEmpleado.Procesos misProcesos;
    private final InterfacesGraficas.PagoEmpleado.ModeloDeDatos miModeloDatos;

    public VentanaUsuario() {
        misProcesos = new Procesos();
        miModeloDatos = new ModeloDeDatos();

        setTitle("Pago Empleados");
        setSize(659, 572);
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
        formPanel.add(new JLabel("Categoria"), formGbc);


        formGbc.gridx = 3;
        formGbc.gridy = 2;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtDocumento = new JTextField(15);
        formPanel.add(txtCategoria, formGbc);

        JPanel resultPanel = new JPanel(new BorderLayout());
        lblResultado = new JLabel("Resultado:");
        resultPanel.add(lblResultado, BorderLayout.WEST);

        JPanel consultaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnConsultaIndividual = createButton("Agregar", e -> consultaIndividual());
        btnLista = createButton("Lista", e -> consultarLista());
        consultaPanel.add(btnConsultaIndividual);
        consultaPanel.add(btnLista);
        resultPanel.add(consultaPanel, BorderLayout.EAST);

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        btnCalcular = createButton("Calcular", e -> calcular());
        btnLimpiar = createButton("Limpiar", e -> limpiar());
        btnEliminar = createButton("Eliminar", e -> eliminarEstudiante());
        btnActualizar = createButton("Actualizar", e -> actualizarEstudiante());

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
    private int parseAndValidateCat(String CATStr) {
        int categoria = Integer.parseInt(CATStr);
        if (categoria < CAT_MIN || categoria > CAT_MAX) {
            throw new IllegalArgumentException("La categoria debe estar entre " + CAT_MIN + " y " + CAT_MAX);
        }
        return categoria;
    }
    private double parseAndValidateSal(String SALStr) {
        double salario = Double.parseDouble(SALStr);
        if (salario < 0) {
            throw new IllegalArgumentException("El salario debe ser positivo");
        }
        return salario;
    }
    public Persona pedirDatos() {
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
            mostrarError("El salario y la categoria deben ser numeros validos");
            return null;
        } catch (IllegalArgumentException e) {
        mostrarError(e.getMessage());
        return null;
    }


    }
    private void mostrarError(String message) {
        lblResultado.setText(message);
        lblResultado.setForeground(COLOR_ERROR);
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}