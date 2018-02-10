package abused_master.javagame.input;

import java.awt.event.*;

public class CustomInputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {

    public boolean[] key = new boolean[68836];
    public static int mouseX;
    public static int mouseY;

    //Use will come l8tr
    public boolean displayFPS = true;

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode > 0 && keyCode < key.length) {
            key[keyCode] = true;
        }

        //checking if Insert keycode, change l8tr
        if(keyCode == 45) {
            if(displayFPS) {
                displayFPS = false;
            }else {
                displayFPS = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode > 0 && keyCode < key.length) {
            key[keyCode] = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        for (int i = 0; i < key.length; i++) {
            key[i] = false;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //clicked mouse
        System.out.println("X: " + mouseX + " Y: " + mouseY);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //entered particular component
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //clicked and dragged
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
