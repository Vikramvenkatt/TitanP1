package geneticAlgorithm;

import GenBody.Vector;

public class Individual {
    private double fitness;
    private Vector targetPosition;
    private Vector velocity;




    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness){
        this.fitness = fitness;
    }

    public Vector getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Vector targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
}
