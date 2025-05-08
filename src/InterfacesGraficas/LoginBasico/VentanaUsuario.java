package InterfacesGraficas.LoginBasico;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaUsuario extends JFrame implements ActionListener {
    private JPanel panelUsuario;

    public VentanaUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        panelUsuario = new JPanel();

        setContentPane(panelUsuario);
        panelUsuario.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}