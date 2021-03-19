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
    private TestPhysic tp = new TestPhysic();
    private StateInterface[] positionOfPlanets = null;

    public Environment(){
        Planets planets = new Planets();
        this.planetsList= planets.getPlanets();
    }


    public void paintComponent(Graphics g) {
        //Adds all our planets and calls every individual draw from each planet
//        g.setColor(Color.WHITE);
//        g.drawString("hello",100,100);
        Vector3dInterface[] positionOfSpacechip = sim.trajectory( new Vector(-1.471922101663588e+11,-2.860995816266412e+10,8.278183193596080e+06),new Vector(5.427193405797901e+03,-2.931056622265021e+04,6.575428158157592e-01),100,10);
        positionOfPlanets = sim.getPositionOfPlanets();
        StateOfSolarSystem[] arr2 = new StateOfSolarSystem[positionOfPlanets.length];
        StateOfSpaceShip[] arr3 = new StateOfSpaceShip[positionOfSpacechip.length];
        for (int m = 0; m < arr2.length; m++) {
            arr2[m] = (StateOfSolarSystem) positionOfPlanets[m];
    //        arr3[m] = (StateOfSpaceShip) positionOfSpacechip[m];
        }

        Vector3dInterface[] pPlanets = new Vector3dInterface[11];

        for (int i = 0; i < arr2.length; i++) {
            pPlanets = arr2[i].getPositionOfPlanets();
            for (int m = 0 ; m< planetsList.size(); m++) {

                planetsList.get(m).setX(pPlanets[m].getX());
                planetsList.get(m).setY(pPlanets[m].getY());
                planetsList.get(m).draw(g);

            }
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

        this.paintComponent(g);

        g.dispose();
        bs.show();

    }


    //this method should be called before paintComponent as it sets the position of our ship on earth
    public void setLaunchInfo(Vector startPosition) {
        ship.setLaunchData(new Vector((int) startPosition.getX(), (int) startPosition.getY(), 0));
    }


}
