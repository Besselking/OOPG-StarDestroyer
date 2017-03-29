package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

import static processing.core.PApplet.radians;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Player extends GameObject implements ICollidableWithGameObjects {
    private int life;
    private int shots;
    private IPowerup PU;
    private GameApp app;
    private float direction = getDirection();
    private boolean gas;
    private boolean[] keys;

    public Player(GameApp app) {
        this.app = app;
        this.life = 3;
        this.shots = 1;
        setFriction(0.04f);
        gas = false;
        keys = new boolean[3];
    }

    @Override
    public void update() {
        float newSpeed = getSpeed();

        if (keys[0]) direction += -3;
        if (keys[1]) direction += 3;
        if (keys[2]) newSpeed = (getSpeed() > 4) ? 4 : getSpeed() + 2;

        setDirectionSpeed(direction, newSpeed);
        setFriction(0.4f);
        gas = false;
    }

    @Override
    public void draw(PGraphics g) {
        g.translate(super.x, super.y);
        g.rotate(radians(direction));
        g.fill(255);
        g.stroke(255);
        g.rectMode(CENTER);
        g.rect(0, 0, 20, 20);
        g.line(-10, 10, -10, 20);
        g.line(10, 10, 10, 20);
        if (gas) {
            g.fill(255, 128, 0);
            g.noStroke();
            g.triangle(-8, 11, 8, 11, 0, 30);
        }
        g.rotate(radians(-direction));
        g.translate(-super.x, -super.y);
    }

    public void keyPressed(int keyCode, char key) {
        gas = true;
        if (key == 'a') keys[0] = true;
        if (key == 'd') keys[1] = true;
        if (key == 'w') keys[2] = true;
        if (key == ' ') shoot();

    }

    public void keyReleased(int keyCode, char key) {
        if (key == 'a') keys[0] = false;
        if (key == 'd') keys[1] = false;
        if (key == 'w') keys[2] = false;
    }


    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
    }


    public void shoot() {
    }


    public void applyPU() {
    }


    public void setLife(int life) {
    }


    public int getLife() {
        return life;
    }


    public void die() {
    }
}
