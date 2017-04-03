package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Textobject extends GameObject {
    private String text;
    private int data;


    /**
     * Constructor
     *
     * @param text text to be displayed
     * @param data data to be displayed after text
     */
    public Textobject(String text, int data) {
        this.text = text;
        this.data = data;
    }

    public void update() {
    }

    public void draw(PGraphics g) {
        g.textSize(20);
        g.fill(255);
        g.textAlign(g.LEFT, g.TOP);
        g.text(text + data, getX(), getY());
    }

    /**
     * sets the data to the given parameter
     *
     * @param data data to be set
     */
    public void setData(int data) {
        this.data = data;
    }
}
