package abused_master.javagame.input;

public class InputController {

    public double x, y, z, rotation, xa, za, rotationa;
    public static boolean turnLeft = false, turnRight = false;

    public void tick(boolean forward, boolean backward, boolean left, boolean right, boolean ctrl, boolean jump, boolean crouch) {
        double rotationspeed = 0.008;
        double walkSpeed = 1.0;
        double jumpHeight = 10.0;
        double xMove = 0;
        double zMove = 0;

        if(ctrl)
            walkSpeed = 1.8;

        if (forward)
            zMove++;

        if (backward)
            zMove--;

        if (left)
            xMove--;

        if (right)
            xMove++;

        if (turnLeft)
            rotationa -= rotationspeed;

        if (turnRight)
            rotationa += rotationspeed;

        if(jump)
            y =+ jumpHeight;

        if(crouch)
            return;

        xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
        za += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;

        x += xa;
        y *= 0.9;
        z += za;

        xa *= 0.1;
        za *= 0.1;
        rotation += rotationa;
        rotationa *= 0.8;
    }
}
