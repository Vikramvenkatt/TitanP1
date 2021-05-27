package gui;

import GenBody.*;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.List;

public class Environment extends Canvas  {
    private List<Planet> planetsList;
    boolean running = false;
    Thread runner = null;
    Calendar startDate;
    long start;
    Calendar current;
    private SimulationVerlet sim = new SimulationVerlet();
    private StateOfSolarSystem[] positionsOfPlanets;
    private Vector3dInterface[] positionOfSpacechip;

    //old angle for the rocket = 0.5896979523584819, -0.8075660030972522, -0.00966827935790599
    public Environment(){
        Planets planets = new Planets();
        this.planetsList=planets.getPlanets();
        //0.5896979523584819, -0.8075660030972522, -0.0096682793579059
        positionOfSpacechip = sim.trajectory(  new Vector(6371e3,0,0), new Vector(1.0519798448579987, 9.78871953507167, -9.152967539273916  ),31556926, 1000);
       StateInterface[] arr = sim.getPositionOfPlanets(); // vector before  new Vector(-1.471922101663588e+11+6371e3,-2.860995816266412e+10,8.278183193596080e+06),new Vector(5.427193405797901e+03,-2.931056622265021e+04,6.575428158157592e-01)
        positionsOfPlanets = new StateOfSolarSystem[arr.length];
        for (int m = 0; m < positionsOfPlanets.length; m++) {
            positionsOfPlanets[m] = (StateOfSolarSystem) arr[m];
        }
    }


    public void drawplease(Graphics g,int index) {
        //Adds all our planets and calls every individual draw from each planet
//        g.setColor(Color.WHITE);
//        g.drawString("hello",100,100);

        //System.out.println(index);
       //System.out.println( positionsOfPlanets.length);

        Vector3dInterface[] pPlanets = positionsOfPlanets[index].getPositionOfPlanets();


            for (int m = 0 ; m< planetsList.size(); m++) {

                planetsList.get(m).setX(pPlanets[m].getX());
                planetsList.get(m).setY(pPlanets[m].getY());
                planetsList.get(m).draw(g);


            }
        /*    if(planetsList.get(8).getInitialPosition().sub(planetsList.get(11).getInitialPosition()).norm() < 3e9)
            {
                System.out.println("Sussy");
            }
            else
            {
                System.out.println("Amogus");
            } */

            for(int m = 0 ; m< planetsList.size()-1; m++){

                if(isInside(
                        planetsList.get(m).getInitialPosition().getX(),
                        planetsList.get(m).getInitialPosition().getY(),
                        planetsList.get(m).getRadius(),
                        planetsList.get(11).getInitialPosition().getX(),
                        planetsList.get(11).getInitialPosition().getY())
                ){
                    System.out.println("");
                    System.out.println("its in the "+planetsList.get(m).getName());
                    System.out.println("");
                }else{
                  //  System.out.println(planetsList.get(m).getName()+" : "+planetsList.get(m).getInitialPosition());
                    //System.out.println(planetsList.get(11).getName()+" : "+planetsList.get(11).getInitialPosition());
                }


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

        g.setColor(Color.black);
        g.fillRect(0, 0, 1500, 1000);

        this.drawplease(g,frames);

        g.dispose();
        bs.show();

    }


/*    //this method should be called before paintComponent as it sets the position of our ship on earth
    public void setLaunchInfo(Vector startPosition) {
        ship.setLaunchData(new Vector((int) startPosition.getX(), (int) startPosition.getY(), 0));
    } */


    public static boolean isInside(double circle_x, double circle_y,
                                   double rad, double x, double y) {

        return (x - circle_x) * (x - circle_x) +
                (y - circle_y) * (y - circle_y) <= rad * rad;
    }



}
