package Encapsulamiento.EjemploGUIEncapsulamiento.gui;

import Encapsulamiento.EjemploGUIEncapsulamiento.clases.ModeloDatos;
import Encapsulamiento.EjemploGUIEncapsulamiento.clases.Procesos;
import Encapsulamiento.EjemploGUIEncapsulamiento.entidades.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VentanaPromedio extends JFrame {
    private JTextField txtMateria, txtDocumento, txtNombre, txtNota1, txtNota2, txtNota3;
    private JButton btnCalcular, btnLimpiar, btnConsultaIndividual, btnLista, btnEliminar, btnActualizar;
    private JLabel lblResultado;

    private static final double NOTA_MIN = 0.0;
    private static final double NOTA_MAX = 5.0;
    private static final Color COLOR_ERROR = Color.RED;
    private static final Color COLOR_SUCCESS = Color.BLUE;
    private static final Color COLOR_DEFAULT = Color.BLACK;

    private final Procesos misProcesos;
    private final ModeloDatos miModeloDatos;

    public VentanaPromedio() {
        misProcesos = new Procesos();
        miModeloDatos = new ModeloDatos();

        setTitle("Promedio estudiantes");
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
        JLabel lblTitulo = new JLabel("SISTEMA CONTROL DE NOTAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 25));
        titlePanel.add(lblTitulo, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(5, 5, 5, 5);
        formGbc.anchor = GridBagConstraints.WEST;

        formGbc.gridx = 0; formGbc.gridy = 0;
        formPanel.add(new JLabel("Nombre:"), formGbc);

        formGbc.gridx = 1; formGbc.gridy = 0;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtNombre = new JTextField(20);
        formPanel.add(txtNombre, formGbc);

        formGbc.gridx = 2; formGbc.gridy = 0;
        formGbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Materia:"), formGbc);

        formGbc.gridx = 3; formGbc.gridy = 0;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtMateria = new JTextField(15);
        formPanel.add(txtMateria, formGbc);

        formGbc.gridx = 0; formGbc.gridy = 1;
        formGbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Nota1:"), formGbc);

        formGbc.gridx = 1; formGbc.gridy = 1;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtNota1 = new JTextField(10);
        formPanel.add(txtNota1, formGbc);

        formGbc.gridx = 2; formGbc.gridy = 1;
        formGbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Nota2:"), formGbc);

        formGbc.gridx = 3; formGbc.gridy = 1;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtNota2 = new JTextField(10);
        formPanel.add(txtNota2, formGbc);

        formGbc.gridx = 0; formGbc.gridy = 2;
        formGbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Nota3:"), formGbc);

        formGbc.gridx = 1; formGbc.gridy = 2;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtNota3 = new JTextField(10);
        formPanel.add(txtNota3, formGbc);

        formGbc.gridx = 2; formGbc.gridy = 2;
        formGbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Documento:"), formGbc);

        formGbc.gridx = 3; formGbc.gridy = 2;
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        txtDocumento = new JTextField(15);
        formPanel.add(txtDocumento, formGbc);

        JPanel resultPanel = new JPanel(new BorderLayout());
        lblResultado = new JLabel("Resultado:");
        resultPanel.add(lblResultado, BorderLayout.WEST);

        JPanel consultaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnConsultaIndividual = createButton("Consultar", e -> consultaIndividual());
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

        gbc.gridx = 0; gbc.gridy = 0;
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

    private void calcular() {
        try {
            if (!validateRequiredFields()) {
                return;
            }

            Estudiante miEstudiante = createEstudianteFromForm();
            if (miEstudiante == null) {
                return;
            }

            double promedio = misProcesos.calcularPromedio(miEstudiante);
            miEstudiante.setPromedio(promedio);

            if (promedio >= 0) { // Valid promedio
                displayResult(miEstudiante);
                registerStudent(miEstudiante);
            } else {
                mostrarError("Revise los datos, porque no pueden ser negativos ni mayores a 5");
            }
        } catch (Exception ex) {
            mostrarError("Error al calcular: " + ex.getMessage());
        }
    }

    private boolean validateRequiredFields() {
        if (isEmptyField(txtNombre) || isEmptyField(txtMateria) || isEmptyField(txtDocumento) ||
                isEmptyField(txtNota1) || isEmptyField(txtNota2) || isEmptyField(txtNota3)) {
            mostrarError("Todos los campos son obligatorios");
            return false;
        }
        return true;
    }

    private boolean isEmptyField(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    private Estudiante createEstudianteFromForm() {
        try {
            double nota1 = parseAndValidateNota(txtNota1.getText());
            double nota2 = parseAndValidateNota(txtNota2.getText());
            double nota3 = parseAndValidateNota(txtNota3.getText());

            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(txtNombre.getText().trim());
            estudiante.setMateria(txtMateria.getText().trim());
            estudiante.setDocumento(txtDocumento.getText().trim());
            estudiante.setNota1(nota1);
            estudiante.setNota2(nota2);
            estudiante.setNota3(nota3);

            return estudiante;
        } catch (NumberFormatException e) {
            mostrarError("Las notas deben ser números válidos");
            return null;
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
            return null;
        }
    }

    private double parseAndValidateNota(String notaStr) {
        double nota = Double.parseDouble(notaStr);
        if (nota < NOTA_MIN || nota > NOTA_MAX) {
            throw new IllegalArgumentException("Las notas deben estar entre " + NOTA_MIN + " y " + NOTA_MAX);
        }
        return nota;
    }

    private void displayResult(Estudiante estudiante) {
        String message = "Resultado: Hola " + estudiante.getNombre() + ", su promedio es: " + estudiante.getPromedio();
        lblResultado.setText(message);
        lblResultado.setForeground(estudiante.getPromedio() < 3.5 ? COLOR_ERROR : COLOR_SUCCESS);
    }

    private void registerStudent(Estudiante estudiante) {
        String resultado = miModeloDatos.registrarEstudiante(estudiante);
        if (!resultado.equals("ok")) {
            JOptionPane.showMessageDialog(this, resultado, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtMateria.setText("");
        txtDocumento.setText("");
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
        lblResultado.setText("Resultado:");
        lblResultado.setForeground(COLOR_DEFAULT);
        txtNombre.requestFocus();
    }

    private void consultarLista() {
        String listaConsultada = miModeloDatos.imprimirListaEstudiantes();
        if (listaConsultada.equals("DATOS ESTUDIANTES\n")) {
            JOptionPane.showMessageDialog(this, "No hay estudiantes registrados", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        VentanaLista miVentanaLista = new VentanaLista();
        miVentanaLista.setTextoLista(listaConsultada);
        miVentanaLista.setVisible(true);
    }

    private String consultaIndividual() {
        try {
            String documento = JOptionPane.showInputDialog(this, "Ingrese el documento del estudiante a consultar");

            if (documento == null || documento.trim().isEmpty()) {
                return null;
            }

            Estudiante estudiante = miModeloDatos.consultaEstudiante(documento.trim());

            if (estudiante != null) {
                mostrarEstudianteEnFormulario(estudiante);
                return documento;
            } else {
                JOptionPane.showMessageDialog(this, "No se encuentra el estudiante", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        } catch (Exception e) {
            mostrarError("Error al consultar: " + e.getMessage());
            return null;
        }
    }

    private void mostrarEstudianteEnFormulario(Estudiante estudiante) {
        txtNombre.setText(estudiante.getNombre());
        txtMateria.setText(estudiante.getMateria());
        txtDocumento.setText(estudiante.getDocumento());
        txtNota1.setText(String.valueOf(estudiante.getNota1()));
        txtNota2.setText(String.valueOf(estudiante.getNota2()));
        txtNota3.setText(String.valueOf(estudiante.getNota3()));

        String resultMessage = "El promedio es: " + estudiante.getPromedio();
        lblResultado.setText(resultMessage);
        lblResultado.setForeground(estudiante.getPromedio() < 3.5 ? COLOR_ERROR : COLOR_SUCCESS);
    }

    private void eliminarEstudiante() {
        try {
            String documento = JOptionPane.showInputDialog(this, "Ingrese el documento del estudiante a eliminar");

            if (documento == null || documento.trim().isEmpty()) {
                return;
            }

            Estudiante estudiante = miModeloDatos.consultaEstudiante(documento.trim());
            if (estudiante == null) {
                JOptionPane.showMessageDialog(this, "No se encuentra el estudiante", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de eliminar a " + estudiante.getNombre() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                miModeloDatos.eliminarEstudiante(documento.trim());
                JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            }
        } catch (Exception e) {
            mostrarError("Error al eliminar: " + e.getMessage());
        }
    }

    private void actualizarEstudiante() {
        try {
            if (!validateRequiredFields()) {
                return;
            }

            Estudiante miEstudiante = createEstudianteFromForm();
            if (miEstudiante == null) {
                return;
            }

            if (miModeloDatos.consultaEstudiante(miEstudiante.getDocumento()) == null) {
                mostrarError("No existe un estudiante con el documento " + miEstudiante.getDocumento());
                return;
            }

            double promedio = misProcesos.calcularPromedio(miEstudiante);
            miEstudiante.setPromedio(promedio);

            if (promedio >= 0) {
                displayResult(miEstudiante);
                updateStudent(miEstudiante);
            } else {
                mostrarError("Revise los datos, porque no pueden ser negativos ni mayores a 5");
            }
        } catch (Exception ex) {
            mostrarError("Error al actualizar: " + ex.getMessage());
        }
    }

    private void updateStudent(Estudiante estudiante) {
        String resultado = miModeloDatos.actualizarEstudiante(estudiante);
        if (Objects.equals(resultado, "ok")) {
            JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, resultado, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarError(String message) {
        lblResultado.setText(message);
        lblResultado.setForeground(COLOR_ERROR);
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
