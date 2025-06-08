//package Game;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.*;
//import java.util.List;
//import javax.sound.sampled.*;
//import javax.swing.Timer;
//import java.io.*;
//
//// Clase principal del juego
//public class RuneWizardGame extends JPanel implements KeyListener, ActionListener {
//    private static final int WINDOW_WIDTH = 900;
//    private static final int WINDOW_HEIGHT = 700;
//    private static final int ROOM_WIDTH = 17;
//    private static final int ROOM_HEIGHT = 13;
//    private static final int TILE_SIZE = 40;
//
//    private game2.Player player;
//    private game2.Room currentRoom;
//    private List<game2.Enemy> enemies;
//    private List<game2.Rune> runes;
//    private List<game2.Projectile> projectiles;
//    private List<game2.VisualEffect> effects;
//    private Timer gameTimer;
//    private Random random;
//    private boolean[] keys;
//    private int currentFloor;
//    private int score;
//    private boolean bossRoom;
//    private game2.GameState gameState;
//    private game2.SoundManager soundManager;
//
//    public RuneWizardGame() {
//        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
//        setBackground(Color.BLACK);
//        setFocusable(true);
//        addKeyListener(this);
//
//        random = new Random();
//        keys = new boolean[256];
//        currentFloor = 1;
//        score = 0;
//        bossRoom = false;
//        gameState = game2.GameState.PLAYING;
//        soundManager = new game2.SoundManager();
//
//        initializeGame();
//
//        gameTimer = new Timer(16, this); // ~60 FPS
//        gameTimer.start();
//    }
//
//    private void initializeGame() {
//        player = new game2.Player(ROOM_WIDTH / 2 * TILE_SIZE, ROOM_HEIGHT / 2 * TILE_SIZE);
//        currentRoom = new game2.Room(currentFloor);
//        enemies = new ArrayList<>();
//        runes = new ArrayList<>();
//        projectiles = new ArrayList<>();
//        effects = new ArrayList<>();
//
//        generateRoom();
//    }
//
//    private void generateRoom() {
//        enemies.clear();
//        runes.clear();
//        projectiles.clear();
//        effects.clear();
//
//        // Determinar si es habitación de jefe (cada 5 pisos)
//        bossRoom = (currentFloor % 5 == 0);
//
//        if (bossRoom) {
//            // Generar jefe
//            game2.Enemy boss = new game2.Enemy(ROOM_WIDTH / 2 * TILE_SIZE, 3 * TILE_SIZE, game2.EnemyType.BOSS, true);
//            enemies.add(boss);
//            soundManager.playSound("boss");
//        } else {
//            // Generar enemigos normales según el tipo de habitación
//            int enemyCount = Math.min(8, 2 + currentFloor / 2 + random.nextInt(4));
//            for (int i = 0; i < enemyCount; i++) {
//                int x, y;
//                do {
//                    x = (2 + random.nextInt(ROOM_WIDTH - 4)) * TILE_SIZE;
//                    y = (2 + random.nextInt(ROOM_HEIGHT - 4)) * TILE_SIZE;
//                } while (Math.abs(x - player.getBounds().x) < 100 && Math.abs(y - player.getBounds().y) < 100);
//
//                game2.EnemyType type = getRandomEnemyType();
//                enemies.add(new game2.Enemy(x, y, type, false));
//            }
//        }
//
//        // Generar runas
//        int runeCount = bossRoom ? 1 : (2 + random.nextInt(4));
//        for (int i = 0; i < runeCount; i++) {
//            int x = (2 + random.nextInt(ROOM_WIDTH - 4)) * TILE_SIZE;
//            int y = (2 + random.nextInt(ROOM_HEIGHT - 4)) * TILE_SIZE;
//            game2.RuneType type = bossRoom ? getRandomRareRune() : getRandomRune();
//            runes.add(new game2.Rune(x, y, type));
//        }
//    }
//
//    private game2.EnemyType getRandomEnemyType() {
//        game2.EnemyType[] normalTypes = {game2.EnemyType.GOBLIN, game2.EnemyType.ORC, game2.EnemyType.SKELETON, game2.EnemyType.DEMON,
//                game2.EnemyType.WRAITH, game2.EnemyType.GOLEM, game2.EnemyType.NECROMANCER};
//        return normalTypes[random.nextInt(normalTypes.length)];
//    }
//
//    private game2.RuneType getRandomRune() {
//        // Runas comunes más probables
//        if (random.nextFloat() < 0.7f) {
//            game2.RuneType[] common = {game2.RuneType.FIRE, game2.RuneType.ICE, game2.RuneType.LIGHTNING, game2.RuneType.EARTH, game2.RuneType.ARCANE};
//            return common[random.nextInt(common.length)];
//        } else {
//            return getRandomRareRune();
//        }
//    }
//
//    private game2.RuneType getRandomRareRune() {
//        game2.RuneType[] rare = {game2.RuneType.SHADOW, game2.RuneType.LIGHT, game2.RuneType.BLOOD, game2.RuneType.VOID, game2.RuneType.TIME};
//        return rare[random.nextInt(rare.length)];
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        if (gameState == game2.GameState.PLAYING) {
//            // Dibujar habitación
//            currentRoom.draw(g2d);
//
//            // Dibujar runas
//            for (game2.Rune rune : runes) {
//                rune.draw(g2d);
//            }
//
//            // Dibujar enemigos
//            for (game2.Enemy enemy : enemies) {
//                enemy.draw(g2d);
//            }
//
//            // Dibujar proyectiles
//            for (game2.Projectile proj : projectiles) {
//                proj.draw(g2d);
//            }
//
//            // Dibujar efectos visuales
//            for (game2.VisualEffect effect : effects) {
//                effect.draw(g2d);
//            }
//
//            // Dibujar jugador
//            player.draw(g2d);
//
//            // Dibujar UI
//            drawUI(g2d);
//        } else if (gameState == game2.GameState.GAME_OVER) {
//            drawGameOver(g2d);
//        }
//    }
//
//    private void drawUI(Graphics2D g) {
//        // Panel de información principal
//        g.setColor(new Color(0, 0, 0, 150));
//        g.fillRect(10, 10, 300, 200);
//        g.setColor(Color.WHITE);
//        g.drawRect(10, 10, 300, 200);
//
//        g.setFont(new Font("Arial", Font.BOLD, 16));
//        g.drawString("HP: " + player.getHealth() + "/" + player.getMaxHealth(), 20, 35);
//        g.drawString("Mana: " + player.getMana() + "/" + player.getMaxMana(), 20, 55);
//        g.drawString("Floor: " + currentFloor + (bossRoom ? " (BOSS)" : ""), 20, 75);
//        g.drawString("Score: " + score, 20, 95);
//
//        // Mostrar runas recolectadas
//        g.setFont(new Font("Arial", Font.BOLD, 12));
//        g.drawString("Runas Recolectadas:", 20, 120);
//        int y = 135;
//        for (game2.RuneType runeType : player.getCollectedRunes().keySet()) {
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
//        for (game2.RuneCombination combo : player.getActiveCombinations()) {
//            g.drawString("• " + combo.getName(), 20, y);
//            y += 12;
//        }
//
//        // Barra de experiencia del piso
//        g.setColor(Color.GRAY);
//        g.fillRect(20, WINDOW_HEIGHT - 40, 200, 10);
//        g.setColor(Color.BLUE);
//        int progress = Math.max(0, 200 - (enemies.size() * 25));
//        g.fillRect(20, WINDOW_HEIGHT - 40, progress, 10);
//        g.setColor(Color.WHITE);
//        g.drawRect(20, WINDOW_HEIGHT - 40, 200, 10);
//        g.drawString("Progreso del Piso", 20, WINDOW_HEIGHT - 45);
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
//        if (gameState == game2.GameState.PLAYING) {
//            update();
//        }
//        repaint();
//    }
//
//    private void update() {
//        // Actualizar jugador
//        player.update(keys);
//
//        // Actualizar enemigos
//        for (game2.Enemy enemy : enemies) {
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
//            game2.Projectile proj = player.shoot();
//            if (proj != null) {
//                projectiles.add(proj);
//                soundManager.playSound("shoot");
//            }
//        }
//
//        // Verificar si todos los enemigos están muertos
//        if (enemies.isEmpty()) {
//            nextFloor();
//        }
//
//        // Verificar game over
//        if (player.getHealth() <= 0) {
//            gameState = game2.GameState.GAME_OVER;
//            soundManager.playSound("gameover");
//        }
//    }
//
//    private void checkCollisions() {
//        // Colisiones jugador-runas
//        runes.removeIf(rune -> {
//            if (player.getBounds().intersects(rune.getBounds())) {
//                player.collectRune(rune);
//                score += rune.getType().getScoreValue();
//                effects.add(new game2.VisualEffect(rune.getBounds().x, rune.getBounds().y,
//                        game2.VisualEffect.Type.RUNE_COLLECT, rune.getType().getColor()));
//                soundManager.playSound("collect");
//                return true;
//            }
//            return false;
//        });
//
//        // Colisiones proyectiles-enemigos
//        Iterator<game2.Projectile> projIter = projectiles.iterator();
//        while (projIter.hasNext()) {
//            game2.Projectile proj = projIter.next();
//            Iterator<game2.Enemy> enemyIter = enemies.iterator();
//            while (enemyIter.hasNext()) {
//                game2.Enemy enemy = enemyIter.next();
//                if (proj.getBounds().intersects(enemy.getBounds())) {
//                    int damage = proj.getDamage();
//                    enemy.takeDamage(damage);
//
//                    // Efectos especiales según el tipo de proyectil
//                    effects.add(new game2.VisualEffect(enemy.getBounds().x, enemy.getBounds().y,
//                            game2.VisualEffect.Type.HIT, proj.getColor()));
//
//                    projIter.remove();
//                    soundManager.playSound("hit");
//
//                    if (enemy.isDead()) {
//                        score += enemy.getScoreValue();
//                        effects.add(new game2.VisualEffect(enemy.getBounds().x, enemy.getBounds().y,
//                                game2.VisualEffect.Type.ENEMY_DEATH, Color.RED));
//                        enemyIter.remove();
//                        soundManager.playSound("kill");
//                    }
//                    break;
//                }
//            }
//        }
//
//        // Colisiones jugador-enemigos
//        for (game2.Enemy enemy : enemies) {
//            if (player.getBounds().intersects(enemy.getBounds()) && player.canTakeDamage()) {
//                player.takeDamage(enemy.getDamage());
//                effects.add(new game2.VisualEffect(player.getBounds().x, player.getBounds().y,
//                        game2.VisualEffect.Type.PLAYER_HIT, Color.RED));
//                soundManager.playSound("damage");
//            }
//        }
//    }
//
//    private void nextFloor() {
//        currentFloor++;
//        score += 100 * currentFloor; // Bonus por completar piso
//        currentRoom = new game2.Room(currentFloor);
//        generateRoom();
//        player.setPosition(ROOM_WIDTH / 2 * TILE_SIZE, ROOM_HEIGHT / 2 * TILE_SIZE);
//        player.healSlightly(); // Curar un poco al avanzar de piso
//        soundManager.playSound("levelup");
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        keys[e.getKeyCode()] = true;
//
//        if (gameState == game2.GameState.GAME_OVER && e.getKeyCode() == KeyEvent.VK_R) {
//            // Reiniciar juego
//            currentFloor = 1;
//            score = 0;
//            gameState = game2.GameState.PLAYING;
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
//        JFrame frame = new JFrame("Rune Wizard - Enhanced Edition");
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
//// Clase del jugador mejorada
//class Player {
//    private int x, y;
//    private int health, maxHealth;
//    private int mana, maxMana;
//    private int speed;
//    private int damage;
//    private long lastShot;
//    private long lastDamage;
//    private int shootCooldown;
//    private int invulnerabilityTime;
//    private Map<game2.RuneType, Integer> collectedRunes;
//    private List<game2.RuneCombination> activeCombinations;
//
//    public Player(int x, int y) {
//        this.x = x;
//        this.y = y;
//        this.maxHealth = 100;
//        this.health = maxHealth;
//        this.maxMana = 50;
//        this.mana = maxMana;
//        this.speed = 4;
//        this.damage = 15;
//        this.shootCooldown = 250;
//        this.invulnerabilityTime = 1000;
//        this.collectedRunes = new HashMap<>();
//        this.activeCombinations = new ArrayList<>();
//    }
//
//    public void update(boolean[] keys) {
//        // Movimiento
//        int newX = x, newY = y;
//        if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) newY -= speed;
//        if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) newY += speed;
//        if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) newX -= speed;
//        if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) newX += speed;
//
//        // Limitar movimiento a los bordes de la habitación
//        x = Math.max(40, Math.min(newX, 17 * 40 - 60));
//        y = Math.max(40, Math.min(newY, 13 * 40 - 60));
//
//        // Regenerar mana
//        if (mana < maxMana) {
//            mana = Math.min(maxMana, mana + 1);
//        }
//
//        // Regenerar vida lentamente si tiene la combinación adecuada
//        if (hasActiveCombination("Regeneración Vital") && health < maxHealth) {
//            if (System.currentTimeMillis() % 60 == 0) {
//                health = Math.min(maxHealth, health + 1);
//            }
//        }
//    }
//
//    public void draw(Graphics2D g) {
//        // Efecto de invulnerabilidad
//        if (System.currentTimeMillis() - lastDamage < invulnerabilityTime) {
//            if ((System.currentTimeMillis() / 100) % 2 == 0) return; // Parpadeo
//        }
//
//        g.setColor(Color.BLUE);
//        g.fillOval(x, y, 35, 35);
//        g.setColor(Color.CYAN);
//        g.fillOval(x + 5, y + 5, 25, 25);
//
//        // Efecto mágico si tiene runas especiales
//        if (hasActiveCombination("Aura Mágica")) {
//            g.setColor(new Color(255, 255, 0, 100));
//            g.fillOval(x - 10, y - 10, 55, 55);
//        }
//
//        // Dibujar barra de vida
//        g.setColor(Color.RED);
//        g.fillRect(x, y - 12, 35, 5);
//        g.setColor(Color.GREEN);
//        g.fillRect(x, y - 12, (int)(35 * ((double)health / maxHealth)), 5);
//        g.setColor(Color.WHITE);
//        g.drawRect(x, y - 12, 35, 5);
//    }
//
//    public boolean canShoot() {
//        return System.currentTimeMillis() - lastShot > shootCooldown && mana >= getManaCost();
//    }
//
//    private int getManaCost() {
//        return hasActiveCombination("Eficiencia Mágica") ? 3 : 5;
//    }
//
//    public game2.Projectile shoot() {
//        if (canShoot()) {
//            lastShot = System.currentTimeMillis();
//            mana -= getManaCost();
//
//            Color projColor = Color.YELLOW;
//            int projDamage = damage;
//            int projSpeed = 7;
//
//            // Modificar proyectil según combinaciones activas
//            if (hasActiveCombination("Proyectiles de Fuego")) {
//                projColor = Color.RED;
//                projDamage += 10;
//            }
//            if (hasActiveCombination("Proyectiles de Hielo")) {
//                projColor = Color.CYAN;
//                projSpeed = 5;
//            }
//            if (hasActiveCombination("Proyectiles de Rayo")) {
//                projColor = Color.YELLOW;
//                projSpeed = 10;
//            }
//
//            return new game2.Projectile(x + 17, y + 17, 0, -projSpeed, projDamage, projColor);
//        }
//        return null;
//    }
//
//    public void collectRune(game2.Rune rune) {
//        game2.RuneType type = rune.getType();
//        collectedRunes.put(type, collectedRunes.getOrDefault(type, 0) + 1);
//
//        // Aplicar efecto base de la runa
//        type.applyEffect(this);
//
//        // Verificar nuevas combinaciones
//        checkForNewCombinations();
//    }
//
//    private void checkForNewCombinations() {
//        activeCombinations.clear();
//
//        // Definir combinaciones
//        if (hasRunes(game2.RuneType.FIRE, 2) && hasRunes(game2.RuneType.EARTH, 1)) {
//            activeCombinations.add(new game2.RuneCombination("Proyectiles de Fuego", "Disparos incendiarios"));
//        }
//        if (hasRunes(game2.RuneType.ICE, 2) && hasRunes(game2.RuneType.ARCANE, 1)) {
//            activeCombinations.add(new game2.RuneCombination("Proyectiles de Hielo", "Disparos congelantes"));
//        }
//        if (hasRunes(game2.RuneType.LIGHTNING, 2) && hasRunes(game2.RuneType.VOID, 1)) {
//            activeCombinations.add(new game2.RuneCombination("Proyectiles de Rayo", "Disparos eléctricos"));
//        }
//        if (hasRunes(game2.RuneType.EARTH, 3)) {
//            activeCombinations.add(new game2.RuneCombination("Regeneración Vital", "Curación constante"));
//        }
//        if (hasRunes(game2.RuneType.ARCANE, 3)) {
//            activeCombinations.add(new game2.RuneCombination("Eficiencia Mágica", "Menor costo de maná"));
//        }
//        if (hasRunes(game2.RuneType.LIGHT, 1) && hasRunes(game2.RuneType.SHADOW, 1)) {
//            activeCombinations.add(new game2.RuneCombination("Equilibrio Perfecto", "Bonificaciones balanceadas"));
//        }
//        if (hasRunes(game2.RuneType.TIME, 1)) {
//            activeCombinations.add(new game2.RuneCombination("Manipulación Temporal", "Efectos de tiempo"));
//        }
//        if (hasRunes(game2.RuneType.BLOOD, 2)) {
//            activeCombinations.add(new game2.RuneCombination("Poder Sanguinario", "Daño por vida perdida"));
//        }
//        if (hasRunes(game2.RuneType.VOID, 2) && hasRunes(game2.RuneType.SHADOW, 2)) {
//            activeCombinations.add(new game2.RuneCombination("Aura Mágica", "Campo de energía protector"));
//        }
//    }
//
//    private boolean hasRunes(game2.RuneType type, int amount) {
//        return collectedRunes.getOrDefault(type, 0) >= amount;
//    }
//
//    private boolean hasActiveCombination(String name) {
//        return activeCombinations.stream().anyMatch(combo -> combo.getName().equals(name));
//    }
//
//    public boolean canTakeDamage() {
//        return System.currentTimeMillis() - lastDamage > invulnerabilityTime;
//    }
//
//    public void takeDamage(int damage) {
//        if (canTakeDamage()) {
//            int actualDamage = damage;
//
//            // Reducir daño con ciertas combinaciones
//            if (hasActiveCombination("Aura Mágica")) {
//                actualDamage = (int)(damage * 0.7);
//            }
//
//            health = Math.max(0, health - actualDamage);
//            lastDamage = System.currentTimeMillis();
//        }
//    }
//
//    public void healSlightly() {
//        health = Math.min(maxHealth, health + 20);
//        mana = Math.min(maxMana, mana + 15);
//    }
//
//    public void setPosition(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle(x, y, 35, 35);
//    }
//
//    // Getters
//    public int getHealth() { return health; }
//    public int getMaxHealth() { return maxHealth; }
//    public int getMana() { return mana; }
//    public int getMaxMana() { return maxMana; }
//    public Map<game2.RuneType, Integer> getCollectedRunes() { return collectedRunes; }
//    public List<game2.RuneCombination> getActiveCombinations() { return activeCombinations; }
//
//    // Métodos para modificar stats (usados por las runas)
//    public void increaseDamage(int amount) { damage += amount; }
//    public void increaseSpeed(int amount) { speed += amount; }
//    public void increaseMaxHealth(int amount) {
//        maxHealth += amount;
//        health += amount;
//    }
//    public void increaseMaxMana(int amount) {
//        maxMana += amount;
//        mana += amount;
//    }
//    public void decreaseShootCooldown(int amount) {
//        shootCooldown = Math.max(50, shootCooldown - amount);
//    }
//}
//
//// Enum expandido para tipos de runas
//enum RuneType {
//    // Runas básicas
//    FIRE("Fuego", Color.RED, 10, (player) -> player.increaseDamage(8)),
//    ICE("Hielo", Color.CYAN, 10, (player) -> player.decreaseShootCooldown(40)),
//    LIGHTNING("Rayo", new Color(255, 255, 0), 10, (player) -> player.increaseSpeed(2)),
//    EARTH("Tierra", Color.GREEN, 10, (player) -> player.increaseMaxHealth(25)),
//    ARCANE("Arcano", Color.MAGENTA, 10, (player) -> player.increaseMaxMana(15)),
//
//    // Runas raras
//    SHADOW("Sombra", new Color(64, 0, 128), 25, (player) -> {
//        player.increaseDamage(5);
//        player.increaseSpeed(1);
//    }),
//    LIGHT("Luz", Color.WHITE, 25, (player) -> {
//        player.increaseMaxHealth(15);
//        player.increaseMaxMana(10);
//    }),
//    BLOOD("Sangre", new Color(139, 0, 0), 30, (player) -> player.increaseDamage(12)),
//    VOID("Vacío", new Color(25, 25, 25), 35, (player) -> {
//        player.increaseDamage(10);
//        player.decreaseShootCooldown(30);
//    }),
//    TIME("Tiempo", new Color(255, 215, 0), 50, (player) -> {
//        player.increaseSpeed(3);
//        player.decreaseShootCooldown(50);
//    });
//
//    private final String name;
//    private final Color color;
//    private final int scoreValue;
//    private final RuneEffect effect;
//
//    RuneType(String name, Color color, int scoreValue, RuneEffect effect) {
//        this.name = name;
//        this.color = color;
//        this.scoreValue = scoreValue;
//        this.effect = effect;
//    }
//
//    public void applyEffect(game2.Player player) {
//        effect.apply(player);
//    }
//
//    public String getName() { return name; }
//    public Color getColor() { return color; }
//    public int getScoreValue() { return scoreValue; }
//}
//
//@FunctionalInterface
//interface RuneEffect {
//    void apply(game2.Player player);
//}
//
//// Clase de combinaciones de runas
//class RuneCombination {
//    private String name;
//    private String description;
//
//    public RuneCombination(String name, String description) {
//        this.name = name;
//        this.description = description;
//    }
//
//    public String getName() { return name; }
//    public String getDescription() { return description; }
//}
//
//// Clase de runas mejorada
//class Rune {
//    private int x, y;
//    private game2.RuneType type;
//    private float glowPhase;
//
//    public Rune(int x, int y, game2.RuneType type) {
//        this.x = x;
//        this.y = y;
//        this.type = type;
//        this.glowPhase = 0;
//    }
//
//    public void draw(Graphics2D g) {
//        glowPhase += 0.1f;
//
//        // Efecto de brillo
//        int glowIntensity = (int)(50 + 30 * Math.sin(glowPhase));
//        Color glowColor = new Color(type.getColor().getRed(), type.getColor().getGreen(),
//                type.getColor().getBlue(), glowIntensity);
//
//        g.setColor(glowColor);
//        g.fillOval(x + 5, y + 5, 30, 30);
//
//        g.setColor(type.getColor());
//        g.fillRect(x + 10, y + 10, 20, 20);
//        g.setColor(Color.WHITE);
//        g.drawRect(x + 10, y + 10, 20, 20);
//
//        // Dibujar símbolo más elaborado
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Arial", Font.BOLD, 14));
//        String symbol = getRuneSymbol();
//        FontMetrics fm = g.getFontMetrics();
//        int textX = x + 20 - fm.stringWidth(symbol) / 2;
//        int textY = y + 25;
//        g.drawString(symbol, textX, textY);
//    }
//
//    private String getRuneSymbol() {
//        switch (type) {
//            case FIRE: return "🔥";
//            case ICE: return "❄";
//            case LIGHTNING: return "⚡";
//            case EARTH: return "🌍";
//            case ARCANE: return "✨";
//            // Continuación del método getRuneSymbol() en la clase Rune
//            case SHADOW: return "🌙";
//            case LIGHT: return "☀";
//            case BLOOD: return "💀";
//            case VOID: return "⚫";
//            case TIME: return "⏰";
//            default: return "?";
//        }
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle(x, y, 40, 40);
//    }
//
//    public game2.RuneType getType() { return type; }
//}
//
//// Enum para tipos de enemigos
//enum EnemyType {
//    GOBLIN("Goblin", Color.GREEN, 30, 8, 1, 10),
//    ORC("Orco", new Color(139, 69, 19), 50, 12, 2, 15),
//    SKELETON("Esqueleto", Color.WHITE, 40, 10, 3, 12),
//    DEMON("Demonio", Color.RED, 70, 15, 2, 20),
//    WRAITH("Espectro", new Color(128, 128, 128), 35, 8, 4, 18),
//    GOLEM("Golem", new Color(105, 105, 105), 100, 20, 1, 25),
//    NECROMANCER("Nigromante", new Color(75, 0, 130), 60, 12, 2, 30),
//    BOSS("Jefe", new Color(139, 0, 0), 300, 25, 1, 100);
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
//// Clase Enemy mejorada
//class Enemy {
//    private int x, y;
//    private int health, maxHealth;
//    private int damage;
//    private int speed;
//    private game2.EnemyType type;
//    private boolean isBoss;
//    private long lastMove;
//    private int moveDirection;
//    private Random random;
//
//    public Enemy(int x, int y, game2.EnemyType type, boolean isBoss) {
//        this.x = x;
//        this.y = y;
//        this.type = type;
//        this.isBoss = isBoss;
//        this.maxHealth = type.getHealth() * (isBoss ? 3 : 1);
//        this.health = maxHealth;
//        this.damage = type.getDamage();
//        this.speed = type.getSpeed();
//        this.lastMove = 0;
//        this.moveDirection = 0;
//        this.random = new Random();
//    }
//
//    public void update(game2.Player player) {
//        // Movimiento hacia el jugador
//        if (System.currentTimeMillis() - lastMove > 100) {
//            int playerX = player.getBounds().x;
//            int playerY = player.getBounds().y;
//
//            if (Math.abs(x - playerX) > Math.abs(y - playerY)) {
//                if (x < playerX) x += speed;
//                else if (x > playerX) x -= speed;
//            } else {
//                if (y < playerY) y += speed;
//                else if (y > playerY) y -= speed;
//            }
//
//            // Mantener dentro de los límites
//            x = Math.max(40, Math.min(x, 17 * 40 - 60));
//            y = Math.max(40, Math.min(y, 13 * 40 - 60));
//
//            lastMove = System.currentTimeMillis();
//        }
//    }
//
//    public void draw(Graphics2D g) {
//        // Dibujar enemigo
//        g.setColor(type.getColor());
//        int size = isBoss ? 50 : 30;
//        g.fillOval(x, y, size, size);
//
//        // Contorno más oscuro
//        g.setColor(type.getColor().darker());
//        g.drawOval(x, y, size, size);
//
//        // Ojos rojos para dar aspecto más amenazante
//        g.setColor(Color.RED);
//        if (isBoss) {
//            g.fillOval(x + 10, y + 10, 8, 8);
//            g.fillOval(x + 30, y + 10, 8, 8);
//        } else {
//            g.fillOval(x + 5, y + 5, 5, 5);
//            g.fillOval(x + 20, y + 5, 5, 5);
//        }
//
//        // Barra de vida
//        if (health < maxHealth) {
//            g.setColor(Color.RED);
//            g.fillRect(x, y - 10, size, 4);
//            g.setColor(Color.GREEN);
//            g.fillRect(x, y - 10, (int)(size * ((double)health / maxHealth)), 4);
//            g.setColor(Color.WHITE);
//            g.drawRect(x, y - 10, size, 4);
//        }
//
//        // Efecto especial para jefe
//        if (isBoss) {
//            g.setColor(new Color(255, 0, 0, 50));
//            g.fillOval(x - 10, y - 10, 70, 70);
//        }
//    }
//
//    public void takeDamage(int damage) {
//        health = Math.max(0, health - damage);
//    }
//
//    public boolean isDead() {
//        return health <= 0;
//    }
//
//    public Rectangle getBounds() {
//        int size = isBoss ? 50 : 30;
//        return new Rectangle(x, y, size, size);
//    }
//
//    public int getDamage() { return damage; }
//    public int getScoreValue() { return type.getScoreValue() * (isBoss ? 5 : 1); }
//}
//
//// Clase Room
//class Room {
//    private int floor;
//    private Color wallColor;
//    private Color floorColor;
//
//    public Room(int floor) {
//        this.floor = floor;
//        // Colores que cambian según el piso
//        int intensity = Math.min(255, 50 + floor * 10);
//        this.wallColor = new Color(intensity / 4, intensity / 6, intensity / 3);
//        this.floorColor = new Color(intensity / 8, intensity / 8, intensity / 6);
//    }
//
//    public void draw(Graphics2D g) {
//        // Dibujar suelo
//        g.setColor(floorColor);
//        g.fillRect(40, 40, 17 * 40, 13 * 40);
//
//        // Dibujar paredes
//        g.setColor(wallColor);
//        g.fillRect(0, 0, 900, 40); // Pared superior
//        g.fillRect(0, 40 + 13 * 40, 900, 40); // Pared inferior
//        g.fillRect(0, 0, 40, 700); // Pared izquierda
//        g.fillRect(40 + 17 * 40, 0, 40, 700); // Pared derecha
//
//        // Líneas de grid para el suelo
//        g.setColor(wallColor.brighter());
//        for (int i = 0; i <= 17; i++) {
//            g.drawLine(40 + i * 40, 40, 40 + i * 40, 40 + 13 * 40);
//        }
//        for (int i = 0; i <= 13; i++) {
//            g.drawLine(40, 40 + i * 40, 40 + 17 * 40, 40 + i * 40);
//        }
//    }
//}
//
//// Clase Projectile
//class Projectile {
//    private int x, y;
//    private int dx, dy;
//    private int damage;
//    private Color color;
//    private boolean active;
//
//    public Projectile(int x, int y, int dx, int dy, int damage, Color color) {
//        this.x = x;
//        this.y = y;
//        this.dx = dx;
//        this.dy = dy;
//        this.damage = damage;
//        this.color = color;
//        this.active = true;
//    }
//
//    public boolean update() {
//        x += dx;
//        y += dy;
//
//        // Verificar límites
//        if (x < 40 || x > 17 * 40 + 40 || y < 40 || y > 13 * 40 + 40) {
//            active = false;
//        }
//
//        return active;
//    }
//
//    public void draw(Graphics2D g) {
//        g.setColor(color);
//        g.fillOval(x - 3, y - 3, 6, 6);
//        g.setColor(color.brighter());
//        g.fillOval(x - 1, y - 1, 2, 2);
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle(x - 3, y - 3, 6, 6);
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
//    public enum Type {
//        HIT, ENEMY_DEATH, PLAYER_HIT, RUNE_COLLECT
//    }
//
//    public VisualEffect(int x, int y, Type type, Color color) {
//        this.x = x;
//        this.y = y;
//        this.type = type;
//        this.color = color;
//        this.maxDuration = 30;
//        this.duration = maxDuration;
//        this.alpha = 1.0f;
//    }
//
//    public boolean update() {
//        duration--;
//        alpha = (float)duration / maxDuration;
//        return duration > 0;
//    }
//
//    public void draw(Graphics2D g) {
//        Color effectColor = new Color(color.getRed(), color.getGreen(), color.getBlue(),
//                (int)(255 * alpha));
//        g.setColor(effectColor);
//
//        switch (type) {
//            case HIT:
//                g.fillOval(x - 5, y - 5, 10, 10);
//                break;
//            case ENEMY_DEATH:
//                int size = (int)(20 * alpha);
//                g.fillOval(x - size/2, y - size/2, size, size);
//                break;
//            case PLAYER_HIT:
//                g.fillRect(x - 10, y - 10, 20, 20);
//                break;
//            case RUNE_COLLECT:
//                for (int i = 0; i < 8; i++) {
//                    double angle = i * Math.PI / 4;
//                    int sparkX = x + (int)(15 * Math.cos(angle) * alpha);
//                    int sparkY = y + (int)(15 * Math.sin(angle) * alpha);
//                    g.fillOval(sparkX - 2, sparkY - 2, 4, 4);
//                }
//                break;
//        }
//    }
//}
//
//// Clase SoundManager simplificada
//class SoundManager {
//    private Map<String, String> soundPaths;
//
//    public SoundManager() {
//        soundPaths = new HashMap<>();
//        // Aquí podrías agregar rutas a archivos de sonido reales
//        soundPaths.put("shoot", "sounds/shoot.wav");
//        soundPaths.put("hit", "sounds/hit.wav");
//        soundPaths.put("collect", "sounds/collect.wav");
//        soundPaths.put("kill", "sounds/kill.wav");
//        soundPaths.put("damage", "sounds/damage.wav");
//        soundPaths.put("levelup", "sounds/levelup.wav");
//        soundPaths.put("boss", "sounds/boss.wav");
//        soundPaths.put("gameover", "sounds/gameover.wav");
//    }
//
//    public void playSound(String soundName) {
//        // Implementación simplificada - en un juego real cargarías y reproducirías archivos de audio
//        // Por ahora solo imprime el sonido que se reproduciría
//        System.out.println("♪ " + soundName);
//
//
//        try {
//            String path = soundPaths.get(soundName);
//            if (path != null) {
//                File soundFile = new File(path);
//                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioIn);
//                clip.start();
//            }
//        } catch (Exception e) {
//            System.err.println("Error reproduciendo sonido: " + soundName);
//        }
//
//    }
//}