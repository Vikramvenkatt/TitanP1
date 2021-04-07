package gui;

import GenBody.*;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

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
    private Simulation sim = new Simulation();
    private StateOfSolarSystem[] positionsOfPlanets;
    private Vector3dInterface[] positionOfSpacechip;

    public Environment(){
        Planets planets = new Planets();
        this.planetsList=planets.getPlanets();
        positionOfSpacechip = sim.trajectory( new Vector(-1.471922101663588e+12,-2.860995816266412e+10,8.278183193596080e+06),new Vector(5.427193405797901e+03,-2.931056622265021e+04,6.575428158157592e-01),31556926, 1000);
       StateInterface[] arr = sim.getPositionOfPlanets();
        positionsOfPlanets = new StateOfSolarSystem[arr.length];
        for (int m = 0; m < positionsOfPlanets.length; m++) {
            positionsOfPlanets[m] = (StateOfSolarSystem) arr[m];
        }
    }


    public void drawplease(Graphics g,int index) {
        //Adds all our planets and calls every individual draw from each planet
//        g.setColor(Color.WHITE);
//        g.drawString("hello",100,100);



        Vector3dInterface[] pPlanets = positionsOfPlanets[index].getPositionOfPlanets();


            for (int m = 0 ; m< planetsList.size(); m++) {

                planetsList.get(m).setX(pPlanets[m].getX());
                planetsList.get(m).setY(pPlanets[m].getY());
                planetsList.get(m).draw(g);

            }


        //draw the ship at the initial position
        //ship.draw(g, (int) ship.getPosition().getX(), (int) ship.getPosition().getY());
    }

    public void run(int frames){

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1500, 1000);

        this.drawplease(g,frames);

        g.dispose();
        bs.show();

    }


    //this method should be called before paintComponent as it sets the position of our ship on earth
    public void setLaunchInfo(Vector startPosition) {
        ship.setLaunchData(new Vector((int) startPosition.getX(), (int) startPosition.getY(), 0));
    }


}
