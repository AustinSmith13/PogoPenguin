package edu.angelo.FinalProjectSmith;

/**
 * Created by Austin on 12/8/2016.
 * Holds a X and Y component.
 */
public class Vec2 {
    float x, y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 operand) {
        this.x = this.x + operand.x;
        this.y = this.y + operand.y;
        return new Vec2(x, y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vec2 operand) {
        this.x = operand.x;
        this.y = operand.y;
    }

    public float distance(Vec2 operand) {
        return (float)Math.sqrt(Math.pow(operand.x - x, 2) + Math.pow(operand.y - y, 2));
    }

    public static Vec2 add(Vec2 operand1, Vec2 operand2) {
        return new Vec2(operand1.x + operand2.x, operand1.y + operand2.y);
    }
}
