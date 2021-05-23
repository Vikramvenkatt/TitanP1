package GenBody;

import interfaces.Vector3dInterface;

public class Engine {

    private final Vector startVector = new Vector(0.5896979523584819, -0.8075660030972522, -0.00966827935790599);
    private final double massOfCraft = 78000;
    private final double massOfLander = 6000;
    private static double massOfFuel = 100000;
    private double totalMass = massOfCraft + massOfFuel + massOfLander;
    private final double effectiveExhaustVelocity = 2e4;
    private final double maxThrust = 3e7;
    private double fuelBurnedPerSecondMax = 1500;
    private StateOfSolarSystem state;
    private static int called = 0;

    public Engine(StateOfSolarSystem s){
        this.state = s;
    }

    // F#resulting = F#G + F#engine
    // F#G = F#resulting - F#engine
    public Vector3dInterface calculateAccelaration(Vector3dInterface posShip, Vector3dInterface posTarget, Vector3dInterface FG) {

        Vector3dInterface  FResulting = posTarget.sub(posShip);
        double scalar = FResulting.norm();
        FResulting = FResulting.unitVector();
        double accScalar;
        called+=1;
        System.out.println("called:"+ called);

        if (scalar > (maxThrust / totalMass)) {
            accScalar = maxThrust / totalMass;
            massOfFuel -= fuelBurnedPerSecondMax;
            totalMass = massOfCraft + massOfFuel + massOfLander;
            FResulting = FResulting.mul(accScalar);
            Vector3dInterface FEngine = FResulting.sub(FG);
            return FEngine;
        }
        else{return null;}
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
            Vector3dInterface force = direction.mul((state.a[11].norm() * (state.mass[8]/squareRootDistance)));
            force = force.mul(1/state.mass[11]);
            return force;
        }
    }

    //Initially this was Vector3dInterface but it doesn't have the dot() method
    private boolean isPerpendicular(Vector vector)
    {
        return (vector.dot((Vector) state.v[11]) == 0);
    }

    public Vector3dInterface slowDown(Vector3dInterface posShip, Vector3dInterface posTarget, Vector3dInterface FG){
        Vector3dInterface  FResulting = posShip.sub(posTarget);
      //  FResulting = FResulting.sub(state.v[11]); // with velocity?
        double scalar = FResulting.norm();
        FResulting = FResulting.unitVector();
        double accScalar;
        called+=1;
        System.out.println("called slowdown:"+ called);
        System.out.println("v:"+ state.v[11].norm());

        if (scalar > (maxThrust / totalMass)) {
            accScalar = maxThrust / totalMass;
            massOfFuel -= fuelBurnedPerSecondMax;
            totalMass = massOfCraft + massOfFuel + massOfLander;
            FResulting = FResulting.mul(accScalar);
            Vector3dInterface FEngine = FResulting.sub(FG);
            return FEngine;
        }
        return new Vector();
    }

    public double getTotalMass(){
        return totalMass;
    }

    //TODO: State is public, do defensive copying
}
