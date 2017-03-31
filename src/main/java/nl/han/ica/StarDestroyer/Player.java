package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PShape;

import java.util.List;

import static processing.core.PApplet.radians;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 * better
 */
public class Player extends GameObject implements ICollidableWithGameObjects {
    private int life;
    private int shots;
    private IPowerup PU;
    private GameApp app;
    private float direction = getDirection();
    private boolean[] keys;
    private PShape ship, engines;
    private PApplet papp;

    public Player(GameApp app) {
        this.app = app;
        this.life = 3;
        this.shots = 1;
        setFriction(0.04f);
        keys = new boolean[4];
    }

    @Override
    public void update() {
        float newSpeed = getSpeed();

        if (keys[0]) direction += -3;
        if (keys[1]) direction += 3;
        if (keys[2]) newSpeed = (getSpeed() > 4) ? 4 : getSpeed() + 2;
        if (keys[3]) shoot();

        setDirectionSpeed(direction, newSpeed);
        setFriction(0.4f);
    }

    @Override
    public void draw(PGraphics g) {
        g.pushMatrix();
        g.translate(super.x, super.y);
        g.rotate(radians(direction));
        g.fill(255);
        g.stroke(255);
        g.rectMode(CENTER);
        g.rect(0, 0, 20, 20);
        g.line(-10, 10, -10, 20);
        g.line(10, 10, 10, 20);
        if (keys[2]) {
            g.fill(255, 128, 0);
            g.noStroke();
            g.triangle(-8, 11, 8, 11, 0, 30);
        }
        g.rotate(radians(-direction));
        g.popMatrix();
    }

    public void keyPressed(int keyCode, char key) {
        if (key == 'a') keys[0] = true;
        if (key == 'd') keys[1] = true;
        if (key == 'w') keys[2] = true;
        if (key == ' ') keys[3] = true;

    }

    public void keyReleased(int keyCode, char key) {
        if (key == 'a') keys[0] = false;
        if (key == 'd') keys[1] = false;
        if (key == 'w') keys[2] = false;
        if (key == ' ') keys[3] = false;

    }


    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
    }


    public void shoot() {
        Bullet bullet = new Bullet(app, this, 5, direction);
        app.addGameObject(bullet);
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
