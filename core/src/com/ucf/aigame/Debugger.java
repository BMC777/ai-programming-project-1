package com.ucf.aigame;


/**
 * Created by Bryan on 2/10/2016.
 */
public class Debugger
{
    private GameWorld gameWorld;
    private boolean displayDebugger;
    private float[] wallSensorLengthArray;
    private PlayerEntity playerEntity;

    Debugger(GameWorld gameWorld)
    {
        // Instantiate objects; no  display by default
        this.gameWorld = gameWorld;
        playerEntity = gameWorld.getPlayerEntity();

        displayDebugger = false;

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

    public void setDebugDisplayState(boolean setTo)
    {
        displayDebugger = setTo;
    }

    public boolean getDebugDisplayState()
    {
        return displayDebugger;
    }

    public float getWallSensorLengthOutput(int index)
    {
        return wallSensorLengthArray[index];
    }

    private void updateWallSensorLengthArray()
    {
        wallSensorLengthArray = playerEntity.getWallSensorLengthArray();
    }
}
