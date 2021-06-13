package landing;

public class InputController {
    /**
     * @author Vikram
     * define (not specific-open)inputs for controller such as x,y,theta,velocities
     * why do we need state class?
     * Ans:that will be the angle when spaceship falls out of orbit and crashes on titan(i think)
     */
    private double x; // horizontal orientation/ position of spaceship
    private double y; // vertical orientation of spaceship
    private double theta;// angle of rotation
    public double velocityOfX; 	// velocity of horizontal component during orbit
    public double velocityOfY; // velocity of vertical component
    public double thetaDerivative; // first derivative of theta for

    //tolerance values in the project manual PAGE 12
    private final double xTolerance = 0.1;
    private final double thetaTolerance = 0.02;
    private final double xDerTolerance = 0.1;
    private final double yDerTolerance = 0.1;
    private final double thetaDerivativeTolerance = 0.01;

    //public double u;    // acceleration provided by the main thruster
    //public double v;    // the torque provided by the side thrusters
    public final double gravityOnTitan = 1.352;//JUST THE CONSTANT ACCELERATION, NO CALCULATIONS REQUIRED

    public InputController(double x, double y, double theta, double XVelocity, double YVelocity, double thetaDerivative) {

        this.x = x;
        this.y = y;
        this.theta = theta;
        this.velocityOfX = XVelocity;
        this.velocityOfY = YVelocity;
        this.thetaDerivative = thetaDerivative;

    }

    /**
     * getter methods for x,y,theta
     * @return x,y,theta
     */
    public double getX() {

        return x;
    }

    public double getY() {

        return y;
    }

    public double getTheta() {//angle of rotation, input free for open loop controller

        return theta;
    }

    public boolean testAllAtPositionY0() {
        if(testX() && testEta() && testDerX() && testDerY() && testDerEta()) {
            return true;
        }
        return false;
    }

    /**
     * testing methods against tolerances in project manual
     * @return true or false
     */
    public boolean testX() {
        if(Math.abs(this.x) <= xTolerance) {
            return true;
        }
        return false;
    }

    public boolean testEta() {
        if(Math.abs(this.theta % 2*Math.PI) <= thetaTolerance) {
            return true;
        }
        return false;
    }

    public boolean testDerX() {
        if(Math.abs(this.velocityOfX) <= xDerTolerance) {
            return true;
        }
        return false;
    }

    public boolean testDerY() {
        if(Math.abs(this.velocityOfY) <= yDerTolerance) {
            return true;
        }
        return false;
    }

    public boolean testDerEta() {
        if(Math.abs(this.thetaDerivative) == thetaDerivativeTolerance) {
            return true;
        }
        return false;
    }

}
