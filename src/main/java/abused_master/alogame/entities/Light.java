package abused_master.alogame.entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {

    private Vector3f posotion;
    private Vector3f color;

    public Light(Vector3f posotion, Vector3f color) {
        this.posotion = posotion;
        this.color = color;
    }

    public Vector3f getPosotion() {
        return posotion;
    }

    public void setPosotion(Vector3f posotion) {
        this.posotion = posotion;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }
}
