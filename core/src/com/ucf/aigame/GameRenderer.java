package com.ucf.aigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Bryan on 1/21/2016.
 */
public class GameRenderer
{
    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private SpriteBatch batcher;
    private ShapeRenderer shapeRenderer;

    private Texture floorTile;

    private float gameWidth;
    private float gameHeight;

    public GameRenderer(GameWorld gameWorld, float gameWidth, float gameHeight)
    {
        this.gameWorld = gameWorld;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        //Sets Camera to Orthographic for 2D view of the screen.
        camera = new OrthographicCamera();
        //false so y increases as it goes up instead of down, bottom left corner is coordinate (0,0)
        camera.setToOrtho(false, gameWidth, gameHeight);

        //Telling SpriteBatch and shapeRenderer to use camera's coordinates when drawing Sprites
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime)
    {
        PlayerEntity playerEntity = gameWorld.getPlayerEntity();

        //OpenGL graphics stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();

        //Fills the screen with floor and wall tiles.
        for (int x = 0; x < gameWidth; x += 32)
        {
            for (int y = 0; y < gameHeight; y += 32)
            {
                if (x == 0 || x == gameWidth - 32 || y == 0 || y == gameHeight - 32)
                {
                    batcher.draw(AssetLoader.wallTile, x, y);
                }
                else
                {
                    batcher.draw(AssetLoader.floorTile, x, y);
                }
            }
        }

        batcher.enableBlending();

        //Drawing an Enemy
        batcher.draw(AssetLoader.playerEntity, playerEntity.getCurrentXPosition(), playerEntity.getCurrentYPosition());

        batcher.end();
    }
}
