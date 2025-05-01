package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

// Clase principal que inicializa el juego
public class Tetris extends JFrame {
    private final int TABLERO_ANCHO = 10;
    private final int TABLERO_ALTO = 22;
    private final int TAMANO_CELDA = 30;

    private JPanel panelPrincipal;
    private JLabel etiquetaPuntaje;
    private JLabel etiquetaNivel;
    private JLabel etiquetaLineas;
    private TableroPanel tableroPanel;
    private VistaPrevia vistaPreviaPieza;
    private Timer temporizador;
    private boolean pausado = false;
    private JButton botonNuevoJuego;
    private JButton botonPausa;

    public Tetris() {
        setTitle("Tetris en Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Inicializar componentes
        inicializarComponentes();

        // Configurar layout
        configurarLayout();

        pack();
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Panel de información
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(4, 1));

        etiquetaPuntaje = new JLabel("Puntaje: 0");
        etiquetaNivel = new JLabel("Nivel: 1");
        etiquetaLineas = new JLabel("Líneas: 0");

        panelInfo.add(etiquetaPuntaje);
        panelInfo.add(etiquetaNivel);
        panelInfo.add(etiquetaLineas);

        // Panel de vista previa
        vistaPreviaPieza = new VistaPrevia();
        panelInfo.add(vistaPreviaPieza);

        // Panel de tablero
        tableroPanel = new TableroPanel(this, TABLERO_ANCHO, TABLERO_ALTO, TAMANO_CELDA);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        botonNuevoJuego = new JButton("Nuevo Juego");
        botonPausa = new JButton("Pausa");

        panelBotones.add(botonNuevoJuego);
        panelBotones.add(botonPausa);

        // Configurar eventos
        botonNuevoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });

        botonPausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausarJuego();
            }
        });

        // Configurar temporizador
        temporizador = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarJuego();
            }
        });
    }

    private void configurarLayout() {
        // Panel izquierdo (información)
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BorderLayout());

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(4, 1, 10, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInfo.add(etiquetaPuntaje);
        panelInfo.add(etiquetaNivel);
        panelInfo.add(etiquetaLineas);
        panelInfo.add(vistaPreviaPieza);

        panelIzquierdo.add(panelInfo, BorderLayout.NORTH);

        // Botones en la parte inferior izquierda
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 1, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelBotones.add(botonNuevoJuego);
        panelBotones.add(botonPausa);

        panelIzquierdo.add(panelBotones, BorderLayout.SOUTH);

        // Agregar a panel principal
        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(tableroPanel, BorderLayout.CENTER);

        // Agregar panel principal al frame
        add(panelPrincipal);
    }

    public void iniciarJuego() {
        tableroPanel.iniciarJuego();
        temporizador.start();
        tableroPanel.requestFocus();
    }

    public void reiniciarJuego() {
        temporizador.stop();
        tableroPanel.reiniciarJuego();
        actualizarPuntaje(0);
        actualizarNivel(1);
        actualizarLineas(0);
        pausado = false;
        botonPausa.setText("Pausa");
        temporizador.start();
        tableroPanel.requestFocus();
    }

    public void pausarJuego() {
        pausado = !pausado;

        if (pausado) {
            temporizador.stop();
            botonPausa.setText("Continuar");
        } else {
            temporizador.start();
            botonPausa.setText("Pausa");
            tableroPanel.requestFocus();
        }
    }

    public void actualizarJuego() {
        tableroPanel.moverPiezaAbajo();
    }

    public void actualizarPuntaje(int puntaje) {
        etiquetaPuntaje.setText("Puntaje: " + puntaje);
    }

    public void actualizarNivel(int nivel) {
        etiquetaNivel.setText("Nivel: " + nivel);
        // Ajustar velocidad del temporizador según el nivel
        int velocidad = Math.max(100, 500 - ((nivel - 1) * 50));
        temporizador.setDelay(velocidad);
    }

    public void actualizarLineas(int lineas) {
        etiquetaLineas.setText("Líneas: " + lineas);
    }

    public void actualizarSiguientePieza(Pieza pieza) {
        vistaPreviaPieza.setPieza(pieza);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tetris tetris = new Tetris();
                tetris.setVisible(true);
                tetris.iniciarJuego();
            }
        });
    }
}

// Clase para representar las piezas del Tetris
class Pieza {
    public enum Tipo {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    }

    private Tipo tipo;
    private int[][] coordenadas;
    private int[][][] tablaCoordenadas;
    private int rotacionActual;

