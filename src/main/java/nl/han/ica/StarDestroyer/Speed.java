package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Marijn Besseling & Hendrik Camps on 24-Mar-17.
 */
public class Speed extends GameObject implements IPowerup {
    private GameApp app;
    private boolean show;
    private int speed;


    public Speed(GameApp app, float x, float y, int speed) {
        super(x, y, 20, 20);
        this.app = app;
        this.speed = speed;
        show = true;
    }


    public void update() {
    }


    public void draw(PGraphics g) {
        if (show) {
            g.fill(0xf4eb42);
            g.rect(super.x, super.y, super.width, super.height);
            g.text(speed, super.x, super.y);
        }
    }


    public void apply(Player player) {
        show = false;
        player.addSpeed(speed);
    }
}