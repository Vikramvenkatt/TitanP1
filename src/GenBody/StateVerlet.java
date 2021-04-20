package GenBody;

import interfaces.RateInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

import java.util.ArrayList;

//x(t+Δt)=2x(t)−x(t−Δt)+a(t)Δt2+O(Δt4)

public class StateVerlet implements StateInterface {

    public Vector3dInterface[] currentPos = new Vector3dInterface[12];

    public Vector3dInterface[] formerPos1;

    public Vector3dInterface[] formerPos2;

    public Vector3dInterface[] v = new Vector3dInterface[12];

    public final double[] mass = {1.9891e30, 4.8685e24, 3.302e23, 1.89813e27, 6.4171e23, 5.97219e24, 8.6813e25, 5.6834e26, 1.34553e23, 7.349e22, 1.02413e26, 1500};

    public StateVerlet(StateVerlet formerPos1){
        this.formerPos2 = formerPos1.getPositionOfPlanets();
        this.formerPos1 = formerPos1.getFormerPos();
    }

    public StateVerlet() {

    }

    public Vector3dInterface[] getPositionOfPlanets() {
        Vector3dInterface[] newp = new Vector3dInterface[12];

        for (int i = 0; i < newp.length; i++) {
            newp[i] = currentPos[i];
        }
        return newp;

    }

    public Vector3dInterface[] getFormerPos() {
        Vector3dInterface[] newp = new Vector3dInterface[12];

        for (int i = 0; i < newp.length; i++) {
            newp[i] = formerPos1[i];
        }
        return newp;

    }

    public void addOrigin(ArrayList<Planet> list, Vector3dInterface p0, Vector3dInterface v0) {
        for (int i = 0; i < list.size(); i++) {
            currentPos[i] = list.get(i).getInitialPosition();
            v[i] = list.get(i).getInitialVelocity();
        }
        currentPos[11] = p0;
        v[11] = v0;
    }


    @Override
    public StateInterface addMul(double step, RateInterface rate) {

        Change v2 = (Change) rate;

        Vector3dInterface[] a = v2.getA();

        for (int i = 0; i < currentPos.length ; i++) {
            currentPos[i] = formerPos1[i].mul(2);
            currentPos[i] = currentPos[i].sub(formerPos2[i]);
            a[i] = a[i].mul(step*step);
            currentPos[i] = currentPos[i].add(a[i]);
        }

        return this;
    }

    @java.lang.Override
    public String toString() {
        return null;
    }

    public double[] getMass() {
        return mass;
    }
}
