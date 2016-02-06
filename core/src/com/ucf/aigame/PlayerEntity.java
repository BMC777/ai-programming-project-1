package com.ucf.aigame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Bryan on 2/3/2016.
 */
public class PlayerEntity
{
    private Vector2 currentVelocity;
    private Vector2 currentPlayerHeading;   //Direction player is facing (Should always be Normalized)
    private Vector2 nextPlayerHeading;

    private int playerWidth;
    private int playerHeight;
    private float xPlayerOrigin;
    private float yPlayerOrigin;

    private float xCurrentWorldPosition;
    private float yCurrentWorldPosition;
    private float xNextWorldPosition;
    private float yNextWorldPosition;

    private float rotationAngle; //Angle between current and next Heading

    private static final float BASE_VELOCITY = 100;
    private static final Vector2 REFERENCE_VECTOR = new Vector2(0, 0);  //Normalized Vector pointing directly
                                                                        // 'North' (90 degrees on Unit Circle)

    PlayerEntity(float xCurrentWorldPosition, float yCurrentWorldPosition, int playerWidth, int playerHeight)
    {
        //Player Sprite dimensions
        this.playerWidth = playerWidth;
        this.playerHeight = playerHeight;

        this.xPlayerOrigin = playerWidth / 2;
        this.yPlayerOrigin = playerHeight / 2;

        //Spawn position
        this.xCurrentWorldPosition = xCurrentWorldPosition;
        this.yCurrentWorldPosition = yCurrentWorldPosition;

        currentPlayerHeading = new Vector2(REFERENCE_VECTOR);   //Player always spawns facing 'East'
        nextPlayerHeading = new Vector2(currentPlayerHeading);
        currentVelocity = new Vector2(); //Velocity is initially 0
    }

    public void update(float timeSinceLastUpdate)
    {
        rotationAngle = currentPlayerHeading.angle();

        //xCurrentWorldPosition += currentVelocity.x * timeSinceLastUpdate;
        //yCurrentWorldPosition += currentVelocity.y * timeSinceLastUpdate;
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
        //Determine the new heading vector
        nextPlayerHeading.x = xCurrentMousePosition - xCurrentWorldPosition;
        nextPlayerHeading.y = yCurrentMousePosition - yCurrentWorldPosition;

        //Normalize the vector;
        nextPlayerHeading = nextPlayerHeading.nor();
        currentPlayerHeading = nextPlayerHeading;   //Update to new calculated heading
    }

   public float getCurrentXPosition()
    {
        return xCurrentWorldPosition;
    }

    public float getCurrentYPosition()
    {
        return yCurrentWorldPosition;
    }

    public float getRotationAngle()
    {
        return rotationAngle;
    }

    public int getWidth()
    {
        return playerWidth;
    }

    public int getHeight()
    {
        return playerHeight;
    }

    public float getXPlayerOrigin()
    {
        return xPlayerOrigin;
    }

    public float getYPlayerOrigin()
    {
        return yPlayerOrigin;
    }
}
