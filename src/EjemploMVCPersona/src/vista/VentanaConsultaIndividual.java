package EjemploMVCPersona.src.vista;

import EjemploMVCPersona.src.controlador.Coordinador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import java.awt.Font;

public class VentanaConsultaIndividual extends JDialog {
   
	private JButton btnConsulta;
	private JTextField txtNombre;
	private JButton btnActualizar;
	private JLabel lblDocumento;
	private JTextField txtDocumento;
	private JLabel lblEdad;
	private JTextField txtEdad;
	private JLabel lblConsultaDeUsuarios;
	private JButton btnEliminar;

    public VentanaConsultaIndividual(VentanaPrincipal ventanaPrincipal, boolean modal) {
    	/**Al llamar al constructor super(), le enviamos el
   	  * JFrame Padre y la propiedad booleana que determina
   	  * que es hija*/
    	super(ventanaPrincipal, modal);
        setTitle("Ventana Registro Persona");
        setSize(370, 277);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        iniciarComponentes();
    }

    private void iniciarComponentes() {
    	txtNombre = new JTextField();
        txtNombre.setBounds(17, 117, 200, 30);
        getContentPane().add(txtNombre);

        btnConsulta = new JButton("...");
        btnConsulta.setBounds(290, 49, 56, 30);
        getContentPane().add(btnConsulta);
        
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(17, 194, 159, 30);
        getContentPane().add(btnActualizar);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(17, 91, 144, 30);
        getContentPane().add(lblNombre);
        
        lblDocumento = new JLabel("Documento");
        lblDocumento.setBounds(114, 48, 91, 30);
        getContentPane().add(lblDocumento);
        
        txtDocumento = new JTextField();
        txtDocumento.setBounds(204, 48, 85, 30);
        getContentPane().add(txtDocumento);
        
        lblEdad = new JLabel("Edad");
        lblEdad.setBounds(249, 91, 49, 30);
        getContentPane().add(lblEdad);
        
        txtEdad = new JTextField();
        txtEdad.setBounds(247, 117, 87, 30);
        getContentPane().add(txtEdad);
        
        lblConsultaDeUsuarios = new JLabel("CONSULTA DE USUARIOS");
        lblConsultaDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        lblConsultaDeUsuarios.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblConsultaDeUsuarios.setBounds(17, 6, 335, 30);
        getContentPane().add(lblConsultaDeUsuarios);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(188, 195, 151, 29);
        getContentPane().add(btnEliminar);
	}


    public void setCoordinador(Coordinador miCoordinador) {
    }
}

