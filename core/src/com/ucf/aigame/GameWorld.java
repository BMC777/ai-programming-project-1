package com.ucf.aigame;

import java.util.ArrayList;

public class GameWorld
{
    private PlayerEntity playerEntity;

    private ArrayList<WallObject> wallObjectArrayList;
    private ArrayList<GameEntity> gameEntityArrayList;

    private static final float TILE_DIMENSIONS = 32;

    public GameWorld(float midPointX, float midPointY, float gameWidth, float gameHeight)
    {
        playerEntity = new PlayerEntity(midPointX, midPointY, TILE_DIMENSIONS, TILE_DIMENSIONS);
        //gameEntity1 = new GameEntity(64, 544, TILE_DIMENSIONS, TILE_DIMENSIONS);
        //gameEntity2 = new GameEntity(544, 544, TILE_DIMENSIONS, TILE_DIMENSIONS);

        gameEntityArrayList = new ArrayList<GameEntity>();
        gameEntityArrayList.add(new GameEntity(64, 544, TILE_DIMENSIONS, TILE_DIMENSIONS));
        gameEntityArrayList.add(new GameEntity(544, 544, TILE_DIMENSIONS, TILE_DIMENSIONS));

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

        for (int i = 0; i < gameEntityArrayList.size(); i++)
        {
            gameEntityArrayList.get(i).update(delta);
        }
    }

    public void newWall(float x, float y)
    {
        wallObjectArrayList.add(new WallObject(x - 32, y - 32, TILE_DIMENSIONS, TILE_DIMENSIONS));
    }

    public void newEntity(float x, float y)
    {
        gameEntityArrayList.add(new GameEntity(x - 32, y - 32, TILE_DIMENSIONS, TILE_DIMENSIONS));
    }

    public PlayerEntity getPlayerEntity()
    {
        return playerEntity;
    }

    public ArrayList<WallObject> getWallList()
    {
        return wallObjectArrayList;
    }

    public ArrayList<GameEntity> getEntityList()
    {
        return gameEntityArrayList;
    }
}
