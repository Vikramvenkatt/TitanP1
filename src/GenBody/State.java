package GenBody;

import interfaces.RateInterface;
import interfaces.StateInterface;
import test.RateChange;

/**
 * @author Vikram
 * refactored and written for phase 3 from the Derivative class
 * Purpose:to represent state of any planet list object when it is being implemented by an ODE
 * IMPLEMENTED CHANGES:changed addmul method to include acceleration functions
 */
public class State implements StateInterface {

    public Vector position[];
    public Vector velocity[];
    public double time;
    public boolean spaceShip[];

    public State(Vector[] pos, Vector[] velocity, boolean[] spaceShip, double time) {

        this.position = new Vector[pos.length];
        this.velocity = new Vector[velocity.length];
        this.spaceShip = spaceShip;

        System.arraycopy(pos, 0, this.position, 0, pos.length);
        System.arraycopy(velocity, 0, this.velocity, 0, velocity.length);
        this.time = time;
    }

    /**
     * getter setter methods
     * @return only variables
     */
    public Vector[] getPosition() {

        return position;
    }


    public void setPosition(Vector[] position) {

        System.arraycopy(position, 0, this.position, 0, position.length);
    }


    public Vector[] getVelocity() {

        return velocity;
    }


    public void setVelocity(Vector[] velocity) {

        System.arraycopy(velocity, 0, this.velocity, 0, velocity.length);
    }


    public boolean[] getspaceShip() {
        return spaceShip;
    }


    public void setSpaceShip(boolean[] spaceShip) {

        System.arraycopy(spaceShip, 0, this.spaceShip, 0, spaceShip.length);
    }


    public double getTime() {

        return time;
    }


    public void setTime(double time) {

        this.time = time;
    }


    /**
     * the new state is calculated by: step into rate
     *THIS IS THE ADDITION FROM DERIVATIVE CLASS
     * @param step
     * @param rate   average rate change taken from ratechange class
     * @return The new state created in line 91
     */
    @Override
    public State addMul(double step, RateInterface rate) {

        State newState = new State(getPosition(), getVelocity(), getspaceShip(), getTime() + step);

        for (int i = 0; i < position.length; i++) {

            Vector newVelocity = (Vector) newState.getVelocity()[i].addMul(step, ((Rate) rate).getAcceleration()[i]);
            Vector newPosition = (Vector) newState.getPosition()[i].addMul(step, ((Rate) rate).getVelocity()[i]);

            newState.getPosition()[i] = newPosition;
            newState.getVelocity()[i] = newVelocity;
        }
        return newState;
    }
}
