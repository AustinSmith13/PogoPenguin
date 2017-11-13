package edu.angelo.FinalProjectSmith;

import com.badlogic.androidgames.framework.Pixmap;

/**
 * Created by Austin on 12/5/2016.
 * An actor is a renderable game object with behavior.
 */
public abstract class Actor {
    public boolean noCollide = true;                  // gets collision call
    public boolean globalCollide = true;              // does it collide in general
    public Pixmap texture;                            // texture used for rendering
    public Vec2 position = new Vec2(0, 0);            // the actors position
    public Vec2 size = new Vec2(0, 0);                // size of box
    public Vec2 collider = new Vec2(0, 0);            // point collider


    // collision event.
    public abstract void onCollision(Actor sender, Vec2 hit);

    // Update behavior of actor respect to deltatime.
    public abstract void update(float deltatime);
}
