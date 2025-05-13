package Encapsulamiento.EjemploGUIEncapsulamiento.gui;

import Encapsulamiento.EjemploGUIEncapsulamiento.clases.ModeloDatos;
import Encapsulamiento.EjemploGUIEncapsulamiento.clases.Procesos;
import Encapsulamiento.EjemploGUIEncapsulamiento.entidades.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPromedio extends JFrame implements ActionListener {
    private JTextField txtMateria, txtDocumento, txtNombre, txtNota1, txtNota2, txtNota3;
    private JButton btnCalcular, btnLimpiar, btnConsultaIndividual, btnLista, btnEliminar, btnActualizar;
    private JLabel lblResultado;


    private Procesos misProcesos;
    private ModeloDatos miModeloDatos;

    public VentanaPromedio() {
        misProcesos = new Procesos();
        miModeloDatos = new ModeloDatos();

        setTitle("Promedio estudiantes");
        setSize(659, 572);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JLabel lblTitulo = new JLabel("SISTEMA CONTROL DE NOTAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 25));
        lblTitulo.setBounds(10, 26, 606, 59);
        add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(24, 117, 72, 22);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(106, 120, 201, 19);
        add(txtNombre);

        JLabel lblMateria = new JLabel("Materia:");
        lblMateria.setBounds(348, 117, 72, 22);
        add(lblMateria);

        txtMateria = new JTextField();
        txtMateria.setBounds(430, 120, 180, 19);
        add(txtMateria);

        JLabel lblNota1 = new JLabel("Nota1:");
        lblNota1.setBounds(24, 159, 72, 22);
        add(lblNota1);

        txtNota1 = new JTextField();
        txtNota1.setBounds(106, 162, 96, 19);
        add(txtNota1);

        JLabel lblNota2 = new JLabel("Nota2:");
        lblNota2.setBounds(227, 162, 72, 22);
        add(lblNota2);

        txtNota2 = new JTextField();
        txtNota2.setBounds(309, 165, 96, 19);
        add(txtNota2);

        JLabel lblNota3 = new JLabel("Nota3:");
        lblNota3.setBounds(431, 162, 72, 22);
        add(lblNota3);

        txtNota3 = new JTextField();
        txtNota3.setBounds(513, 165, 96, 19);
        add(txtNota3);

        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setBounds(348, 190, 72, 22);
        add(lblDocumento);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(430, 190, 180, 19);
        add(txtDocumento);

        lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(24, 215, 586, 22);
        add(lblResultado);

        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(106, 276, 201, 21);
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(409, 276, 201, 21);
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(106, 326, 201, 21);
        btnEliminar.addActionListener(this);
        add(btnEliminar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(409, 326, 201, 21);
        btnActualizar.addActionListener(this);
        add(btnActualizar);

        btnConsultaIndividual = new JButton("Consultar");
        btnConsultaIndividual.setBounds(375, 225, 112, 21);
        btnConsultaIndividual.addActionListener(this);
        add(btnConsultaIndividual);

        btnLista = new JButton("Lista");
        btnLista.setBounds(498, 225, 112, 21);
        btnLista.addActionListener(this);
        add(btnLista);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) calcular();
        else if (e.getSource() == btnLimpiar) limpiar();
        else if (e.getSource() == btnConsultaIndividual) consultaIndividual();
        else if (e.getSource() == btnLista) consultarLista();
        else if (e.getSource() == btnEliminar) eliminarEstudiante();
        else if (e.getSource() == btnActualizar) actualizarEstudiante();
    }

    private void calcular() {
        Estudiante miEstudiante = new Estudiante();
        miEstudiante.setNombre(txtNombre.getText());
        miEstudiante.setMateria(txtMateria.getText());
        miEstudiante.setDocumento(txtDocumento.getText());
        miEstudiante.setNota1(Double.parseDouble(txtNota1.getText()));
        miEstudiante.setNota2(Double.parseDouble(txtNota2.getText()));
        miEstudiante.setNota3(Double.parseDouble(txtNota3.getText()));

        double promedio = misProcesos.calcularPromedio(miEstudiante);
        miEstudiante.setPromedio(promedio);

        if (promedio != -1) {
            lblResultado.setText("Resultado: Hola " + miEstudiante.getNombre() + ", su promedio es: " + promedio);
            lblResultado.setForeground(promedio < 3.5 ? Color.red : Color.blue);

            String registro = miModeloDatos.registrarEstudiante(miEstudiante);
            if (!registro.equals("ok")) {
                JOptionPane.showMessageDialog(null, registro, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            lblResultado.setText("Revise los datos, porque no pueden ser negativos ni mayores a 5");
            lblResultado.setForeground(Color.red);
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
        lblResultado.setForeground(Color.black);
    }

    private void consultarLista() {
        String listaConsultada = miModeloDatos.imprimirListaEstudiantes();

        VentanaLista miVentanaLista = new VentanaLista();

        miVentanaLista.setTextoLista(listaConsultada);

        miVentanaLista.setVisible(true);
    }


    private String consultaIndividual() {
        String documento = JOptionPane.showInputDialog("Ingrese el documento del estudiante a consultar");
        Estudiante estudiante = miModeloDatos.consultaEstudiante(documento);

        if (estudiante != null) {
            txtNombre.setText(estudiante.getNombre());
            txtMateria.setText(estudiante.getMateria());
            txtNota1.setText(estudiante.getNota1() + "");
            txtNota2.setText(estudiante.getNota2() + "");
            txtNota3.setText(estudiante.getNota3() + "");
            lblResultado.setText("El promedio es: " + estudiante.getPromedio());
        } else {
            JOptionPane.showMessageDialog(null, "No se encuentra el estudiante", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }return documento;
    }
    private void eliminarEstudiante() {
        String documento = JOptionPane.showInputDialog("Ingrese el documento del estudiante a eliminar del registro");
        miModeloDatos.eliminarEstudiante(documento);
    }
    private void actualizarEstudiante() {
        Estudiante miEstudiante = new Estudiante();
        miEstudiante.setNombre(txtNombre.getText());
        miEstudiante.setMateria(txtMateria.getText());
        miEstudiante.setDocumento(txtDocumento.getText());
        miEstudiante.setNota1(Double.parseDouble(txtNota1.getText()));
        miEstudiante.setNota2(Double.parseDouble(txtNota2.getText()));
        miEstudiante.setNota3(Double.parseDouble(txtNota3.getText()));

        double promedio = misProcesos.calcularPromedio(miEstudiante);
        miEstudiante.setPromedio(promedio);
        if (promedio != -1) {
            lblResultado.setText("Resultado: Hola " + miEstudiante.getNombre() + ", su promedio es: " + promedio);
            lblResultado.setForeground(promedio < 3.5 ? Color.red : Color.blue);

            String registro = miModeloDatos.actualizarEstudiante(miEstudiante);
            if (!registro.equals("ok")) {
                JOptionPane.showMessageDialog(null, registro, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            lblResultado.setText("Revise los datos, porque no pueden ser negativos ni mayores a 5");
            lblResultado.setForeground(Color.red);
        }




    }
}