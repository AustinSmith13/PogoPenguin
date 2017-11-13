package edu.angelo.FinalProjectSmith;

import java.util.Random;

/**
 * Created by Austin on 12/5/2016.
 * Represents a platform that the player jumps on.
 */
public class PlatformActor extends Actor {
    public Vec2 velocity;
    public static float minY;
    public static boolean lf = false;
    Random random = new Random();

    public PlatformActor() {
        super.texture = Assets.platform;
        super.size.set(64, 32); // low width
        super.noCollide = false;
        super.globalCollide = true;
    }

    public void update(float deltatime) {
        // Left to right movement code
        if(position.x > World.WORLD_WIDTH - size.x) {
            position.x = World.WORLD_WIDTH - size.x-1;
            velocity.x = -velocity.x;
        }
        else if(position.x < 0) {
            position.x = 1;
            velocity.x = -velocity.x;
        }

        position.x += velocity.x; // Move the platform

        if(position.y > World.WORLD_HEIGHT + World.cameraYOffset)
        {
            position.y = minY - 50;
            velocity.x = random.nextFloat() * 2f;
            position.x = random.nextFloat() * World.WORLD_WIDTH;

            noCollide = false;
            float r = random.nextFloat();
            if(r > 0.95 && !lf) {
                texture = Assets.platformFaded;
                noCollide = true;
                lf = true;
            }
            else if(r > 0.9) {
                texture = Assets.tramp;
                lf = false;
            }
            else if(r > 0.4) {
                velocity.x = 0;
            }
            else {
                texture = Assets.platform;
                lf = false;
            }

            if(position.y < minY) {
                minY = position.y;
            }
        }
    }

    @Override
    public void onCollision(Actor sender, Vec2 hit) {
        // do nothing
    }
}
