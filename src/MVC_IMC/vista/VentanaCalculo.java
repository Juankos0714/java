package MVC_IMC.vista;

import MVC_IMC.controlador.Coordinador;
import MVC_IMC.modelo.Procesos;
import MVC_IMC.modelo.dto.PersonaDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCalculo extends JDialog implements ActionListener {

    private JLabel lblTitulo, lblNombre, lblEdad, lblPeso, lblTalla, lblResultadoHeader;
    private JTextField txtNombre, txtEdad, txtPeso, txtTalla;
    private JButton btnCalcular, btnLimpiar;
    private JTextArea areaResultado;
    private JPanel panelPrincipal;

    private Procesos misProcesos;

    public VentanaCalculo(VentanaPrincipal ventanaPrincipal, boolean modal) {
        /**Al llamar al constructor super(), le enviamos el
         * JFrame Padre y la propiedad booleana que determina
         * que es hija*/
        super(ventanaPrincipal, modal);
        setTitle("Ventana Registro Persona");
        setSize(382, 277);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        misProcesos = new Procesos();
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        setTitle("Calculadora de IMC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        lblTitulo = new JLabel("Calculadora de Índice de Masa Corporal");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);

        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);

        lblEdad = new JLabel("Edad (años):");
        txtEdad = new JTextField(5);

        lblPeso = new JLabel("Peso (kg):");
        txtPeso = new JTextField(5);

        lblTalla = new JLabel("Talla (metros ej: 1.75):");
        txtTalla = new JTextField(5);

        btnCalcular = new JButton("Calcular IMC");
        btnLimpiar = new JButton("Limpiar Campos");

        lblResultadoHeader = new JLabel("Resultado:");
        lblResultadoHeader.setFont(new Font("Arial", Font.BOLD, 14));
        areaResultado = new JTextArea(8, 30);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
    }

    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelPrincipal.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(lblEdad, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelPrincipal.add(txtEdad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPrincipal.add(lblPeso, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelPrincipal.add(txtPeso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(lblTalla, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panelPrincipal.add(txtTalla, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.add(btnCalcular);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(panelBotones, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelPrincipal.add(lblResultadoHeader, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelPrincipal.add(new JScrollPane(areaResultado), gbc);

        add(panelPrincipal);
    }

    private void setupListeners() {
        btnCalcular.addActionListener(this);
        btnLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            procesarCalculo();
        } else if (e.getSource() == btnLimpiar) {
            limpiarCampos();
        }
    }

    private void procesarCalculo() {
        String nombre = txtNombre.getText();
        String edadStr = txtEdad.getText();
        String pesoStr = txtPeso.getText();
        String tallaStr = txtTalla.getText();

        if (nombre.isEmpty() || edadStr.isEmpty() || pesoStr.isEmpty() || tallaStr.isEmpty()) {
            areaResultado.setText("Por favor, complete todos los campos.");
            return;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            double peso = Double.parseDouble(pesoStr);
            double talla = Double.parseDouble(tallaStr);

            if (edad <= 0 || peso <= 0 || talla <= 0) {
                areaResultado.setText("La edad, el peso y la talla deben ser valores positivos.");
                return;
            }
            if (talla > 3) {
                areaResultado.setText("La talla parece estar en centímetros (ej: 175).\nPor favor, ingrese la talla en metros (ej: 1.75).");
                return;
            }


            PersonaDTO persona = new PersonaDTO();
            persona.nombre = nombre;
            persona.edad = edad;
            persona.peso = peso;
            persona.talla = talla;

            misProcesos.calcularIMC(persona);

            StringBuilder resultadoTexto = new StringBuilder();
            resultadoTexto.append("Resultados para: ").append(persona.nombre).append("\n");
            resultadoTexto.append("---------------------------------------\n");
            resultadoTexto.append(String.format("Edad: %d años\n", persona.edad));
            resultadoTexto.append(String.format("Peso: %.2f kg\n", persona.peso));
            resultadoTexto.append(String.format("Talla: %.2f m\n", persona.talla));
            resultadoTexto.append(String.format("IMC: %.2f\n", persona.imc)).append("\n");
            resultadoTexto.append("Clasificación: ").append(persona.estado).append("\n");
            resultadoTexto.append("Recomendación: ").append(persona.mensaje).append("\n");

            areaResultado.setText(resultadoTexto.toString());

        } catch (NumberFormatException ex) {
            areaResultado.setText("Error en el formato de los números.\n" +
                    "Asegúrese de que la edad sea un número entero,\n" +
                    "y el peso/talla sean números válidos (use '.' para decimales).");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtPeso.setText("");
        txtTalla.setText("");
        areaResultado.setText("");
        txtNombre.requestFocus();
    }

    public void setCoordinador(Coordinador miCoordinador) {
    }
}
