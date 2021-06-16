package landing;

import GenBody.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class EulerLanding {

    private Vector preStepPos; //= new Vector(x,y,theta);
    private Vector postStepPos;
    private Vector preStepVel;
    private Vector postStepVel;
    private static double vMax; // max torque
    private static double uMax;

    public EulerLanding(Vector preStepPos, Vector preStepVel){
        this.preStepPos = preStepPos;
        this.preStepVel = preStepVel;
    }

    public Vector step(double v,double u, double h){

        double deltaTheta = preStepVel.getZ()+v*h;
        double theta = preStepPos.getZ()+deltaTheta*h;

        double deltaX = preStepVel.getX()+ (u*sin(theta))*h;
        double x = preStepPos.getX()+deltaX*h;

        double deltaY = preStepVel.getY()+(u*cos(theta)-1.352)*h;
        double y = preStepPos.getY()+deltaY*h;

        postStepPos = new Vector(deltaX,deltaY,deltaTheta);
        postStepVel = new Vector(x,y,theta);

        return postStepPos;
    }

    public Vector getPostStepVel() {
        return postStepVel;
    }
}
