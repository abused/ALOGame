package abused_master.javagame.graphics;

import abused_master.javagame.Game;

public class RenderManager extends Render {

    public double floorPosition = 16;
    public double skyPosition = 50001;
    public double renderDistance = 50000;
    public double[] zBuffer;

    public RenderManager(int width, int height) {
        super(width, height);
        zBuffer = new double[width * height];
    }

    public void floor(Game game) {
        double rotation = game.controller.rotation;
        double sin = Math.sin(rotation);
        double costine = Math.cos(rotation);
        double forward = game.controller.x;
        double right = game.controller.z;

        for (int y = 0; y < height; y++) {
            double yDepth = (y - height / 2.0) / height;
            double z = floorPosition / yDepth;

            if(yDepth < 0) {
                z = skyPosition / -yDepth;
            }


            for (int x = 0; x < width; x++) {
                double xDepth = (x - width / 2.0) / height;
                xDepth *= z;
                double xx = xDepth * costine + z * sin;
                double yy = z * costine - xDepth * sin;

                int xPix = (int) (xx + forward);
                int yPix = (int) (yy + right);
                zBuffer[x + y * width] = z;
                //old renders
                //pixels[x + y * width] = ((xPix & 15) * 16) | ((yPix & 15) * 16) << 8;
                //pixels[x + y * width] = TextureManager.floor.pixels[(xPix & 255) + (yPix & 255) * TextureManager.floor.width];
                pixels[x + y * width] = TextureManager.floor.pixels[(xPix & 15) * 16+ (yPix & 15) * 16 * TextureManager.floor.width];

                //16x textures
                //pixels[x + y * width] = TextureManager.floor.pixels[(xPix & 15) + (yPix & 15) * TextureManager.floor.width];
            }
        }
    }

    public void renderDistanceLimiter() {
        for (int i = 0; i < width * height; i++) {
            int color = pixels[i];
            int brightness = (int) (renderDistance / zBuffer[i]);

            //brightness = 0 to 255
            if (brightness < 0) {
                brightness = 0;
            }else if(brightness > 255) {
                brightness = 255;
            }

            int r = (color >> 16) & 0xff;
            int g = (color >> 8) & 0xff;
            int b = (color) & 0xff;

            r = r * brightness / 255;
            g = g * brightness / 255;
            b = b * brightness / 255;

            pixels[i] = r << 16 | g << 8 | b;
        }
    }
}
