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
        if(x > 1000 + width) x = -width;
        else if(x < -width) x = 1000 + width;
        if(y > 900 + height) y = -height;
        else if(y < -height) y = 900 + height;
    }

    public abstract void hit();
    public abstract void action();
}
