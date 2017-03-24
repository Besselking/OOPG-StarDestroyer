package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Player extends GameObject implements ICollidableWithGameObjects {
    private int life;
    private int shots;
    private IPowerup PU;
    private GameApp app;

    public Player(GameApp app) {
        this.app = app;
        this.life = 3;
        this.shots = 1;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(PGraphics g) {
    }


    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
    }


    public void shoot() {
    }


    public void applyPU() {
    }


    public void setLife(int life) {
    }


    public int getLife() {
        return life;
    }


    public void die() {
    }
}
