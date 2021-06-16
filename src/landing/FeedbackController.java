package landing;

public class FeedbackController {
    private WindModel module;
    private InputController u;
    private InputController v;
    private double g = 1.352;
    protected boolean isXPositive; // positive/true = tilt 45 degrees left, negative/false = tilt 45 degrees right

    protected double activationHeight;
    public FeedbackController(WindModel module)
    {
        this.module = module;
        activationHeight = calculateThrustHeight();
    }

    /**
     *
     * @return derivative of theta (for a stepsize?)
     */
    public double tiltShip()
    {
        getDirection();
        if(isXPositive)
        {
            return -1;
        }
        return -1;
    }

    public void getDirection()
    {
        if(u.getxDist() > 0)
        {
            isXPositive = true;
        }
        else
        {
            isXPositive = false;
        }
    }

    public void adjustThrusters()
    {
        //add 250 meters as safety for now
        if(u.getxDist() < activationHeight + 250)
        {
            module.setMainThrustersToMax();
        }
    }


    //Called once and then saved
    public double calculateThrustHeight()
    {
        //calculates the height at which the thrusters should give max thrust to stop in time
        //the maximum thrust will be applied at the point where the y velocity will be zero at y = 0
        double activationHeight = u.getY() / (module.getMainThrusters()/g);
    }

    public boolean checkArrival()
    {
        return u.testAllAtPositionY0();
    }


}
