package GenBody;



import interfaces.RateInterface;
import interfaces.StateInterface;

import java.util.ArrayList;

public class Derivative implements StateInterface
{
    public ArrayList<Vector> velocity = new ArrayList<Vector>();
    public ArrayList<Vector> position = new ArrayList<Vector>();
    public double time;

    /**
     * State constructor (No time parameter)
     *  The velocity vector
     * pos The position vector
     */
    public Derivative(ArrayList<Vector> velocity, ArrayList<Vector> position)
    {
        this.velocity = velocity;
        this.position = position;
        this.time = 0;
    }

    /**
     * State constructor (Time parameter)
     *  vel The velocity vector
     *  pos The position vector
     * t The time quantity of the current state
     */
    public Derivative(ArrayList<Vector> velocity, ArrayList<Vector> position, double time)
    {
        this.velocity = velocity;
        this.position = position;
        this.time = time;
    }

    /**
     * Modifies state to yield the next sequential state
     * @param step The time step
     * @param rate The rate of change
     * @return The next sequential state
     */
    public Derivative addmul(double step, RateInterface rate)     //Essentially carry out step yi+1 = yi + hif(ti, yi)
    {
        RateChange changeInfo = (RateChange) rate;                                 //Cast RateInterface into rate

        ArrayList<Vector> v = new ArrayList<Vector>();            //Initialise new ArrayLists to aid in construction of resultant StateInterface
        ArrayList<Vector> p = new ArrayList<Vector>();

        for(int i=0; i< changeInfo.velocityChange.size(); i++)        //Iterate over Rate fields
        {
            v.add((Vector) velocity.get(i).addMul(step,changeInfo.velocityChange.get(i)));
            p.add((Vector) position.get(i).addMul(step,changeInfo.positionChange.get(i)));
        }
        double time = this.time + step;                            //Calculate increase in time:

        return new Derivative(v,p,time);
    }

    /**
     * Adds a position and location vector change to each vector held in the state
     * @param step - The time step to be taken
     * @param rate - The rate of change
     * @return a new state as at time+step
     */
    public Derivative addMultiple(double step, RateInterface rate)
    {
        //Cast RateInterface into rate
        RateChange changeInfo = (RateChange) rate;

        //Initialise new ArrayLists to aid in construction of resultant StateInterface
        ArrayList<Vector> v = new ArrayList<Vector>();
        ArrayList<Vector> p = new ArrayList<Vector>();

        //Iterate over Rate fields
        for(int i=0; i< changeInfo.velocityChange.size(); i++)
        {
            v.add((Vector) velocity.get(i).addMul(step,changeInfo.velocityChange.get(i)));
            p.add((Vector) position.get(i).addMul(step,changeInfo.positionChange.get(i)));
        }
        double time = this.time + step;     //Calculate increase in time:
        return new Derivative(v,p,time);
    }

    @Override
    public StateInterface addMul(double step, RateInterface rate) {
        return null;
    }

    /**
     * Displays State in String format
     * @return String representing state object
     */
    public String toString()
    {
        String sum = "";
        for(int i=0; i< velocity.size(); i++)
        {
            sum += "\n(V: "+ velocity.get(i).toString() + " |P: "+ position.get(i).toString()+ " | Time: "+ time + "),";
        }
        return sum;
    }


    /**
     * Adds the argument with this state and returns the answer
     * @param state to be addd
     * @return sum of the two states
     */
    public Derivative add(Derivative state)
    {
        for(int i = 0; i < position.size(); i++)
        {
            state.position.get(i).add(this.position.get(i));
            state.velocity.get(i).add(this.velocity.get(i));
        }
        return state;
    }

    /**
     * Multiplies this class by the scalar argument
     * @param scalar
     * @return a scaled copy of this class
     */
    public Derivative scale(double scalar)
    {
        ArrayList<Vector> vCopy = velocity;
        ArrayList<Vector> pCopy = position;

        for(int i = 0; i < position.size(); i++)
        {
            vCopy.get(i).mul(scalar);
            pCopy.get(i).mul(scalar);
        }
        return new Derivative(vCopy, pCopy);
    }
}
