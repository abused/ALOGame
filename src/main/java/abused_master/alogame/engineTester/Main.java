package abused_master.alogame.engineTester;

import abused_master.alogame.entities.Camera;
import abused_master.alogame.entities.Entity;
import abused_master.alogame.entities.Light;
import abused_master.alogame.models.TexturedModel;
import abused_master.alogame.renderEngine.*;
import abused_master.alogame.models.RawModel;
import abused_master.alogame.terrains.Terrain;
import abused_master.alogame.textures.ModelTexture;
import abused_master.alogame.textures.TerrainTexture;
import abused_master.alogame.textures.TerrainTexturePack;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        RawModel model = OBJLoader.loadOBJModel("tree", loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("tree"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        texture.setShineDamper(1);
        texture.setReflectivity(1);

        Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -20), 0, 0, 0, 1);
        Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

        Camera camera = new Camera();
        MasterRenderer renderer = new MasterRenderer();

        List<Entity> entities = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 150; i++) {
            entities.add(new Entity(texturedModel, new Vector3f(random.nextFloat() * 1000, 0, -random.nextFloat() * 1000),0, 0, 0, 8));
        }

        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grass"));
        TerrainTexture pathTexture = new TerrainTexture(loader.loadTexture("path"));
        TerrainTexture pixelizedTexture = new TerrainTexture(loader.loadTexture("pixelized"));
        TerrainTexture testTexture = new TerrainTexture(loader.loadTexture("test"));
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blend_map"));
        TerrainTexturePack pack = new TerrainTexturePack(backgroundTexture, pixelizedTexture, testTexture, pathTexture);

        Terrain terrain = new Terrain(0, -1, loader, pack, blendMap);
        //Terrain terrain2 = new Terrain(1, -1, loader, backgroundTexture, );

        while (!Display.isCloseRequested()) {
            camera.move();

            renderer.processTerrain(terrain);
            //renderer.processTerrain(terrain2);
            renderer.processEntity(entity);
            for (Entity e : entities) {
                renderer.processEntity(e);
            }
            //renderer.processEntity(entities);
            renderer.render(light, camera);

            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanLists();
        DisplayManager.closeDisplay();
    }
}
