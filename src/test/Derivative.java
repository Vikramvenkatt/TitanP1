package test;

import GenBody.Vector;
import interfaces.RateInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class Derivative implements StateInterface
{
    //PUTTING VELOCITY FROM CHANGE CLASS INTO AN ARRAYLIST
    public ArrayList<Vector> velocity = new ArrayList<Vector>();//containing the array list for velocities of 12 objects
    public ArrayList<Vector> position = new ArrayList<Vector>();//containing the velocities for the same
    public double time;

    public Derivative(ArrayList<Vector> velocity, ArrayList<Vector> position, double time)//1ST Constructor without velocity to make it compatible with physics engine
    {
        this.velocity = velocity;
        this.position = position;
        this.time = 0;//starting from time 0
    }


    public Derivative(Vector3dInterface[] velocity, Vector3dInterface[] position, double time)
    {
        this.v = velocity;
        this.p = position;
        this.time = time;
    }

    public Derivative(Vector3dInterface[] velocity, Vector3dInterface[] position)
    {
        this.v = velocity;
        this.p = position;
    }
    //ERROR- FILL UP P
    //public Vector3dInterface[] p;//position of titan is in this list and of spaceship
    Vector3dInterface[] p = new Vector3dInterface[2];

    public Vector3dInterface[] v;
    //RATE IS THE RATE OF CHANGE
    public Derivative addmul(double step, RateInterface rate) //Essentially carry out this formula: yi+1 = yi + hif(ti, yi)
    //Thanks Youtube!!
    {
        RateChange change = (RateChange) rate;//from the new class I made

        Vector3dInterface[] v = new Vector3dInterface[this.p.length];
        Vector3dInterface[] p = new Vector3dInterface[this.p.length];

        for(int i=0; i< change.velocityChange.size(); i++)//looping through rate field arrays for time calculations
        {
            v[i]=((Vector) v[i].addMul(step,change.velocityChange.get(i)));
            p[i]=((Vector) p[i].addMul(step,change.positionChange.get(i)));
        }
        double time = this.time + step;

        return new Derivative(v,p,time);
    }


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


    public String toString()
    {
        String sum = "";
        for(int i=0; i< velocity.size(); i++)
        {
            sum += "\n(V: "+ velocity.get(i).toString() + "Position "+ position.get(i).toString()+ " Time Taken: "+ time + "),";
        }
        return sum;
    }

    public final double[] mass = {1.9891e30, 4.8685e24, 3.302e23, 1.89813e27, 6.4171e23, 5.97219e24, 8.6813e25, 5.6834e26, 1.34553e23, 7.349e22, 1.02413e26, 1500};

    public Vector3dInterface[] getPositionOfPlanets() {//called from State of Solar System
        Vector3dInterface[] newp = new Vector3dInterface[2];
        p[0] = (new Vector( -6.806783239281648e+08,   1.080005533878725e+09,   6.564012751690170e+06));
        p[1] = ( new Vector(  6.047855986424127e+06,  -6.801800047868888e+10,  -5.702742359714534e+09));
        System.out.println(Arrays.stream(p).toArray());
        for (int i = 0; i < newp.length; i++) {
            newp[i] = new Vector();
            newp[i] = newp[i].add(p[i]);
        }
        return newp;
    }
    public double[] getMass() {
        return mass;
    }



    public Derivative add(Derivative state)
    {
        for(int i = 0; i < position.size(); i++)
        {
            state.position.get(i).add(this.position.get(i));
            state.velocity.get(i).add(this.velocity.get(i));
        }
        return state;
    }

    //MULTIPLIES ALL ABOVE CO-ORDINATES BY THE SCALAR FOR THE WHOLE CLASS
    public Derivative scale(double scalar)
    {
        Vector3dInterface[] vCopy = v;
        Vector3dInterface[] pCopy = p;

        for(int i = 0; i < position.size(); i++)
        {
            vCopy[i].mul(scalar);//SCALING METHODS
            pCopy[i].mul(scalar);
        }
        return new Derivative(vCopy, pCopy);
    }
}
