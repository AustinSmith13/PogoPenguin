package edu.angelo.FinalProjectSmith;

import java.util.Random;

/**
 * Created by Austin on 12/8/2016.
 * Keeps track of the pogo penguin scene
 */
public class PogoPenguinScene extends SceneGraph {

    public static int MAX_PLATFORMS = 15;
    PlatformActor[] platforms;
    Random random = new Random();

    public PogoPenguinScene() {
        super(); // call super's constructor

        PlatformActor.minY = -1;
        // Instantiate all platforms
        platforms = new PlatformActor[MAX_PLATFORMS];
        for(int i = 0; i < MAX_PLATFORMS; i++) {
            platforms[i] = new PlatformActor();
            platforms[i].position.set(random.nextFloat() * World.WORLD_WIDTH, (World.WORLD_HEIGHT / MAX_PLATFORMS) * i);
            platforms[i].velocity = new Vec2(random.nextFloat() * 2, 0);

            if(platforms[i].position.y < PlatformActor.minY)
                PlatformActor.minY = platforms[i].position.y;

            add(platforms[i]);
        }
    }

    @Override
    /**
     * Updates the scene logic
     */
    public void update(float deltatime) {

        for(int i = 0; i < actors.size(); i++) {
            // update actor logic
            actors.get(i).update(deltatime);

            // Wrap the world around on its X axis
            if(actors.get(i).position.x > World.WORLD_WIDTH) {
                actors.get(i).position.x = -actors.get(i).size.x;
            }
            else if(actors.get(i).position.x < -actors.get(i).size.x) {
                actors.get(i).position.x = World.WORLD_WIDTH;
            }
        }

        doCollisions();
    }

    /**
     * Do the collisions part of the behavior
     */
    private void doCollisions() {

        // Collisions for penguin only
        Actor a = World.penguinActor;
        for(int i = 0; i < actors.size(); i++) {
            Actor b = actors.get(i);
            // If they are the same object ignore
            if(a == b)
               continue;

            if(a.position.distance(b.position) < 100f) {
                if (isCollision(b.position, b.size, Vec2.add(a.position, a.collider))) {
                    if(!b.noCollide)
                        a.onCollision(b, a.position); // for now
                }
            }
        }

    }

   /* private boolean isCollision(Vec2 p1, Vec2 s1, Vec2 p2, Vec2 s2) {
        return p1.x < p2.x + s2.x && p1.x + s1.x > p2.x
                && p1.y < p2.y + s2.y && p1.y + s1.y > s2.y;
    } */

    /**
     * Calculates if the point is within the rect defined by pos and size.
     * @param pos position of the rect
     * @param size size of the rect
     * @param point the point tested for collision
     * @return true if collision detected, false otherwise
     */
    private boolean isCollision(Vec2 pos, Vec2 size, Vec2 point) {
        float x1 = pos.x;
        float y1 = pos.y;
        float x2 = pos.x + size.x;
        float y2 = pos.y;

        if(point.x > x1 && point.y < pos.y + size.y && point.x < x2 && point.y > pos.y) {
            return true;
        }
        return false;

        /*
        float x1 = pos.x;
        float y1 = pos.y;
        float x2 = pos.x + size.x;
        float y2 = pos.y;
        float x3 = pos.x + size.x;
        float y3 = pos.y + size.y;
        float x4 = pos.x;
        float y4 = pos.y + size.y / 2f;

        if(pointInsideTriangle(x1, y1, x2, y2, x3, y3, point.x, point.y))
            return true;

        if(pointInsideTriangle(x3, y3, x4, y4, x1, y1, point.x, point.y))
            return true;

        return false;
        */
    }

    boolean pointInsideTriangle(float sx, float sy, float ax, float ay, float bx, float by,
                                float cx, float cy)
    {
        float as_x = sx-ax;
        float as_y = sy-ay;

        boolean s_ab = (bx-ax)*as_y-(by-ay)*as_x > 0;

        if((cx-ax)*as_y-(cy-ay)*as_x > 0 == s_ab) return false;

        if((cx-bx)*(sy-by)-(cy-by)*(sx-bx) > 0 != s_ab) return false;

        return true;
    }
}
