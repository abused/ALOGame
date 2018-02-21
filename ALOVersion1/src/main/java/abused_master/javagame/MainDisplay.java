package abused_master.javagame;

import abused_master.javagame.graphics.Screen;
import abused_master.javagame.input.CustomInputHandler;
import abused_master.javagame.input.InputController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

public class MainDisplay extends Canvas implements Runnable {

    public static int WIDTH = 1000;
    public static int HEIGHT = 600;

    private Thread thread;
    private Screen render;
    private BufferedImage img;
    private Game game;
    private boolean running = false;
    private int[] pixels;
    private int frames;
    public int FPS;
    private CustomInputHandler input;
    private int newX = 0, newY = 0, oldX = 0, oldY = 0;

    public MainDisplay() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        this.render = new Screen(WIDTH, HEIGHT);
        this.game = new Game();
        this.img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        this.input = new CustomInputHandler();
        addKeyListener(input);
        addFocusListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);
    }

    private void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public void run() {
        double unprocessedSeconds = 0;
        long previousTime = System.nanoTime();
        double secondsPerTick = 1 / 60.0;
        int tickCount = 0;
        boolean ticked = false;

        requestFocus();

        while (running) {
            long currentTime = System.nanoTime();
            long pastTime = currentTime - previousTime;
            previousTime = currentTime;
            unprocessedSeconds += pastTime / 1000000000.0;

            while (unprocessedSeconds > secondsPerTick) {
                tick();
                unprocessedSeconds -= secondsPerTick;
                ticked = true;
                tickCount++;
                if (tickCount % 60 == 0) {
                    FPS = frames;
                    previousTime += 1000;
                    frames = 0;
                }
            }

            if (ticked) {
                render();
                frames++;
            }

            render();
            frames++;

            newX = CustomInputHandler.mouseX;
            newY  = CustomInputHandler.mouseY;

            if(newX > oldX) {
                InputController.turnRight = true;
            }else if(newX < oldX) {
                InputController.turnLeft = true;
            }else if(newX == oldX) {
                InputController.turnLeft = false;
                InputController.turnRight = false;
            }

            if(newY < oldY) {
                //System.out.println("Moving up");
            }else if(newY > oldY) {
                //System.out.println("Moving down");
            }

            oldX = newX;
            oldY = newY;
        }
    }

    private void tick() {
        game.tick(input.key);
    }

    private void render() {
        String FPSCounter = "FPS: " + FPS;
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        render.render(game);

        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            pixels[i] = render.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.setColor(Color.CYAN);
        g.setFont(new Font("Ariel", 0, 35));
        g.drawString(FPSCounter, 0, 30);
        g.dispose();
        bs.show();
    }

    public static void startGame() throws IOException {
        BufferedImage cursorImage = ImageIO.read(new File("src/main/resources/assets/textures/mouse_cursor.png"));
        Cursor cursos = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "cursorName");
        MainDisplay display = new MainDisplay();
        JFrame frame = new JFrame();
        frame.add(display);

        frame.pack();
        frame.setTitle("ALO Game");
        frame.getContentPane().setCursor(cursos);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

        display.start();
    }
}
