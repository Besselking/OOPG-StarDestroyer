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
    private int life, shots;
    private IPowerup PU;
    private GameApp app;
    private float direction = getDirection();
    private boolean[] keys;
    private boolean shoot;
    private boolean vulnerable;
    private int iFrames;

    public Player(GameApp app) {
        setWidth(20);
        setHeight(20);
        setFriction(0.04f);
        this.app = app;
        this.life = 3;
        this.shots = 1;
        this.keys = new boolean[4];
        this.vulnerable = false;
    }

    @Override
    public void update() {
        float newSpeed = getSpeed();
        if (!vulnerable) iFrames++;
        if (iFrames > 240) vulnerable = true;

        if (keys[0]) direction += -3;
        if (keys[1]) direction += 3;
        if (keys[2]) newSpeed = 4;
        else newSpeed -= (newSpeed/100);
        if (keys[3]) {
            if (shoot) {
                shoot();
                shoot = false;
            }
        } else shoot = true;

        setDirectionSpeed(direction, newSpeed);
        wrap();
    }

    @Override
    public void draw(PGraphics g) {
        if (iFrames % 2 != 0 && !vulnerable) {
            display(g);
        } else {
            display(g);
        }
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
        for (GameObject c : collidedGameObjects) {
            if (vulnerable) {
                if (c instanceof Enemy) die();
                else if (c instanceof Bullet) {
                    if (((Bullet) c).getOwner() != this) die();
                }
            }
        }
    }


    public void shoot() {
        Bullet bullet = new Bullet(app, this, 5, direction);
        app.addGameObject(bullet);
    }


    public void applyPU() {
    }


    public void setLife(int life) {
        this.life = life;
    }

    private void display(PGraphics g) {
        g.pushMatrix();
        g.translate(super.x, super.y);
        g.rotate(radians(direction));
        g.fill(255,(vulnerable)?255:100);
        g.stroke(255,(vulnerable)?255:100);
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

    public void wrap() {
        if(x > app.getWidth() + width) x = -width;
        else if(x < -width) x = app.getWidth() + width;
        if(y > app.getHeight() + height) y = -height;
        else if(y < -height) y = app.getHeight() + height;
    }

    public int getLife() {
        return life;
    }


    public void die() {
        vulnerable = false;
        iFrames = 0;
        life--;
        setX(app.getWidth() / 2);
        setY(app.getHeight() / 2);
    }
}
