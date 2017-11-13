package edu.angelo.FinalProjectSmith;

/**
 * Created by Austin on 12/5/2016.
 * Keeps track of the pogo penguin state.
 */
public class PenguinActor extends Actor {
    public Vec2 velocity;
    private Vec2 force;

    public PenguinActor(Vec2 startPosition) {
        position = startPosition;
        this.velocity = new Vec2(0, 0);
        this.force = new Vec2(0,0);
        super.globalCollide = true;
        super.noCollide = false;
        super.texture = Assets.penguin;
        super.size.set(32, 64);
        super.collider.set(15, 64);
    }

    /**
     * Collision event sends hit position
     */
    public void onCollision(Actor sender, Vec2 hit) {
        if(velocity.y < 0)
            return;

        Assets.pogo.play(1);
        World.targetCameraYOffset = hit.y-World.WORLD_HEIGHT/2f;
        // correct positioning
        //position.set(hit);
        // Add drag on the x axis velocity of penguin
        velocity.x = velocity.x / 3f;
        // Stop the y velocity
        velocity.y = 0;
        // Send the penguin in the upward direction with a velocity of 10
        addForce(0, -10);

        if(sender.texture == Assets.tramp) {
            addForce(0, -10);
        }
    }

    /**
     * Apply force to penguin.
     * @param force is applied to the penguins velocity.
     */
    public void addForce(Vec2 force) {
        velocity.add(force);
    }

    /**
     * Apply force to penguin.
     */
    public void addForce(float x, float y) {
        velocity.x += x;
        velocity.y += y;
    }


    /**
     * Updates the penguins behavior respect to the deltatime.
     */
    public void update(float deltatime) {
        if(position.y > World.WORLD_HEIGHT - 64 && velocity.y > 0) {
            // Add drag on the x axis velocity of penguin
            velocity.x = velocity.x / 3f;
            // Stop the y velocity
            velocity.y = 0;
            // Send the penguin in the upward direction with a velocity of 10
            addForce(0, -15);
        }

        // Get the x axis acceleration
        float accelX = World.accelHandler.getAccelX();

        // Check the sign of the acceleration X value to decide if the penguin actor should
        // face right or left
        if(accelX < -0.5) {
            texture = Assets.penguino;
        } else if(accelX > 0.5) {
            texture = Assets.penguin;
        }

        // Move the penguin side to side based on the accelerometers x axis
        addForce(-accelX / 10f,0);

        force.set(0, 15*deltatime);
        velocity.add(force);
        position.add(velocity);
    }
}
