package GenBody;

import interfaces.Vector3dInterface;

import static java.lang.Math.abs;

public class Engine {

    private final Vector startVector = new Vector(0.5896979523584819, -0.8075660030972522, -0.00966827935790599);
    private final double massOfCraft = 78000;
    private final double massOfLander = 6000;
    private static double massOfFuel = 3.5E8;
    private double totalMass = massOfCraft + massOfFuel + massOfLander;
    private final double effectiveExhaustVelocity = 2e4;
    private final double maxThrust = 3e7;
    private double fuelBurnedPerSecondMax = 1500;
    private StateOfSolarSystem state;
    private static int called = 0;
    private double stepsize;
    private Vector3dInterface forceOnShip;

    public Engine(StateOfSolarSystem s, double h){
        this.state = s;
        stepsize = h;
        //System.out.println(totalMass);
        //System.out.println(totalMass);
    }

    // F#resulting = F#G + F#engine
    // F#G = F#resulting - F#engine
    public Vector3dInterface takeOff(Vector3dInterface posShip, Vector3dInterface posTarget) {

     /*   called+=1;
        System.out.println("called:"+ called); */

       Vector3dInterface FResulting = posTarget.sub(posShip);


       FResulting = FResulting.unitVector().mul(maxThrust);

       return transformForceToAcceleration(FResulting);

    }

    //Calculates the acceleration vector for the rocket so that it stays in circular orbit
    public Vector3dInterface createOrbitalVector(Vector3dInterface posShip, Vector3dInterface posTitan)
    {
        //TODO: If there is a bug, is it because I didn't apply defensive copying to the params?
        Vector3dInterface orbitalVector;

        //Get the distance between Titan and the ship
        double distance =  posTitan.dist(posShip);
        double squareRootDistance = Math.sqrt(distance);
        //The force of gravity is the same as the centrifugal force for our purposes here
        Vector3dInterface direction = (posTitan.sub(posShip));
        if(isPerpendicular((Vector) direction))
        {
            return new Vector(0,0,0);
        }
        else
        {
            //TODO: state.a[11] returns null
            Vector3dInterface force = direction.mul((forceOnShip.norm() * (state.mass[8]/squareRootDistance)));
            force = force.mul(1/state.mass[11]);
            return force;
        }
    }

    //Initially this was Vector3dInterface but it doesn't have the dot() method
    private boolean isPerpendicular(Vector vector)
    {
        return (vector.dot((Vector) state.v[11]) == 0);
    }

    public double getTotalMass(){
        return totalMass;
    }

    /*
        Transforms vector to acceleration and updates the mass
        @param force: scaled force vector in direction in which the ship should move
        @return: accelaration vector
     */
    public Vector3dInterface transformForceToAcceleration(Vector3dInterface force){

        double weight = totalMass;

        massOfFuel -= stepsize*force.norm()/effectiveExhaustVelocity;
        totalMass = abs(massOfCraft + massOfFuel + massOfLander);


        double approxWeightOverTime = (weight + totalMass) / 2;

        return force.mul(1/approxWeightOverTime);
    }

    /*
      Calculates the vector in which the spaceship must accelerate to reach the direction vector, according to formulae:
        F#resulting = F#G + F#engine
        F#G = F#resulting - F#engine
        @param direction: Takes a scaled force vector in which direction the ship should move
        @return: Force vector
     */
    private Vector3dInterface calcFResulting(Vector3dInterface direction){
        return direction.sub(forceOnShip);
    }

    public void addForceOnShip(Vector3dInterface accelerationOnShip) {
        forceOnShip = accelerationOnShip.mul(totalMass);
    }

    //NOTE: State is public, do defensive copying
}
