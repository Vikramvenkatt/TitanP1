package geneticAlgorithm;

import GenBody.Vector;

import java.util.Random;

public class Individual {
    private double fitness;
    private Vector velocity;

    public Individual()
    {
    }

    public Individual(Vector velocity)
    {
        this.velocity = velocity;
    }

    public Individual getMutatedIndividual(Individual a)
    {
        double x = a.velocity.getX();
        double y = a.velocity.getY();
        double z = a.velocity.getZ();
        //OLD MUTATE METHOD x + (Math.random() * (20000)-10000)/1000,y + (Math.random() * (20000)-10000)/1000,z + (Math.random() * (20000)-10000)/1000
        Vector newVel = new Vector((x + Math.random() * (20000)-10000)/1000,(y + Math.random() * (20000)-10000)/1000,(z + Math.random() * (20000)-10000)/1000);
        return new Individual(newVel);
    }

    public Individual getRandomIndividual()
    {
        double x = 1;
        double y = 1;
        double z = 1;
        Vector velocity = new Vector(Math.random()*x* getRandomSign(),Math.random()*y* getRandomSign(),Math.random()*z* getRandomSign());
        return new Individual(velocity);
    }

    //Our closest vector normally
    public Individual getStartIndividual()
    {
        velocity = new Vector(0.5896979523584819, -0.8075660030972522, -0.0096682793579059);
        return new Individual(velocity);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness){
        this.fitness = fitness;
    }


    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
    public int getRandomSign() {
        Random rand = new Random();
        if(rand.nextBoolean())
            return -1;
        else
            return 1;
    }
}
