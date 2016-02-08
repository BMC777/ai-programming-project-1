package com.ucf.aigame;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

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


    CollisionDetector (GameWorld gameWorld)
    {
        this.playerEntity = gameWorld.getPlayerEntity();
        this.gameEntity1 = gameWorld.getGameEntity1();
        this.gameEntity2 = gameWorld.getGameEntity2();
        this.wallList = gameWorld.getWallList();

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
    }
}
