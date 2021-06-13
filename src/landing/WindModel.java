package landing;

import java.util.Random;

/**
 * class calculate the wind impact on landing module
 *
 *
 * M*a=Ve*(mass flow rate)
 * M=lander mass
 * force=contactArea * airDensity * windSpeed
 *
 * a=windforce/M
 */
class WindModel extends LandingModule {
    private double g = 1.352; // m / s^2  gravity on Titan
    private double areaContact = 50; // m^2
    private double atmosphereDensity = 1.2*1.6; // kg/m^3

    private double meanWindSpeed; // overall mean wind speed
    private double windSpeedCurrent; // current wind speed after noise has been applied
    private double windAcceleration; // acceleration caused by wind

    public WindModel(){
        windFirstInitialization();
        windForceOnLander();
    }


    public void windFirstInitialization() {
        // random wind speed ranges form -20 to 20 meters per seconds;
        if(meanWindSpeed!=-10) {
            Random rand = new Random();
            meanWindSpeed = rand.nextInt(41) - 20;;
        }
    }


    public void windForceOnLander() {
        // random wind noise ranges from -0.5  to 0.5
        double windNoise = Math.random()-0.5;
        windSpeedCurrent = meanWindSpeed * windNoise;
        // force = contact area * atmosphere density * windSpeed
        double windForce = areaContact * atmosphereDensity * windSpeedCurrent;
        windAcceleration = windForce/totalMass;
        //System.out.println("Landing acceleration: " + acceleration.getX());
        //System.out.println("Wind acceleration: " + windAcc);
        //System.out.println();
        acceleration.setX(acceleration.getX() + windAcceleration);

    }


    public double getWindAcc() {
        // random wind noise ranges from -0.5 to 0.5
        double windNoise = Math.random()-0.5;
        windSpeedCurrent = meanWindSpeed * windNoise;
        // force = area of contact * air density * windSpeed
        double windForce = areaContact * atmosphereDensity * windSpeedCurrent;
        return windAcceleration = windForce/totalMass;
    }

    public double getWindSpeedCurrent() {
        return windSpeedCurrent;
    }

    public double getMeanWindSpeed() {
        return meanWindSpeed;
    }



}