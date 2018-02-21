package abused_master.javagame.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TextureManager {

    public static Render floor = loadBitmap("src/main/resources/assets/textures/uneditedgrass.png");
    //with border
    //public static Render floor = loadBitmap("src/main/resources/assets/textures/grass.png");

    public static Render loadBitmap(String location) {
        try {
            BufferedImage texture = ImageIO.read(new File(location));
            int width = texture.getWidth();
            int height = texture.getHeight();
            Render result = new Render(width, height);
            texture.getRGB(0, 0, width, height, result.pixels, 0, width);
            return result;
        } catch (Exception e) {
            System.out.println("Cannot load texture " + location);
            throw new RuntimeException(e);
        }
    }
}
