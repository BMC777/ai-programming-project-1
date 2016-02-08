package com.ucf.aigame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bryan on 2/7/2016.
 */
public class WallSensor
{
    private Vector2 wallSensorArray[];

    private float wallSensorRange;

    private static final int NUMBER_OF_RAYS = 5;

    WallSensor(float wallSensorRange)
    {
        this.wallSensorRange = wallSensorRange;

        wallSensorArray = new Vector2[NUMBER_OF_RAYS];

        for (int i = 0; i < NUMBER_OF_RAYS; i++)
        {
            wallSensorArray[i] = new Vector2();
        }
    }

    public void update(Vector2 currentHeading)
    {
        float rotateSensorDegrees = 60;

        for (int i = 0; i < NUMBER_OF_RAYS; i++)
        {
            wallSensorArray[i].set(currentHeading);
            wallSensorArray[i].rotate(rotateSensorDegrees);
            wallSensorArray[i].scl(wallSensorRange);

            rotateSensorDegrees -= 30;
        }
    }

    public Vector2 getSensorArray(int i)
    {
        return wallSensorArray[i];
    }
}
