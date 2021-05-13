package test;


import GenBody.Vector;
import interfaces.RateInterface;

import java.util.ArrayList;


/**
 * Derivative of y, such that it reflects the rate of change of velocity and position
 */
public class RateChange implements RateInterface
{
    public ArrayList<Vector> velocityChange = new ArrayList<Vector>();
    public ArrayList<Vector> positionChange = new ArrayList<Vector>();


    public RateChange(ArrayList<Vector> cv, ArrayList<Vector> cp)
    {
        this.velocityChange = cv;
        this.positionChange = cp;
    }


    public RateChange average(RateChange comp)
    {
        ArrayList<Vector> compareVelocity = comp.velocityChange;
        ArrayList<Vector> comparePosition = comp.positionChange;

        ArrayList<Vector> averageVelocity = new ArrayList<Vector>();
        ArrayList<Vector> averagePosition = new ArrayList<Vector>();

        for(int i = 0; i < velocityChange.size(); i++)
        {
            Vector velocityDifference = velocityChange.get(i).add(compareVelocity.get(i));
            averageVelocity.add((Vector) velocityDifference.mul(0.5));

            Vector positionDifference = positionChange.get(i).add(comparePosition.get(i));
            averagePosition.add((Vector) positionDifference.mul(0.5));
        }

        return new RateChange(averageVelocity, averagePosition);
    }

    public String toString()
    {
        String sum = "";
        for(int i=0; i< velocityChange.size(); i++)
        {
            sum += "(CV: "+ velocityChange.get(i).toString() + " |CP: "+ positionChange.get(i).toString()+ " ),";
        }
        return sum;
    }

    public RateChange add(RateChange rate)
    {
        for(int i = 0; i < positionChange.size(); i++)
        {
            rate.positionChange.get(i).add(this.positionChange.get(i));
            rate.velocityChange.get(i).add(this.velocityChange.get(i));
        }
        return rate;
    }


    public RateChange scale(double scalar)
    {
        ArrayList<Vector> vCopy = velocityChange;
        ArrayList<Vector> pCopy = positionChange;

        for(int i = 0; i < positionChange.size(); i++)
        {
            vCopy.get(i).mul(scalar);
            pCopy.get(i).mul(scalar);
        }
        return new RateChange(vCopy, pCopy);
    }
}

