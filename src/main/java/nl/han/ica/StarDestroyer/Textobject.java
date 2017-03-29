package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Textobject extends GameObject {
    private String text;
    private int score;

    public Textobject(String text, int score) {
        this.text = text;
        this.score = score;
    }

    public void update() {
    }

    public void draw(PGraphics g) {
        g.textSize(20);
        g.fill(255);
        g.textAlign(g.LEFT, g.TOP);
        g.text(text + score, getX(), getY());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
