package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Multishot extends GameObject implements IPowerup {
    private int shots;
    private boolean show;

    /**
     * Constructor
     *
     * @param x     x location
     * @param y     y location
     * @param shots amount of shots the player shoots when this powerup is active
     */
    public Multishot(GameApp app, float x, float y, int shots) {
        super(x, y, 20, 20);
        this.shots = shots;
        this.show = true;
    }


    public void update() {
    }


    public void draw(PGraphics g) {
        if (show) {
            g.noStroke();
            g.fill(0x0e, 0x72, 0xd6);
            g.rect(super.x, super.y, super.width, super.height);
            g.fill(255);
            g.text(shots, super.x, super.y);
        }
    }


    @Override
    public void apply(Player player) {
        show = false;
        player.addShots(shots);
    }
}
