package GenBody;

import interfaces.*;
/**
 * stores only the acceleration resulting from the gravitational forces.
 */
public class Change implements RateInterface{

    public Vector3dInterface[] a = new Vector3dInterface[12];
    //contains position and velocities
    private StateOfSolarSystem state;
    private Vector finaltitan = new Vector(8.790206157956954E11, -1.2037722320318977E12, -1.4411708984699354E10);
    private Vector earth = new Vector(6371e3,0,0);

    private Engine engine;


    //order of planets is sun[0],venus[1],mercury[2], jupiter[3], mars[4],earth[5], uranus[6], saturn[7],titan[8],moon[9],neptune[10]

    public void addA(Vector3dInterface[] a){
        for(int i =0; i < a.length; i++){
            this.a[i] = a[i];
        }
        engine.addForceOnShip(a[11]);
    }

    public Change(StateOfSolarSystem s,double t){
        this.state = s;
        engine = new Engine(state,t);
        earth = (Vector) earth.add(state.p[5]);
    }


    //Gets triggered every time a step is taken
    //Checks distance for the spaceship & titan/saturn
    public Vector3dInterface[] getA(){

        Vector3dInterface[] newA = new Vector3dInterface[12];
        if(calculatedistance()) {
            addAcceleration(engine.takeOff(earth, finaltitan));
            state.updateMassShip(engine.getTotalMass());
        }
        if(distanceToTitan())
        {
            System.out.println("It's inside the trigger range!");
            Vector3dInterface forceVector = engine.createOrbitalVector(state.p[11],state.p[8]);
            addAcceleration(engine.transformForceToAccelaration(forceVector));
        }
        for(int i =0; i< a.length; i++){
            newA[i] = a[i];
        }
        return newA;
    }


    private void addAcceleration(Vector3dInterface accEngine){
        a[11] = a[11].add(accEngine);
    }

    private boolean calculatedistance(){
       if(state.p[5].sub(state.p[11]).norm() > 8e9) //3e7 meters, 30 000 km
           return false;
       else
           return true;
    }
    private boolean distanceToTitan()
    {
        //should be 3e5
        if(state.p[8].sub(state.p[11]).norm() < 3e10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean calculatedistanceSaturn(){
        //should be 3e10 but deactived for now for testing
        if( state.p[7].sub(state.p[11]).norm() > 3e1)
            return false;
        else
            return true;
    }


}
