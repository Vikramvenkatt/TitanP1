package GenBody;

import interfaces.*;
/**
 * stores only the acceleration resulting from the gravitational forces.
 */
public class Change implements RateInterface{

    public Vector3dInterface[] a = new Vector3dInterface[12];
    private StateOfSolarSystem state;
    private Vector finaltitan = new Vector(8.790206157956954E11, -1.2037722320318977E12, -1.4411708984699354E10);
    private Vector earth = new Vector(6371e3,0,0);

    private Engine engine;


    //order of planets is sun[0],venus[1],mercury[2], jupiter[3], mars[4],earth[5], uranus[6], saturn[7],titan[8],moon[9],neptune[10]

    public void addA(Vector3dInterface[] a){
        for(int i =0; i < a.length; i++){
            this.a[i] = a[i];
        }
    }

    public Change(StateOfSolarSystem s){
        this.state = s;
        engine = new Engine(state);
    }


    public Vector3dInterface[] getA(){

        Vector3dInterface[] newA = new Vector3dInterface[12];
        if(calculatedistance()) {
            addAcceleration(engine.calculateAccelaration(earth, finaltitan, a[11]));
            state.updateMass(engine.getTotalMass());
        }
        if(calculatedistanceSaturn()) {
            addAcceleration(engine.slowDown(state.p[11], state.p[7], a[11]));
            state.updateMass(engine.getTotalMass());
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
       if(state.p[5].sub(state.p[11]).norm() > 3e7)
           return false;
       else
           return true;
    }

    private boolean calculatedistanceSaturn(){
        if( state.p[7].sub(state.p[11]).norm() > 3e10)
            return false;
        else
            return true;
    }


}
