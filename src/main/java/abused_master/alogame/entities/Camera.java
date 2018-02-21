package abused_master.alogame.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import java.awt.event.MouseEvent;
import java.security.Key;

public class Camera {

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;
    private float ctrlModifier = 0.16f;
    private int oldX = 0, oldY = 0, newX = 0, newY = 0;

    public Camera() {
    }

    public void move() {
        if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            ctrlModifier = 0.32f;
        }else if(!Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            ctrlModifier = 0.16f;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.z -= ctrlModifier;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.z += ctrlModifier;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.x += ctrlModifier;
        }


        if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.x -= ctrlModifier;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            position.y += ctrlModifier;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            position.y -= ctrlModifier;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            yaw -= 0.8;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            yaw += 0.8;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            pitch -= 0.5;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            pitch += 0.5;
        }

        //if(Mouse.isButtonDown(1)) {
            float movementSpeed = 1.0f;
            newX = Mouse.getX();
            newY = Mouse.getY();

            if(newX > oldX) {
                yaw += movementSpeed;
            }if (newX < oldX) {
                yaw -= movementSpeed;
            }

            if(newY > oldY) {
                pitch -= movementSpeed;
            }else if(newY < oldY) {
                pitch += movementSpeed;
            }

            oldX = newX;
            oldY = newY;
        //}
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
}
