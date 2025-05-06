package InterfacesGraficas.CalculadoraGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Metodos extends JFrame implements ActionListener {
    JButton btnSum, btnRes, btnDiv, btnMult;
    private JTextField txtNum1, txtNum2;
    private JLabel lblTitulo, lblNum1, lblNum2, lblResultado;
    OperacionesMatematicas misOperaciones;

    public Metodos() {
        setTitle("Ventana Operaciones");
        setSize(900, 600);
        setBackground(Color.lightGray);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        lblTitulo = new JLabel("OPERACIONES MATEMATICAS");
        lblTitulo.setBounds(300, 10, 390, 50);
        lblTitulo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
        add(lblTitulo);

        lblNum1 = new JLabel("Numero 1");
        lblNum1.setBounds(110, 50, 190, 50);
        lblNum1.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        add(lblNum1);
        lblNum2 = new JLabel("Numero 2");
        lblNum2.setBounds(110, 100, 190, 50);
        lblNum2.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        add(lblNum2);

        lblResultado = new JLabel("");
        lblResultado.setBounds(140,480,260,50);
        add(lblResultado);

        txtNum1 = new JTextField();
        txtNum1.setBounds(210, 60, 230, 30);
        txtNum1.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        add(txtNum1);

        txtNum2 = new JTextField();
        txtNum2.setBounds(210, 110, 230, 30);
        txtNum2.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        add(txtNum2);


        btnSum = new JButton("Suma");
        btnSum.setBounds(140, 280, 190, 50);
        btnSum.addActionListener(this);
        add(btnSum);

        btnRes = new JButton("Resta");
        btnRes.setBounds(430, 280, 190, 50);
        btnRes.addActionListener(this);
        add(btnRes);
        btnMult = new JButton("Multi");
        btnMult.setBounds(140, 380, 190, 50);
        btnMult.addActionListener(this);
        add(btnMult);
        btnDiv = new JButton("Div");
        btnDiv.setBounds(430, 380, 190, 50);
        btnDiv.addActionListener(this);
        add(btnDiv);







    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnSum==e.getSource()){
            int num1=Integer.parseInt(txtNum1.getText());
            int num2=Integer.parseInt(txtNum2.getText());
            int resp=misOperaciones.sumar(num1,num2);
            lblResultado.setText("EL resultado de la Suma es "+resp);
        }
        if(btnRes==e.getSource()){
            int num1=Integer.parseInt(txtNum1.getText());
            int num2=Integer.parseInt(txtNum2.getText());
            int resp=misOperaciones.restar(num1,num2);
            lblResultado.setText("EL resultado de la Resta es "+resp);
        }
        if(btnMult==e.getSource()){
            int num1=Integer.parseInt(txtNum1.getText());
            int num2=Integer.parseInt(txtNum2.getText());
            int resp=misOperaciones.multiplicar(num1,num2);
            lblResultado.setText("EL resultado de la Multiplicacion es "+resp);
        }
        if(btnDiv==e.getSource()){
            int num1=Integer.parseInt(txtNum1.getText());
            int num2=Integer.parseInt(txtNum2.getText());
            String resp=misOperaciones.dividir(num1,num2);
            lblResultado.setText("EL resultado de la Division es "+resp);

        }

    }
    public void asignarOperaciones(OperacionesMatematicas misOperaciones){
        this.misOperaciones=misOperaciones;
    }

}

