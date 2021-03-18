package GenBody;

import interfaces.RateInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

/**
 * stores the current position and velocity and the previous one
 */
public class StateOfSpaceShip implements StateInterface {

    public Vector3dInterface v = null;

    private Vector3dInterface p = null;

    private Vector3dInterface formerP = null;

    private Vector3dInterface formerV = null;

    public StateOfSpaceShip(StateOfSpaceShip formerS){

        formerP = formerS.getP();

        formerV = formerS.getV();

    }

    public StateOfSpaceShip() {

    }

    public Vector3dInterface getV() {

        Vector3dInterface newV = (Vector3dInterface) new Vector(v.getX(), v.getY(),v.getZ());

        return newV;
    }

    public Vector3dInterface getP() {

        Vector3dInterface newP = (Vector3dInterface) new Vector(p.getX(), p.getY(),p.getZ());

        return newP;
    }

    public void addLaunchData(Vector3dInterface v, Vector3dInterface p){
        this.p = p;
        this.v = v;
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

        Change change = (Change) rate;

        Vector3dInterface a = change.getAShip();

       v = formerV.addMul(step,a);

        p = formerP.addMul(step,v);

        return this;
    }

    public void print(){
        System.out.println("PositionOfSpaceship: "+ p.toString());
    }
}
