package nl.han.ica.Cellsplitter;

import processing.core.PGraphics;

import static java.lang.Math.cos;
import static java.lang.Math.toRadians;
import static java.lang.Math.sin;
import static processing.core.PApplet.sqrt;

/**
 * Created by Besselking on 04-Mar-17.
 */
public class Player  extends Cell{
    private final CellSplitter world;

    public Player(CellSplitter world, float mass){
        super(world,mass);
        this.world = world;
    }

    public void draw(PGraphics g) {
        g.noStroke();
        g.fill(255,0,0);
        g.ellipse(getX(),getY(),size,size);
    }

    public void mousePressed(int x, int y, int button){
        final int speed = 5;
        final float splitMass = mass/30;
        final float splitDirection = getAngleFrom(x,y);

        float splitX = (size/2 + sqrt(splitMass/PI)) * (float)cos(toRadians(splitDirection-90));
        float splitY = (size/2 + sqrt(splitMass/PI)) * (float)sin(toRadians(splitDirection-90));


        setDirectionSpeed(getAngleFrom(x,y)-180, getSpeed()+speed);
        world.addGameObject(new Cell(world,splitMass,getSpeed(),splitDirection, false),splitX+getX(),splitY+getY());
        mass -= splitMass;

    }
}
