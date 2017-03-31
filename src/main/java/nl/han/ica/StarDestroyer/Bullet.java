package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Bullet extends GameObject {
    private GameApp app;
    private GameObject owner;

    public Bullet(GameApp app, GameObject owner, float initSpeed, float initDirection) {
        super(owner.getX(), owner.getY(), 3, 3);
        this.app = app;
        this.owner = owner;
        setDirectionSpeed(initDirection, initSpeed);
    }

    @Override
    public void update() {
        if (!inBounds()) app.deleteGameObject(this);

    }

    private boolean inBounds() {
        return (super.x < app.getWidth() && super.x > 0 && super.y < app.getHeight() && super.y > 0);
    }

    @Override
    public void draw(PGraphics g) {
        g.stroke(255);
        g.fill(255);
        g.ellipse(super.x, super.y, 3, 3);

    }
}
