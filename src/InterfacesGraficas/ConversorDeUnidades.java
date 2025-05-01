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


public class ConversorDeUnidades extends JFrame implements ActionListener {
    private JLabel lblTitulo, lblValor, lblResultado;
    private JTextField txtValor;
    private JButton btnConvertir;
    private JPanel panel;
    private JRadioButton radioCentimetros, radioPulgadas;
    private ButtonGroup grupoRadio;

    public ConversorDeUnidades() {
        setTitle("Conversor de Longitud");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
        setVisible(true);
    }

    private void inicializarComponentes() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.white);

        lblTitulo = new JLabel("Conversor de Longitud");
        lblTitulo.setBounds(100, 10, 150, 20);
        panel.add(lblTitulo);

        lblValor = new JLabel("Valor a convertir:");
        lblValor.setBounds(20, 50, 120, 25);
        panel.add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(150, 50, 150, 25);
        panel.add(txtValor);

        radioCentimetros = new JRadioButton("Centímetros (cm)");
        radioCentimetros.setBounds(20, 80, 150, 25);
        radioCentimetros.setActionCommand("cm");
        radioCentimetros.setSelected(true);
        panel.add(radioCentimetros);

        radioPulgadas = new JRadioButton("Pulgadas (in)");
        radioPulgadas.setBounds(180, 80, 150, 25);
        radioPulgadas.setActionCommand("in");
        panel.add(radioPulgadas);

        grupoRadio = new ButtonGroup();
        grupoRadio.add(radioCentimetros);
        grupoRadio.add(radioPulgadas);

        btnConvertir = new JButton("Convertir");
        btnConvertir.setBounds(100, 120, 120, 30);
        btnConvertir.addActionListener(this);
        panel.add(btnConvertir);

        lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(20, 160, 300, 25);
        panel.add(lblResultado);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConvertir) {
            realizarConversion();
        }
    }

    private void realizarConversion() {
        try {
            double valor = Double.parseDouble(txtValor.getText());
            String unidadEntrada = grupoRadio.getSelection().getActionCommand();
            double resultado = 0;

            if (unidadEntrada.equals("cm")) {
                resultado = valor * 0.393701;
                lblResultado.setText(valor + " cm son: " + String.format("%.2f", resultado) + " pulgadas.");
            } else if (unidadEntrada.equals("in")) {
                resultado = valor * 2.54;
                lblResultado.setText(valor + " pulgadas son: " + String.format("%.2f", resultado) + " centímetros.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una unidad de entrada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConversorDeUnidades::new);
    }
}
