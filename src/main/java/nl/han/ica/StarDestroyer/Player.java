package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

import static processing.core.PApplet.radians;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 * The player class (small white ship)
 */
public class Player extends GameObject implements ICollidableWithGameObjects {
    private int life, shots, bulletSize;
    private float playerSpeed, bulletSpeed;
    private IPowerup PU;
    private GameApp app;
    private float direction = getDirection();
    private boolean[] keys;
    private boolean shoot;
    private boolean vulnerable;
    private int iFrames;

    /**
     * Constructor
     *
     * @param app reference to the main app
     */
    public Player(GameApp app) {
        setWidth(20);
        setHeight(20);
        setFriction(0.04f);
        this.app = app;
        this.life = 3;
        this.shots = 1;
        this.keys = new boolean[4];
        this.vulnerable = false;
        this.playerSpeed = 4;
        this.bulletSize = 3;
        this.bulletSpeed = 6;
    }

    @Override
    public void update() {
        if (PU != null) applyPU();
        float newSpeed = getSpeed();
        if (!vulnerable) iFrames++;
        if (iFrames > 240) vulnerable = true;

        if (keys[0]) direction += -3;
        if (keys[1]) direction += 3;
        if (keys[2]) newSpeed = playerSpeed;
        else newSpeed -= (newSpeed/100);
        if (keys[3]) {
            if (shoot) {
                shoot();
                shoot = false;
            }
        } else shoot = true;

        setFriction(0.04f);
        setDirectionSpeed(direction, newSpeed);
        wrap();

        this.playerSpeed = 4;
        this.bulletSize = 3;
        this.bulletSpeed = 6;
        this.shots = 1;
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
                } else if (c instanceof IPowerup) PU = (IPowerup) c;
            }
        }
    }

    /**
     * shoots 1 or multiple bullets based on the "shots" variable
     */
    public void shoot() {
        for (int i = 0; i < shots; i++) {
            Bullet bullet = new Bullet(app, this, bulletSpeed, direction + ((i % 2 == 0) ? i : -i - 1), bulletSize);
            app.addGameObject(bullet);
        }
    }

    /**
     * calls the apply function in the current active power-up
     */
    public void applyPU() {
        PU.apply(this);
    }

    /**
     * sets the speed of the player to the amount given by the active power-up
     *
     * @param amount the new speed
     */
    public void addSpeed(float amount) {
        playerSpeed = amount;
    }

    /**
     * sets the amount of bullets the player spawns in shoot()
     * @param amount amount of bullets
     */
    public void addShots(int amount) {
        shots = amount;
    }

    /**
     * adds a life to the player
     * @param amount amount of lifes to be added
     */
    public void addLife(int amount) {
        life += amount;
        PU = null;
    }

    /**
     * makes the player shoot slower and bigger bullets
     */
    public void makeFatBullets() {
        bulletSize = 10;
        bulletSpeed = 4;
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


    /**
     * causes the player to wrap around the screen when going out of bounds
     */
    public void wrap() {
        if(x > app.getWidth() + width) x = -width;
        else if(x < -width) x = app.getWidth() + width;
        if(y > app.getHeight() + height) y = -height;
        else if(y < -height) y = app.getHeight() + height;
    }

    /**
     * getter for the life variable
     * @return the current amount of lifes
     */
    public int getLife() {
        return life;
    }

    /**
     * called when the player is supposed to lose a life
     * the player will als0 be invulnerable for a few seconds and loses his power-up
     */
    public void die() {
        vulnerable = false;
        PU = null;
        iFrames = 0;
        life--;
        setX(app.getWidth() / 2);
        setY(app.getHeight() / 2);
    }
}
