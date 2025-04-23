package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.event.*;
import java.awt.event.ActionListener;
//Ejercicio: Conversor de Unidades de Longitud
//
//Crea una aplicación en Java Swing que permita al usuario convertir entre dos unidades de longitud: centímetros (cm) y pulgadas (in).
//
//Requisitos:
//
//Interfaz Gráfica:
//
//Debe tener una ventana con los siguientes elementos:
//Una etiqueta para el título de la aplicación (ej., "Conversor de Longitud").
//Un campo de texto para que el usuario ingrese el valor a convertir.
//Dos botones de opción (JRadioButton) para que el usuario seleccione la unidad de entrada: "Centímetros (cm)" y "Pulgadas (in)".
//Un botón para realizar la conversión.
//Una etiqueta para mostrar el resultado de la conversión.
//Utiliza un JPanel para organizar los componentes.
//Asegúrate de que los componentes estén claramente etiquetados y ubicados de manera lógica.
//        Funcionalidad:
//
//Cuando el usuario ingrese un valor en el campo de texto y seleccione la unidad de entrada (cm o in) y luego haga clic en el botón de conversión, la aplicación debe:
//Obtener el valor ingresado por el usuario desde el campo de texto.
//Convertir el valor a la otra unidad:
//Si la entrada está en centímetros, convertir a pulgadas (1 cm = 0.393701 in).
//Si la entrada está en pulgadas, convertir a centímetros (1 in = 2.54 cm).
//Mostrar el resultado de la conversión en la etiqueta de resultado.
//Manejo de errores:
//Si el usuario ingresa un valor no numérico en el campo de texto, muestra un mensaje de error utilizando JOptionPane.

public class ConversorDeUnidades extends JFrame implements ActionListener, ChangeListener {

    private JLabel  lblValor, lblResultado;
    private JTextField txtValor;
    private JButton btnCalcular;
    private JPanel panel;
    private JRadioButton Centimetros, Pulgadas;
    private ButtonGroup bg;
    private void inicializarComponentes() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.white);
        bg=new ButtonGroup();

        Centimetros=new JRadioButton("640*480");
        Centimetros.setBounds(10,20,100,30);
        Centimetros.addChangeListener(this);
        add(Centimetros);
        bg.add(Centimetros);

        Pulgadas =new JRadioButton("800*600");
        Pulgadas.setBounds(10,70,100,30);
        Pulgadas.addChangeListener(this);
        add(Pulgadas);
        bg.add(Pulgadas);

        txtValor = new JTextField();
        txtValor.setBounds(180, 20, 150, 25);

        lblValor = new JLabel("Valor a convertir:");
        lblValor.setBounds(20, 50, 150, 25);


        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(100, 180, 150, 30);
        btnCalcular.addActionListener(this);

        lblResultado = new JLabel("Conversion:");
        lblResultado.setBounds(20, 220, 700, 25);


        panel.add(txtValor);
        panel.add(lblValor);
        panel.add(btnCalcular);
        panel.add(lblResultado);

        add(panel);
    }
    public void stateChanged(ChangeEvent e) {
        if (Centimetros.isSelected()) {
            setSize(640,480);

        }
        if (Pulgadas.isSelected()) {
            setSize(800,600);
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            ConversorDeUnidades();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
