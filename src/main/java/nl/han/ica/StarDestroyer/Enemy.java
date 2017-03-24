package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public abstract class Enemy extends GameObject{

    public Enemy(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void wrap() {

    }

    public abstract void hit();
    public abstract void action();
}
