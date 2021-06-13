package landing;

import GenBody.Vector;

import java.util.ArrayList;
import java.util.Random;

/**
 * class calculate the wind impact on landing module
 *
 *
 * M*a=Ve*(mass flow rate)
 * M=lander mass
 * force=contactArea * airDensity * windSpeed
 *
 * a=windforce/M
 */
class WindModel extends LandingModule {
    private double g = 1.352; // m / s^2  gravity on Titan
    private double areaContact = 50; // m^2 //TODO: mention in next project meeting
    private double atmosphereDensity = 1.2*1.19; // kg/m^3
    private double massOfTitan = 1.34553e23;
    private double unappliedWindSpeed; // overall mean wind speed
    private double appliedWindSpeed; // current wind speed after noise has been applied
    private double windAcceleration; // acceleration caused by wind, only for appliedWindSpeed


    public WindModel(){
        windFirstInitialization();
        windForceOnLander();
    }


    public void windFirstInitialization() {
        // random wind speed ranges form -20 to 20 meters per seconds;
        if(unappliedWindSpeed!=-10) {
            Random rand = new Random();
            unappliedWindSpeed = rand.nextInt(41) - 20;;
        }
    }


    public void windForceOnLander() {
        // random wind noise ranges from -0.5  to 0.5
        double windNoise = Math.random()-0.5;
        appliedWindSpeed = unappliedWindSpeed * windNoise;
        // force = contact area * atmosphere density * windSpeed
        double windForce = areaContact * atmosphereDensity * appliedWindSpeed;
        windAcceleration = windForce/totalMass;
        //System.out.println("Landing acceleration: " + acceleration.getX());
        //System.out.println("Wind acceleration: " + windAcc);
        //System.out.println();
        acceleration.setX(acceleration.getX() + windAcceleration);

    }


    public double getWindAcc() {
        // random wind noise ranges from -0.5 to 0.5
        double windNoise = Math.random()-0.5;
        appliedWindSpeed = unappliedWindSpeed * windNoise;
        // force = area of contact * air density * windSpeed
        double windForce = areaContact * atmosphereDensity * appliedWindSpeed;
        return windAcceleration = windForce/totalMass;
    }

    public double getappliedWindSpeed() {
        return appliedWindSpeed;
    }

    public double getunappliedWindSpeed() {
        return unappliedWindSpeed;
    }


    //TODO: Titan's position is given ONCE, it should actually be
    /**
        Applies gravitation force on the landing module, will be called every time step
        IF THIS DOESNT WORK JUST USE G
     */
    public Vector gravitationalForces(Vector positionOfTitan) {
        Vector gForces = new Vector(); // reset the acceleration
        // get the relative position to the reference object
        Vector r = super.position.substract(positionOfTitan);
        // radius of distance
        double dist = r.length();
        // F(m1<-m2) = (g * m1 * m2) / r^2
        double netForce = (g * super.massOfLander * massOfTitan) / Math.pow(dist, 2);// create the force vector of between the two objects. It is in the direction of
        // the relative position of the object and the magnitude of the net force
        Vector thisForce = (Vector) super.acceleration.createUnitVector().mul(netForce);// add force to total acceleration
        gForces = gForces.add(thisForce);
        return gForces;
    }



}