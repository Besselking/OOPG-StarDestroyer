package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

/**
 * Created by Marijn Besseling on 22-Mar-17.
 */
public class GameApp extends GameEngine {
    private Textobject dashText;
    private Player player;


    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.StarDestroyer.GameApp"});
    }

    public void setupGame() {
        int worldWidth = 1000;
        int worldHeight = 900;

        createViewWithoutViewport(worldWidth, worldHeight);
        createObjects();
    }

    private void createObjects() {
        player = new Player(this);
        addGameObject(player, width / 2, height / 2);

        Dashboard dashBoard = new Dashboard(0, 0, width, 100);
        dashText = new Textobject("score", 0);
        dashBoard.addGameObject(dashText);
        addDashboard(dashBoard);
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(20, 20, 20);

        setView(view);
        size(screenWidth, screenHeight);
    }

    public void update() {
    }
}
