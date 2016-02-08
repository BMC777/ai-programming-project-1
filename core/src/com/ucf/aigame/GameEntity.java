package com.ucf.aigame;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bryan on 2/7/2016.
 */
public class GameEntity
{
    private Vector2 currentEntityVelocity;
    private Vector2 nextEntityVelocity;
    private Vector2 currentEntityHeading;   //Direction entity is facing (Should always be Normalized)
    private Vector2 nextEntityHeading;

    private int entityWidth;
    private int entityHeight;
    private int inputX;
    private int inputY;

    private float xEntityOrigin;
    private float yEntityOrigin;


    private float xCurrentWorldPosition;
    private float yCurrentWorldPosition;

    private float rotationAngle; //Angle between current and next Heading

    private static final float BASE_VELOCITY = 125;
    private static final Vector2 REFERENCE_VECTOR = new Vector2(0, 0);  //Normalized Vector pointing to 0 degrees

    GameEntity(float xCurrentWorldPosition, float yCurrentWorldPosition, int entityWidth, int entityHeight)
    {
        //Player Sprite dimensions
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;

        //Center of the Sprite
        this.xEntityOrigin = entityWidth / 2;
        this.yEntityOrigin = entityHeight / 2;

        //Spawn Position
        this.xCurrentWorldPosition = xCurrentWorldPosition;
        this.yCurrentWorldPosition = yCurrentWorldPosition;

        currentEntityHeading = new Vector2(REFERENCE_VECTOR);       //Player always spawns facing 'East'
        nextEntityHeading = new Vector2(currentEntityHeading);
        currentEntityVelocity = new Vector2();                      //Velocity is initially 0
        nextEntityVelocity = new Vector2(currentEntityVelocity);
    }

    public void update(float timeSinceLastUpdate)
    {
        currentEntityHeading.set(nextEntityHeading);    //Update to new calculated heading
        rotationAngle = currentEntityHeading.angle();   //Angle new heading was rotated by.

        nextEntityVelocity.set(inputX, inputY);         //Velocity initialized to basic input velocities

        if (inputX != 0 && inputY != 0)
        {
            nextEntityVelocity.scl(0.5f);               //Diagonal movement should not be faster
        }

        nextEntityVelocity.scl(BASE_VELOCITY);          //Applying the velocity magnitude
        nextEntityVelocity.rotate(rotationAngle - 90);  //Rotating the vector to match the heading
        currentEntityVelocity.set(nextEntityVelocity);  //Update the current velocity

        //Update World position, scaling velocity over timeSinceLastUpdate
        xCurrentWorldPosition += currentEntityVelocity.x * timeSinceLastUpdate;
        yCurrentWorldPosition += currentEntityVelocity.y * timeSinceLastUpdate;
    }

    //Updated by InputHandler
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
        //Determine the new heading vector offset by entityOrigin to align heading with center of sprite
        nextEntityHeading.x = xCurrentMousePosition - (xCurrentWorldPosition + xEntityOrigin);
        nextEntityHeading.y = yCurrentMousePosition - (yCurrentWorldPosition + yEntityOrigin);

        //Normalize the heading vector
        nextEntityHeading.nor();
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
        return entityWidth;
    }

    public int getHeight()
    {
        return entityHeight;
    }

    public float getXEntityOrigin()
    {
        return xEntityOrigin;
    }

    public float getYEntityOrigin()
    {
        return yEntityOrigin;
    }
}
