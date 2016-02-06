package com.ucf.aigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Bryan on 1/31/2016.
 */
public class AssetLoader
{
    public static Texture floorTile;
    public static Texture wallTile;
    public static Texture gameEntity;
    public static Texture playerEntity;

    public static void load()
    {
        //Retrieving Floor Tile from assets folder
        floorTile = new Texture(Gdx.files.internal("Floor Tile.png"));
        wallTile =  new Texture(Gdx.files.internal("Wall Tile.png"));
        gameEntity = new Texture(Gdx.files.internal("Game Entity.png"));
        playerEntity = new Texture(Gdx.files.internal("Player.png"));
    }

    public static void dispose()
    {
        floorTile.dispose();
        wallTile.dispose();
    }
}
