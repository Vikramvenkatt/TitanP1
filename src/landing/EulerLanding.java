package landing;

import GenBody.Vector;
import interfaces.Vector3dInterface;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class EulerLanding {

    private Vector preStepPos; //= new Vector(x,y,theta);
    private Vector postStepPos;
    private Vector preStepVel;
    private Vector postStepVel;

    public EulerLanding(Vector3dInterface preStepPos, Vector3dInterface preStepVel){
        this.preStepPos = (Vector) preStepPos;
        this.preStepVel = (Vector) preStepVel;
    }

    public void step(double v,double u, double h){

        double deltaTheta = preStepVel.getZ()+v*h;
        double theta = preStepPos.getZ()+deltaTheta*h;

        if (theta>360)
          theta = (int) Math.ceil((theta%360));

        double deltaX = preStepVel.getX()+ (u*sin(theta))*h;
        double x = preStepPos.getX()+deltaX*h;

        double deltaY = preStepVel.getY()+(u*cos(theta)-1.352)*h;
        double y = preStepPos.getY()+deltaY*h;

        postStepVel = new Vector(deltaX,deltaY,deltaTheta);
        postStepPos = new Vector(x,y,theta);

        return;
    }

    public Vector getPostStepVel() {
        return postStepVel;
    }

    public Vector getPostStepPos() {
        return postStepPos;
    }
}
