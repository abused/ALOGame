package abused_master.javagame.graphics;

import abused_master.javagame.Game;

import java.util.Random;

public class Screen extends Render {

    private RenderManager manager;

    public Screen(int WIDTH, int HEIGHT) {
        super(WIDTH, HEIGHT);
        manager = new RenderManager(width, height);
    }

    public void render(Game game) {
        for (int i = 0; i < width * height; i++) {
            pixels[i] = 0;
        }

        manager.floor(game);
        manager.renderDistanceLimiter();
        draw(manager, 0, 0);
    }
}
