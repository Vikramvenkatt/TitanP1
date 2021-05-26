package geneticAlgorithm;

import GenBody.Planet;
import GenBody.Planets;
import GenBody.Simulation;
import GenBody.Vector;
import interfaces.Vector3dInterface;

import java.util.List;

public class GeneticAlgorithm {
    private List<Planet> planetsList;
    private Simulation sim = new Simulation();
    private Vector3dInterface[] positionOfSpacechip;
    private Vector titanFinalPosition = new Vector(8.790206157956954E11, -1.2037722320318977E12, -1.4411708984699354E10);
    public GeneticAlgorithm()
    {
        Planets planets = new Planets();
        this.planetsList=planets.getPlanets();
        positionOfSpacechip = sim.trajectory(  new Vector(6371e3,0,0), new Vector(0.5896979523584819, -0.8075660030972522, -0.0096682793579059),31556926, 1000);
    }
}
