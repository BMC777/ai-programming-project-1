package com.ucf.aigame;

import java.util.ArrayList;

/**
 * Created by Bryan on 1/21/2016.
 */
public class GameWorld
{
    private PlayerEntity playerEntity;
    private GameEntity gameEntity1;
    private GameEntity gameEntity2;

    private ArrayList<WallObject> wallObjectArrayList;
    private ArrayList<GameEntity> gameEntityArrayList;

    private static final float TILE_DIMENSIONS = 32;

    public GameWorld(float midPointX, float midPointY, float gameWidth, float gameHeight)
    {
        playerEntity = new PlayerEntity(midPointX, midPointY, TILE_DIMENSIONS, TILE_DIMENSIONS);
        gameEntity1 = new GameEntity(64, 544, TILE_DIMENSIONS, TILE_DIMENSIONS);
        gameEntity2 = new GameEntity(544, 544, TILE_DIMENSIONS, TILE_DIMENSIONS);

        gameEntityArrayList = new ArrayList<GameEntity>();
        gameEntityArrayList.add(gameEntity1);
        gameEntityArrayList.add(gameEntity2);

        wallObjectArrayList = new ArrayList<WallObject>();

        //Fills the screen edges with Wall Objects.
        for (int x = 0; x < gameWidth; x += TILE_DIMENSIONS)
        {
            for (int y = 0; y < gameHeight; y += TILE_DIMENSIONS)
            {
                if (x == 0 || x == gameWidth - TILE_DIMENSIONS || y == 0 || y == gameHeight - TILE_DIMENSIONS)
                {
                    wallObjectArrayList.add(new WallObject(x, y, TILE_DIMENSIONS, TILE_DIMENSIONS));
                }
            }
        }
    }

    public void update(float delta)
    {
        playerEntity.update(delta);
        gameEntity1.update(delta);
        gameEntity2.update(delta);
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

    public ArrayList<WallObject> getWallList()
    {
        return wallObjectArrayList;
    }

    public ArrayList<GameEntity> getEntityList() {
        return gameEntityArrayList;
    }
}
