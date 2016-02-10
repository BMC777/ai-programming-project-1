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
    private ShapeRenderer shapeRenderer;

    private TextureRegion playerEntityTextureRegion;
    private TextureRegion gameEntityTextureRegion;
    private Texture floorTileTexture;
    private Texture wallTileTexture;

    private PlayerEntity playerEntity;
    private GameEntity gameEntity1;
    private GameEntity gameEntity2;

    private float gameWidth;
    private float gameHeight;

    private static final float TILE_DIMENSIONS = 32;

    private float tempFloat = 0;

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
        for (int x = 0; x < gameWidth; x += TILE_DIMENSIONS)
        {
            for (int y = 0; y < gameHeight; y += TILE_DIMENSIONS)
            {
                if (x == 0 || x == gameWidth - TILE_DIMENSIONS || y == 0 || y == gameHeight - TILE_DIMENSIONS)
                {
                    batcher.draw(wallTileTexture, x, y);
                }
                else
                {
                    batcher.draw(floorTileTexture, x, y);
                }
            }
        }

        batcher.enableBlending();

        //Drawing the playerEntityTexture
        batcher.draw(playerEntityTextureRegion, playerEntity.getCurrentXPosition(), playerEntity.getCurrentYPosition(),
                playerEntity.getXPlayerOrigin(), playerEntity.getYPlayerOrigin(), playerEntity.getWidth(), playerEntity.getHeight(),
                1, 1, playerEntity.getRotationAngle());

        batcher.draw(gameEntityTextureRegion, gameEntity1.getCurrentXPosition(), gameEntity1.getCurrentYPosition(),
                gameEntity1.getXEntityOrigin(), gameEntity1.getYEntityOrigin(), gameEntity1.getWidth(), gameEntity1.getHeight(),
                1, 1, gameEntity1.getRotationAngle());

        batcher.draw(gameEntityTextureRegion, gameEntity2.getCurrentXPosition(), gameEntity2.getCurrentYPosition(),
                gameEntity2.getXEntityOrigin(), gameEntity2.getYEntityOrigin(), gameEntity2.getWidth(), gameEntity2.getHeight(),
                1, 1, gameEntity2.getRotationAngle());

        batcher.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < 5; i++)
        {

            shapeRenderer.rectLine(playerEntity.getWallSensorOriginX(), playerEntity.getWallSensorOriginY(),
                    playerEntity.getWallSensorEndpointX(i), playerEntity.getWallSensorEndpointY(i), 1);

            shapeRenderer.circle(playerEntity.getWallSensorEndpointX(i), playerEntity.getWallSensorEndpointY(i), 4);

            shapeRenderer.rectLine(gameEntity1.getWallSensorOriginX(), gameEntity1.getWallSensorOriginY(),
                    gameEntity1.getWallSensorEndpointX(i), gameEntity1.getWallSensorEndpointY(i), 1);

            shapeRenderer.circle(gameEntity1.getWallSensorEndpointX(i), gameEntity1.getWallSensorEndpointY(i), 4);

            shapeRenderer.rectLine(gameEntity2.getWallSensorOriginX(), gameEntity2.getWallSensorOriginY(),
                    gameEntity2.getWallSensorEndpointX(i), gameEntity2.getWallSensorEndpointY(i), 1);

            shapeRenderer.circle(gameEntity2.getWallSensorEndpointX(i), gameEntity2.getWallSensorEndpointY(i), 4);
        }

        shapeRenderer.end();

        
        // For AdjacentAgentSensor
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Draw AdjacentAgentSensor Circle
        shapeRenderer.circle(playerEntity.getAdjecentAgentSensor().x, playerEntity.getAdjecentAgentSensor().y,
                playerEntity.getAdjecentAgentSensor().radius);

        // Check all GameEntities
        for (int i = 0; i < gameWorld.getEntityList().size(); i++) {

            // Check if detected by AdjacentAgentSensor
            if ( gameWorld.getEntityList().get(i).isDetected() ) {
                shapeRenderer.setColor(255, 0, 0, 1);

                // Draw Circle
                shapeRenderer.circle(gameWorld.getEntityList().get(i).getEntityCenter().x,
                        gameWorld.getEntityList().get(i).getEntityCenter().y,
                        gameWorld.getEntityList().get(i).getWidth());

                // Draw Relative Heading
                shapeRenderer.rectLine(playerEntity.getvOrigin(), gameWorld.getEntityList().get(i).getEntityCenter(), 1);

                // For: PieSliceSensor (detected)
                // Identifies Quadrant and increments its Activation Level
                playerEntity.getPieSliceSensor().identifyQuadrant(
                        playerEntity.getCurrentHeading(),
                        gameWorld.getEntityList().get(i).getEntityCenter().sub(playerEntity.getvOrigin()));
            }
        }

        // Activation Levels have been tallied, Now get highest Activation Level and draw / color lines

        // FRONT RIGHT
        tempFloat = Math.max(playerEntity.getPieSliceSensor().getActivationLevelFRONT(), playerEntity.getPieSliceSensor().getActivationLevelRIGHT());
        if (tempFloat < 1) { // Green
            shapeRenderer.setColor(0, 255, 0, 1);
        }
        else if (tempFloat == 1) { // Yellow
            shapeRenderer.setColor(255, 255, 0, 1);
        }
        else { // Red
            shapeRenderer.setColor(255, 0, 0, 1);
        }
        shapeRenderer.rectLine(playerEntity.getvOrigin(),
                playerEntity.getPieSliceSensor().getFrontRight().add(playerEntity.getvOrigin()), 1);

        // FRONT LEFT
        tempFloat = Math.max(playerEntity.getPieSliceSensor().getActivationLevelFRONT(), playerEntity.getPieSliceSensor().getActivationLevelLEFT());
        if (tempFloat < 1) { // Green
            shapeRenderer.setColor(0, 255, 0, 1);
        }
        else if (tempFloat == 1) { // Yellow
            shapeRenderer.setColor(255, 255, 0, 1);
        }
        else { // Red
            shapeRenderer.setColor(255, 0, 0, 1);
        }
        shapeRenderer.rectLine(playerEntity.getvOrigin(),
                playerEntity.getPieSliceSensor().getFrontLeft().add(playerEntity.getvOrigin()), 1);

        // BACK LEFT
        tempFloat = Math.max(playerEntity.getPieSliceSensor().getActivationLevelBACK(), playerEntity.getPieSliceSensor().getActivationLevelLEFT());
        if (tempFloat < 1) { // Green
            shapeRenderer.setColor(0, 255, 0, 1);
        }
        else if (tempFloat == 1) { // Yellow
            shapeRenderer.setColor(255, 255, 0, 1);
        }
        else { // Red
            shapeRenderer.setColor(255, 0, 0, 1);
        }
        shapeRenderer.rectLine(playerEntity.getvOrigin(),
                playerEntity.getPieSliceSensor().getBackLeft().add(playerEntity.getvOrigin()), 1);

        // BACK RIGHT
        tempFloat = Math.max(playerEntity.getPieSliceSensor().getActivationLevelBACK(), playerEntity.getPieSliceSensor().getActivationLevelRIGHT());
        if (tempFloat < 1) { // Green
            shapeRenderer.setColor(0, 255, 0, 1);
        }
        else if (tempFloat == 1) { // Yellow
            shapeRenderer.setColor(255, 255, 0, 1);
        }
        else { // Red
            shapeRenderer.setColor(255, 0, 0, 1);
        }
        shapeRenderer.rectLine(playerEntity.getvOrigin(),
                playerEntity.getPieSliceSensor().getBackRight().add(playerEntity.getvOrigin()), 1);


        // Reset Color to White and Reset PieSliceSensors
        shapeRenderer.setColor(255, 255, 255, 1);
        playerEntity.getPieSliceSensor().resetActivationLevels();

        shapeRenderer.end();
    }

    private void initializeGameAssets()
    {
        playerEntity = gameWorld.getPlayerEntity();
        gameEntity1 = gameWorld.getGameEntity1();
        gameEntity2 = gameWorld.getGameEntity2();
    }

    private void initializeAssets()
    {
        playerEntityTextureRegion = AssetLoader.playerEntityTextureRegion;
        gameEntityTextureRegion = AssetLoader.gameEntityTextureRegion;
        wallTileTexture = AssetLoader.wallTileTexture;
        floorTileTexture = AssetLoader.floorTileTexture;
    }
}
