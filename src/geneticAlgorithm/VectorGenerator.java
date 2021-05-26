package geneticAlgorithm;

import GenBody.Vector;
import GenBody.Planets;

public class VectorGenerator {
    //Multiplier randomly selected, do research on other values
    private static double multiplier = 1e11;
    public static Vector getRandomVector()
    {

        Planets planets = new Planets();
        double x = Math.random() * multiplier;
        double y = Math.random() * multiplier;
        double z = Math.random() * 1e9;

        double[] randomPos = {x, y, z};
        double[] earthPosValues = {planets.getEarth().getInitialPosition().getX(),
                planets.getEarth().getInitialPosition().getY(),
                planets.getEarth().getInitialPosition().getY()};

        double[] posVectorValues = new double[3];

        for(int i = 0; i < 3; i++){
            double randomNum = Math.random();
            if(randomNum < 0.5){
                posVectorValues[i] = earthPosValues[i] - randomPos[i];
            }
            else{
                posVectorValues[i] = earthPosValues[i] + randomPos[i];
            }
        }
        Vector targetPosition = new Vector(posVectorValues[0], posVectorValues[1], posVectorValues[2]);

        return targetPosition;
    }
}
