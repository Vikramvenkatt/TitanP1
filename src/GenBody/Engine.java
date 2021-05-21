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

}
