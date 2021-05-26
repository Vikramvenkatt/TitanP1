package geneticAlgorithm;

import GenBody.Vector;
import interfaces.Vector3dInterface;

public class Population {

    private Individual[] individuals;
    private int popSize;
    private MiniSimulation sim;

    public Population(int popSize)
    {
        this.popSize = popSize;
        individuals = new Individual[popSize];

        for(int i = 0; i < popSize; i++)
        {
            Individual individual = new Individual();
        }
    }

    public Individual getBestIndividual()
    {
        //Assume the first indivdual is the best
        Individual bestIndividual = individuals[0];
        for(int i = 0; i < popSize; i++)
        {
            if(individuals[i].getFitness() < bestIndividual.getFitness())
            {
                bestIndividual = individuals[i];
            }
        }
        return bestIndividual;
    }

    public void calculateFitnessOfPopulation(Vector targetPosition)
    {
        for(int i = 0; i < popSize; i++)
        {
            //I added Math.abs just to be sure, remove it to see the changes later
            sim.trajectory(new Vector(6371e3,0,0),individuals[i].getVelocity(),31556926,1000);
            double fitness = targetPosition.sub(individuals[i].getVelocity()).norm();
            individuals[i].setFitness(1);
        }
    }

    public Individual crossover(Individual a, Individual b)
    {
        Individual child = new Individual();
        double x = a.getVelocity().sub(b.getVelocity()).getX()/2;
        double y = a.getVelocity().sub(b.getVelocity()).getY()/2;
        double z = a.getVelocity().sub(b.getVelocity()).getZ()/2;
        Vector newVelocity = new Vector(x,y,z);
        child.setVelocity(newVelocity);
        child.setTargetPosition(a.getTargetPosition());
        return child;
    }

    public Individual getIndividual(int i)
    {
        return individuals[i];
    }
}
