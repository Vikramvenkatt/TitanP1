package landing;
import GenBody.Vector;
import interfaces.ProbeSimulatorInterface;
import interfaces.Vector3dInterface;

public class SimulationLanding  {

    private int k=1;
    private double rotationTime=0;
    private double uY;
    private boolean alligned = false;
    private PIDtest closedControllerX = new PIDtest(0.0001,0.003,0.195,0,0,0,8000);
   // private PIDtest closedControllerY = new PIDtest(0.00000001,0.00000001,0.1998,0,0,0,30000);
   private PIDtest closedControllerY = new PIDtest(0.000021,0.00061,0.3998,0,0,0,30000);
    public Vector[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {

        closedControllerY.BoundedLimits(0,3);

        Vector[] trajectory = new Vector[(int)Math.ceil(tf/h)+1];

        EulerLanding n;

        double time=0;

        for (int i = 0; i < trajectory.length; i++) {

            n = step(p0,v0,h);

            p0 = n.getPostStepPos();

            v0 = n.getPostStepVel();

            trajectory[i] = (Vector) p0;

            time+=h;

        }
        return  trajectory;
    }

    private EulerLanding step(Vector3dInterface p0, Vector3dInterface v0, double h) {
        EulerLanding n = new EulerLanding(p0,v0);
        double  u=0;


        // = this.closedControllerY.calculateOutput(p0.getY(),h);
        double v=0;


      if(p0.getX() <= 0)
        u=-5;

        if(alligned){
            k++;
            u=uY;
        }

      if(p0.getX()>= 0 && v0.getX()==0 && p0.getZ()!=0){
          u = 0;
          rotationTime = rotationTime(p0.getZ());
          h = rotationTime;
          v = -5;
        }

      if(p0.getX()==0&&v0.getZ()!=0){
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

    private EulerLanding step2(Vector3dInterface p0, Vector3dInterface v0, double h) {
        AltWindModel a = new AltWindModel(true);

        v0.setX(v0.getX()+a.simulateWind(p0.getY()));
        EulerLanding n = new EulerLanding(p0,v0);
        double u = closedControllerX.calculateOutput(p0.getX(),h);
        if(p0.getX()==0)
            u = closedControllerY.calculateOutput(p0.getY(),h);
        n.step(0,u,h);
        return n;
    }
    private double openLoop(double pos, double vel){
        double acc = (vel*vel)/(2*pos)+1.352;
        return acc;
    }

    private double rotationTime(double angle){
        double time = Math.sqrt(angle/5);
        return time;
    }
}
