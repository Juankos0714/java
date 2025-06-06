//package game2;
//
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.*;
//import java.util.List;
//import javax.sound.sampled.*;
//import javax.swing.Timer;
//
//// Clase principal del juego
//public class RuneWizardGame2 extends JPanel implements KeyListener, ActionListener {
//    private static final int WINDOW_WIDTH = 900;
//    private static final int WINDOW_HEIGHT = 700;
//    private static final int ROOM_WIDTH = 17;
//    private static final int ROOM_HEIGHT = 13;
//    private static final int TILE_SIZE = 40;
//
//    private Player player;
//    private DungeonMap dungeonMap;
//    private Room currentRoom;
//    private List<Enemy> enemies;
//    private List<Rune> runes;
//    private List<Projectile> projectiles;
//    private List<VisualEffect> effects;
//    private Timer gameTimer;
//    private Random random;
//    private boolean[] keys;
//    private int currentFloor;
//    private int score;
//    private GameState gameState;
//    private SoundManager soundManager;
//    private boolean roomTransitioning;
//
//    public RuneWizardGame2() {
//        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
//        setBackground(Color.BLACK);
//        setFocusable(true);
//        addKeyListener(this);
//
//        random = new Random();
//        keys = new boolean[256];
//        currentFloor = 1;
//        score = 0;
//        gameState = GameState.PLAYING;
//        soundManager = new SoundManager();
//        roomTransitioning = false;
//
//        initializeGame();
//
//        gameTimer = new Timer(16, this); // ~60 FPS
//        gameTimer.start();
//    }
//
//    private void initializeGame() {
//        player = new Player(ROOM_WIDTH / 2 * TILE_SIZE, ROOM_HEIGHT / 2 * TILE_SIZE);
//        dungeonMap = new DungeonMap(currentFloor);
//        currentRoom = dungeonMap.getCurrentRoom();
//        enemies = new ArrayList<>();
//        runes = new ArrayList<>();
//        projectiles = new ArrayList<>();
//        effects = new ArrayList<>();
//
//        loadCurrentRoom();
//    }
//
//    private void loadCurrentRoom() {
//        if (roomTransitioning) return;
//
//        enemies.clear();
//        runes.clear();
//        projectiles.clear();
//        effects.clear();
//
//        currentRoom = dungeonMap.getCurrentRoom();
//
//        // Solo generar contenido si la sala no ha sido visitada
//        if (!currentRoom.isCleared()) {
//            generateRoomContent();
//        }
//    }
//
//    private void generateRoomContent() {
//        // Determinar si es habitación de jefe
//        boolean isBossRoom = currentRoom.getType() == RoomType.BOSS;
//
//        if (isBossRoom) {
//            // Generar jefe
//            Enemy boss = new Enemy(ROOM_WIDTH / 2 * TILE_SIZE, 3 * TILE_SIZE, EnemyType.BOSS, true);
//            enemies.add(boss);
//            soundManager.playSound("boss");
//        } else {
//            // Generar enemigos normales según el tipo de habitación
//            int baseEnemies = currentRoom.getType() == RoomType.TREASURE ? 1 :
//                    currentRoom.getType() == RoomType.ELITE ? 4 : 3;
//            int enemyCount = Math.min(8, baseEnemies + currentFloor / 2 + random.nextInt(3));
//
//            for (int i = 0; i < enemyCount; i++) {
//                int x, y;
//                do {
//                    x = (2 + random.nextInt(ROOM_WIDTH - 4)) * TILE_SIZE;
//                    y = (2 + random.nextInt(ROOM_HEIGHT - 4)) * TILE_SIZE;
//                } while (Math.abs(x - player.getBounds().x) < 120 && Math.abs(y - player.getBounds().y) < 120);
//
//                EnemyType type = currentRoom.getType() == RoomType.ELITE ? getEliteEnemyType() : getRandomEnemyType();
//                enemies.add(new Enemy(x, y, type, false));
//            }
//        }
//
//        // Generar runas según el tipo de habitación
//        int runeCount = switch (currentRoom.getType()) {
//            case TREASURE -> 4 + random.nextInt(3);
//            case BOSS -> 2 + random.nextInt(2);
//            case ELITE -> 3 + random.nextInt(2);
//            default -> 1 + random.nextInt(3);
//        };
//
//        for (int i = 0; i < runeCount; i++) {
//            int x, y;
//            do {
//                x = (2 + random.nextInt(ROOM_WIDTH - 4)) * TILE_SIZE;
//                y = (2 + random.nextInt(ROOM_HEIGHT - 4)) * TILE_SIZE;
//            } while (isPositionOccupied(x, y));
//
//            RuneType type = currentRoom.getType() == RoomType.TREASURE ? getRandomRareRune() :
//                    currentRoom.getType() == RoomType.BOSS ? getRandomRareRune() :
//                            getRandomRune();
//            runes.add(new Rune(x, y, type));
//        }
//    }
//
//    private boolean isPositionOccupied(int x, int y) {
//        Rectangle testRect = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
//
//        // Verificar con enemigos
//        for (Enemy enemy : enemies) {
//            if (enemy.getBounds().intersects(testRect)) return true;
//        }
//
//        // Verificar con runas existentes
//        for (Rune rune : runes) {
//            if (rune.getBounds().intersects(testRect)) return true;
//        }
//
//        // Verificar con jugador
//        if (player.getBounds().intersects(testRect)) return true;
//
//        return false;
//    }
//
//    private EnemyType getRandomEnemyType() {
//        EnemyType[] normalTypes = {EnemyType.GOBLIN, EnemyType.ORC, EnemyType.SKELETON, EnemyType.DEMON};
//        return normalTypes[random.nextInt(normalTypes.length)];
//    }
//
//    private EnemyType getEliteEnemyType() {
//        EnemyType[] eliteTypes = {EnemyType.WRAITH, EnemyType.GOLEM, EnemyType.NECROMANCER};
//        return eliteTypes[random.nextInt(eliteTypes.length)];
//    }
//
//    private RuneType getRandomRune() {
//        // Runas comunes más probables
//        if (random.nextFloat() < 0.7f) {
//            RuneType[] common = {RuneType.FIRE, RuneType.ICE, RuneType.LIGHTNING, RuneType.EARTH, RuneType.ARCANE};
//            return common[random.nextInt(common.length)];
//        } else {
//            return getRandomRareRune();
//        }
//    }
//
//    private RuneType getRandomRareRune() {
//        RuneType[] rare = {RuneType.SHADOW, RuneType.LIGHT, RuneType.BLOOD, RuneType.VOID, RuneType.TIME};
//        return rare[random.nextInt(rare.length)];
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        if (gameState == GameState.PLAYING) {
//            // Dibujar habitación
//            currentRoom.draw(g2d);
//
//            // Dibujar puertas
//            drawDoors(g2d);
//
//            // Dibujar runas
//            for (Rune rune : runes) {
//                rune.draw(g2d);
//            }
//
//            // Dibujar enemigos
//            for (Enemy enemy : enemies) {
//                enemy.draw(g2d);
//            }
//
//            // Dibujar proyectiles
//            for (Projectile proj : projectiles) {
//                proj.draw(g2d);
//            }
//
//            // Dibujar efectos visuales
//            for (VisualEffect effect : effects) {
//                effect.draw(g2d);
//            }
//
//            // Dibujar jugador
//            player.draw(g2d);
//
//            // Dibujar UI
//            drawUI(g2d);
//
//            // Dibujar minimapa
//            drawMinimap(g2d);
//        } else if (gameState == GameState.GAME_OVER) {
//            drawGameOver(g2d);
//        }
//    }
//
//    private void drawDoors(Graphics2D g) {
//        // Puerta Norte
//        if (currentRoom.hasExit(Direction.NORTH)) {
//            g.setColor(enemies.isEmpty() ? Color.GREEN : Color.RED);
//            g.fillRect(ROOM_WIDTH / 2 * TILE_SIZE - 20, 40, 40, 20);
//            g.setColor(Color.WHITE);
//            g.drawRect(ROOM_WIDTH / 2 * TILE_SIZE - 20, 40, 40, 20);
//        }
//
//        // Puerta Sur
//        if (currentRoom.hasExit(Direction.SOUTH)) {
//            g.setColor(enemies.isEmpty() ? Color.GREEN : Color.RED);
//            g.fillRect(ROOM_WIDTH / 2 * TILE_SIZE - 20, (ROOM_HEIGHT + 1) * TILE_SIZE - 20, 40, 20);
//            g.setColor(Color.WHITE);
//            g.drawRect(ROOM_WIDTH / 2 * TILE_SIZE - 20, (ROOM_HEIGHT + 1) * TILE_SIZE - 20, 40, 20);
//        }
//
//        // Puerta Este
//        if (currentRoom.hasExit(Direction.EAST)) {
//            g.setColor(enemies.isEmpty() ? Color.GREEN : Color.RED);
//            g.fillRect((ROOM_WIDTH + 1) * TILE_SIZE - 20, ROOM_HEIGHT / 2 * TILE_SIZE - 20, 20, 40);
//            g.setColor(Color.WHITE);
//            g.drawRect((ROOM_WIDTH + 1) * TILE_SIZE - 20, ROOM_HEIGHT / 2 * TILE_SIZE - 20, 20, 40);
//        }
//
//        // Puerta Oeste
//        if (currentRoom.hasExit(Direction.WEST)) {
//            g.setColor(enemies.isEmpty() ? Color.GREEN : Color.RED);
//            g.fillRect(40, ROOM_HEIGHT / 2 * TILE_SIZE - 20, 20, 40);
//            g.setColor(Color.WHITE);
//            g.drawRect(40, ROOM_HEIGHT / 2 * TILE_SIZE - 20, 20, 40);
//        }
//    }
//
//    private void drawMinimap(Graphics2D g) {
//        int mapSize = 150;
//        int mapX = WINDOW_WIDTH - mapSize - 20;
//        int mapY = 20;
//
//        // Fondo del minimapa
//        g.setColor(new Color(0, 0, 0, 180));
//        g.fillRect(mapX, mapY, mapSize, mapSize);
//        g.setColor(Color.WHITE);
//        g.drawRect(mapX, mapY, mapSize, mapSize);
//
//        // Dibujar salas visitadas
//        dungeonMap.drawMinimap(g, mapX, mapY, mapSize);
//    }
//
//    private void drawUI(Graphics2D g) {
//        // Panel de información principal
//        g.setColor(new Color(0, 0, 0, 150));
//        g.fillRect(10, 10, 300, 220);
//        g.setColor(Color.WHITE);
//        g.drawRect(10, 10, 300, 220);
//
//        g.setFont(new Font("Arial", Font.BOLD, 16));
//        g.drawString("HP: " + player.getHealth() + "/" + player.getMaxHealth(), 20, 35);
//        g.drawString("Mana: " + player.getMana() + "/" + player.getMaxMana(), 20, 55);
//        g.drawString("Floor: " + currentFloor, 20, 75);
//        g.drawString("Room: " + currentRoom.getType().getName(), 20, 95);
//        g.drawString("Score: " + score, 20, 115);
//
//        // Mostrar runas recolectadas
//        g.setFont(new Font("Arial", Font.BOLD, 12));
//        g.drawString("Runas Recolectadas:", 20, 140);
//        int y = 155;
//        for (RuneType runeType : player.getCollectedRunes().keySet()) {
//            int count = player.getCollectedRunes().get(runeType);
//            g.setColor(runeType.getColor());
//            g.drawString(runeType.getName() + ": " + count, 20, y);
//            y += 15;
//        }
//
//        // Mostrar combinaciones activas
//        g.setColor(Color.YELLOW);
//        g.setFont(new Font("Arial", Font.BOLD, 14));
//        g.drawString("Combinaciones Activas:", 20, y + 10);
//        y += 25;
//        g.setFont(new Font("Arial", Font.PLAIN, 11));
//        for (RuneCombination combo : player.getActiveCombinations()) {
//            g.drawString("• " + combo.getName(), 20, y);
//            y += 12;
//        }
//
//        // Instrucciones de movimiento
//        g.setColor(Color.CYAN);
//        g.setFont(new Font("Arial", Font.PLAIN, 12));
//        g.drawString("WASD: Mover | SPACE: Disparar", 20, WINDOW_HEIGHT - 60);
//        g.drawString("Mata todos los enemigos para abrir puertas", 20, WINDOW_HEIGHT - 45);
//        g.drawString("Camina hacia las puertas para cambiar de sala", 20, WINDOW_HEIGHT - 30);
//    }
//
//    private void drawGameOver(Graphics2D g) {
//        g.setColor(new Color(0, 0, 0, 200));
//        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
//
//        g.setColor(Color.RED);
//        g.setFont(new Font("Arial", Font.BOLD, 48));
//        String gameOverText = "GAME OVER";
//        FontMetrics fm = g.getFontMetrics();
//        int x = (WINDOW_WIDTH - fm.stringWidth(gameOverText)) / 2;
//        g.drawString(gameOverText, x, WINDOW_HEIGHT / 2 - 50);
//
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Arial", Font.BOLD, 24));
//        String scoreText = "Puntuación Final: " + score;
//        fm = g.getFontMetrics();
//        x = (WINDOW_WIDTH - fm.stringWidth(scoreText)) / 2;
//        g.drawString(scoreText, x, WINDOW_HEIGHT / 2);
//
//        String floorText = "Llegaste al Piso: " + currentFloor;
//        x = (WINDOW_WIDTH - fm.stringWidth(floorText)) / 2;
//        g.drawString(floorText, x, WINDOW_HEIGHT / 2 + 30);
//
//        g.setFont(new Font("Arial", Font.PLAIN, 16));
//        String restartText = "Presiona R para reiniciar";
//        fm = g.getFontMetrics();
//        x = (WINDOW_WIDTH - fm.stringWidth(restartText)) / 2;
//        g.drawString(restartText, x, WINDOW_HEIGHT / 2 + 80);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (gameState == GameState.PLAYING) {
//            update();
//        }
//        repaint();
//    }
//
//    private void update() {
//        // Actualizar jugador
//        player.update(keys);
//
//        // Verificar transiciones de sala
//        checkRoomTransitions();
//
//        // Actualizar enemigos
//        for (Enemy enemy : enemies) {
//            enemy.update(player);
//        }
//
//        // Actualizar proyectiles
//        projectiles.removeIf(proj -> !proj.update());
//
//        // Actualizar efectos visuales
//        effects.removeIf(effect -> !effect.update());
//
//        // Verificar colisiones
//        checkCollisions();
//
//        // Disparar proyectiles
//        if (keys[KeyEvent.VK_SPACE] && player.canShoot()) {
//            Projectile proj = player.shoot();
//            if (proj != null) {
//                projectiles.add(proj);
//                soundManager.playSound("shoot");
//            }
//        }
//
//        // Marcar sala como limpia si no hay enemigos
//        if (enemies.isEmpty() && !currentRoom.isCleared()) {
//            currentRoom.setCleared(true);
//            score += 50; // Bonus por limpiar sala
//        }
//
//        // Verificar game over
//        if (player.getHealth() <= 0) {
//            gameState = GameState.GAME_OVER;
//            soundManager.playSound("gameover");
//        }
//    }
//
//    private void checkRoomTransitions() {
//        if (roomTransitioning || !enemies.isEmpty()) return;
//
//        Rectangle playerBounds = player.getBounds();
//
//        // Verificar puerta Norte
//        if (currentRoom.hasExit(Direction.NORTH) &&
//                playerBounds.intersects(new Rectangle(ROOM_WIDTH / 2 * TILE_SIZE - 20, 30, 40, 30))) {
//            transitionToRoom(Direction.NORTH);
//        }
//        // Verificar puerta Sur
//        else if (currentRoom.hasExit(Direction.SOUTH) &&
//                playerBounds.intersects(new Rectangle(ROOM_WIDTH / 2 * TILE_SIZE - 20, (ROOM_HEIGHT + 1) * TILE_SIZE - 30, 40, 30))) {
//            transitionToRoom(Direction.SOUTH);
//        }
//        // Verificar puerta Este
//        else if (currentRoom.hasExit(Direction.EAST) &&
//                playerBounds.intersects(new Rectangle((ROOM_WIDTH + 1) * TILE_SIZE - 30, ROOM_HEIGHT / 2 * TILE_SIZE - 20, 30, 40))) {
//            transitionToRoom(Direction.EAST);
//        }
//        // Verificar puerta Oeste
//        else if (currentRoom.hasExit(Direction.WEST) &&
//                playerBounds.intersects(new Rectangle(30, ROOM_HEIGHT / 2 * TILE_SIZE - 20, 30, 40))) {
//            transitionToRoom(Direction.WEST);
//        }
//    }
//
//    private void transitionToRoom(Direction direction) {
//        roomTransitioning = true;
//
//        if (dungeonMap.moveToRoom(direction)) {
//            // Posicionar jugador en el lado opuesto de la nueva sala
//            switch (direction) {
//                case NORTH -> player.setPosition(ROOM_WIDTH / 2 * TILE_SIZE, (ROOM_HEIGHT) * TILE_SIZE - 60);
//                case SOUTH -> player.setPosition(ROOM_WIDTH / 2 * TILE_SIZE, 60);
//                case EAST -> player.setPosition(60, ROOM_HEIGHT / 2 * TILE_SIZE);
//                case WEST -> player.setPosition((ROOM_WIDTH - 1) * TILE_SIZE - 40, ROOM_HEIGHT / 2 * TILE_SIZE);
//            }
//
//            loadCurrentRoom();
//            soundManager.playSound("room_enter");
//
//            // Verificar si hemos completado el piso
//            if (dungeonMap.isFloorComplete()) {
//                nextFloor();
//            }
//        }
//
//        roomTransitioning = false;
//    }
//
//    private void checkCollisions() {
//        // Colisiones jugador-runas
//        runes.removeIf(rune -> {
//            if (player.getBounds().intersects(rune.getBounds())) {
//                player.collectRune(rune);
//                score += rune.getType().getScoreValue();
//                effects.add(new VisualEffect(rune.getBounds().x, rune.getBounds().y,
//                        VisualEffect.Type.RUNE_COLLECT, rune.getType().getColor()));
//                soundManager.playSound("collect");
//                return true;
//            }
//            return false;
//        });
//
//        // Colisiones proyectiles-enemigos
//        Iterator<Projectile> projIter = projectiles.iterator();
//        while (projIter.hasNext()) {
//            Projectile proj = projIter.next();
//            Iterator<Enemy> enemyIter = enemies.iterator();
//            while (enemyIter.hasNext()) {
//                Enemy enemy = enemyIter.next();
//                if (proj.getBounds().intersects(enemy.getBounds())) {
//                    int damage = proj.getDamage();
//                    enemy.takeDamage(damage);
//
//                    // Efectos especiales según el tipo de proyectil
//                    effects.add(new VisualEffect(enemy.getBounds().x, enemy.getBounds().y,
//                            VisualEffect.Type.HIT, proj.getColor()));
//
//                    projIter.remove();
//                    soundManager.playSound("hit");
//
//                    if (enemy.isDead()) {
//                        score += enemy.getScoreValue();
//                        effects.add(new VisualEffect(enemy.getBounds().x, enemy.getBounds().y,
//                                VisualEffect.Type.ENEMY_DEATH, Color.RED));
//                        enemyIter.remove();
//                        soundManager.playSound("kill");
//                    }
//                    break;
//                }
//            }
//        }
//
//        // Colisiones jugador-enemigos
//        for (Enemy enemy : enemies) {
//            if (player.getBounds().intersects(enemy.getBounds()) && player.canTakeDamage()) {
//                player.takeDamage(enemy.getDamage());
//                effects.add(new VisualEffect(player.getBounds().x, player.getBounds().y,
//                        VisualEffect.Type.PLAYER_HIT, Color.RED));
//                soundManager.playSound("damage");
//            }
//        }
//    }
//
//    private void nextFloor() {
//        currentFloor++;
//        score += 500 * currentFloor; // Bonus grande por completar piso
//        dungeonMap = new DungeonMap(currentFloor);
//        currentRoom = dungeonMap.getCurrentRoom();
//
//        // Posicionar jugador en el centro de la primera sala del nuevo piso
//        player.setPosition(ROOM_WIDTH / 2 * TILE_SIZE, ROOM_HEIGHT / 2 * TILE_SIZE);
//        player.healSlightly(); // Curar al avanzar de piso
//
//        loadCurrentRoom();
//        soundManager.playSound("levelup");
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        keys[e.getKeyCode()] = true;
//
//        if (gameState == GameState.GAME_OVER && e.getKeyCode() == KeyEvent.VK_R) {
//            // Reiniciar juego
//            currentFloor = 1;
//            score = 0;
//            gameState = GameState.PLAYING;
//            roomTransitioning = false;
//            initializeGame();
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        keys[e.getKeyCode()] = false;
//    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {}
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Rune Wizard - Dungeon Explorer");
//        RuneWizardGame game = new RuneWizardGame();
//
//        frame.add(game);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//}
//
//// Estados del juego
//enum GameState {
//    PLAYING, GAME_OVER
//}
//
//// Direcciones para el mapa
//enum Direction {
//    NORTH, SOUTH, EAST, WEST
//}
//
//// Tipos de salas
//enum RoomType {
//    NORMAL("Normal", Color.GRAY),
//    TREASURE("Tesoro", Color.YELLOW),
//    ELITE("Elite", Color.ORANGE),
//    BOSS("Jefe", Color.RED),
//    START("Inicio", Color.GREEN);
//
//    private final String name;
//    private final Color color;
//
//    RoomType(String name, Color color) {
//        this.name = name;
//        this.color = color;
//    }
//
//    public String getName() { return name; }
//    public Color getColor() { return color; }
//}
//
//// Clase del mapa de mazmorras
//class DungeonMap {
//    private static final int MAP_SIZE = 7; // 7x7 grid de salas
//    private Room[][] rooms;
//    private int currentX, currentY;
//    private int floor;
//    private Random random;
//    private int roomsCleared;
//    private int totalRooms;
//
//    public DungeonMap(int floor) {
//        this.floor = floor;
//        this.random = new Random();
//        this.rooms = new Room[MAP_SIZE][MAP_SIZE];
//        this.currentX = MAP_SIZE / 2;
//        this.currentY = MAP_SIZE / 2;
//        this.roomsCleared = 0;
//
//        generateDungeon();
//    }
//
//    private void generateDungeon() {
//        // Crear sala inicial
//        rooms[currentX][currentY] = new Room(RoomType.START, floor);
//
//        // Generar salas conectadas usando algoritmo de crecimiento aleatorio
//        List<Point> availablePositions = new ArrayList<>();
//        availablePositions.add(new Point(currentX, currentY));
//
//        int roomsToGenerate = 8 + floor; // Más salas en pisos superiores
//        totalRooms = roomsToGenerate;
//
//        while (roomsToGenerate > 0 && !availablePositions.isEmpty()) {
//            Point current = availablePositions.get(random.nextInt(availablePositions.size()));
//
//            // Intentar generar sala en una dirección aleatoria
//            Direction[] directions = Direction.values();
//            Collections.shuffle(Arrays.asList(directions), random);
//
//            for (Direction dir : directions) {
//                Point newPos = getPositionInDirection(current, dir);
//
//                if (isValidPosition(newPos) && rooms[newPos.x][newPos.y] == null) {
//                    // Determinar tipo de sala
//                    RoomType type = determineRoomType(roomsToGenerate);
//                    rooms[newPos.x][newPos.y] = new Room(type, floor);
//
//                    // Crear conexiones bidireccionales
//                    rooms[current.x][current.y].addExit(dir);
//                    rooms[newPos.x][newPos.y].addExit(getOppositeDirection(dir));
//
//                    availablePositions.add(newPos);
//                    roomsToGenerate--;
//                    break;
//                }
//            }
//
//            // Si no se pudo expandir desde esta posición, removerla
//            if (roomsToGenerate > 0) {
//                boolean canExpand = false;
//                for (Direction dir : directions) {
//                    Point newPos = getPositionInDirection(current, dir);
//                    if (isValidPosition(newPos) && rooms[newPos.x][newPos.y] == null) {
//                        canExpand = true;
//                        break;
//                    }
//                }
//                if (!canExpand) {
//                    availablePositions.remove(current);
//                }
//            }
//        }
//
//        // Asegurar que hay al menos una sala jefe
//        addBossRoom();
//    }
//
//    private RoomType determineRoomType(int roomsLeft) {
//        if (roomsLeft == 1) return RoomType.BOSS; // Última sala siempre es jefe
//
//        float rand = random.nextFloat();
//        if (rand < 0.15f) return RoomType.TREASURE;
//        if (rand < 0.25f) return RoomType.ELITE;
//        return RoomType.NORMAL;
//    }
//
//    private void addBossRoom() {
//        // Buscar una sala normal en la periferia y convertirla en sala jefe
//        for (int x = 0; x < MAP_SIZE; x++) {
//            for (int y = 0; y < MAP_SIZE; y++) {
//                if (rooms[x][y] != null && rooms[x][y].getType() == RoomType.NORMAL) {
//                    // Verificar si está en la periferia (tiene menos de 3 conexiones)
//                    int connections = 0;
//                    for (Direction dir : Direction.values()) {
//                        if (rooms[x][y].hasExit(dir)) connections++;
//                    }
//                    if (connections <= 2) {
//                        rooms[x][y] = new Room(RoomType.BOSS, floor);
//                        // Mantener las conexiones existentes
//                        for (Direction dir : Direction.values()) {
//                            Point neighbor = getPositionInDirection(new Point(x, y), dir);
//                            if (isValidPosition(neighbor) && rooms[neighbor.x][neighbor.y] != null) {
//                                rooms[x][y].addExit(dir);
//                            }
//                        }
//                        return;
//                    }
//                }
//            }
//        }
//    }
//
//    private Point getPositionInDirection(Point current, Direction dir) {
//        return switch (dir) {
//            case NORTH -> new Point(current.x, current.y - 1);
//            case SOUTH -> new Point(current.x, current.y + 1);
//            case EAST -> new Point(current.x + 1, current.y);
//            case WEST -> new Point(current.x - 1, current.y);
//        };
//    }
//
//    private Direction getOppositeDirection(Direction dir) {
//        return switch (dir) {
//            case NORTH -> Direction.SOUTH;
//            case SOUTH -> Direction.NORTH;
//            case EAST -> Direction.WEST;
//            case WEST -> Direction.EAST;
//        };
//    }
//
//    private boolean isValidPosition(Point pos) {
//        return pos.x >= 0 && pos.x < MAP_SIZE && pos.y >= 0 && pos.y < MAP_SIZE;
//    }
//
//    public boolean moveToRoom(Direction direction) {
//        Point newPos = getPositionInDirection(new Point(currentX, currentY), direction);
//
//        if (isValidPosition(newPos) && rooms[newPos.x][newPos.y] != null) {
//            currentX = newPos.x;
//            currentY = newPos.y;
//            return true;
//        }
//        return false;
//    }
//
//    public Room getCurrentRoom() {
//        return rooms[currentX][currentY];
//    }
//
////    public boolean isFloorComplete() {
////        // Contar salas limpiadas
////        int cleared = 0;
////        int mapSize = MAP_SIZE;
////        for (int x = 0; x < mapSize; x++) {
////            for (int y< mapSize y++) {
////                if (rooms[x][y] != null && rooms[x][y].isCleared()) {
////                    cleared++;
////                }
////            }
//////        }
////
////        // Piso completado si todas las salas están limpiadas
////        return cleared >= totalRooms;
////    }
//
//    public void drawMinimap(Graphics2D g, int mapX, int mapY, int mapSize) {
//        int cellSize = mapSize / MAP_SIZE;
//
//        for (int x = 0; x < MAP_SIZE; x++) {
//            for (int y = 0; y < MAP_SIZE; y++) {
//                if (rooms[x][y] != null) {
//                    int drawX = mapX + x * cellSize;
//                    int drawY = mapY + y * cellSize;
//
//                    // Color según el tipo de sala
//                    g.setColor(rooms[x][y].getType().getColor());
//                    if (rooms[x][y].isCleared()) {
//                        g.fillRect(drawX, drawY, cellSize, cellSize);
//                    } else {
//                        g.drawRect(drawX, drawY, cellSize, cellSize);
//                    }
//
//                    // Marcar sala actual
//                    if (x == currentX && y == currentY) {
//                        g.setColor(Color.WHITE);
//                        g.fillOval(drawX + cellSize/4, drawY + cellSize/4,
//                                cellSize/2, cellSize/2);
//                    }
//
//                    // Dibujar conexiones
//                    g.setColor(Color.LIGHT_GRAY);
//                    if (rooms[x][y].hasExit(Direction.NORTH) && y > 0) {
//                        g.drawLine(drawX + cellSize/2, drawY,
//                                drawX + cellSize/2, drawY - cellSize/4);
//                    }
//                    if (rooms[x][y].hasExit(Direction.SOUTH) && y < MAP_SIZE-1) {
//                        g.drawLine(drawX + cellSize/2, drawY + cellSize,
//                                drawX + cellSize/2, drawY + cellSize + cellSize/4);
//                    }
//                    if (rooms[x][y].hasExit(Direction.EAST) && x < MAP_SIZE-1) {
//                        g.drawLine(drawX + cellSize, drawY + cellSize/2,
//                                drawX + cellSize + cellSize/4, drawY + cellSize/2);
//                    }
//                    if (rooms[x][y].hasExit(Direction.WEST) && x > 0) {
//                        g.drawLine(drawX, drawY + cellSize/2,
//                                drawX - cellSize/4, drawY + cellSize/2);
//                    }
//                }
//            }
//        }
//    }
//}
//
//// Clase Room
//class Room {
//    private RoomType type;
//    private boolean cleared;
//    private Set<Direction> exits;
//    private int difficulty;
//
//    public Room(RoomType type, int floor) {
//        this.type = type;
//        this.cleared = false;
//        this.exits = new HashSet<>();
//        this.difficulty = floor;
//    }
//
//    public void addExit(Direction direction) {
//        exits.add(direction);
//    }
//
//    public boolean hasExit(Direction direction) {
//        return exits.contains(direction);
//    }
//
//    public RoomType getType() { return type; }
//    public boolean isCleared() { return cleared; }
//    public void setCleared(boolean cleared) { this.cleared = cleared; }
//    public int getDifficulty() { return difficulty; }
////
////    public void draw(Graphics2D g) {
////        // Dibujar fondo de la habitación
////        g.setColor(new Color(40, 40, 40));
////        g.fillRect(40, 40, (RuneWizardGame.ROOM_WIDTH) * 40, (RuneWizardGame.ROOM_HEIGHT) * 40);
////
////        // Dibujar bordes
////        g.setColor(Color.WHITE);
////        g.drawRect(40, 40, (RuneWizardGame.ROOM_WIDTH) * 40, (RuneWizardGame.ROOM_HEIGHT) * 40);
////
////        // Dibujar patrón del suelo según el tipo de habitación
////        g.setColor(new Color(type.getColor().getRed(), type.getColor().getGreen(),
////                type.getColor().getBlue(), 30));
////        for (int x = 1; x < RuneWizardGame.ROOM_WIDTH; x++) {
////            for (int y = 1; y < RuneWizardGame.ROOM_HEIGHT; y++) {
////                if ((x + y) % 2 == 0) {
////                    g.fillRect(40 + x * 40, 40 + y * 40, 40, 40);
////                }
////            }
////        }
////    }
////}
//
//// Clase Player
//class Player {
//    private int x, y;
//    private int health, maxHealth;
//    private int mana, maxMana;
//    private int speed;
//    private Map<RuneType, Integer> collectedRunes;
//    private List<RuneCombination> activeCombinations;
//    private long lastShootTime;
//    private long lastDamageTime;
//    private int shootCooldown;
//    private int invulnerabilityTime;
//
//    public Player(int x, int y) {
//        this.x = x;
//        this.y = y;
//        this.maxHealth = 100;
//        this.health = maxHealth;
//        this.maxMana = 50;
//        this.mana = maxMana;
//        this.speed = 4;
//        this.collectedRunes = new HashMap<>();
//        this.activeCombinations = new ArrayList<>();
//        this.shootCooldown = 300; // milliseconds
//        this.invulnerabilityTime = 1000; // 1 second
//        this.lastShootTime = 0;
//        this.lastDamageTime = 0;
//    }
//
//    public void update(boolean[] keys) {
//        // Movimiento
//        int newX = x, newY = y;
//
//        if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) newY -= speed;
//        if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) newY += speed;
//        if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) newX -= speed;
//        if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) newX += speed;
//
//        // Verificar límites de la habitación
//        if (newX >= 50 && newX <= (RuneWizardGame.ROOM_WIDTH) * 40 - 30) {
//            x = newX;
//        }
//        if (newY >= 50 && newY <= (RuneWizardGame.ROOM_HEIGHT) * 40 - 30) {
//            y = newY;
//        }
//
//        // Regenerar mana lentamente
//        if (mana < maxMana && System.currentTimeMillis() % 100 == 0) {
//            mana = Math.min(maxMana, mana + 1);
//        }
//
//        // Actualizar combinaciones activas
//        updateActiveCombinations();
//    }
//
//    public void draw(Graphics2D g) {
//        // Efecto de invulnerabilidad
//        if (System.currentTimeMillis() - lastDamageTime < invulnerabilityTime) {
//            if ((System.currentTimeMillis() / 100) % 2 == 0) {
//                g.setColor(new Color(255, 255, 255, 128));
//            } else {
//                g.setColor(Color.BLUE);
//            }
//        } else {
//            g.setColor(Color.BLUE);
//        }
//
//        // Dibujar jugador
//        g.fillRect(x, y, 30, 30);
//        g.setColor(Color.WHITE);
//        g.drawRect(x, y, 30, 30);
//
//        // Dibujar barra de vida
//        g.setColor(Color.RED);
//        g.fillRect(x, y - 10, 30, 5);
//        g.setColor(Color.GREEN);
//        g.fillRect(x, y - 10, (int)(30 * (health / (float)maxHealth)), 5);
//
//        // Dibujar barra de mana
//        g.setColor(Color.DARK_BLUE);
//        g.fillRect(x, y - 5, 30, 3);
//        g.setColor(Color.CYAN);
//        g.fillRect(x, y - 5, (int)(30 * (mana / (float)maxMana)), 3);
//    }
//
//    public boolean canShoot() {
//        return System.currentTimeMillis() - lastShootTime > shootCooldown && mana >= 5;
//    }
//
//    public Projectile shoot() {
//        if (!canShoot()) return null;
//
//        lastShootTime = System.currentTimeMillis();
//        mana -= 5;
//
//        // Determinar tipo de proyectil basado en runas recolectadas
//        RuneType projectileType = determineProjectileType();
//
//        return new Projectile(x + 15, y + 15, 0, -8, projectileType);
//    }
//
//    private RuneType determineProjectileType() {
//        // Usar la runa más abundante
//        RuneType bestType = RuneType.ARCANE;
//        int maxCount = 0;
//
//        for (Map.Entry<RuneType, Integer> entry : collectedRunes.entrySet()) {
//            if (entry.getValue() > maxCount) {
//                maxCount = entry.getValue();
//                bestType = entry.getKey();
//            }
//        }
//
//        return bestType;
//    }
//
//    public void collectRune(Rune rune) {
//        RuneType type = rune.getType();
//        collectedRunes.put(type, collectedRunes.getOrDefault(type, 0) + 1);
//    }
//
//    public boolean canTakeDamage() {
//        return System.currentTimeMillis() - lastDamageTime > invulnerabilityTime;
//    }
//
//    public void takeDamage(int damage) {
//        if (!canTakeDamage()) return;
//
//        health -= damage;
//        lastDamageTime = System.currentTimeMillis();
//
//        if (health < 0) health = 0;
//    }
//
//    public void healSlightly() {
//        health = Math.min(maxHealth, health + 20);
//        mana = Math.min(maxMana, mana + 10);
//    }
//
//    private void updateActiveCombinations() {
//        activeCombinations.clear();
//
//        // Verificar combinaciones posibles
//        for (RuneCombination combo : RuneCombination.values()) {
//            if (combo.canActivate(collectedRunes)) {
//                activeCombinations.add(combo);
//            }
//        }
//    }
//
//    public void setPosition(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle(x, y, 30, 30);
//    }
//
//    // Getters
//    public int getHealth() { return health; }
//    public int getMaxHealth() { return maxHealth; }
//    public int getMana() { return mana; }
//    public int getMaxMana() { return maxMana; }
//    public Map<RuneType, Integer> getCollectedRunes() { return collectedRunes; }
//    public List<RuneCombination> getActiveCombinations() { return activeCombinations; }
//}
//
//// Enum RuneType
//enum RuneType {
//    FIRE("Fuego", Color.RED, 10, 15),
//    ICE("Hielo", Color.CYAN, 10, 12),
//    LIGHTNING("Rayo", Color.YELLOW, 10, 18),
//    EARTH("Tierra", Color.GREEN, 10, 10),
//    ARCANE("Arcano", Color.MAGENTA, 10, 14),
//    SHADOW("Sombra", Color.DARK_GRAY, 20, 20),
//    LIGHT("Luz", Color.WHITE, 20, 16),
//    BLOOD("Sangre", new Color(128, 0, 0), 20, 22),
//    VOID("Vacío", new Color(64, 0, 128), 20, 25),
//    TIME("Tiempo", new Color(255, 215, 0), 20, 30);
//
//    private final String name;
//    private final Color color;
//    private final int scoreValue;
//    private final int damage;
//
//    RuneType(String name, Color color, int scoreValue, int damage) {
//        this.name = name;
//        this.color = color;
//        this.scoreValue = scoreValue;
//        this.damage = damage;
//    }
//
//    public String getName() { return name; }
//    public Color getColor() { return color; }
//    public int getScoreValue() { return scoreValue; }
//    public int getDamage() { return damage; }
//}
//
//// Clase Rune
//class Rune {
//    private int x, y;
//    private RuneType type;
//    private float animationPhase;
//
//    public Rune(int x, int y, RuneType type) {
//        this.x = x;
//        this.y = y;
//        this.type = type;
//        this.animationPhase = 0;
//    }
//
//    public void draw(Graphics2D g) {
//        animationPhase += 0.1f;
//
//        // Efecto de flotación
//        int offsetY = (int)(Math.sin(animationPhase) * 3);
//
//        // Dibujar glow
//        g.setColor(new Color(type.getColor().getRed(), type.getColor().getGreen(),
//                type.getColor().getBlue(), 50));
//        g.fillOval(x - 5, y - 5 + offsetY, 50, 50);
//
//        // Dibujar runa
//        g.setColor(type.getColor());
//        g.fillOval(x, y + offsetY, 40, 40);
//        g.setColor(Color.WHITE);
//        g.drawOval(x, y + offsetY, 40, 40);
//
//        // Dibujar símbolo
//        g.setFont(new Font("Arial", Font.BOLD, 12));
//        FontMetrics fm = g.getFontMetrics();
//        String symbol = type.getName().substring(0, 1);
//        int textX = x + 20 - fm.stringWidth(symbol) / 2;
//        int textY = y + 20 + fm.getHeight() / 4 + offsetY;
//        g.drawString(symbol, textX, textY);
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle(x, y, 40, 40);
//    }
//
//    public RuneType getType() { return type; }
//}
//
//// Enum RuneCombination
//enum RuneCombination {
//    FIRE_ICE("Vapor", Map.of(RuneType.FIRE, 2, RuneType.ICE, 2)),
//    LIGHTNING_EARTH("Tormenta", Map.of(RuneType.LIGHTNING, 2, RuneType.EARTH, 2)),
//    SHADOW_LIGHT("Eclipse", Map.of(RuneType.SHADOW, 1, RuneType.LIGHT, 1)),
//    BLOOD_VOID("Corrupción", Map.of(RuneType.BLOOD, 1, RuneType.VOID, 1)),
//    TIME_ARCANE("Cronomanía", Map.of(RuneType.TIME, 1, RuneType.ARCANE, 3));
//
//    private final String name;
//    private final Map<RuneType, Integer> requirements;
//
//    RuneCombination(String name, Map<RuneType, Integer> requirements) {
//        this.name = name;
//        this.requirements = requirements;
//    }
//
//    public boolean canActivate(Map<RuneType, Integer> collectedRunes) {
//        for (Map.Entry<RuneType, Integer> req : requirements.entrySet()) {
//            if (collectedRunes.getOrDefault(req.getKey(), 0) < req.getValue()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public String getName() { return name; }
//    public Map<RuneType, Integer> getRequirements() { return requirements; }
//}
//
//// Clase Enemy
//class Enemy {
//    private int x, y;
//    private int health, maxHealth;
//    private int damage;
//    private int speed;
//    private EnemyType type;
//    private boolean isBoss;
//    private long lastAttackTime;
//    private int attackCooldown;
//
//    public Enemy(int x, int y, EnemyType type, boolean isBoss) {
//        this.x = x;
//        this.y = y;
//        this.type = type;
//        this.isBoss = isBoss;
//        this.maxHealth = isBoss ? type.getHealth() * 3 : type.getHealth();
//        this.health = maxHealth;
//        this.damage = isBoss ? type.getDamage() * 2 : type.getDamage();
//        this.speed = type.getSpeed();
//        this.attackCooldown = 1000;
//        this.lastAttackTime = 0;
//    }
//
//    public void update(Player player) {
//        // IA simple: moverse hacia el jugador
//        Rectangle playerBounds = player.getBounds();
//        int playerCenterX = playerBounds.x + playerBounds.width / 2;
//        int playerCenterY = playerBounds.y + playerBounds.height / 2;
//
//        int dx = playerCenterX - (x + 20);
//        int dy = playerCenterY - (y + 20);
//
//        // Normalizar movimiento
//        double distance = Math.sqrt(dx * dx + dy * dy);
//        if (distance > 0) {
//            int moveX = (int)((dx / distance) * speed);
//            int moveY = (int)((dy / distance) * speed);
//
//            x += moveX;
//            y += moveY;
//        }
//    }
//
//    public void draw(Graphics2D g) {
//        // Color basado en el tipo
//        g.setColor(type.getColor());
//        if (isBoss) {
//            // Jefe más grande
//            g.fillRect(x, y, 60, 60);
//            g.setColor(Color.WHITE);
//            g.drawRect(x, y, 60, 60);
//
//            // Barra de vida para jefe
//            g.setColor(Color.RED);
//            g.fillRect(x, y - 15, 60, 8);
//            g.setColor(Color.GREEN);
//            g.fillRect(x, y - 15, (int)(60 * (health / (float)maxHealth)), 8);
//        } else {
//            g.fillRect(x, y, 40, 40);
//            g.setColor(Color.WHITE);
//            g.drawRect(x, y, 40, 40);
//
//            // Barra de vida pequeña
//            g.setColor(Color.RED);
//            g.fillRect(x, y - 8, 40, 4);
//            g.setColor(Color.GREEN);
//            g.fillRect(x, y - 8, (int)(40 * (health / (float)maxHealth)), 4);
//        }
//    }
//
//    public void takeDamage(int damage) {
//        health -= damage;
//        if (health < 0) health = 0;
//    }
//
//    public boolean isDead() {
//        return health <= 0;
//    }
//
//    public Rectangle getBounds() {
//        return isBoss ? new Rectangle(x, y, 60, 60) : new Rectangle(x, y, 40, 40);
//    }
//
//    public int getDamage() { return damage; }
//    public int getScoreValue() { return isBoss ? type.getScoreValue() * 5 : type.getScoreValue(); }
//}
//
//// Enum EnemyType
//enum EnemyType {
//    GOBLIN("Goblin", Color.GREEN, 30, 10, 2, 50),
//    ORC("Orco", new Color(139, 69, 19), 50, 15, 1, 75),
//    SKELETON("Esqueleto", Color.WHITE, 25, 12, 3, 60),
//    DEMON("Demonio", Color.RED, 70, 20, 2, 100),
//    WRAITH("Espectro", new Color(128, 128, 128), 40, 18, 4, 120),
//    GOLEM("Golem", new Color(139, 69, 19), 100, 25, 1, 150),
//    NECROMANCER("Nigromante", new Color(75, 0, 130), 60, 30, 2, 200),
//    BOSS("Jefe", new Color(128, 0, 0), 200, 40, 2, 500);
//
//    private final String name;
//    private final Color color;
//    private final int health;
//    private final int damage;
//    private final int speed;
//    private final int scoreValue;
//
//    EnemyType(String name, Color color, int health, int damage, int speed, int scoreValue) {
//        this.name = name;
//        this.color = color;
//        this.health = health;
//        this.damage = damage;
//        this.speed = speed;
//        this.scoreValue = scoreValue;
//    }
//
//    public String getName() { return name; }
//    public Color getColor() { return color; }
//    public int getHealth() { return health; }
//    public int getDamage() { return damage; }
//    public int getSpeed() { return speed; }
//    public int getScoreValue() { return scoreValue; }
//}
//
//// Clase Projectile
//class Projectile {
//    private int x, y;
//    private int velX, velY;
//    private RuneType type;
//    private int damage;
//    private Color color;
//
//    public Projectile(int x, int y, int velX, int velY, RuneType type) {
//        this.x = x;
//        this.y = y;
//        this.velX = velX;
//        this.velY = velY;
//        this.type = type;
//        this.damage = type.getDamage();
//        this.color = type.getColor();
//    }
//
//    public boolean update() {
//        x += velX;
//        y += velY;
//
//        // Eliminar si sale de la pantalla
//        return x > 0 && x < 900 && y > 0 && y < 700;
//    }
//
//    public void draw(Graphics2D g) {
//        g.setColor(color);
//        g.fillOval(x - 5, y - 5, 10, 10);
//        g.setColor(Color.WHITE);
//        g.drawOval(x - 5, y - 5, 10, 10);
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle(x - 5, y - 5, 10, 10);
//    }
//
//    public int getDamage() { return damage; }
//    public Color getColor() { return color; }
//}
//
//// Clase VisualEffect
//class VisualEffect {
//    private int x, y;
//    private Type type;
//    private Color color;
//    private int duration;
//    private int maxDuration;
//    private float alpha;
//
//    public VisualEffect(int x, int y, Type type, Color color) {
//        this.x = x;
//        this.y = y;
//        this.type = type;
//        this.color = color;
//        this.maxDuration = switch (type) {
//            case HIT -> 20;
//            case ENEMY_DEATH -> 40;
//            case PLAYER_HIT -> 30;
//            case RUNE_COLLECT -> 25;
//        };
//        this.duration = maxDuration;
//        this.alpha = 1.0f;
//    }
//
//    public boolean update() {
//        duration--;
//        alpha = duration / (float)maxDuration;
//        return duration > 0;
//    }
//
//    public void draw(Graphics2D g) {
//        Color effectColor = new Color(color.getRed(), color.getGreen(), color.getBlue(),
//                (int)(255 * alpha));
//        g.setColor(effectColor);
//
//        switch (type) {
//            case HIT -> {
//                g.fillOval(x, y, 20, 20);
//                g.drawString("HIT!", x, y - 5);
//            }
//            case ENEMY_DEATH -> {
//                g.fillOval(x - 10, y - 10, 40, 40);
//                g.drawString("KILL!", x, y);
//            }
//            case PLAYER_HIT -> {
//                g.fillOval(x, y, 30, 30);
//            }
//            case RUNE_COLLECT -> {
//                g.fillOval(x, y, 15, 15);
//                g.drawString("+", x + 20, y + 10);
//            }
//        }
//    }
//
//    enum Type {
//        HIT, ENEMY_DEATH, PLAYER_HIT, RUNE_COLLECT
//    }
//}
//
//// Clase SoundManager
//class SoundManager {
//    private Map<String, Clip> sounds;
//
//    public SoundManager() {
//        sounds = new HashMap<>();
//        loadSounds();
//    }
//
//    private void loadSounds() {
//        // Crear sonidos sintéticos simples
//        try {
//            sounds.put("shoot", createTone(440, 100));
//            sounds.put("hit", createTone(220, 150));
//            sounds.put("kill", createTone(880, 200));
//            sounds.put("collect", createTone(660, 100));
//            sounds.put("damage", createTone(110, 300));
//            sounds.put("boss", createTone(55, 500));
//            sounds.put("levelup", createTone(1320, 400));
//            sounds.put("gameover", createTone(82, 1000));
//            sounds.put("room_enter", createTone(330, 150));
//        } catch (Exception e) {
//            System.err.println("Error loading sounds: " + e.getMessage());
//        }
//    }
//
//    private Clip createTone(float frequency, int duration) throws LineUnavailableException {
//        int sampleRate = 44100;
//        int samples = (int)(sampleRate * duration / 1000.0);
//        byte[] buffer = new byte[samples * 2];
//
//        for (int i = 0; i < samples; i++) {
//            double angle = 2.0 * Math.PI * i * frequency / sampleRate;
//            short sample = (short)(Math.sin(angle) * 32767 * 0.3);
//            buffer[i * 2] = (byte)(sample & 0xFF);
//            buffer[i * 2 + 1] = (byte)((sample >> 8) & 0xFF);
//        }
//
//        AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
//        DataLine.Info info = new DataLine.Info(Clip.class, format);
//        Clip clip = (Clip)AudioSystem.getLine(info);
//        clip.open(format, buffer, 0, buffer.length);
//
//        return clip;
//    }
//
//    public void playSound(String soundName) {
//        try {
//            Clip clip = sounds.get(soundName);
//            if (clip != null) {
//                clip.setFramePosition(0);
//                clip.start();
//            }
//        } catch (Exception e) {
//            System.err.println("Error playing sound: " + e.getMessage());
//        }
//    }
//}
