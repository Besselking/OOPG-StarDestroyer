package nl.han.ica.Cellsplitter;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

import static processing.core.PApplet.dist;
import static processing.core.PApplet.sqrt;

/**
 * Created by Besselking on 04-Mar-17.
 */
public class Cell extends GameObject {
    protected float size;
    protected float mass;
    private boolean growing;
    private final CellSplitter world;

    public Cell(CellSplitter world, float mass, float initSpeed, float initDirection, boolean growing){
        this.world = world;
        this.mass = mass;
        this.size = 2*sqrt(mass/PI);
        this.growing = growing;

        setDirectionSpeed(initDirection, initSpeed);
        setFriction(0.05f);
    }

    public Cell(CellSplitter world, float mass){
        this.world = world;
        this.mass = mass;
        setFriction(0.05f);
    }

    public void update() {
        edges();
        growingCollision();
        if(growing){
            if (!(this instanceof Player)) mass += 100;
        }

        size = 2*sqrt(mass/PI);
        setFriction(0.02f);


        if((int)mass<=0){
            world.deleteGameObject(this);
        }

        if(getX()-size/2 <= 0){
            setDirection(360-getDirection());
            setX(size/2);
        }
        if(getY()-size/2 <= 0){
            setDirection(180 + 360-getDirection());
            setY(size/2);
        }
        if(getX()+size/2 >= world.getWidth()){
            setDirection(360-getDirection());
            setX(world.getWidth()-size/2);
        }
        if(getY()+size/2 >= world.getHeight()){
            setDirection(180 + 360-getDirection());
            setY(world.getHeight()-size/2);
        }

    }

    public void edges(){
        if ((getX()-size/2 <= 0 || getY()-size/2 <= 0 || getX()+size/2 >= world.getWidth()-1 || getY()+size/2 >= world.getHeight()-1)){
            growing = false;
        }
    }

    public void growingCollision(){
        for(GameObject o: world.getGameObjectItems()){
            if(o instanceof Cell && o != this){
                float d = dist(getX(),getY(),o.getX(),o.getY());
                if(d < size/2+1 + ((Cell)o).getSize()/2+1){
                    growing = false;
                    break;
                }
            }
        }
    }


    public void decreaseMass(){
        mass--;
        size = 2 * sqrt(mass / PI);
    }

    public void increaseMass(){
        mass++;
        size = 2 * sqrt(mass / PI);
    }

    public float getMass(){return mass;}
    public float getSize(){return size;}

    public void draw(PGraphics g) {
        g.noStroke();
        g.fill(0,255,0);
        g.ellipse(getX(),getY(),size,size);
    }

    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects){
        for (GameObject g :collidedGameObjects) {
            if (g != this) {
                if(dist(g.getX(), g.getY(), getX(), getY())+ ((Cell)g).getSize()/2 <= size/2){
                    mass += ((Cell)g).getMass();
                    world.deleteGameObject(g);
                }
                while(dist(g.getX(), g.getY(), getX(), getY()) - (((Cell) g).getSize() / 2 + size / 2) < 0) {
                    if (((Cell) g).getMass() < mass) {
                        mass++;
                        size = 2 * sqrt(mass / PI);
                        ((Cell)g).decreaseMass();
                    } else {
                        mass--;
                        size = 2 * sqrt(mass / PI);
                        ((Cell)g).increaseMass();
                    }
                }
            }
        }
    }
    public String toString(){
        return Float.toString(getMass());
    }
}
