package nl.han.ica.StarDestroyer;

import processing.core.PGraphics;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Alien extends Enemy{
    private GameApp app;

    public Alien(float x, float y, float width, float height, GameApp app) {
        super(x, y, width, height);
        this.app = app;
    }

    @Override
    public void draw(PGraphics g) {
        g.fill(100, 200, 100);
        g.ellipse(x, y, width, height);
        g.fill(255);
        g.ellipse(x-width/4, y-height/5, width/4, height/4);
        g.ellipse(x+width/4, y-height/5, width/4, height/4);
        g.fill(0);
        g.ellipse(x-width/5, y-height/5, width/8, height/8);
        g.ellipse(x+width/5, y-height/5, width/8, height/8);
        g.rect(x-width/4, y);
    }

    @Override
    public void action() {

    }

    @Override
    public void hit() {

    }

    @Override
    public void update() {
        if(x < app.getWidth()/3 || x > (app.getWidth()/3)*2) setDirectionSpeed(90, 3);
        else setDirectionSpeed(135, 3);
        wrap(app);
    }
}
