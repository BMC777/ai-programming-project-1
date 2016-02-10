package com.ucf.aigame;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Steven on 2/8/2016.
 */
public class AdjacentAgentSensor {

    private static final float ROTATION_SPEED = 1; // How many degrees to rotate per update
    private float currentAngle = 0;

    private float sensorRange;
    private Vector2 vOrigin;

    private Circle sensor;

    public AdjacentAgentSensor(float sensorRange, float xOrigin, float yOrigin) {

        this.sensorRange = sensorRange;
        vOrigin = new Vector2(xOrigin, yOrigin);

        sensor = new Circle(vOrigin, sensorRange);
    }

    public void update(float xOrigin, float yOrigin) {


        // Recenter the axis of rotation
        vOrigin.set(xOrigin, yOrigin);

        // Recenter Circle
        sensor.setPosition(vOrigin);
    }

    public Vector2 getvOrigin() {
        return vOrigin;
    }

    public Circle getSensor() {
        return sensor;
    }
}
