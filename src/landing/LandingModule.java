package landing;

import GenBody.Vector;

public class LandingModule {
    //gravity in m/s^2, for reference it's LOWER than earth's
    protected Vector position;
    protected Vector acceleration;

    //mass of lander according to announcement
    protected double massOfLander = 6000; //kg
    protected double massOfFuel = 1000;

    protected double mainThrusters; //aft thrusters/rear thrusters
    protected double sideThrusters; //we have 4 side thrusters
    private double dt; //deltaTime

    protected double totalMass = massOfFuel +massOfLander;

    protected boolean isLanded = false;

    public LandingModule(Vector position)
    {
        this.position = position;
    }
    
    public void calculateMass()
    {
        //NOTE: sideThrusters can't generate NEGATIVE thrust, so only two sideThrusters SHOULD be accounted for
        double totalThrust = Math.abs(mainThrusters) + Math.abs(sideThrusters)*4;
        //calculate spent fuel by calculating how much fuel has been burned
        //TODO: FORMULA NEEDS ADJUSTMENT
        double lostFuel = totalThrust*dt;
        massOfFuel = massOfFuel - lostFuel;
        if(massOfFuel <= 0)
        {
            System.out.println("No fuel left");
            massOfFuel = 0;
        }
        totalMass = massOfLander + massOfFuel;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public double getMainThrusters() {
        return mainThrusters;
    }

    public void setMainThrusters(double mainThrusters) {
        this.mainThrusters = mainThrusters;
    }

    public double getSideThrusters() {
        return sideThrusters;
    }

    public void setSideThrusters(double sideThrusters) {
        this.sideThrusters = sideThrusters;
    }

    public boolean isLanded() {
        return isLanded;
    }

    public void setLanded(boolean landed) {
        isLanded = landed;
    }

    public double getMassOfLander() {
        calculateMass();
        return massOfLander;
    }
}
