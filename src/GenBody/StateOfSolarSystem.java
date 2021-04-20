package GenBody;

import interfaces.*;

import java.util.ArrayList;


public class StateOfSolarSystem implements StateInterface {

    public Vector3dInterface[] p = new Vector3dInterface[12];

    public Vector3dInterface[] v = new Vector3dInterface[12];

    public Vector3dInterface[] previousP = new Vector3dInterface[12];

    public Vector3dInterface[] previousV = new Vector3dInterface[12];




    private Vector position;
    private Vector velocity;

    public StateOfSolarSystem(Vector position, Vector velocity){
        this.position = new Vector(position.getX(), position.getY(), position.getZ());
        this.velocity = new Vector(velocity.getX(),velocity.getY(),velocity.getZ());
    }

    /*public StateOfSolarSystem(BasicRungeKutta basicRungeKutta){
        this.position = basicRungeKutta.getPosition3D();
        this.velocity = basicRungeKutta.getVelocity3D();
    }*/




    public Vector getPosition3D() {
        return new Vector(position.getX(),position.getY(),position.getZ());
    }


    public Vector getVelocity3D() {
        return new Vector(velocity.getX(),velocity.getY(),velocity.getZ());
    }


    public void setPosition3D(Vector position) {
        this.position = new Vector(position.getX(),position.getY(),position.getZ());
    }


    public void setVelocity3D(Vector velocity) {
        this.velocity = new Vector(velocity.getX(),velocity.getY(),velocity.getZ());
    }
    public final double[] mass = {1.9891e30, 4.8685e24, 3.302e23, 1.89813e27, 6.4171e23, 5.97219e24, 8.6813e25, 5.6834e26, 1.34553e23, 7.349e22, 1.02413e26, 1500};

    public final String[] names = {"sun", "venus", " mercury", "jupiter", "mars", "earth", "uranus", "saturn", "titan", "moon", "neptune", "spaceship"};

    //order of planets is sun[0],venus[1],mercury[2], jupiter[3], mars[4],earth[5], uranus[6], saturn[7],titan[8],moon[9],neptune[10]

    public StateOfSolarSystem(StateOfSolarSystem s) {
        this.previousP = s.getPositionOfPlanets();
        previousV = s.getVelocityOfPlanets();
    }

    public StateOfSolarSystem() {

    }

    public void addOrigin(ArrayList<Planet> list, Vector3dInterface p0, Vector3dInterface v0) {
        for (int i = 0; i < list.size(); i++) {
            p[i] = list.get(i).getInitialPosition();
            v[i] = list.get(i).getInitialVelocity();
        }
        p[11] = p0;
        v[11] = v0;
    }

    public Vector3dInterface[] getPositionOfPlanets() {
        Vector3dInterface[] newp = new Vector3dInterface[12];
        for (int i = 0; i < newp.length; i++) {
            newp[i] = p[i];
        }
        return newp;
    }

    public Vector3dInterface[] getVelocityOfPlanets() {
        Vector3dInterface[] newV = new Vector3dInterface[12];
        for (int i = 0; i < newV.length; i++) {
            newV[i] = v[i];
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

    public StateInterface RungeKutta(double step, RateInterface rate ){

        Change v2 = (Change) rate;

        Vector3dInterface[] a = v2.getA();

    Vector3dInterface[] intermediatePosition1 = new Vector3dInterface[12];

    Vector3dInterface[] intermediatePosition2 = new Vector3dInterface[12];

    Vector3dInterface[] endPosition = new Vector3dInterface[12];

    Vector3dInterface[] intermediateVelocity1 = new Vector3dInterface[12];

    Vector3dInterface[] intermediateVelocity2 = new Vector3dInterface[12];

    Vector3dInterface[] endVelocity = new Vector3dInterface[12];

    Vector3dInterface[] intermediateAcceleration1 = new Vector3dInterface[12];

    Vector3dInterface[] intermediateAcceleration2 = new Vector3dInterface[12];

    Vector3dInterface[] endAcceleration = new Vector3dInterface[12];

        for (int i = 0; i < previousP.length; i++) {
            //STEP 1
            ; //a1

            //STEP 2
            intermediatePosition1[i] = previousP[i].add(previousV[i].mul(step / 2.0)); //p2 = p1 + v1 * 1/2 * step
            intermediateVelocity1[i] = previousV[i].add(a[i].mul(step / 2.0)); //v2 = v1 + a1 * 1/2 * step
          //  intermediateAcceleration1 = acceleration(intermediatePosition1, intermediateVelocity1); //a2 = acceleration(p2,v2)

            //STEP 3
            intermediatePosition2[i] = previousP[i].add(intermediateVelocity1[i].mul(step / 2.0)); //p3 = p1 + v2 * 1/2 * step
            intermediateVelocity2[i] = previousV[i].add(intermediateAcceleration1[i].mul(step / 2.0)); //v3 = v1 + a2 * 1/2 * step
           // intermediateAcceleration2 = acceleration(intermediatePosition2, intermediateVelocity2); //a3 = acceleration(p3,v3)

            //STEP 4
            endPosition[i] = previousP[i].add(intermediateVelocity2[i].mul(step)); //p4 = p1 + v3 * step
            endVelocity[i] = previousV[i].add(intermediateAcceleration2[i].mul(step)); //v4 = v1 + a3 * step
          //  endAcceleration = acceleration(endPosition, endVelocity); //a4 = acceleration(p4,v4)

            //positionStep = 1/6 * step * (v1 + 2*v2 + 2*v3 + v4);
            Vector3dInterface positionStep = previousV[i].add(intermediateVelocity1[i].mul(2.0)).add(intermediateVelocity2[i].mul(2.0)).add(endVelocity[i]).mul(step / 6.0);
            //velocityStep = 1/6 * step (a1 + 2*a2 + 2*a3 + a4)
            Vector3dInterface velocityStep = a[i].add(intermediateAcceleration1[i].mul(2.0)).add(intermediateAcceleration2[i].mul(2.0)).add(endAcceleration[i]).mul(step / 6.0);

            //Calculate next position and velocity and update current position and velocity
            p[i] = previousP[i].add(positionStep);
            v[i] = previousV[i].add(velocityStep);

        }
        return this;
    }

    /**
     * prints position of planets
     */
    public void print() {

        System.out.println("Position Titan" + " : " + p[8].toString());
        System.out.println("Position SpaceShip" + " : " + p[11].toString());

    }

}


