package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Marijn Besseling & Hendrik Camps on 24-Mar-17.
 */
public class Bullet extends GameObject {
    private GameApp app;
    private GameObject owner;

    public Bullet(GameApp app, GameObject owner, float initSpeed, float initDirection, int size) {
        super(owner.getX(), owner.getY(), size, size);
        this.app = app;
        this.owner = owner;
        setDirectionSpeed(initDirection, initSpeed);
    }

    public GameObject getOwner() {
        return owner;
    }

    @Override
    public void update() {
        if (!inBounds()) app.deleteGameObject(this);
    }

    private boolean inBounds() {
        return (super.x < app.getWidth()+10 && super.x > -10 && super.y < app.getHeight()+10 && super.y > -10);
    }

    @Override
    public void draw(PGraphics g) {
        g.stroke(255);
        g.fill(255);
        g.ellipse(super.x, super.y, super.width, super.height);
    }

}
