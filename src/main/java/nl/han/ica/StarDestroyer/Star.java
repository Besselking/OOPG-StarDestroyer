package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Star extends Enemy{
    private boolean blackhole;

    Star(float x, float y, float width, float height) {
        super(x, y, width, height);
        blackhole = false;
    }

    @Override
    public void draw(PGraphics g) {

    }

    @Override
    public void move() {

    }

    @Override
    public void action() {

    }

    @Override
    public void hit() {

    }

    @Override
    public void update() {

    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }
}
