package Encapsulamiento.EjemploGUIEncapsulamiento_SOLID.gui;

import Encapsulamiento.EjemploGUIEncapsulamiento.clases.ModeloDatos;
import Encapsulamiento.EjemploGUIEncapsulamiento.clases.Procesos;
import Encapsulamiento.EjemploGUIEncapsulamiento.entidades.Estudiante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaLista extends JFrame implements ActionListener {
    private Procesos misProcesos;
    private ModeloDatos miModeloDatos;
    private JPanel panelLista;
    private JScrollPane scrollPane;
    private JTextArea textArea;

    public VentanaLista() {
        misProcesos = new Procesos();
        miModeloDatos = new ModeloDatos();

        setTitle("Lista de Estudiantes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 500);

        panelLista = new JPanel();
        setContentPane(panelLista);
        panelLista.setLayout(null);

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(30, 30, 580, 400);
        panelLista.add(scrollPane);
    }

    public void setTextoLista(String texto) {
        textArea.setText(texto);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}