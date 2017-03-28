package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Marijn Besseling on 22-Mar-17.
 */
public class GameApp extends GameEngine {
    private Textobject dashText, boardText;
    private Player player;
    private ArrayList<Enemy> enemys = new ArrayList<>();
    private Random r = new Random();


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
        for(int i=0; i<5; i++) {
            enemys.add(new Astroid(r.nextInt(1000), r.nextInt(900), 70, 70));
        }
        addGameObject(player, width/2, height/2);
        for(Enemy e : enemys) {
            addGameObject(e);
        }

        Dashboard dashBoard = new Dashboard(0, 0, width, 100);
        dashText = new Textobject("score: ", 0);
        boardText = new Textobject("lifes: ", 3);
        dashBoard.addGameObject(dashText, 500, 10);
        dashBoard.addGameObject(boardText, 20, 10);
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
