import interfaces.*;


public class StateOfSolarSystem implements StateInterface {

    public Vector3dInterface[] positionOfPlanets = new Vector3dInterface[11];
    public Vector3dInterface[] formerPositionOfPlanets = new Vector3dInterface[11] ;
    public double[] mass = new double[11];

    //order of planets is sun[0],venus[1],mercury[2], jupiter[3], mars[4],earth[5], uranus[6], saturn[7],titan[8],moon[9],neptune[10]

    public StateOfSolarSystem(){

    }

    public void addFormerPosition(StateOfSolarSystem s){
        formerPositionOfPlanets = s.getPositionOfPlanets();
    }

    public Vector3dInterface[] getPositionOfPlanets(){
        return positionOfPlanets;
    }

    public double[] getMass(){
        return mass;
    }

    @Override
    public StateInterface addMul(double step, RateInterface rate) {

        VelocityOfPlanets v = (VelocityOfPlanets) rate;

        Vector3dInterface[] velocityOfPlanet = v.velocityOfPlanets;

        for(int i =0; i< positionOfPlanets.length;i++){
           positionOfPlanets[i] = formerPositionOfPlanets[i].addMul(step,velocityOfPlanet[i]);
        }

        return this;
    }
}


