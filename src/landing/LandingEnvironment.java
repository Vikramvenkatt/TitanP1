package landing;

import GenBody.*;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Calendar;
import java.util.List;

public class LandingEnvironment extends Canvas {

    public static double zoomFactorLanding = 0.95;
    public static double sizeFactorLanding = 0.805;

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
    private SimulationLanding k;
    private Vector[] n;
    private Vector[] j;

    public LandingEnvironment() {
        SimulationLanding k = new SimulationLanding();
        n = k.trajectoryOpenloop(new Vector(-100000 + 500, 30000, 1.57079633), new Vector(1000, 0, 0), 230, 1);
        j = k.trajectoryCLosedloop(new Vector(-100000 + 500, 30000, 1.57079633), new Vector(1000, 0, 0), 230, 1);
        for (int i = 0; i < n.length; i++) {
            System.out.println(n[i].toString());
            //System.out.println(j[i].toString());
        }
    }


    public void drawLanding(Graphics2D g, int index) {
        // n = k.trajectory(new Vector(8000, 8000, 0), new Vector(-500, 0, 0), 4000, 1);

        g.setColor(Color.ORANGE);
        g.drawLine(0, 1000 / 2+(int) (4 * sizeFactorLanding *5), 1500, 1000 / 2+(int) (4 * sizeFactorLanding *5));



            double angle = n[index].getZ();
            double xCoord = -(n[index].getX());
            double yCoord = -(n[index].getY());

            double scale = 1e8;
            double probeRad = 10;
            double spaceshipSize = 4;

            g.setColor(Color.pink);
            g.drawString("Probe", (int) (changePosXC(xCoord) + (int) (probeRad / scale / 2) + 15), (int) changePosYC(yCoord) + (int) (probeRad / scale / 2) + 15);
            g.rotate(angle, (int) (changePosXC(xCoord) - (int) (probeRad / scale / 2)), (int) (changePosYC(yCoord) - (int) (probeRad / scale / 2)));
            g.fillRect((int) (changePosXC(xCoord) - (int) (probeRad / scale / 2)), (int) (changePosYC(yCoord) - (int) (probeRad / scale / 2)), (int) (spaceshipSize * sizeFactorLanding *3), (int) (spaceshipSize * sizeFactorLanding * 5));

            //System.out.println(xCoord+","+yCoord+","+angle);



    }


    public double changePosXC(double x) {
        return 750 + x * 1500 / -98505.0 / zoomFactorLanding; //* 1500 / 4.382692942729203e+12
    }

    public double changePosYC(double y) {
        return 500 + y * 1000 / 29998.648000016026 / zoomFactorLanding; //* 1000 / 1.744450959214586e+12
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

        this.drawLanding((Graphics2D) g, frames);

        g.dispose();
        bs.show();

    }
}