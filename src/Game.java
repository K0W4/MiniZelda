import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
    public static int width = 640, height = 480;
    public static int scale = 3;
    public static Player player;
    public static World world;
    public static List<Enemie> enemies = new ArrayList<Enemie>();

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(width, height));

        new SpriteSheet();
        player = new Player(100, 100);
        enemies.add(new Enemie(200, 200));
        world = new World();
    }

    public void tick() {
        player.tick();

        for (Enemie enemy : enemies) {
            enemy.tick();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0, 70, 10));
        g.fillRect(0, 0, width * scale, height * scale);

        player.render(g);
        for (Enemie enemy : enemies) {
            enemy.render(g);;
        }
        world.render(g);

        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Mini Zelda");
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {
        while(true) {
            tick();
            render();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.shooting = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        }
    }
}
