import GenBody.Planets;
import GenBody.Vector;
import gui.Environment;
import gui.UserInterface;
import java.awt.Graphics;
import GenBody.Simulation;

public class Main {
    private static Simulation sim = new Simulation();
    private static Planets planets = new Planets();
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        Environment e = new Environment();
        ui.setEnvironment(e);
        ui.setVisible(true);
        ui.repaint();
        sim.trajectory(new Vector(planets.getEarth().getInitialPosition().getX()+planets.getEarth().getRadius(),
                planets.getEarth().getInitialPosition().getY()+planets.getEarth().getRadius(),
                planets.getEarth().getInitialPosition().getZ()+planets.getEarth().getRadius())
                ,new Vector(60,30,100),
                100, 10);
    }
}