    public Pieza() {
        coordenadas = new int[4][2];
        setTipo(Tipo.NoShape);
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;

        tablaCoordenadas = new int[][][] {
                // NoShape
                { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },
                // ZShape
                { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } },
                // SShape
                { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } },
                // LineShape
                { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } },
                // TShape
                { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } },
                // SquareShape
                { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } },
                // LShape
                { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } },
                // MirroredLShape
                { { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }
        };

        // Inicializar las coordenadas según el tipo
        for (int i = 0; i < 4; i++) {
            System.arraycopy(tablaCoordenadas[tipo.ordinal()][i], 0, coordenadas[i], 0, 2);
        }

        rotacionActual = 0;
    }

    public void setRandomTipo() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        setTipo(Tipo.values()[x]);
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getX(int index) {
        return coordenadas[index][0];
    }

    public int getY(int index) {
        return coordenadas[index][1];
    }

    public void setX(int index, int x) {
        coordenadas[index][0] = x;
    }

    public void setY(int index, int y) {
        coordenadas[index][1] = y;
    }

    public int minX() {
        int m = coordenadas[0][0];
        for (int i = 1; i < 4; i++) {
            m = Math.min(m, coordenadas[i][0]);
        }
        return m;
    }

    public int minY() {
        int m = coordenadas[0][1];
        for (int i = 1; i < 4; i++) {
            m = Math.min(m, coordenadas[i][1]);
        }
        return m;
    }

    public Pieza rotar() {
        if (tipo == Tipo.SquareShape) {
            return this;
        }

        Pieza resultado = new Pieza();
        resultado.tipo = tipo;

        for (int i = 0; i < 4; i++) {
            resultado.setX(i, -getY(i));
            resultado.setY(i, getX(i));
        }

        return resultado;
    }

    public Color getColor() {
        switch (tipo) {
            case ZShape:
                return Color.RED;
            case SShape:
                return Color.GREEN;
            case LineShape:
                return Color.CYAN;
            case TShape:
                return Color.MAGENTA;
            case SquareShape:
                return Color.YELLOW;
            case LShape:
                return Color.ORANGE;
            case MirroredLShape:
                return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }
}

// Clase para el panel del tablero de juego
class TableroPanel extends JPanel {
    private final int TABLERO_ANCHO;
    private final int TABLERO_ALTO;
    private final int TAMANO_CELDA;

    private Tetris tetrisParent;
    private Pieza piezaActual;
    private Pieza siguientePieza;
    private int piezaActualX;
    private int piezaActualY;
    private Pieza.Tipo[][] tablero;

    private int lineasCompletadas;
    private int puntaje;
    private int nivel;
    private boolean juegoTerminado;

