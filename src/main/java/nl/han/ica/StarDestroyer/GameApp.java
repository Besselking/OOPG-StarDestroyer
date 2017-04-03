package nl.han.ica.StarDestroyer;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Marijn Besseling & Hendrik Camps on 22-Mar-17.
 */
public class GameApp extends GameEngine {
    private Textobject dashText, boardText;
    private Player player;
    private Random r = new Random();
    private int enemy;
    protected int score = 0;


    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.StarDestroyer.GameApp"});
    }

    public void setupGame() {
        int worldWidth = 1000;
        int worldHeight = 650;
        createViewWithoutViewport(worldWidth, worldHeight);
        createObjects();
    }

    private void createObjects() {
        player = new Player(this);
        addGameObject(player, width/2, height/2);
        Dashboard dashBoard = new Dashboard(0, 0, width, 100);
        dashText = new Textobject("score: ", score);
        boardText = new Textobject("lifes: ", player.getLife());
        dashBoard.addGameObject(dashText, this.width/2, 10);
        dashBoard.addGameObject(boardText, 20, 10);
        addDashboard(dashBoard);

        Multishot pu = new Multishot(this, 100, 100, 5);
        addGameObject(pu);
    }

    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(40, 40, 40);
        setView(view);
        size(screenWidth, screenHeight);
    }

    private void createEnemy() {
        for(int i=0; i<6; i++) {
            addGameObject(new Astroid(r.nextInt(this.getWidth()), r.nextInt(this.getHeight()), 3, this));
        }
        addGameObject(new Alien(100, 100, 70, 70, this, player));
        addGameObject(new Star(0, this.getHeight()/3*2, 50, 50, this));
    }

    private void updateEnemy() {
        for(GameObject O : this.getGameObjectItems()) {
            if(O instanceof Astroid || O instanceof Alien) {
                enemy++;
            }
        }
        if (enemy == 0) createEnemy();
        enemy = 0;
    }

    public void update() {
        updateEnemy();
        boardText.setData(player.getLife());
        dashText.setData(score);
        if(player.getLife() < 0){
            deleteAllGameOBjects();
        }
    }
}
