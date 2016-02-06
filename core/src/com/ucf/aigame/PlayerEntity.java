package com.ucf.aigame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Bryan on 2/3/2016.
 */
public class PlayerEntity
{
    private Vector2 currentVelocity;
    private Vector2 currentPlayerHeading; //Direction player is facing (Should always be Normalized)

    private int playerWidth;
    private int playerHeight;

    private float xCurrentWorldPosition;
    private float yCurrentWorldPosition;
    private float xNextWorldPosition;
    private float yNextWorldPosition;

    private float currentRotationAngle; //Angle in which player is facing with reference to 0 (degrees)

    private static final float BASE_VELOCITY = 100;
    private static final float REFERENCE_ANGLE = 0; //0 degrees on Unit Circle


    PlayerEntity(float xCurrentWorldPosition, float yCurrentWorldPosition, int playerWidth, int playerHeight)
    {
        //Player Sprite dimensions
        this.playerWidth = playerWidth;
        this.playerHeight = playerHeight;

        //Spawn position
        this.xCurrentWorldPosition = xCurrentWorldPosition;
        this.yCurrentWorldPosition = yCurrentWorldPosition;

        currentRotationAngle = 90; //Player always spawns facing 'North'

        currentVelocity = new Vector2(); //Velocity is initially 0
    }

    public void update(float timeSinceLastUpdate)
    {
        //currentPosition.add((currentVelocity.cpy().scl(delta)));
        System.out.println(currentVelocity.x);
        System.out.println(currentVelocity.y);
        System.out.println();
    }

    public void moveLeft()
    {
        currentVelocity.x += BASE_VELOCITY * MathUtils.cosDeg(180);
    }

    public void moveRight()
    {
        currentVelocity.x += BASE_VELOCITY * MathUtils.cosDeg(0);
    }

    public void moveUp()
    {
        currentVelocity.y += BASE_VELOCITY * MathUtils.sinDeg(90);
    }

    public void moveDown()
    {
        currentVelocity.y += BASE_VELOCITY * MathUtils.sinDeg(270);
    }

    public void rotateToFaceMouse(float xCurrentMousePosition, float yCurrentMousePosition)
    {

    }

   public float getCurrentXPosition()
    {
        return xCurrentWorldPosition;
    }

    public float getCurrentYPosition()
    {
        return yCurrentWorldPosition;
    }

    public int getWidth()
    {
        return playerWidth;
    }

    public int getHeight()
    {
        return playerHeight;
    }
}
