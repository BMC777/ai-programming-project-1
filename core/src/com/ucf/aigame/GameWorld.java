package com.ucf.aigame;

/**
 * Created by Bryan on 1/21/2016.
 */
public class GameWorld
{
    private PlayerEntity playerEntity;

    public GameWorld(int midPointX, int midPointY)
    {
        playerEntity = new PlayerEntity(midPointX, midPointY, 32, 32);
    }

    public void update(float delta)
    {
        playerEntity.update(delta);
    }

    public PlayerEntity getPlayerEntity()
    {
        return playerEntity;
    }
}
