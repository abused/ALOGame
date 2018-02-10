package abused_master.javagame;

import abused_master.javagame.input.InputController;

import java.awt.event.KeyEvent;

public class Game {

    public int time;
    public InputController controller;

    public Game() {
        controller = new InputController();
    }

    public void tick(boolean[] key) {
        time++;
        boolean forward = key[KeyEvent.VK_W];
        boolean backwards = key[KeyEvent.VK_S];
        boolean left = key[KeyEvent.VK_A];
        boolean right = key[KeyEvent.VK_D];
        boolean ctrl = key[KeyEvent.VK_CONTROL];

        controller.tick(forward, backwards, left, right, ctrl);
    }
}
