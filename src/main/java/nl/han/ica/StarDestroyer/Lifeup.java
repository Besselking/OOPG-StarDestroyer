package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Marijn Besseling & Hendrik Camps on 24-Mar-17.
 */
public class Lifeup extends GameObject implements IPowerup {
    private GameApp app;
    private boolean show;


    public Lifeup(GameApp app, float x, float y) {
        super(x, y, 20, 20);
        this.app = app;
        this.show = true;
    }


    public void update() {
    }


    public void draw(PGraphics g) {
        if (show) {
            g.fill(0xd6, 0x0d, 0xc2);
            g.rect(super.x, super.y, super.width, super.height);
            g.fill(255);
            g.text("1up", super.x, super.y);
        }
    }


    public void apply(Player player) {
        show = false;
        player.addLife(1);
        app.deleteGameObject(this);
    }
}
