package landing;

import GenBody.Vector;

import java.util.Random;

public class AltWindModel {

    //make true to make the windModel stochastic/randomized
    private boolean isStochastic;

    public AltWindModel(boolean isStochastic)
    {
        this.isStochastic = isStochastic;
    }
    //multiplier = strength variation of wind as %
    //height is given as meters --> convert to km
    public double simulateWind(double height)
    {
        height = height/1000;
        Random rand = new Random();
        //gives an average number --> less likely to encounter extreme winds
        double randomGauss = rand.nextGaussian();
        // insert Math.abs check for randomGauss to prevent multiplier > 1
        if(Math.abs(randomGauss) > 1)
        {
            randomGauss = 1;
        }

        //true = wind goes to the right, false = wind goes left



        double windVelocity = 0;  //in m/s
        if(height > 120)
        {
            windVelocity = 0;
        }
        else if(height < 120 && height > 100)
        {
            windVelocity = 120;
        }
        else if(height < 100 && height > 60)
        {
            windVelocity = 5;
        }
        else if(height < 60)
        {
            windVelocity = 1;
        }

        boolean direction = true;
        if(isStochastic)
        {
            windVelocity *= randomGauss;
            double randomDirectionVal = Math.random();
            if(randomDirectionVal < 0.5)
            {
                direction = false;
            }
            else { direction = true; }
        }
        if(direction)
        {
            return windVelocity;
        }
        else return -windVelocity;

    }
}
