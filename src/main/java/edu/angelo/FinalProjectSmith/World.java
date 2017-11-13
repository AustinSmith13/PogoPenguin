package edu.angelo.FinalProjectSmith;


import com.badlogic.androidgames.framework.impl.AccelerometerHandler;
import java.util.Random;

public class World {
    static final int WORLD_WIDTH = 320;
    static final int WORLD_HEIGHT = 480;
    static final int SCORE_INCREMENT = 10;
    static final int SCORE_DECREMENT = 1;
    static final float TICK_INITIAL = 0.01f;
    public static AccelerometerHandler accelHandler;
   //static final float TICK_DECREMENT = 0.05f;

    public static PenguinActor penguinActor;

    public boolean gameOver = false;;
    public int score = 0;

    float tickTime = 0;
    float tick = TICK_INITIAL;
    public static float targetCameraYOffset;
    public static float cameraYOffset; // Used to scroll the level up and down
    public SceneGraph scene;
    private boolean fast = false;

    public World() {
        penguinActor = new PenguinActor(new Vec2(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f));
        scene = new PogoPenguinScene();
        scene.add(penguinActor);
        targetCameraYOffset = 0f;
        cameraYOffset = 0f;
    }



    /**
     * Contains the update logic of the world
     * @param deltaTime
     */
    public void update(float deltaTime) {
        if (gameOver)
            return;

        // Move the camera up to the target position
        if ((penguinActor.velocity.y < -12 || fast)) {

            fast = true;
            if(penguinActor.velocity.y > -2)
                fast = false;

            //cameraYOffset += -((-100 + penguinActor.position.y - cameraYOffset) * 0.0002) * (penguinActor.position.y - 100);
            cameraYOffset += penguinActor.velocity.y * 0.9f;
            targetCameraYOffset = penguinActor.position.y;

        }
        // Move the camera when the player is moving relatively slow
        else if( (targetCameraYOffset - cameraYOffset) < 0.1f && targetCameraYOffset < cameraYOffset) {
            cameraYOffset -= 4;
            //cameraYOffset += -((targetCameraYOffset - cameraYOffset) * 0.00008) * targetCameraYOffset;
        }

        // If the penguin falls below the screen then game-over
        if(penguinActor.position.y > cameraYOffset + WORLD_HEIGHT)
            gameOver = true;

        // The score is based on elevation
        score = (int)(-cameraYOffset/4f);
        // Update the pogo penguin game scene
        scene.update(deltaTime);



        tickTime += deltaTime;

        while (tickTime > tick) {
            tickTime -= tick;
        }
    }
}
