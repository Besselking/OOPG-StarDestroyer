package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

import java.util.List;

/**
 * Created by Marijn Besseling & Hendrik Camps on 24-Mar-17.
 */
public abstract class Enemy extends GameObject implements ICollidableWithGameObjects{
    protected GameApp app;

    /**
     * Constructor
     *
     * @param x      x location of the enemy
     * @param y      y location of the enemy
     * @param width  width of the enemy
     * @param height height of the enemy
     * @param app    references the main app
     */
    public Enemy(float x, float y, float width, float height, GameApp app) {
        super(x, y, width, height);
        this.app = app;
    }

    /**
     * causes the enemy to wrap around the screen when going out of bounds
     */
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

    @Override
    public void update() {
        updateEnemyObject();
    }

    public abstract void hit();
    public abstract void action();
    public abstract void updateEnemyObject();
}
