package abused_master.javagame.graphics;

import abused_master.javagame.MainDisplay;

public class Render {

    public final int width;
    public final int height;
    public final int[] pixels;

    public Render(int WIDTH, int HEIGHT) {
        width = WIDTH;
        height = HEIGHT;
        pixels = new int[width * height];
    }

    public void draw(Render render, int xOffset, int yOffset) {
        for (int y = 0; y < render.height; y++) {
            int yPix = y + yOffset;
            if(yPix < 0 || yPix >= MainDisplay.HEIGHT) {
                continue;
            }
            for (int x = 0; x < render.width; x++) {
                int xPix = x + xOffset;
                if(xPix < 0 || xPix >= MainDisplay.WIDTH) {
                    continue;
                }

                int alpha = render.pixels[x + y * render.width];
                if (alpha > 0) {
                    pixels[xPix + yPix * width] = alpha;
                }
            }
        }
    }
}
