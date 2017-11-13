package edu.angelo.FinalProjectSmith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Austin on 12/5/2016.
 * base class
 */
public abstract class SceneGraph {
    protected List<Actor> actors;

    public SceneGraph() {
        actors = new ArrayList();
    }

    /**
     * Adds the actor to the scene for rendering and collisions.
     * @param actor the actor to be added.
     */
    public void add(Actor actor) {
        actors.add(actor);
    }

    /**
     * The update behavior of the scene.
     * @param deltatime difference time of last frame.
     */
    public abstract void update(float deltatime);
}
