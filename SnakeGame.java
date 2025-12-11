import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        setTitle("Snake Game with Pause/Resume and Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener {

    private static final int TILE_SIZE = 25;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int DELAY = 550; // Slower game speed

    private final Timer timer;
    private final LinkedList<Point> snake;
    private Point food;
    private char direction = 'R';
    private boolean running = true;
    private boolean paused = false;
    private int score = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        snake = new LinkedList<>();
        snake.add(new Point(5, 5));
        placeFood();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void placeFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(HEIGHT);
            food = new Point(x, y);
        } while (snake.contains(food));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !paused) {
            move();
        }
        repaint();
    }

    private void move() {
        Point head = new Point(snake.getFirst());
        switch (direction) {
            case 'U' -> head.y--;
            case 'D' -> head.y++;
            case 'L' -> head.x--;
            case 'R' -> head.x++;
        }

        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT || snake.contains(head)) {
            running = false;
            timer.stop();
            return;
        }

        snake.addFirst(head);
        if (head.equals(food)) {
            score += 5; // Score increment
            placeFood();
        } else {
            snake.removeLast();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw grid (optional)
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i <= WIDTH; i++) g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, HEIGHT * TILE_SIZE);
        for (int i = 0; i <= HEIGHT; i++) g.drawLine(0, i * TILE_SIZE, WIDTH * TILE_SIZE, i * TILE_SIZE);

        // Draw food
        g.setColor(Color.RED);
        g.fillOval(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Draw snake
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + score, 10, 20);

        // Draw paused message
        if (paused) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("PAUSED", WIDTH * TILE_SIZE / 2 - 40, HEIGHT * TILE_SIZE / 2);
        }

        // Draw game over
        if (!running) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("GAME OVER", WIDTH * TILE_SIZE / 2 - 60, HEIGHT * TILE_SIZE / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT -> {
                if (direction != 'R') direction = 'L';
            }
            case KeyEvent.VK_RIGHT -> {
                if (direction != 'L') direction = 'R';
            }
            case KeyEvent.VK_UP -> {
                if (direction != 'D') direction = 'U';
            }
            case KeyEvent.VK_DOWN -> {
                if (direction != 'U') direction = 'D';
            }
            case KeyEvent.VK_SPACE -> togglePause(); // Pause/resume with spacebar
        }
    }

    private void togglePause() {
        if (!running) return;
        paused = !paused;
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
