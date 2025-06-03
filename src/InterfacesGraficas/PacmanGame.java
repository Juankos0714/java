package InterfacesGraficas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PacmanGame extends JPanel implements ActionListener, KeyListener {
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 650;
    private static final int UNIT_SIZE = 25;
    private static final int DELAY = 120;

    private Timer timer;
    private Timer ghostTimer;
    private boolean running = false;
    private int score = 0;
    private int ghostMoveCounter = 0;

    // Pacman
    private int pacmanX = 0;
    private int pacmanY = 0;
    private char direction = 'R';

    // Fantasmas
    private List<Ghost> ghosts;

    // Puntos normales y power pellets
    private List<Point> dots;
    private List<Point> powerPellets;

    // Paredes del laberinto
    private List<Rectangle> walls;

    // Estado de vulnerabilidad de fantasmas
    private boolean ghostsVulnerable = false;
    private Timer vulnerabilityTimer;
    private int vulnerabilityTimeLeft = 0;

    // Clase para los fantasmas
    class Ghost {
        int x, y;
        char direction;
        Color originalColor;
        Color currentColor;
        boolean eaten = false;

        Ghost(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.originalColor = color;
            this.currentColor = color;
            this.direction = 'R';
        }

        void makeVulnerable() {
            this.currentColor = Color.BLUE;
            this.eaten = false;
        }

        void makeNormal() {
            this.currentColor = this.originalColor;
            this.eaten = false;
        }

        void makeEaten() {
            this.eaten = true;
            this.currentColor = Color.GRAY;
        }
    }

    public PacmanGame() {
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        initializeGame();
        startGame();
    }

    public void initializeGame() {
        ghosts = new ArrayList<>();
        dots = new ArrayList<>();
        powerPellets = new ArrayList<>();
        walls = new ArrayList<>();

        // Crear laberinto más complejo
        createMaze();

        // Crear puntos y power pellets
        createDots();
        createPowerPellets();

        // Crear fantasmas fuera de la casa
        ghosts.add(new Ghost(275, 150, Color.RED));
        ghosts.add(new Ghost(300, 150, Color.PINK));
        ghosts.add(new Ghost(325, 150, Color.CYAN));
        ghosts.add(new Ghost(250, 150, Color.ORANGE));

        // Posición inicial de Pacman
        pacmanX = 25;
        pacmanY = 25;
        direction = 'R';
        score = 0;
        ghostsVulnerable = false;
        vulnerabilityTimeLeft = 0;
    }

    public void createMaze() {
        // Paredes exteriores
        walls.add(new Rectangle(0, 0, BOARD_WIDTH, UNIT_SIZE)); // Top
        walls.add(new Rectangle(0, BOARD_HEIGHT - UNIT_SIZE, BOARD_WIDTH, UNIT_SIZE)); // Bottom
        walls.add(new Rectangle(0, 0, UNIT_SIZE, BOARD_HEIGHT)); // Left
        walls.add(new Rectangle(BOARD_WIDTH - UNIT_SIZE, 0, UNIT_SIZE, BOARD_HEIGHT)); // Right

        // Laberinto más complejo con más pasadizos
        // Parte superior
        walls.add(new Rectangle(75, 75, 125, UNIT_SIZE));
        walls.add(new Rectangle(250, 75, 100, UNIT_SIZE));
        walls.add(new Rectangle(400, 75, 125, UNIT_SIZE));

        // Paredes verticales izquierda
        walls.add(new Rectangle(75, 125, UNIT_SIZE, 75));
        walls.add(new Rectangle(125, 150, UNIT_SIZE, 100));
        walls.add(new Rectangle(75, 250, UNIT_SIZE, 100));

        // Paredes verticales derecha
        walls.add(new Rectangle(500, 125, UNIT_SIZE, 75));
        walls.add(new Rectangle(450, 150, UNIT_SIZE, 100));
        walls.add(new Rectangle(500, 250, UNIT_SIZE, 100));

        // Centro - casa de fantasmas (con entrada abierta)
        walls.add(new Rectangle(225, 200, 50, UNIT_SIZE)); // Pared izquierda superior
        walls.add(new Rectangle(325, 200, 50, UNIT_SIZE)); // Pared derecha superior
        walls.add(new Rectangle(225, 200, UNIT_SIZE, 75));  // Pared izquierda
        walls.add(new Rectangle(350, 200, UNIT_SIZE, 75));  // Pared derecha
        walls.add(new Rectangle(225, 325, 150, UNIT_SIZE)); // Pared inferior

        // Paredes horizontales medias
        walls.add(new Rectangle(75, 400, 100, UNIT_SIZE));
        walls.add(new Rectangle(200, 375, UNIT_SIZE, 75));
        walls.add(new Rectangle(375, 375, UNIT_SIZE, 75));
        walls.add(new Rectangle(425, 400, 100, UNIT_SIZE));

        // Parte inferior
        walls.add(new Rectangle(75, 500, 75, UNIT_SIZE));
        walls.add(new Rectangle(200, 475, 200, UNIT_SIZE));
        walls.add(new Rectangle(450, 500, 75, UNIT_SIZE));

        // Paredes adicionales para crear más pasadizos
        walls.add(new Rectangle(300, 125, UNIT_SIZE, 50));
        walls.add(new Rectangle(175, 300, 25, UNIT_SIZE));
        walls.add(new Rectangle(400, 300, 25, UNIT_SIZE));
        walls.add(new Rectangle(300, 450, UNIT_SIZE, 75));
    }

    public void createDots() {
        // Crear puntos en posiciones válidas
        for (int x = UNIT_SIZE; x < BOARD_WIDTH - UNIT_SIZE; x += UNIT_SIZE) {
            for (int y = UNIT_SIZE; y < BOARD_HEIGHT - UNIT_SIZE; y += UNIT_SIZE) {
                if (!isWall(x, y) && !isGhostHouse(x, y)) {
                    dots.add(new Point(x, y));
                }
            }
        }
    }

    public void createPowerPellets() {
        // Power pellets en las esquinas y posiciones estratégicas
        powerPellets.add(new Point(50, 50));
        powerPellets.add(new Point(525, 50));
        powerPellets.add(new Point(50, 575));
        powerPellets.add(new Point(525, 575));
        powerPellets.add(new Point(175, 250));
        powerPellets.add(new Point(400, 250));

        // Remover dots donde están los power pellets
        powerPellets.forEach(pp -> dots.removeIf(dot -> dot.equals(pp)));
    }

    public boolean isGhostHouse(int x, int y) {
        // Solo la parte interior de la casa de fantasmas
        return x >= 250 && x <= 325 && y >= 225 && y <= 300;
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Dibujar paredes
            g.setColor(Color.BLUE);
            for (Rectangle wall : walls) {
                g.fillRect(wall.x, wall.y, wall.width, wall.height);
            }

            // Dibujar puntos normales
            g.setColor(Color.YELLOW);
            for (Point dot : dots) {
                g.fillOval(dot.x + 10, dot.y + 10, 5, 5);
            }

            // Dibujar power pellets (más grandes y parpadeantes)
            if (System.currentTimeMillis() % 500 < 250) { // Efecto parpadeo
                g.setColor(Color.ORANGE);
                for (Point powerPellet : powerPellets) {
                    g.fillOval(powerPellet.x + 7, powerPellet.y + 7, 11, 11);
                }
            }

            // Dibujar Pacman
            g.setColor(Color.YELLOW);
            g.fillOval(pacmanX, pacmanY, UNIT_SIZE, UNIT_SIZE);

            // Dibujar la boca de Pacman
            g.setColor(Color.BLACK);
            switch(direction) {
                case 'R':
                    g.fillArc(pacmanX, pacmanY, UNIT_SIZE, UNIT_SIZE, 315, 90);
                    break;
                case 'L':
                    g.fillArc(pacmanX, pacmanY, UNIT_SIZE, UNIT_SIZE, 135, 90);
                    break;
                case 'U':
                    g.fillArc(pacmanX, pacmanY, UNIT_SIZE, UNIT_SIZE, 45, 90);
                    break;
                case 'D':
                    g.fillArc(pacmanX, pacmanY, UNIT_SIZE, UNIT_SIZE, 225, 90);
                    break;
            }

            // Dibujar fantasmas
            for (Ghost ghost : ghosts) {
                if (!ghost.eaten) {
                    g.setColor(ghost.currentColor);
                    g.fillOval(ghost.x, ghost.y, UNIT_SIZE, UNIT_SIZE);

                    // Ojos blancos
                    g.setColor(Color.WHITE);
                    g.fillOval(ghost.x + 5, ghost.y + 5, 5, 5);
                    g.fillOval(ghost.x + 15, ghost.y + 5, 5, 5);

                    // Pupilas negras
                    g.setColor(Color.BLACK);
                    g.fillOval(ghost.x + 6, ghost.y + 6, 3, 3);
                    g.fillOval(ghost.x + 16, ghost.y + 6, 3, 3);

                    // Parte inferior ondulada
                    g.setColor(ghost.currentColor);
                    int[] xPoints = {ghost.x, ghost.x + 5, ghost.x + 10, ghost.x + 15, ghost.x + 20, ghost.x + 25, ghost.x + 25, ghost.x};
                    int[] yPoints = {ghost.y + 20, ghost.y + 25, ghost.y + 20, ghost.y + 25, ghost.y + 20, ghost.y + 25, ghost.y + 25, ghost.y + 20};
                    g.fillPolygon(xPoints, yPoints, 8);
                }
            }

            // Mostrar información en pantalla
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Score: " + score, 10, 20);

            if (ghostsVulnerable) {
                g.setColor(Color.CYAN);
                g.drawString("Power Mode: " + vulnerabilityTimeLeft + "s", 200, 20);
            }

            if (dots.isEmpty() && powerPellets.isEmpty()) {
                g.setColor(Color.GREEN);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("¡YOU WIN!", (BOARD_WIDTH - metrics.stringWidth("¡YOU WIN!")) / 2,
                        BOARD_HEIGHT / 2);
            }
        } else {
            gameOver(g);
        }
    }

    public void move() {
        int newX = pacmanX;
        int newY = pacmanY;

        switch(direction) {
            case 'U':
                newY -= UNIT_SIZE;
                break;
            case 'D':
                newY += UNIT_SIZE;
                break;
            case 'L':
                newX -= UNIT_SIZE;
                break;
            case 'R':
                newX += UNIT_SIZE;
                break;
        }

        if (!isWall(newX, newY)) {
            pacmanX = newX;
            pacmanY = newY;
        }

        // Mover fantasmas más lentamente
        ghostMoveCounter++;
        if (ghostMoveCounter >= 2) { // Fantasmas se mueven cada 2 ciclos
            moveGhosts();
            ghostMoveCounter = 0;
        }
    }

    public void moveGhosts() {
        for (Ghost ghost : ghosts) {
            if (ghost.eaten) continue; // Fantasmas comidos no se mueven

            // Movimiento más inteligente hacia Pacman cuando no son vulnerables
            int newX = ghost.x;
            int newY = ghost.y;

            if (!ghostsVulnerable) {
                // Perseguir a Pacman
                if (Math.random() < 0.7) { // 70% del tiempo persigue
                    if (Math.abs(pacmanX - ghost.x) > Math.abs(pacmanY - ghost.y)) {
                        if (pacmanX > ghost.x) newX += UNIT_SIZE;
                        else newX -= UNIT_SIZE;
                    } else {
                        if (pacmanY > ghost.y) newY += UNIT_SIZE;
                        else newY -= UNIT_SIZE;
                    }
                } else {
                    // Movimiento aleatorio ocasional
                    randomGhostMove(ghost);
                    continue;
                }
            } else {
                // Huir de Pacman cuando son vulnerables
                if (Math.abs(pacmanX - ghost.x) > Math.abs(pacmanY - ghost.y)) {
                    if (pacmanX > ghost.x) newX -= UNIT_SIZE;
                    else newX += UNIT_SIZE;
                } else {
                    if (pacmanY > ghost.y) newY -= UNIT_SIZE;
                    else newY += UNIT_SIZE;
                }
            }

            if (!isWall(newX, newY)) {
                ghost.x = newX;
                ghost.y = newY;
            } else {
                randomGhostMove(ghost);
            }
        }
    }

    private void randomGhostMove(Ghost ghost) {
        int[] directions = {0, 1, 2, 3};
        int randomDir = directions[(int)(Math.random() * 4)];

        int newX = ghost.x;
        int newY = ghost.y;

        switch(randomDir) {
            case 0: newY -= UNIT_SIZE; break;
            case 1: newY += UNIT_SIZE; break;
            case 2: newX -= UNIT_SIZE; break;
            case 3: newX += UNIT_SIZE; break;
        }

        if (!isWall(newX, newY)) {
            ghost.x = newX;
            ghost.y = newY;
        }
    }

    public boolean isWall(int x, int y) {
        Rectangle pacmanRect = new Rectangle(x, y, UNIT_SIZE, UNIT_SIZE);
        for (Rectangle wall : walls) {
            if (pacmanRect.intersects(wall)) {
                return true;
            }
        }
        return false;
    }

    public void checkFood() {
        Rectangle pacmanRect = new Rectangle(pacmanX, pacmanY, UNIT_SIZE, UNIT_SIZE);

        // Verificar puntos normales
        for (int i = 0; i < dots.size(); i++) {
            Point dot = dots.get(i);
            Rectangle dotRect = new Rectangle(dot.x, dot.y, UNIT_SIZE, UNIT_SIZE);
            if (pacmanRect.intersects(dotRect)) {
                dots.remove(i);
                score += 10;
                break;
            }
        }

        // Verificar power pellets
        for (int i = 0; i < powerPellets.size(); i++) {
            Point powerPellet = powerPellets.get(i);
            Rectangle pelletRect = new Rectangle(powerPellet.x, powerPellet.y, UNIT_SIZE, UNIT_SIZE);
            if (pacmanRect.intersects(pelletRect)) {
                powerPellets.remove(i);
                score += 50;
                activatePowerMode();
                break;
            }
        }

        // Verificar si ganó
        if (dots.isEmpty() && powerPellets.isEmpty()) {
            // ¡Victoria! El juego continúa mostrando el mensaje
        }
    }

    private void activatePowerMode() {
        ghostsVulnerable = true;
        vulnerabilityTimeLeft = 10; // 10 segundos

        // Hacer vulnerables a todos los fantasmas
        for (Ghost ghost : ghosts) {
            if (!ghost.eaten) {
                ghost.makeVulnerable();
            }
        }

        // Timer para contar hacia atrás
        if (vulnerabilityTimer != null) {
            vulnerabilityTimer.stop();
        }

        vulnerabilityTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vulnerabilityTimeLeft--;
                if (vulnerabilityTimeLeft <= 0) {
                    deactivatePowerMode();
                    vulnerabilityTimer.stop();
                }
            }
        });
        vulnerabilityTimer.start();
    }

    private void deactivatePowerMode() {
        ghostsVulnerable = false;
        vulnerabilityTimeLeft = 0;

        // Restaurar fantasmas normales
        for (Ghost ghost : ghosts) {
            if (!ghost.eaten) {
                ghost.makeNormal();
            }
        }
    }

    public void checkCollisions() {
        Rectangle pacmanRect = new Rectangle(pacmanX, pacmanY, UNIT_SIZE, UNIT_SIZE);

        for (Ghost ghost : ghosts) {
            if (ghost.eaten) continue;

            Rectangle ghostRect = new Rectangle(ghost.x, ghost.y, UNIT_SIZE, UNIT_SIZE);
            if (pacmanRect.intersects(ghostRect)) {
                if (ghostsVulnerable) {
                    // Comer fantasma
                    ghost.makeEaten();
                    score += 200;
                } else {
                    // Game Over
                    running = false;
                    if (vulnerabilityTimer != null) {
                        vulnerabilityTimer.stop();
                    }
                }
            }
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (BOARD_WIDTH - metrics1.stringWidth("GAME OVER")) / 2,
                BOARD_HEIGHT / 2 - 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (BOARD_WIDTH - metrics2.stringWidth("Score: " + score)) / 2,
                BOARD_HEIGHT / 2);

        g.setFont(new Font("Arial", Font.BOLD, 18));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press SPACE to restart", (BOARD_WIDTH - metrics3.stringWidth("Press SPACE to restart")) / 2,
                BOARD_HEIGHT / 2 + 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkCollisions();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') direction = 'L';
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') direction = 'R';
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') direction = 'U';
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') direction = 'D';
                break;
            case KeyEvent.VK_SPACE:
                if (!running) {
                    if (vulnerabilityTimer != null) {
                        vulnerabilityTimer.stop();
                    }
                    initializeGame();
                    startGame();
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pacman Game - Enhanced");
        PacmanGame game = new PacmanGame();

        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT + 40);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}