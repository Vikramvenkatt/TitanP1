package landing;

import GenBody.Vector;

import java.util.Random;

public class AltWindModel {

    //multiplier = strength variation of wind as %
    //height is given as meters --> convert to km
    public void simulateWind(double height, double multiplier)
    {
        height = height/1000;
        Random rand = new Random();
        //gives an average number --> less likely to encounter extreme winds
        double randomGauss = rand.nextGaussian();
        // insert Math.abs check for randomGauss to prevent multiplier > 1 or < 0

        double windVelocity;
        Vector direction;

    }
}