    public TableroPanel(Tetris parent, int ancho, int alto, int tamano) {
        TABLERO_ANCHO = ancho;
        TABLERO_ALTO = alto;
        TAMANO_CELDA = tamano;
        tetrisParent = parent;

        setPreferredSize(new Dimension(TABLERO_ANCHO * TAMANO_CELDA, TABLERO_ALTO * TAMANO_CELDA));
        setBackground(Color.BLACK);
        setFocusable(true);

        inicializarTablero();

        // Configurar control de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (juegoTerminado) {
                    return;
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        moverPiezaIzquierda();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moverPiezaDerecha();
                        break;
                    case KeyEvent.VK_DOWN:
                        moverPiezaAbajo();
                        break;
                    case KeyEvent.VK_UP:
                        rotarPieza();
                        break;
                    case KeyEvent.VK_SPACE:
                        caerPieza();
                        break;
                    case KeyEvent.VK_P:
                        tetrisParent.pausarJuego();
                        break;
                }
            }
        });
    }

    private void inicializarTablero() {
        tablero = new Pieza.Tipo[TABLERO_ANCHO][TABLERO_ALTO];

        for (int i = 0; i < TABLERO_ANCHO; i++) {
            for (int j = 0; j < TABLERO_ALTO; j++) {
                tablero[i][j] = Pieza.Tipo.NoShape;
            }
        }
    }

    public void iniciarJuego() {
        inicializarTablero();

        piezaActual = new Pieza();
        siguientePieza = new Pieza();
        siguientePieza.setRandomTipo();

        lineasCompletadas = 0;
        puntaje = 0;
        nivel = 1;
        juegoTerminado = false;

        nuevaPieza();

        tetrisParent.actualizarPuntaje(puntaje);
        tetrisParent.actualizarNivel(nivel);
        tetrisParent.actualizarLineas(lineasCompletadas);
    }

    public void reiniciarJuego() {
        inicializarTablero();
        iniciarJuego();
    }

    private void nuevaPieza() {
        piezaActual = siguientePieza;
        siguientePieza = new Pieza();
        siguientePieza.setRandomTipo();
        tetrisParent.actualizarSiguientePieza(siguientePieza);

        piezaActualX = TABLERO_ANCHO / 2 - 1;
        // Modificación aquí: Ajustamos la posición inicial en Y para evitar que las piezas
        // queden fuera del tablero debido a coordenadas negativas
        piezaActualY = 2; // Cambiado de 0 a 2

        // Si no podemos colocar la nueva pieza, el juego ha terminado
        if (!puedeMover(piezaActual, piezaActualX, piezaActualY)) {
            piezaActual.setTipo(Pieza.Tipo.NoShape);
            juegoTerminado = true;
            tetrisParent.actualizarPuntaje(puntaje); // Asegurarse de que el puntaje final se muestre
            JOptionPane.showMessageDialog(this, "¡Juego terminado! Puntaje: " + puntaje);
        }
    }

    private boolean puedeMover(Pieza pieza, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + pieza.getX(i);
            int y = newY + pieza.getY(i);

            if (x < 0 || x >= TABLERO_ANCHO || y < 0 || y >= TABLERO_ALTO) {
                return false;
            }

            if (tablero[x][y] != Pieza.Tipo.NoShape) {
                return false;
            }
        }

        return true;
    }

    public void moverPiezaIzquierda() {
        if (puedeMover(piezaActual, piezaActualX - 1, piezaActualY)) {
            piezaActualX--;
            repaint();
        }
    }

    public void moverPiezaDerecha() {
        if (puedeMover(piezaActual, piezaActualX + 1, piezaActualY)) {
            piezaActualX++;
            repaint();
        }
    }

    public void moverPiezaAbajo() {
        if (juegoTerminado) {
            return;
        }

        if (puedeMover(piezaActual, piezaActualX, piezaActualY + 1)) {
            piezaActualY++;
            repaint();
        } else {
            fijarPieza();
            repaint();
        }
    }

    public void rotarPieza() {
        Pieza piezaRotada = piezaActual.rotar();

        // Comprobar si puede rotar
        if (puedeMover(piezaRotada, piezaActualX, piezaActualY)) {
            piezaActual = piezaRotada;
            repaint();
        }
    }

    public void caerPieza() {
        int newY = piezaActualY;

        while (newY < TABLERO_ALTO) {
            if (!puedeMover(piezaActual, piezaActualX, newY + 1)) {
                break;
            }
            newY++;
        }

        piezaActualY = newY;
        fijarPieza();
        repaint();
    }

    private void fijarPieza() {
        // Verificar si la pieza actual es válida
        if (piezaActual.getTipo() == Pieza.Tipo.NoShape) {
            return;
        }

        // Fijar la pieza en el tablero
        for (int i = 0; i < 4; i++) {
            int x = piezaActualX + piezaActual.getX(i);
            int y = piezaActualY + piezaActual.getY(i);

            // Verificar que las coordenadas estén dentro del tablero
            if (x >= 0 && x < TABLERO_ANCHO && y >= 0 && y < TABLERO_ALTO) {
                tablero[x][y] = piezaActual.getTipo();
            }
        }

        // Eliminar líneas completas y actualizar puntaje
        eliminarLineasCompletas();

        // Solo crear una nueva pieza si el juego no ha terminado
        if (!juegoTerminado) {
            nuevaPieza();
        }
    }

    private void eliminarLineasCompletas() {
        int lineasEliminadas = 0;

        for (int i = TABLERO_ALTO - 1; i >= 0; i--) {
            boolean lineaCompleta = true;

            for (int j = 0; j < TABLERO_ANCHO; j++) {
                if (tablero[j][i] == Pieza.Tipo.NoShape) {
                    lineaCompleta = false;
                    break;
                }
            }

            if (lineaCompleta) {
                lineasEliminadas++;

                // Mover todas las líneas una posición hacia abajo
                for (int k = i; k > 0; k--) {
                    for (int j = 0; j < TABLERO_ANCHO; j++) {
                        tablero[j][k] = tablero[j][k - 1];
                    }
                }

                // Limpiar la línea superior
                for (int j = 0; j < TABLERO_ANCHO; j++) {
                    tablero[j][0] = Pieza.Tipo.NoShape;
                }

                // Incrementar i para revisar la misma línea de nuevo
                i++;
            }
        }

        if (lineasEliminadas > 0) {
            // Actualizar puntaje y nivel
            lineasCompletadas += lineasEliminadas;

            // Calcular puntos según número de líneas eliminadas de una vez
            int puntos = 0;
            switch (lineasEliminadas) {
                case 1:
                    puntos = 100 * nivel;
                    break;
                case 2:
                    puntos = 300 * nivel;
                    break;
                case 3:
                    puntos = 500 * nivel;
                    break;
                case 4:
                    puntos = 800 * nivel;
                    break;
            }

            puntaje += puntos;

            // Actualizar nivel (cada 10 líneas)
            nivel = (lineasCompletadas / 10) + 1;

            // Actualizar interfaz
            tetrisParent.actualizarPuntaje(puntaje);
            tetrisParent.actualizarNivel(nivel);
            tetrisParent.actualizarLineas(lineasCompletadas);

            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el fondo
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar piezas en el tablero
        for (int i = 0; i < TABLERO_ANCHO; i++) {
            for (int j = 0; j < TABLERO_ALTO; j++) {
                Pieza.Tipo tipo = tablero[i][j];

                if (tipo != Pieza.Tipo.NoShape) {
                    dibujarCuadrado(g, i * TAMANO_CELDA, j * TAMANO_CELDA, obtenerColor(tipo));
                }
            }
        }

        // Dibujar la pieza actual
        if (piezaActual.getTipo() != Pieza.Tipo.NoShape) {
            for (int i = 0; i < 4; i++) {
                int x = piezaActualX + piezaActual.getX(i);
                int y = piezaActualY + piezaActual.getY(i);
                dibujarCuadrado(g, x * TAMANO_CELDA, y * TAMANO_CELDA, piezaActual.getColor());
            }
        }

        // Dibujar la cuadrícula
        g.setColor(new Color(50, 50, 50));
        for (int i = 0; i <= TABLERO_ANCHO; i++) {
            g.drawLine(i * TAMANO_CELDA, 0, i * TAMANO_CELDA, TABLERO_ALTO * TAMANO_CELDA);
        }

        for (int i = 0; i <= TABLERO_ALTO; i++) {
            g.drawLine(0, i * TAMANO_CELDA, TABLERO_ANCHO * TAMANO_CELDA, i * TAMANO_CELDA);
        }
    }

    private void dibujarCuadrado(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x + 1, y + 1, TAMANO_CELDA - 2, TAMANO_CELDA - 2);

        // Efecto 3D
        g.setColor(color.brighter());
        g.drawLine(x, y, x + TAMANO_CELDA - 1, y);
        g.drawLine(x, y, x, y + TAMANO_CELDA - 1);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + TAMANO_CELDA - 1, x + TAMANO_CELDA - 1, y + TAMANO_CELDA - 1);
        g.drawLine(x + TAMANO_CELDA - 1, y + 1, x + TAMANO_CELDA - 1, y + TAMANO_CELDA - 1);
    }

    private Color obtenerColor(Pieza.Tipo tipo) {
        Pieza pieza = new Pieza();
        pieza.setTipo(tipo);
        return pieza.getColor();
    }
}

