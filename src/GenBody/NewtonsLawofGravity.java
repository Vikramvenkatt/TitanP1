package GenBody;

import GenBody.Change;
import interfaces.*;

/**
 * computes the resulting gravitational forces from the planets and calculates and outputs the resulting
 * acceleration on the spaceship and the planets
 */
public class NewtonsLawofGravity implements ODEFunctionInterface {

    private final double G = 6.674e-11;

    private double massOfSpaceship = 1500;
    @Override
    public RateInterface call(double t, StateInterface y) {

        StateOfSolarSystem position = (StateOfSolarSystem) y;

        Vector3dInterface[] positionOfPlanets = position.getPositionOfPlanets();

        Vector3dInterface[] acceleration = new Vector3dInterface[12];

        for(int k =0;k< acceleration.length;k++){
            acceleration[k]= (Vector3dInterface) new Vector(0,0,0);
        }

        double[] mass = position.getMass();

        double distance =0;

        Vector3dInterface a = null;

        for(int i = 0; i < positionOfPlanets.length; i++){

            for(int m = 0; m< positionOfPlanets.length;m++){

                if(i!=m){

                    distance = Math.pow(positionOfPlanets[i].dist(positionOfPlanets[m]), 3); // squared euclidean distance

                    Vector3dInterface d = positionOfPlanets[i].sub(positionOfPlanets[m]); // vector from planet i to m

                    //d = d.mul(1/d.norm()); // unit vector from i to m

                    a = d.mul( (-1*mass[m]* mass[i] * G)/distance ); //force

                    a = a.mul(1/mass[i]); // acceleration

                   acceleration[i] = acceleration[i].add(a); // acceleration gets summed
                }
            }
        }

        return new Change(acceleration);
    }

}
