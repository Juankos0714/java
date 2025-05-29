package MVC_IMC.vista;

import MVC_IMC.controlador.Coordinador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private Coordinador miCoordinador;
    private JMenuBar menuBar;
    private JMenu menuArchivo, menuPrincipal, menuConsultas;
    private JMenuItem itemSalir, itemCalcularIMC, itemRegistrarPersona,
            itemConsultaIndividual, itemConsultarLista;
    private JLabel lblTitulo, lblDescripcion;
    private JPanel panelCentral, panelBotones;
    private JButton btnRegistrar, btnCalcular, btnConsultar;

    public VentanaPrincipal() {
        initComponents();
        setupLayout();
        setupMenu();
    }

    private void initComponents() {
        setTitle("Sistema de Cálculo de IMC");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        lblTitulo = new JLabel("Sistema de Cálculo de IMC");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setForeground(new Color(0, 102, 204));

        lblDescripcion = new JLabel("<html><center>Bienvenido al sistema de cálculo de IMC<br><br>" +
                "Siga estos pasos:<br>" +
                "1. Registre una persona nueva<br>" +
                "2. Calcule su IMC ingresando peso y estatura<br>" +
                "3. Consulte los resultados guardados</center></html>");
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDescripcion.setHorizontalAlignment(JLabel.CENTER);

        btnRegistrar = new JButton("1. Registrar Persona");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(0, 153, 76));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setPreferredSize(new Dimension(200, 40));
        btnRegistrar.addActionListener(this);

        btnCalcular = new JButton("2. Calcular IMC");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 14));
        btnCalcular.setBackground(new Color(0, 102, 204));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setPreferredSize(new Dimension(200, 40));
        btnCalcular.addActionListener(this);

        btnConsultar = new JButton("3. Ver Consultas");
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConsultar.setBackground(new Color(153, 102, 255));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setPreferredSize(new Dimension(200, 40));
        btnConsultar.addActionListener(this);

        panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout(20, 20));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panelCentral.setBackground(Color.WHITE);

        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setBackground(Color.WHITE);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(240, 248, 255));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        panelCentral.add(lblDescripcion, BorderLayout.CENTER);

        JLabel lblLogo = new JLabel("📊", JLabel.CENTER);
        lblLogo.setFont(new Font("Arial", Font.PLAIN, 48));
        panelCentral.add(lblLogo, BorderLayout.NORTH);

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCalcular);
        panelBotones.add(btnConsultar);
        panelCentral.add(panelBotones, BorderLayout.SOUTH);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(245, 245, 245));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblInfo = new JLabel("Sistema MVC - Calculadora de IMC v2.0");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        panelInferior.add(lblInfo);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void setupMenu() {
        menuBar = new JMenuBar();

        menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic('A');

        itemSalir = new JMenuItem("Salir");
        itemSalir.setMnemonic('S');
        itemSalir.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
        itemSalir.addActionListener(this);
        menuArchivo.add(itemSalir);

        menuPrincipal = new JMenu("Principal");
        menuPrincipal.setMnemonic('P');

        itemRegistrarPersona = new JMenuItem("Registrar Persona");
        itemRegistrarPersona.setMnemonic('R');
        itemRegistrarPersona.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
        itemRegistrarPersona.addActionListener(this);

        itemCalcularIMC = new JMenuItem("Calcular IMC");
        itemCalcularIMC.setMnemonic('I');
        itemCalcularIMC.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
        itemCalcularIMC.addActionListener(this);

        menuPrincipal.add(itemRegistrarPersona);
        menuPrincipal.add(itemCalcularIMC);

        menuConsultas = new JMenu("Consultas");
        menuConsultas.setMnemonic('C');

        itemConsultaIndividual = new JMenuItem("Consulta Individual");
        itemConsultaIndividual.setMnemonic('n');
        itemConsultaIndividual.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        itemConsultaIndividual.addActionListener(this);

        itemConsultarLista = new JMenuItem("Lista de Personas");
        itemConsultarLista.setMnemonic('L');
        itemConsultarLista.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        itemConsultarLista.addActionListener(this);

        menuConsultas.add(itemConsultaIndividual);
        menuConsultas.add(itemConsultarLista);

        menuBar.add(menuArchivo);
        menuBar.add(menuPrincipal);
        menuBar.add(menuConsultas);

        setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemSalir) {
            int opcion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea salir de la aplicación?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                miCoordinador.cerrarAplicacion();
            }
        } else if (e.getSource() == itemCalcularIMC || e.getSource() == btnCalcular) {
            miCoordinador.mostrarVentanaCalculo();
        } else if (e.getSource() == itemRegistrarPersona || e.getSource() == btnRegistrar) {
            miCoordinador.mostrarVentanaRegistro();
        } else if (e.getSource() == itemConsultaIndividual || e.getSource() == btnConsultar) {
            miCoordinador.mostrarVentanaConsultaIndividual();
        } else if (e.getSource() == itemConsultarLista) {
            miCoordinador.mostrarVentanaConsultarLista();
        }
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
}