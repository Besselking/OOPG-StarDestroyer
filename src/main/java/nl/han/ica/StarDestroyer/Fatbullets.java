package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Fatbullets extends GameObject implements IPowerup {
    private boolean show;

    /**
     * Constructor
     *
     * @param x x location
     * @param y y location
     */
    public Fatbullets(GameApp app, float x, float y) {
        super(x, y, 20, 20);
        this.show = true;

    }

    public void update() {
    }


    public void draw(PGraphics g) {
        if (show) {
            g.fill(0xce ,0x5c, 0x10);
            g.rect(super.x, super.y, super.width, super.height);
            g.fill(255);
            g.text('F', super.x, super.y);
        }
    }


    public void apply(Player player) {
        show = false;
        player.makeFatBullets();
    }
}