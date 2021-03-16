import interfaces.*;

public class NewtonsLawofGravity implements ODEFunctionInterface {

    private final double G = 6.674e-11;
    @Override
    public RateInterface call(double t, StateInterface y) {

        StateOfSolarSystem position = (StateOfSolarSystem) y;

        Vector3dInterface[] positionOfPlanets = position.getPositionOfPlanets();

        Vector3dInterface[] velocity = new Vector3dInterface[11];

        double[] mass = position.getMass();

        double distance =0;

        //double force =0;

        double accelaration =0;

        for(int i = 0; i < velocity.length; i++){

            for(int m = 0; m< velocity.length;m++){

                if(i!=m){

                    distance = Math.pow(positionOfPlanets[i].dist(positionOfPlanets[m]), 2);

                    accelaration = ( mass[m] * G ) / distance;

                    velocity[i] = accelaration*t;
                }
            }
        }

        return new VelocityOfPlanets(velocity);
    }
}
