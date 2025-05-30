import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {
    public Block(int x, int y) {
        super(x, y, 32, 32);
    }

    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);

        g.setColor(Color.GRAY);
        g.drawRect(x, y, width, height);
    }
}
