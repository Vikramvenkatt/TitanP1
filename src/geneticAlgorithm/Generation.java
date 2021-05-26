package geneticAlgorithm;

import GenBody.Simulation;
import GenBody.Vector;

public class Generation {
    static final int popSize = 50;
    static final int tournamentSize = 3;
    static final int evolutionCycles = 1000;
    static final double mutationRate = 0.3;
    static final double mutationFactor = 1e11;
    static final double diversity = 0.4;
    static final double tf = 31536000;
    static final double h = 1000;
    public static Vector titanPos;
    private MiniSimulation sim;


    public void createGenerations()
    {
        Population pop = new Population(popSize);

    }
}
