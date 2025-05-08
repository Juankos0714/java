package InterfacesGraficas.LoginBasico;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel lblTitulo;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnAceptar, btnLimpiar;
    private JLabel lblConfirmacion;

    public VentanaLogin(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        panel = new JPanel();

        setContentPane(panel);
        panel.setLayout(null);

        iniciarComponentes();

    }

    private void iniciarComponentes() {

        lblTitulo = new JLabel();
        lblTitulo.setText("INICIAR SECCION");
        lblTitulo.setBounds(60, 17, 210, 50);
        panel.add(lblTitulo);

        txtUser = new JTextField();
        txtUser.setBounds(16, 75, 210, 40);
        panel.add(txtUser);
        txtUser.setColumns(10);

        txtPass =new JPasswordField();
        txtPass.setBounds(16, 127, 210, 40);
        panel.add(txtPass);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(6, 179, 101, 29);
        btnAceptar.addActionListener(this);
        panel.add(btnAceptar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(160, 179, 101, 29);
        btnLimpiar.addActionListener(this);
        panel.add(btnLimpiar);

        lblConfirmacion = new JLabel("");
        lblConfirmacion.setBounds(16, 220, 210, 29);
        panel.add(lblConfirmacion);





    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAceptar){
            System.out.println("ACEPTAR");
            validarIngreso();

        }else if (e.getSource() == btnLimpiar){
            System.out.println("LIMPIAR");
            limpiarFormulario();
        }



    }

    private void validarIngreso() {
        String user = txtUser.getText();
        char[] password = txtPass.getPassword();
        String pass = new String(password);
        String msj;

        if (user.equals("juan") && pass.equals("0000")){
            System.out.println("Corresponde");
            lblConfirmacion.setText("Usuario Logueado");
            lblConfirmacion.setForeground(Color.green);

            VentanaUsuario miVentanaUsuario = new VentanaUsuario();
            miVentanaUsuario.setVisible(true);
        }else {
            System.out.println("No corresponde");
            lblConfirmacion.setText("El Usuario No Corresponde");
            lblConfirmacion.setForeground(Color.RED);
        }
    }

    private void limpiarFormulario() {
        txtUser.setText("");
        txtPass.setText("");
    }
}
