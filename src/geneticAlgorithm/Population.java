package geneticAlgorithm;

public class Population {

    private Individual[] individuals;
    private int popSize;


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

    public Individual getIndividual(int i)
    {
        return individuals[i];
    }
}