// Clase para mostrar la vista previa de la siguiente pieza
class VistaPrevia extends JPanel {
    private final int TAMANO_CELDA = 15;
    private Pieza pieza;

    public VistaPrevia() {
        setPreferredSize(new Dimension(80, 80));
        setBorder(BorderFactory.createTitledBorder("Siguiente"));
        pieza = new Pieza();
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Centrar la pieza en el panel
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;

        // Dibujar la pieza
        if (pieza.getTipo() != Pieza.Tipo.NoShape) {
            for (int i = 0; i < 4; i++) {
                int x = centroX + (pieza.getX(i) * TAMANO_CELDA);
                int y = centroY + (pieza.getY(i) * TAMANO_CELDA);
                dibujarCuadrado(g, x, y, pieza.getColor());
            }
        }
    }

    private void dibujarCuadrado(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, TAMANO_CELDA - 2, TAMANO_CELDA - 2);

        // Efecto 3D
        g.setColor(color.brighter());
        g.drawLine(x, y, x + TAMANO_CELDA - 2, y);
        g.drawLine(x, y, x, y + TAMANO_CELDA - 2);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + TAMANO_CELDA - 2, x + TAMANO_CELDA - 2, y + TAMANO_CELDA - 2);
        g.drawLine(x + TAMANO_CELDA - 2, y + 1, x + TAMANO_CELDA - 2, y + TAMANO_CELDA - 2);
    }
}