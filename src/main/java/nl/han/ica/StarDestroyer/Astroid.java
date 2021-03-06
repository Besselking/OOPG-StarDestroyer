package nl.han.ica.StarDestroyer;

import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.Random;

import static processing.core.PApplet.*;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Astroid extends Enemy{
    private Random r = new Random();
    private PApplet PApplet = new PApplet();
    private float smoothness = 0.05f, rot = 0.01f;
    private float minRadius, maxRadius;
    private int seed, direction, type;


    /**
     * Constructor
     *
     * @param x    x location
     * @param y    y location
     * @param type a value beteen 1 2 and 3 that changes the size of the asteroid
     * @param app  references the main app
     */
    public Astroid(float x, float y, int type, GameApp app) {
        super(x, y, type*35f, type*35f, app);
        this.direction = r.nextInt();
        this.seed = r.nextInt();
        this.type = type;
        this.minRadius = type*15.7f;
        this.maxRadius = type*26.7f;
    }

    @Override
    public void draw(PGraphics g) {
        PApplet.noiseSeed(seed);
        g.fill(80, 30,10);
        g.stroke(80);
        g.beginShape();
        for (float i = rot; i < rot+360; ++i) {
            float xr = this.x+(map(PApplet.noise((i-rot)*smoothness),0,1,minRadius,maxRadius)) * cos(radians(i)) + width/2;
            float yr = this.y+(map(PApplet.noise((i-rot)*smoothness),0,1,minRadius,maxRadius)) * sin(radians(i)) + height/2;
            g.vertex(xr,yr);
        }
        g.endShape(CLOSE);
    }

    /**
     * makes the asteroid rotate slowly
     */
    public void rotation() {
        rot += 0.5;
        if(rot>=360) rot = 0;
    }


    /**
     * destroys the asteroid if it's hit
     */
    @Override
    public void hit() {
        app.deleteGameObject(this);
    }


    /**
     * spawns new asteroids when it's hit and adds points to the score
     */
    @Override
    public void action() {
        if(this.type > 1) {
            for (int i = 0; i < 2; i++) {
                app.addGameObject(new Astroid(this.getX(), this.getY(), this.type - 1, app));
            }
            app.addScore(100);
        } else app.addScore(150);
    }

    /**
     * moves and calls helper-functions
     */
    @Override
    public void updateEnemyObject() {
        rotation();
        setDirectionSpeed(direction, 2);
        wrap();
    }
}
