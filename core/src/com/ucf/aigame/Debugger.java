package com.ucf.aigame;

/**
 * Created by Bryan on 2/10/2016.
 */
public class Debugger
{
    private boolean displayDebugger;
    private boolean displayWallSensor;
    private boolean displayAdjacentAgentSensor;
    private boolean displayPieSliceSensor;

    Debugger()
    {
        displayDebugger = false;
        displayWallSensor = false;
        displayAdjacentAgentSensor = false;
        displayPieSliceSensor = false;
    }

    public void update()
    {

    }

    public boolean getDebugDisplayState()
    {
        return displayDebugger;
    }

}
