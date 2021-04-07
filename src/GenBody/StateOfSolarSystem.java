package GenBody;

import interfaces.*;

import java.util.ArrayList;


public class StateOfSolarSystem implements StateInterface {

    public Vector3dInterface[] p = new Vector3dInterface[12];

    public Vector3dInterface[] v = new Vector3dInterface[12];

    public Vector3dInterface[] previousP = new Vector3dInterface[12] ;

    public Vector3dInterface[] previousV = new Vector3dInterface[12] ;

    public final double[] mass = {1.9891e30, 4.8685e24, 3.302e23, 1.89813e27, 6.4171e23, 5.97219e24, 8.6813e25, 5.6834e26, 1.34553e23, 7.349e22, 1.02413e26, 1500};

    public final String[] names= {"sun", "venus", " mercury", "jupiter", "mars", "earth", "uranus", "saturn","titan", "moon", "neptune", "spaceship"};

    //order of planets is sun[0],venus[1],mercury[2], jupiter[3], mars[4],earth[5], uranus[6], saturn[7],titan[8],moon[9],neptune[10]

    public StateOfSolarSystem(StateOfSolarSystem s){
        this.previousP = s.getPositionOfPlanets();
        previousV = s.getVelocityOfPlanets();
    }

    public StateOfSolarSystem(){

    }

    public void addOrigin(ArrayList<Planet> list, Vector3dInterface p0, Vector3dInterface v0){
        for(int i =0; i< list.size();i++){
          p[i] =list.get(i).getInitialPosition();
          v[i] = list.get(i).getInitialVelocity();
        }
        p[11] = p0;
        v[11] = v0;
    }

    public Vector3dInterface[] getPositionOfPlanets(){
        Vector3dInterface[] newp = new Vector3dInterface[12];
        for(int i = 0; i< newp.length;i++){
            newp[i] = p[i];
        }
        return newp;
    }

    public Vector3dInterface[] getVelocityOfPlanets(){
        Vector3dInterface[] newV = new Vector3dInterface[12];
        for(int i = 0; i< newV.length;i++){
            newV[i] = v[i];
        }
        return newV;
    }

    public Vector3dInterface getP(){

        return p[11];
    }

    public double[] getMass(){
        return mass;
    }

    @Override
    /**
     *
     * returns new position at that point in time, by first computing the new velocity: multiply the acceleration on ship by step size
     * (time) to get the added velocity. Add new velocity to old one to get the new resulting velocity. Multiply the new velocity by time
     * to get the added position and add it on top of the old position to obtain the new position.
     * return the new position.
     */
    public StateInterface addMul(double step, RateInterface rate) {

        Change v2 = (Change) rate;

        Vector3dInterface[] a = v2.getA();

        for(int i =0; i< previousV.length;i++){
           v[i] = previousV[i].addMul(step,a[i]);
        }

        for(int i =0; i< previousP.length;i++){
            p[i] = previousP[i].addMul(step, v[i]);
        }
        return this;
    }

    /**
     * prints position of planets
      */
   public void print(){
       for (int i = 0; i < p.length; i++) {
           System.out.println("Position "+names[i]+ " : " + p[i].toString());
       }
    }
}


