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
    private float smoothness = 0.05f, rot = 0.01f, speed = 0.8f;
    private int minRadius = 50, maxRadius = 80, seed = r.nextInt();
    private boolean direction;

    public Astroid(float x, float y, float width, float height) {
        super(x, y, width, height);
        direction = r.nextBoolean();
    }

    @Override
    public void draw(PGraphics g) {
        PApplet.noiseSeed(seed);
        g.fill(0x8B4513);
        g.stroke(255);
        g.beginShape();
        for (float i = rot; i < rot+360; ++i) {
            float xr = this.x+(map(PApplet.noise((i-rot)*smoothness),0,1,minRadius,maxRadius)) * cos(radians(i));
            float yr = this.y+(map(PApplet.noise((i-rot)*smoothness),0,1,minRadius,maxRadius)) * sin(radians(i));
            g.vertex(xr,yr);
        }
        g.endShape(CLOSE);
        g.noFill();
    }

    public void rotation() {
        rot += 0.5;
        if(rot>=360) rot = 0;
    }

    public void move() {
        //setDirectionSpeed(30, 1);
        if(direction) {
            this.x += speed;
            this.y += speed;
        }
        else {
            this.x -= speed;
            this.y -= speed;
        }
    }

    @Override
    public void hit() {

    }

    @Override
    public void action() {

    }

    @Override
    public void update() {
        rotation();
        move();
        wrap();
    }
}
