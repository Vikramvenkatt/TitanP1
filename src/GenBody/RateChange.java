package GenBody;


import interfaces.RateInterface;

import java.util.ArrayList;


/**
 * Derivative of y, such that it reflects the rate of change of velocity and position
 */
public class RateChange implements RateInterface
{
    public ArrayList<Vector> velocityChange = new ArrayList<Vector>();
    public ArrayList<Vector> positionChange = new ArrayList<Vector>();

    /**
     * Constructor
     * @param cv Change in velocity arrayList
     * @param cp Change in position arrayList
     */
    public RateChange(ArrayList<Vector> cv, ArrayList<Vector> cp)
    {
        this.velocityChange = cv;
        this.positionChange = cp;
    }

    /**
     * Find the average values between this interface and the parameter
     * @param comp
     * @return the mean values between this and comp
     */
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

    /**
     * toString returns Rate as a String
     * @return String value representing Rate
     */
    public String toString()
    {
        String sum = "";
        for(int i=0; i< velocityChange.size(); i++)
        {
            sum += "(CV: "+ velocityChange.get(i).toString() + " |CP: "+ positionChange.get(i).toString()+ " ),";
        }
        return sum;
    }

    /**
     * Adds the argument with this state and returns the answer
     * @return sum of the two states
     */
    public RateChange add(RateChange rate)
    {
        for(int i = 0; i < positionChange.size(); i++)
        {
            rate.positionChange.get(i).add(this.positionChange.get(i));
            rate.velocityChange.get(i).add(this.velocityChange.get(i));
        }
        return rate;
    }

    /**
     * Multiplies this class by the scalar argument
     * @param scalar
     * @return a scaled copy of this class
     */
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

