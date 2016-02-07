package com.ucf.aigame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Bryan on 2/3/2016.
 */
public class PlayerEntity
{;
    private Vector2 currentPlayerVelocity;
    private Vector2 nextPlayerVelocity;
    private Vector2 currentPlayerHeading;   //Direction player is facing (Should always be Normalized)
    private Vector2 nextPlayerHeading;

    private int playerWidth;
    private int playerHeight;
    private int inputX;
    private int inputY;

    private float xPlayerOrigin;
    private float yPlayerOrigin;


    private float xCurrentWorldPosition;
    private float yCurrentWorldPosition;

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
        currentPlayerVelocity = new Vector2(); //Velocity is initially 0
        nextPlayerVelocity = new Vector2(currentPlayerVelocity);
    }

    public void update(float timeSinceLastUpdate)
    {
        currentPlayerHeading.set(nextPlayerHeading);    //Update to new calculated heading
        rotationAngle = currentPlayerHeading.angle();   //Angle new heading was rotated by.

        nextPlayerVelocity.set(inputX, inputY);

        if (inputX != 0 && inputY != 0)
        {
            nextPlayerVelocity.scl(0.5f);
        }

        nextPlayerVelocity.scl(BASE_VELOCITY);
        nextPlayerVelocity.rotate(rotationAngle - 90);
        currentPlayerVelocity.set(nextPlayerVelocity);

        xCurrentWorldPosition += currentPlayerVelocity.x * timeSinceLastUpdate;
        yCurrentWorldPosition += currentPlayerVelocity.y * timeSinceLastUpdate;
    }

    public void moveLeft()
    {
        inputX -= 1;
    }

    public void moveRight()
    {
        inputX += 1;
    }

    public void moveUp()
    {
        inputY += 1;
    }

    public void moveDown()
    {
        inputY -= 1;
    }

    public void rotateToFaceMouse(float xCurrentMousePosition, float yCurrentMousePosition)
    {
        //Determine the new heading vector offset by playerOrigin to align heading with center of sprite
        nextPlayerHeading.x = xCurrentMousePosition - (xCurrentWorldPosition + xPlayerOrigin);
        nextPlayerHeading.y = yCurrentMousePosition - (yCurrentWorldPosition + yPlayerOrigin);

        //Normalize the heading vector
        nextPlayerHeading.nor();
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
