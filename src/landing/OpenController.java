package landing;

import GenBody.State;
import GenBody.Vector;

public class OpenController {
    /**
     * VIP Class->input controller
     * @author Vikram
     * only contains the methods for verlet and euler testing, can create for others if needed
     */
    public InputController u;
    public InputController v;

    public double landEuler()
    {
        return -1;
    }


/*
    public InputController LandingEuler(State initialLaunch) {
        /**
         * method for euler wind controls/solver
         * x and y provided in project manual
         * eta created
         * @return u of type inputcontroller after control statements are implemented
         */
/*
        int shipWhenDistanceIsClosest = 0;
        Vector positionLaunch = initialLaunch.getPosition()[shipWhenDistanceIsClosest];
        Vector velocityLaunch = initialLaunch.getVelocity()[shipWhenDistanceIsClosest];
        double x = positionLaunch.getX();
        double y = positionLaunch.getY();
        double vx = velocityLaunch.getX();
        double vy = velocityLaunch.getY();


        double theta1 = 0;
        double theta2 = 0;//I THINK THIS IS FOR THE DERIVATIVE
        InputController f = new InputController(x, y, theta1, vx, vy, theta2);
        return u;
    }

    public InputController LandingVerlet(State initialLaunchState) {
        /**
         * same as previous method, but this is for verlet
         */
/*
        int spaceshipWhenDistanceIsClosest = 0;
        Vector positionLaunch = initialLaunchState.getPosition()[spaceshipWhenDistanceIsClosest];
        Vector velocityLaunch = initialLaunchState.getVelocity()[spaceshipWhenDistanceIsClosest];
        double x = positionLaunch.getX();
        double y = positionLaunch.getY();
        double vx = velocityLaunch.getX();
        double vy = velocityLaunch.getY();


        double theta1 = 0;
        double theta2 = 0;
        InputController f = new InputController(x, y, theta1, vx, vy, theta2);

        return v;
    }

    */
}
