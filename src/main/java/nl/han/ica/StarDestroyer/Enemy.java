package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

import java.util.List;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public abstract class Enemy extends GameObject implements ICollidableWithGameObjects{
    protected GameApp app;

    public Enemy(float x, float y, float width, float height, GameApp app) {
        super(x, y, width, height);
        this.app = app;
    }

    public void wrap() {
        if(x > app.getWidth() + width) x = -width;
        else if(x < -width) x = app.getWidth() + width;
        if(y > app.getHeight() + height) y = -height;
        else if(y < -height) y = app.getHeight() + height;
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject c : collidedGameObjects) {
            if (c instanceof Bullet) {
                if (((Bullet)c).getOwner() instanceof Player) {
                    this.action();
                    this.hit();
                    app.deleteGameObject(c);
                }
            }
        }
    }

    public abstract void hit();
    public abstract void action();
}
