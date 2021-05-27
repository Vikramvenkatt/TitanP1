package geneticAlgorithm;

import GenBody.Vector;
import interfaces.Vector3dInterface;

public class Population {

    private Individual[] individuals;
    private int popSize;
    private MiniSimulation sim;
    private Vector targetPosition;
    static boolean first = true;
    private final double chance = 0.34;
    private Individual ind = new Individual();

    public Population(int popSize)
    {
        this.popSize = popSize;
        individuals = new Individual[popSize];

        individuals[0] = ind.getStartIndividual();
        for(int i = 1; i < popSize; i++)
        {
            individuals[i] = ind.getRandomIndividual();
        }

    }
    public Population(Individual[] individuals)
    {
        this.individuals = individuals;
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

    public void calculateFitnessOfPopulation()
    {
        for(int i = 0; i < popSize; i++)
        {
            //I added Math.abs just to be sure, remove it to see the changes later
            Vector finalPos = sim.trajectory(new Vector(6371e3,0,0),individuals[i].getVelocity(),31556926,1000);
            //TODO: try with targetPosition.add instead
            //NOTE: fitness gets BIGGER with distance TO titan, needs to be changed
            double fitness = targetPosition.sub(finalPos).norm();
            individuals[i].setFitness(fitness);
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
        return child;
    }

    public Individual[] getNewGeneration(Individual[] nextGen)
    {
        Individual[] newGen = new Individual[popSize];
        for(int i = 0; i < popSize; i++)
        {
            int random1 = (int) (Math.random()*(nextGen.length-1))+1;
            int random2 = (int) (Math.random()*(nextGen.length-1))+1;
            Individual a = nextGen[random1];
            Individual b = nextGen[random2];
            Individual c = crossover(a,b);

            Individual newInd = c;
            if(Math.random() < chance)
            {
                newInd = a.getMutatedIndividual(c);
            }
            newGen[i] = newInd;
        }
        return newGen;
    }

    public Individual[] getRoulettePopulation()
    {
        Individual[] nextGen = new Individual[popSize];
        for(int i = 0; i < popSize; i++)
        {
            nextGen[i] = rouletteWheelSelection();
        }
        return nextGen;

    }

    public Individual rouletteWheelSelection()
    {
        calculateFitnessOfPopulation();
        double sumOfFitness = 0;
        for(int i = 0; i < popSize; i++)
        {
            sumOfFitness += getIndividual(i).getFitness();
        }

        double partialSum = 0;
        double rand = Math.random();
        for(int i = 0; i < popSize; i++)
        {
            partialSum += getIndividual(i).getFitness()/sumOfFitness;
            if(partialSum > rand)
            {
                Individual selected = getIndividual(i);
                return selected;
            }
        }
        //Might need to fix the return null;
        return null;
    }

    public void setIndividuals(Individual[] individuals) {
        this.individuals = individuals;
    }

    public Individual getIndividual(int i)
    {
        return individuals[i];
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    public Vector getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Vector targetPosition) {
        this.targetPosition = targetPosition;
    }
}
