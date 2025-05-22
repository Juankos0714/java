package EjemploMVCPersona.src.vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import java.awt.Font;

public class VentanaRegistro extends JDialog {
   
	private JButton btnRegistrar;
	private JTextField txtNombre;
	private JLabel lblResultado;
	private JButton btnCalculos;
	private JLabel lblDocumento;
	private JTextField txtDocumento;
	private JLabel lblEdad;
	private JTextField txtEdad;
	private JButton btnConsultar;
	private JLabel lblRegistrarUsuarios;

    public VentanaRegistro(VentanaPrincipal ventanaPrincipal, boolean modal) {
    	/**Al llamar al constructor super(), le enviamos el
   	  * JFrame Padre y la propiedad booleana que determina
   	  * que es hija*/
    	super(ventanaPrincipal, modal);
        setTitle("Ventana Registro Persona");
        setSize(382, 277);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        iniciarComponentes();
    }

    private void iniciarComponentes() {
    	txtNombre = new JTextField();
        txtNombre.setBounds(50, 74, 200, 30);
        getContentPane().add(txtNombre);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(260, 74, 100, 30);
        getContentPane().add(btnRegistrar);

        lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(50, 178, 300, 30);
        getContentPane().add(lblResultado);
        
        btnCalculos = new JButton("Hacer Calculos");
        btnCalculos.setBounds(39, 213, 160, 30);
        btnCalculos.setVisible(false);
        getContentPane().add(btnCalculos);
        
        JLabel lblNombre = new JLabel("Ingrese su nombre");
        lblNombre.setBounds(50, 48, 300, 30);
        getContentPane().add(lblNombre);
        
        lblDocumento = new JLabel("Documento");
        lblDocumento.setBounds(50, 110, 91, 30);
        getContentPane().add(lblDocumento);
        
        txtDocumento = new JTextField();
        txtDocumento.setBounds(140, 110, 110, 30);
        getContentPane().add(txtDocumento);
        
        lblEdad = new JLabel("Edad");
        lblEdad.setBounds(270, 110, 49, 30);
        getContentPane().add(lblEdad);
        
        txtEdad = new JTextField();
        txtEdad.setBounds(313, 110, 41, 30);
        getContentPane().add(txtEdad);
        
        btnConsultar = new JButton("Consultar Registros");
        btnConsultar.setBounds(211, 214, 149, 29);
        btnConsultar.setVisible(false);
        getContentPane().add(btnConsultar);
        
        lblRegistrarUsuarios = new JLabel("REGISTRAR USUARIOS");
        lblRegistrarUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistrarUsuarios.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblRegistrarUsuarios.setBounds(0, 6, 376, 30);
        getContentPane().add(lblRegistrarUsuarios);
	}



}

