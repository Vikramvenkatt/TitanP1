package landing;

import GenBody.Vector;

import java.util.Random;

public class AltWindModel {

    //multiplier = strength variation of wind as %
    //height is given as meters --> convert to km
    public double simulateWind(double height, double multiplier)
    {
        height = height/1000;
        Random rand = new Random();
        //gives an average number --> less likely to encounter extreme winds
        double randomGauss = rand.nextGaussian();
        // insert Math.abs check for randomGauss to prevent multiplier > 1 or < 0

        //true = wind goes to the right, false = wind goes left
        boolean direction = true;
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

        if(direction)
        {
            return windVelocity;
        }
        else return -windVelocity;

    }
}
