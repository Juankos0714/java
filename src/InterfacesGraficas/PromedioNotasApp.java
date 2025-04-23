package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromedioNotasApp extends JFrame implements ActionListener {

    private JLabel lblNombre, lblMateria, lblNota1, lblNota2, lblNota3, lblResultado;
    private JTextField txtNombre, txtMateria, txtNota1, txtNota2, txtNota3;
    private JButton btnCalcular;
    private JPanel panel;

    public PromedioNotasApp() {
        inicializarComponentes();
        setTitle("Calculadora de Promedio de Notas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void inicializarComponentes() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        lblNombre = new JLabel("Nombre del Estudiante:");
        lblNombre.setBounds(20, 20, 150, 25);
        txtNombre = new JTextField();
        txtNombre.setBounds(180, 20, 150, 25);

        lblMateria = new JLabel("Materia:");
        lblMateria.setBounds(20, 50, 150, 25);
        txtMateria = new JTextField();
        txtMateria.setBounds(180, 50, 150, 25);

        lblNota1 = new JLabel("Nota 1:");
        lblNota1.setBounds(20, 80, 150, 25);
        txtNota1 = new JTextField();
        txtNota1.setBounds(180, 80, 150, 25);

        lblNota2 = new JLabel("Nota 2:");
        lblNota2.setBounds(20, 110, 150, 25);
        txtNota2 = new JTextField();
        txtNota2.setBounds(180, 110, 150, 25);

        lblNota3 = new JLabel("Nota 3:");
        lblNota3.setBounds(20, 140, 150, 25);
        txtNota3 = new JTextField();
        txtNota3.setBounds(180, 140, 150, 25);

        btnCalcular = new JButton("Calcular Promedio");
        btnCalcular.setBounds(100, 180, 150, 30);
        btnCalcular.addActionListener(this);

        lblResultado = new JLabel("Promedio:");
        lblResultado.setBounds(20, 220, 700, 25);

        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblMateria);
        panel.add(txtMateria);
        panel.add(lblNota1);
        panel.add(txtNota1);
        panel.add(lblNota2);
        panel.add(txtNota2);
        panel.add(lblNota3);
        panel.add(txtNota3);
        panel.add(btnCalcular);
        panel.add(lblResultado);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            calcularPromedio();
        }
    }

    private void calcularPromedio() {
        try {
            String nombreEstudiante = txtNombre.getText();
            String nombreMateria = txtMateria.getText();
            double nota1 = Double.parseDouble(txtNota1.getText());
            double nota2 = Double.parseDouble(txtNota2.getText());
            double nota3 = Double.parseDouble(txtNota3.getText());

            double promedio = (nota1 + nota2 + nota3) / 3;

            lblResultado.setText("Promedio de " + nombreEstudiante + " en " + nombreMateria + ": " + String.format("%.2f", promedio));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para las notas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PromedioNotasApp());
    }
}
