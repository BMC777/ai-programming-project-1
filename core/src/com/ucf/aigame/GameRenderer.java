package com.ucf.aigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Bryan on 1/21/2016.
 */
public class GameRenderer
{
    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private SpriteBatch batcher;

    private TextureRegion playerEntityTextureRegion;
    private Texture floorTile;

    private PlayerEntity playerEntity;

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

        initializeGameAssets();
        initializeAssets();
    }

    public void render(float runTime)
    {

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
                    batcher.draw(AssetLoader.wallTileTexture, x, y);
                }
                else
                {
                    batcher.draw(AssetLoader.floorTileTexture, x, y);
                }
            }
        }

        batcher.enableBlending();

        //Drawing the playerEntityTexture
        batcher.draw(playerEntityTextureRegion, playerEntity.getCurrentXPosition(), playerEntity.getCurrentYPosition(),
                playerEntity.getXPlayerOrigin(), playerEntity.getYPlayerOrigin(), playerEntity.getWidth(), playerEntity.getHeight(),
                1, 1, playerEntity.getRotationAngle());

        batcher.end();
    }

    private void initializeGameAssets()
    {
        playerEntity = gameWorld.getPlayerEntity();

    }

    private void initializeAssets()
    {
        playerEntityTextureRegion = AssetLoader.playerEntityTextureRegion;
    }
}
