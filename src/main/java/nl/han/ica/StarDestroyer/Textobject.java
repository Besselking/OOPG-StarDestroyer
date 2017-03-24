package nl.han.ica.StarDestroyer;

/**
 * Created by Marijn Besseling on 24-Mar-17.
 */
public class Textobject {
    private String text;
    private int score;

    public Textobject(String text, int score) {
        this.text = text;
        this.score = score;
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
