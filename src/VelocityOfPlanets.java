import interfaces.*;

public class VelocityOfPlanets implements RateInterface{

    public Vector3dInterface[] velocityOfPlanets = new Vector3dInterface[11];

    //order of planets is sun[0],venus[1],mercury[2], jupiter[3], mars[4],earth[5], uranus[6], saturn[7],titan[8],moon[9],neptune[10]

    public VelocityOfPlanets(Vector3dInterface[] v){
        for(int i =0; i < v.length; i++){
            velocityOfPlanets[i] = v[i];
        }
    }

}
