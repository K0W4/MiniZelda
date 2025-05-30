import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Enemie extends Rectangle {
    public int spd = 2; 
    public int curAnimation = 0, curFrames = 0, targetFrames = 15;
    public int dir = 1;

    public boolean shooting = false;

    public static List<Bullet> bullets = new ArrayList<Bullet>();


    public Enemie(int x, int y) {
        super(x, y, 32, 32);
    }

    public void chasePlayer() {
        Player player = Game.player;

        if (player.x < x && World.isFree(x - spd, y)) {
            x -= spd;
        } else if (player.x > x && World.isFree(x + spd, y)) {
            x += spd;
        }

        if (player.y < y && World.isFree(x, y - spd)) {
            y -= spd;
        } else if (player.y > y && World.isFree(x, y + spd)) {
            y += spd;
        }
    }

    public void tick() {
        boolean moved = true;
        chasePlayer();

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
        g.drawImage(SpriteSheet.enemie_front[curAnimation], x, y, 32, 32, null);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(g);
        }
    }
}
