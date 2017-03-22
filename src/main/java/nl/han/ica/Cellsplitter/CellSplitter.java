package nl.han.ica.Cellsplitter;

import javafx.scene.shape.Rectangle;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marijn Besseling on 04-Mar-17.
 */
public class CellSplitter extends GameEngine{

    private Player player;
    private Quadtree quad;
    private int maxObjects = 0;

    public static void main(String[] args){
        PApplet.main(new String[]{"nl.han.ica.Cellsplitter.CellSplitter"});
    }


    public void setupGame(){

        int worldWidth = 1204;
        int worldHeight = 903;

        createViewWithoutViewport(worldWidth, worldHeight);
        createObjects();
        quad = new Quadtree(0, new Rectangle(0,0,worldWidth,worldHeight));
    }


    private void createViewWithoutViewport(int screenWidth, int screenHeight){
        View view = new View(screenWidth,screenHeight);
        view.setBackground(51,51,51);

        setView(view);
        size(screenWidth, screenHeight);
    }


    private void createObjects(){
        player = new Player(this,10000);
        addGameObject(player, getWidth()/2, getHeight()/2);

//        enemyCell = new Cell(this, 20000);
//        addGameObject(enemyCell, getWidth()/2+400, getHeight()/3);
//
//        Cell testCell = new Cell(this,40000);
//        addGameObject(testCell,getWidth()/4,getHeight()/3);
    }

    private Cell newCell(float x, float y){
        boolean valid = true;

        for (GameObject o: getGameObjectItems()){
            if(o instanceof Cell){
                float d = dist(x,y,o.getX(),o.getY());
                if (d < ((Cell)o).getSize()){
                    valid = false;
                    break;
                }
            }
        }
        if(valid){
            return new Cell(this,0,0,0,true);
        } else {
            return null;
        }

    }


    public void update(){
        if(getGameObjectItems().size() > maxObjects) maxObjects = getGameObjectItems().size();

        int attempts = 0;
        if(maxObjects < 100 && attempts < 1000) {
            float x = random(0,getWidth());
            float y = random(0,getHeight());
//            System.out.println("x: "+x+"\ny:"+y);
            Cell newC = newCell(x, y);
            if (newC != null) {
                addGameObject(newC, x, y);
            }
            attempts++;
        }

        quad.clear();
        for (int i = 0; i < getGameObjectItems().size(); i++) {
            quad.insert((Cell) getGameObjectItems().get(i));
        }

        List returnObjects = new ArrayList();
        for (int i = 0; i < getGameObjectItems().size(); i++) {
            returnObjects.clear();
            quad.retrieve(returnObjects, (Cell) getGameObjectItems().get(i));

            for (Object a: returnObjects) {
                for (Object b: returnObjects) {
                    if(a != b) {
                        if (dist(((Cell) a).getX(), ((Cell) a).getY(), ((Cell) b).getX(), ((Cell) b).getY()) - (((Cell) a).getSize() / 2 + ((Cell) b).getSize() / 2) < 0) {
                            List collidedGameObjects = new ArrayList();
                            collidedGameObjects.add(a);
                            collidedGameObjects.add(b);
                            ((Cell) a).gameObjectCollisionOccurred(collidedGameObjects);
                            ((Cell) b).gameObjectCollisionOccurred(collidedGameObjects);

                        }
                    }
                }
            }
        }
    }

}
