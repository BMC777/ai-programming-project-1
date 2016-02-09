package com.ucf.aigame;

import com.badlogic.gdx.math.Intersector;

import java.util.ArrayList;

/**
 * Created by Bryan on 2/8/2016.
 */
public class CollisionDetector
{
    private Intersector intersector;

    private PlayerEntity playerEntity;
    private GameEntity gameEntity1;
    private GameEntity gameEntity2;

    private ArrayList<WallObject> wallList;
    private ArrayList<GameEntity> entityList;


    CollisionDetector (GameWorld gameWorld)
    {
        this.playerEntity = gameWorld.getPlayerEntity();
        this.gameEntity1 = gameWorld.getGameEntity1();
        this.gameEntity2 = gameWorld.getGameEntity2();
        this.wallList = gameWorld.getWallList();
        this.entityList = gameWorld.getEntityList();

        intersector = new Intersector();
    }

    public void checkCollisions()
    {
        for (int i = 0; i < wallList.size(); i++)
        {
            if (intersector.overlaps(playerEntity.getCollisionBox(), wallList.get(i).getCollisionBox()))
            {
                playerEntity.collisionStop(); //Not working
            }
        }

        for (int i = 0; i < entityList.size(); i++) {
            // Check collision between AdjacentAgentSensor and GameEntity
            if (intersector.overlaps(playerEntity.getAdjecentAgentSensor(), entityList.get(i).getCollisionBox())) {
                entityList.get(i).setDetection(true);
            }
            else {
                entityList.get(i).setDetection(false);
            }
        }
    }
}
