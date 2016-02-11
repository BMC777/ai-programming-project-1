package com.ucf.aigame;


/**
 * Created by Bryan on 2/10/2016.
 */
public class Debugger
{
    private GameWorld gameWorld;

    private boolean displayDebugger;
    private boolean displayWallSensor;
    private boolean displayAdjacentAgentSensor;
    private boolean displayPieSliceSensor;

    private float[] wallSensorLengthArray;

    private PlayerEntity playerEntity;

    Debugger(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;

        playerEntity = gameWorld.getPlayerEntity();

        displayDebugger = true;
        displayWallSensor = false;
        displayAdjacentAgentSensor = false;
        displayPieSliceSensor = false;

        wallSensorLengthArray = new float[playerEntity.getWallSensorLengthArray().length];

        for (int i = 0; i < wallSensorLengthArray.length; i++)
        {
            wallSensorLengthArray[i] = 0;
        }
    }

    public void update()
    {
        updateWallSensorLengthArray();
    }

    public boolean getDebugDisplayState()
    {
        return displayDebugger;
    }

    private void updateWallSensorLengthArray()
    {
        wallSensorLengthArray = playerEntity.getWallSensorLengthArray();
    }

    public float getWallSensorLengthOutput(int index)
    {
        return wallSensorLengthArray[index];
    }

    public boolean getAdjacentAgentSensorOutput()
    {
        return displayAdjacentAgentSensor;
    }

    public boolean getPieSliceSensorOutput()
    {
        return displayPieSliceSensor;
    }
}
