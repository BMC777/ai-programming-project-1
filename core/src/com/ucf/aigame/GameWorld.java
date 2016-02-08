package com.ucf.aigame;

/**
 * Created by Bryan on 1/21/2016.
 */
public class GameWorld
{
    private PlayerEntity playerEntity;
    private GameEntity gameEntity1;
    private GameEntity gameEntity2;

    public GameWorld(int midPointX, int midPointY)
    {
        playerEntity = new PlayerEntity(midPointX, midPointY, 32, 32);
        gameEntity1 = new GameEntity(64, 544, 32, 32);
        gameEntity2 = new GameEntity(544, 544, 32, 32);
    }

    public void update(float delta)
    {
        playerEntity.update(delta);
    }

    public PlayerEntity getPlayerEntity()
    {
        return playerEntity;
    }

    public GameEntity getGameEntity1()
    {
        return gameEntity1;
    }

    public GameEntity getGameEntity2()
    {
        return gameEntity2;
    }
}
