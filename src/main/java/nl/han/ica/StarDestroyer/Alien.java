package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Alien extends Enemy{
    private GameApp app;
    private float direction = getDirection();
    private float target;
    private GameObject player;
    private long shoot = 0;

    public Alien(float x, float y, float width, float height, GameApp app, GameObject player) {
        super(x, y, width, height);
        this.app = app;
        this.player = player;
     }

    @Override
    public void draw(PGraphics g) {
        g.ellipseMode(CORNER);
        g.fill(50, 150, 50);
        g.ellipse(x, y, width, height);
        g.fill(255);
        g.ellipse(x+width/2, y+height/5, width/4, height/4);
        g.ellipse(x+width/6, y+height/5, width/4, height/4);
        g.fill(0);
        g.ellipse(x+width/2, y+height/5, width/8, height/8);
        g.ellipse(x+width/6, y+height/5, width/8, height/8);
        g.ellipseMode(CENTER);
    }

    @Override
    public void action() {
        if(shoot%100 == 0) {
            this.target = getAngleFrom(player);
            Bullet bullet = new Bullet(app, this, 5, target);
            app.addGameObject(bullet);
            shoot++;
        } else {
            shoot++;
        }
    }

    @Override
    public void hit() {

    }

    @Override
    public void update() {
        if(x < app.getWidth()/3 || x > (app.getWidth()/3)*2) setDirectionSpeed(90, 3);
        else setDirectionSpeed(135, 3);
        wrap(app);
        action();
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

    }
}
