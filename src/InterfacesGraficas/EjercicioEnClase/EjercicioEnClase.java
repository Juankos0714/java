package InterfacesGraficas.EjercicioEnClase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class EjercicioEnClase extends JFrame implements ActionListener {
    JButton btnPresioname;
    private JTextField txtCampo, txtEdad, txtDireccion, txtSalario, txtTelefono, txtAnio;
    private JLabel lblMensaje, lblEdad, lblDireccion, lblSalario, lblTelefono, lblAnio;

    public EjercicioEnClase() {
        System.out.println("Ingresa a la ventana");

        setTitle("Hola mundo");
        setSize(900, 600);
        setBackground(Color.lightGray);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        System.out.println("Iniciar");

        JLabel lblTitulo = new JLabel("Bienvenidos");
        lblTitulo.setBounds(10, 10, 190, 50);

        lblMensaje = new JLabel("Nombre");
        lblMensaje.setBounds(110, 50, 190, 50);
        lblMensaje.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        btnPresioname = new JButton("Enviar la informacion");
        btnPresioname.setBounds(140, 480, 190, 50);
        btnPresioname.addActionListener(this);

        txtCampo = new JTextField();
        txtCampo.setBounds(110, 90, 230, 50);
        txtCampo.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        lblEdad = new JLabel("Edad");
        lblEdad.setBounds(350, 50, 90, 50);
        lblEdad.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        txtEdad = new JTextField();
        txtEdad.setBounds(350, 90, 60, 50);
        txtEdad.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        lblDireccion = new JLabel("Direccion");
        lblDireccion.setBounds(110, 140, 90, 50);
        lblDireccion.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        txtDireccion = new JTextField();
        txtDireccion.setBounds(110, 190, 230, 50);
        txtDireccion.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        lblTelefono = new JLabel("Telefono");
        lblTelefono.setBounds(350, 140, 90, 50);
        lblTelefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        txtTelefono = new JTextField();
        txtTelefono.setBounds(350, 190, 230, 50);
        txtTelefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        lblSalario = new JLabel("Salario");
        lblSalario.setBounds(110, 230, 90, 50);
        lblSalario.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        txtSalario = new JTextField();
        txtSalario.setBounds(110, 280, 230, 50);
        txtSalario.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        lblAnio= new JLabel("Años a calcular");
        lblAnio.setBounds(350, 230, 140, 50);
        lblAnio.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));

        txtAnio = new JTextField();
        txtAnio.setBounds(350, 280, 90, 50);
        txtAnio.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        add(lblTitulo);
        add(btnPresioname);
        add(txtCampo);
        add(txtEdad);
        add(lblMensaje);
        add(lblEdad);
        add(lblDireccion);
        add(txtDireccion);
        add(lblTelefono);
        add(txtTelefono);
        add(txtSalario);
        add(lblSalario);
        add(txtAnio);
        add(lblAnio);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPresioname) {
            String name = txtCampo.getText();
            String direccion = txtDireccion.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            int salario = Integer.parseInt(txtSalario.getText());
            int telefono = Integer.parseInt(txtTelefono.getText());
            int anio = Integer.parseInt(txtAnio.getText());
            int resultado =anio+edad;

            String msj ="La edad de " +name + " es " + edad+", pero dentro de "+anio+" tendrá "+resultado;

            System.out.println(msj);
            JOptionPane.showMessageDialog(null, msj);
        }
    }
}