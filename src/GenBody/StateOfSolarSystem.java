package GenBody;

import interfaces.*;

import java.util.ArrayList;


public class StateOfSolarSystem implements StateInterface {

    public Vector3dInterface[] p;//position of titan is in this list and of spaceship

    public Vector3dInterface[] v;

    public Vector3dInterface[] previousP = new Vector3dInterface[12];

    public Vector3dInterface[] previousV = new Vector3dInterface[12];

    public final double[] mass = {1.9891e30, 4.8685e24, 3.302e23, 1.89813e27, 6.4171e23, 5.97219e24, 8.6813e25, 5.6834e26, 1.34553e23, 7.349e22, 1.02413e26, 1500};

    public final String[] names = {"sun", "venus", " mercury", "jupiter", "mars", "earth", "uranus", "saturn", "titan", "moon", "neptune", "spaceship"};

    //order of planets is sun[0],venus[1],mercury[2], jupiter[3], mars[4],earth[5], uranus[6], saturn[7],titan[8],moon[9],neptune[10]

    public StateOfSolarSystem(StateOfSolarSystem s) {
        Vector3dInterface[] previousP = s.getPositionOfPlanets();
        Vector3dInterface[] previousV = s.getVelocityOfPlanets();
        for(int i = 0; i < 12; i++) {
            this.previousP[i] = new Vector((Vector) previousP[i]);
            this.previousV[i] = new Vector((Vector) previousV[i]);
        }
        p = new Vector[12];
        v = new Vector[12];
    }

    public StateOfSolarSystem() {
        p = new Vector[12];
        v = new Vector[12];
        this.previousP = new Vector[12];
        this.previousV = new Vector[12];
    }

    public void addOrigin(ArrayList<Planet> list, Vector3dInterface p0, Vector3dInterface v0) {
        for (int i = 0; i < list.size(); i++) {
            p[i] = new Vector((Vector) list.get(i).getInitialPosition());
            v[i] = new Vector((Vector) list.get(i).getInitialVelocity());
        }
        p[11] = p0.add(p[5]);
        v[11] = v0.add(v[5]);
    }

    public Vector3dInterface[] getPositionOfPlanets() {
        Vector3dInterface[] newp = new Vector3dInterface[12];
        for (int i = 0; i < newp.length; i++) {
            newp[i] = new Vector();
            newp[i] = newp[i].add(p[i]);
        }
        return newp;
    }

    public Vector3dInterface[] getVelocityOfPlanets() {
        Vector3dInterface[] newV = new Vector3dInterface[12];
        for (int i = 0; i < newV.length; i++) {
            newV[i] = new Vector();
            newV[i] = newV[i].add(v[i]);
        }
        return newV;
    }

    public Vector3dInterface getP() {

        return p[11];
    }

    public double[] getMass() {
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

        for (int i = 0; i < previousV.length; i++) {
            v[i] = previousV[i].addMul(step, a[i]);
        }

        for (int i = 0; i < previousP.length; i++) {
            p[i] = previousP[i].addMul(step, v[i]);
        }
        return this;
    }

    public void print() {
        System.out.println("print");
        System.out.println("Position Titan" + " : " + p[8].toString());
        System.out.println("Position SpaceShip" + " : " + p[11].toString());
        System.out.println("Position Earth" + " : " + p[5].toString());
        System.out.println("Velocity Titan" + " : " + v[8].toString());
        System.out.println("Velocity SpaceShip" + " : " + v[11].toString());
        System.out.println("Velocity Earth" + " : " + v[5].toString());
    }
}



