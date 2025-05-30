import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
    public static BufferedImage spriteSheet;
    public static BufferedImage player_front [];
    public static BufferedImage enemie_front [];
    public static BufferedImage tileWall;

    public SpriteSheet() {
        try {
            spriteSheet = ImageIO.read(getClass().getResource("/res/spritesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        player_front = new BufferedImage[2];
        player_front[0] = SpriteSheet.getSprite(0,11, 16, 16);
        player_front[1] = SpriteSheet.getSprite(16, 11, 16, 16);

        enemie_front = new BufferedImage[2];
        enemie_front[0] = SpriteSheet.getSprite(57, 223, 16, 16);
        enemie_front[1] = SpriteSheet.getSprite(75, 223, 16, 16);

        tileWall = SpriteSheet.getSprite(280, 221, 16, 16);
    }

    public static BufferedImage getSprite(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }
}
