import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle {
    public int dir = 1;
    public int spd = 8;
    public int frame = 0;

    public Bullet(int x, int y, int dir) {
        super(x, y, 10, 10);
        this.dir = dir;
    }

    public void tick() {
        x += spd * dir;
        frame++;
        if (frame > 90) {
            Player.bullets.remove(this);
            return;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }
}
