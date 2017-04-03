package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Star extends Enemy{
    private boolean blackhole;
    private int hits = 0;

    Star(float x, float y, float width, float height, GameApp app) {
        super(x, y, width, height, app);
        blackhole = false;
    }

    @Override
    public void draw(PGraphics g) {
        g.ellipseMode(CORNER);
        int edge = 10;
        if (blackhole) g.fill(3, 152, 193, 100);
        else g.fill(249, 222, 14);
        g.ellipse(x, y, width, height);
        if (blackhole) g.fill(0);
        else g.fill(209, 58, 8, 150);
        g.ellipse(x+edge/2, y+edge/2, width-edge, height-edge);

    }

    @Override
    public void action() {
        for(GameObject g : app.getGameObjectItems()){
            if(g instanceof Player || g instanceof IPowerup) g.setFriction(0.8f);
            else g.setDirectionSpeed(g.getAngleFrom(this), 2);
        }
    }

    @Override
    public void hit() {
        blackhole = true;
        hits++;
    }

    @Override
    public void updateEnemyObject() {
        if(!blackhole) {
            if (x < app.getWidth() / 3 || x > (app.getWidth() / 3) * 2) setDirectionSpeed(90, 3);
            else setDirectionSpeed(45, 3);
        } else {
            action();
            setSpeed(0);
        }
        if(hits > 10) app.deleteGameObject(this);
        wrap();

    }
}
