import interfaces.*;

public class NewtonsLawofGravity implements ODEFunctionInterface {

    private final double G = 6.674e-11;
    @Override
    public RateInterface call(double t, StateInterface y) {

        StateOfSolarSystem position = (StateOfSolarSystem) y;

        Vector3dInterface[] positionOfPlanets = position.getPositionOfPlanets();

        Vector3dInterface[] accelaration = new Vector3dInterface[11];

        double[] mass = position.getMass();

        double distance =0;

        Vector3dInterface a =null;

        for(int i = 0; i < positionOfPlanets.length; i++){

            for(int m = 0; m< positionOfPlanets.length;m++){

                if(i!=m){

                    distance = Math.pow(positionOfPlanets[i].dist(positionOfPlanets[m]), 3);

                    Vector3dInterface d = positionOfPlanets[i].sub(positionOfPlanets[m]);

                    a = d.mul( (mass[m]* mass[i] * G)/distance ); //force

                    a = a.mul(1/mass[i]);

                    accelaration[i] = a;
                }
            }
        }

        return new Change(accelaration);
    }
}
