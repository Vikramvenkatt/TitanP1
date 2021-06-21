package landing;
import GenBody.Vector;
import interfaces.ProbeSimulatorInterface;
import interfaces.Vector3dInterface;

public class SimulationLanding  {

    private int k=1;
    private double rotationTime=0;
    private double uY;
    private boolean alligned = false;
    private boolean freefall = true;
    private double tampering = 134.1349913495597;
    private PIDtest closedControllerX = new PIDtest(0.0001,0.003,0.195,0,0,0,8000);
    private PIDtest closedControllerY = new PIDtest(0.000021,0.00061,0.3998,0,0,0,30000);

    /** Method to compute the trajectory by open loop
     *
     * @param p0 starting position
     * @param v0 starting velocity
     * @param tf time for how long the simulation should run
     * @param h time-step
     * @return all the states at each time-step respectively
     */
    public Vector[] trajectoryOpenloop(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {

        Vector[] trajectory = new Vector[(int)Math.ceil(tf/h)+1];

        EulerLanding n;

        double time=0;

        for (int i = 0; i < trajectory.length; i++) {

            n = openloopStep(p0,v0,h);

            p0 = n.getPostStepPos();

            v0 = n.getPostStepVel();

            trajectory[i] = (Vector) p0;

            time+=h;

        }
        return  trajectory;
    }

    /**Method to compute the trajectory by closed loop
     *
     * @param p0 starting position
     * @param v0 starting velocity
     * @param tf time for how long the simulation should run
     * @param h time-step
     * @return all the states at each time-step respectively
     */
    public Vector[] trajectoryCLosedloop(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {

        closedControllerY.BoundedLimits(0,3);

        Vector[] trajectory = new Vector[(int)Math.ceil(tf/h)+1];

        EulerLanding n;

        double time=0;

        for (int i = 0; i < trajectory.length; i++) {

            n = closedLoopStep(p0,v0,h);

            p0 = n.getPostStepPos();

            v0 = n.getPostStepVel();

            trajectory[i] = (Vector) p0;

            time+=h;

        }
        return  trajectory;
    }

    /**
     *
     *
     * @param p0 p0 position vector
     * @param v0 velocity vector
     * @param h timestep
     * @return Eulerlanding which stores the state of the project at that time
     */
    private EulerLanding openloopStep(Vector3dInterface p0, Vector3dInterface v0, double h) {
        EulerLanding n = new EulerLanding(p0,v0);
        double  u=0;
        double v=0;

      if(p0.getX() <= 0)        //once the spaceship finished its manouver to the x=0
        u=-5;
        if(alligned){           // //once the spaceship finished its manouver to the x=0 and is turned
            k++;
            u=uY;
            if(freefall){
                freefall = false;
                h = tampering/-v0.getY();
                u=1.352;
            }
            if(p0.getY()<0.1 && p0.getY()>0){
                u=-v0.getY()+1.352;                     // so that it stops the ship on it lasts meter above the ground
            }
        }

      if(p0.getX()>= 0 && v0.getX()==0 && p0.getZ()!=0){    // rotate the spaceship so that it will slow don the descend on the y axis, first part
          u = 0;
          rotationTime = rotationTime(p0.getZ());
          h = rotationTime;
          v = -5;
        }

      if(p0.getX()==0&&v0.getZ()!=0){                       //// rotate the spaceship so that it will slow don the descend on the y axis,second part of the rotation to stop the spin
          u = 0;
          h = rotationTime;
          v = 5;
          alligned = true;
      }
        n.step(v,u,h);
      if(alligned&&k==1)
          uY =openLoop(n.getPostStepPos().getY(),n.getPostStepVel().getY());
        return n;
    }

    /**
     *
     *
     * @param p0 position vector
     * @param v0 velocity vector
     * @param h timestep
     * @return Eulerlanding which stores the state of the project at that time
     */
    private EulerLanding closedLoopStep(Vector3dInterface p0, Vector3dInterface v0, double h) {
        AltWindModel a = new AltWindModel(true);

        v0.setX(v0.getX()+a.simulateWind(p0.getY()));
        EulerLanding n = new EulerLanding(p0,v0);
        double u = closedControllerX.calculateOutput(p0.getX(),h);
        if(p0.getX()==0)
            u = closedControllerY.calculateOutput(p0.getY(),h);
        n.step(0,u,h);
        return n;
    }

    /**
     *
     *
     * @param pos distance from ground
     * @param vel velocity of the probe at that point
     * @return acceleration needed to land
     */
    private double openLoop(double pos, double vel){
        double acc = (vel*vel)/(2*pos)+1.352;       // +1.352 because thats titan gravity, needed to transfer the accelerated fall into a steady fall
        return acc;
    }

    /**
     *
     *
     * @param angle angle in rad, by which the ship needs to turn to get to zero degrees
     * @return the time it needs to rotate, assumed that u have a thrust of 5 m/s^2
     */
    private double rotationTime(double angle){
        double time = Math.sqrt(angle/5);           // sidethrusters with 5m/s^2
        return time;
    }
}
