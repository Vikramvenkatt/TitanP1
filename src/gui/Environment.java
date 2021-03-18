package gui;

import GenBody.Planet;
import GenBody.Planets;
import GenBody.Spaceship;
import GenBody.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Calendar;
import java.util.List;

public class Environment extends Canvas  {
    private Spaceship ship;
    private List<Planet> planetsList;
    boolean running = false;
    Thread runner = null;
    Calendar startDate;
    long start;
    Calendar current;

    public Environment(){
        Planets planets = new Planets();
        planets.addPlanets();
        this.planetsList=planets.getPlanets();
    }


    public void drawplease(Graphics g) {
        //Adds all our planets and calls every individual draw from each planet
//        g.setColor(Color.WHITE);
//        g.drawString("hello",100,100);


        for (Planet planet : this.planetsList) {
            planet.draw(g);

        }

        //draw the ship at the initial position
        //ship.draw(g, (int) ship.getPosition().getX(), (int) ship.getPosition().getY());
    }

    public void run(){

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1500, 1000);

        this.drawplease(g);

        g.dispose();
        bs.show();

    }


    //this method should be called before paintComponent as it sets the position of our ship on earth
    public void setLaunchInfo(Vector startPosition) {
        ship.setLaunchData(new Vector((int) startPosition.getX(), (int) startPosition.getY(), 0));
    }


}
