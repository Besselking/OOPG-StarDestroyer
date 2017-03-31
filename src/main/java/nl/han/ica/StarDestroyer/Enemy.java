package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public abstract class Enemy extends GameObject implements ICollidableWithGameObjects{

    public Enemy(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void wrap(GameApp app) {
        if(x > app.getWidth() + width) x = -width;
        else if(x < -width) x = app.getWidth() + width;
        if(y > app.getHeight() + height) y = -height;
        else if(y < -height) y = app.getHeight() + height;
    }

    public abstract void hit();
    public abstract void action();
}
