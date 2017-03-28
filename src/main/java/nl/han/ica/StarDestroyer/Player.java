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

    public Player(GameApp app) {
        this.app = app;
        this.life = 3;
        this.shots = 1;
        setFriction(0.04f);
        gas = false;
    }

    @Override
    public void update() {
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
        float newSpeed = getSpeed();
        if (key == 'a') direction += -3;
        if (key == 'd') direction += 3;
        if (key == 'w') newSpeed = (getSpeed() > 4) ? 4 : getSpeed() + 2; gas = !gas;
        setDirectionSpeed(direction, newSpeed);

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
