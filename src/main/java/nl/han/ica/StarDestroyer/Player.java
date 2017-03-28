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
    private float direction = getDirection();

    public Player(GameApp app) {
        this.app = app;
        this.life = 3;
        this.shots = 1;
        setDirection(0);
    }

    @Override
    public void update() {
        setDirection(direction += 0.01);
    }

    @Override
    public void draw(PGraphics g) {
        g.translate(super.x, super.y);
        g.rotate(getDirection() - 90);
        g.fill(255);
        g.stroke(255);
        g.rectMode(CENTER);
        g.rect(0, 0, 20, 20);
        g.line(-10, 10, -10, 20);
        g.line(10, 10, 10, 20);
        g.fill(255, 128, 0);
        g.noStroke();
        g.triangle(-8, 11, 8, 11, 0, 30);
        g.rotate(-getDirection());
        g.translate(-super.x, -super.y);
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
