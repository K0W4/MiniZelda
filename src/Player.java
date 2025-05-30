import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
    public int spd = 4; 
    public int curAnimation = 0, curFrames = 0, targetFrames = 15;
    public int dir = 1;

    public boolean up, down, left, right;
    public boolean shooting = false;

    public static List<Bullet> bullets = new ArrayList<Bullet>();


    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void tick() {
        boolean moved = false;
        if (up && World.isFree(x, y - spd)) {
            y -= spd;
            moved = true;
        } else if (down && World.isFree(x, y + spd)) {
            y += spd;
            moved = true;
        }
        if (left && World.isFree(x - spd, y)) {
            x -= spd;
            moved = true;
            dir = -1;
        } else if (right && World.isFree(x + spd, y)) {
            x += spd;
            moved = true;
            dir = 1;
        }

        if (moved) {
            curFrames++;
            if (curFrames == targetFrames) {
                curFrames = 0;
                curAnimation++;
                if (curAnimation >= SpriteSheet.player_front.length) {
                    curAnimation = 0;
                }
            }
        }

        if (shooting) {
            shooting = false;
            bullets.add(new Bullet(x + 16, y + 16, dir));
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).tick();;
        }
    }
    
    public void render(Graphics g) {
        g.drawImage(SpriteSheet.player_front[curAnimation], x, y, 32, 32, null);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(g);
        }
    }
}
