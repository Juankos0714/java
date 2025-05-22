package EjercicioModeloDatosMVC.src.vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import EjercicioModeloDatosMVC.src.controlador.Coordinador;
import modelo.dto.MascotaDTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegistroMascotaGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Coordinador miCoordinador;
	private JTextField txtNombre;
	private JTextField txtDoc;
	private JButton btnRegistrar;

	/**
	 * Create the frame.
	 */
	public RegistroMascotaGUI() {
		System.out.println("Crea la ventana");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		iniciarComponentes();
		
	
	}


	private void iniciarComponentes() {
		JLabel lblRegistroDeMascotas = new JLabel("REGISTRO DE MASCOTAS");
		lblRegistroDeMascotas.setBounds(38, 10, 622, 34);
		lblRegistroDeMascotas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeMascotas.setFont(new Font("Monaco", Font.PLAIN, 25));
		contentPane.add(lblRegistroDeMascotas);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(38, 61, 90, 16);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(140, 56, 296, 26);
		contentPane.add(txtNombre);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(455, 61, 90, 16);
		contentPane.add(lblDocumento);
		
		txtDoc = new JTextField();
		txtDoc.setColumns(10);
		txtDoc.setBounds(530, 56, 130, 26);
		contentPane.add(txtDoc);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(540, 104, 117, 29);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);
	}


	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador=miCoordinador;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnRegistrar) {
			registrarMascota();
		}
	}


	private void registrarMascota() {
		
		MascotaDTO miMascotaDTO=new MascotaDTO();
		miMascotaDTO.setNombre(txtNombre.getText());
		miMascotaDTO.setIdMascota(txtDoc.getText());
		
		String resp=miCoordinador.registrarMascota(miMascotaDTO);
		if (resp.equals("si")) {
			System.out.println("Registro exitoso!");
		}else {
			System.out.println("No se pudo registrar");
		}
		
	}
}
