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

public class Environment extends Canvas {
    private List<Planet> planetsList;
    boolean running = false;
    Thread runner = null;
    Calendar startDate;
    long start;
    Calendar current;
    private SimulationVerlet sim = new SimulationVerlet();
    //private SimulationRungeKutta sim = new SimulationRungeKutta();
    private StateOfSolarSystem[] positionsOfPlanets;
    private Vector3dInterface[] positionOfSpacechip;

    // angle for the rocket computed by Newton Raphson = with adapted fuel new Vector(1.5739450522683016, -6.062006574679788, -0.17575725363009373)
    //  // angle for the rocket computed by Newton Raphson without out an engine = new Vector(27603.05279102997, -27445.29068487018, -804.3524467856054)
    // new Vector(1.5575225992772161, -6.030633230550018, -0.1866297565456477);
//    new Vector(1.5575086679258936, -6.030629715979966, -0.1866458031673369)
    public Environment() {
        Planets planets = new Planets();
        this.planetsList = planets.getPlanets();
        positionOfSpacechip = sim.trajectory(new Vector(6371e3, 0, 0), new Vector(1.5575225992772161, -6.030633230550018, -0.1866297565456477), 31556926 * 2, 1000);
        StateInterface[] arr = sim.getPositionOfPlanets();
        positionsOfPlanets = new StateOfSolarSystem[arr.length];
        for (int m = 0; m < positionsOfPlanets.length; m++) {
            positionsOfPlanets[m] = (StateOfSolarSystem) arr[m];
        }
    }


    public void drawplease(Graphics g, int index) {
        //Adds all our planets and calls every individual draw from each planet
//        g.setColor(Color.WHITE);
//        g.drawString("hello",100,100);

        //System.out.println(index);
        //System.out.println( positionsOfPlanets.length);

        Vector3dInterface[] pPlanets = positionsOfPlanets[index].getPositionOfPlanets();


/*/////////////////////////////////SUN IN THE MIDDLE
            for (int m = 0 ; m< planetsList.size(); m++) {
//
//                planetsList.get(m).setX(pPlanets[m].getX());
//                planetsList.get(m).setY(pPlanets[m].getY());
//                   planetsList.get(m).draw((Graphics2D) g);
//
//
//

        if (m == 8 || m == 11) {
            planetsList.get(m).setX(pPlanets[m].getX());
            planetsList.get(m).setY(pPlanets[m].getY());
            planetsList.get(m).drawLanding((Graphics2D) g, pPlanets[m].getAngle());
        }

    }
*/
/////////////////////////////////SPACESHIP IN THE MIDDLE

        /*for (int k = 0; k < planetsList.size(); k++) {

                planetsList.get(k).setX(pPlanets[k].getX() - pPlanets[11].getX());
                planetsList.get(k).setY(pPlanets[k].getY() - pPlanets[11].getY());
                planetsList.get(k).draw((Graphics2D) g);

        }*/


/////////////////////////////////Testing

        for (int m = 0 ; m< planetsList.size(); m++) {
            if (m == 8 || m == 11) {
                planetsList.get(m).setX(pPlanets[m].getX()-pPlanets[8].getX());
                planetsList.get(m).setY(pPlanets[m].getY()-pPlanets[8].getY());
                planetsList.get(m).drawLanding((Graphics2D) g, pPlanets[m].getAngle());
            }
        }

//        for (int m = 0; m < planetsList.size(); m++) {
//            if (m == 8 || m == 11) {
//                planetsList.get(m).setX(pPlanets[m].getX());
//                planetsList.get(m).setY(pPlanets[m].getY());
//                planetsList.get(m).drawLanding((Graphics2D) g);
//            }
//
//
//        }

        /*    if(planetsList.get(8).getInitialPosition().sub(planetsList.get(11).getInitialPosition()).norm() < 3e9)
            {
                System.out.println("Sussy");
            }
            else
            {
                System.out.println("Amogus");
            } */

        for (int m = 0; m < planetsList.size() - 1; m++) {

            if (isInside(
                    planetsList.get(m).getInitialPosition().getX(),
                    planetsList.get(m).getInitialPosition().getY(),
                    planetsList.get(m).getRadius(),
                    planetsList.get(11).getInitialPosition().getX(),
                    planetsList.get(11).getInitialPosition().getY())
            ) {
                System.out.println("");
                System.out.println("its in the " + planetsList.get(m).getName());
                System.out.println("");


           /* if (m == 8 || m == 11) {
                planetsList.get(m).setX(pPlanets[m].getX());
                planetsList.get(m).setY(pPlanets[m].getY());
                planetsList.get(m).drawLanding((Graphics2D) g,pPlanets[m].getAngle());
            }*/


            } else {
                //  System.out.println(planetsList.get(m).getName()+" : "+planetsList.get(m).getInitialPosition());
                //System.out.println(planetsList.get(11).getName()+" : "+planetsList.get(11).getInitialPosition());
            }


        }
        //draw the ship at the initial position
        //ship.draw(g, (int) ship.getPosition().getX(), (int) ship.getPosition().getY());
    }

    public void run(int frames) {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, 1500, 1000);

        this.drawplease(g, frames);

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
