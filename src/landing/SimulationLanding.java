package landing;
import GenBody.Vector;
import interfaces.ProbeSimulatorInterface;
import interfaces.Vector3dInterface;

public class SimulationLanding  {

    public void main(String[] args) {
        Vector[] n = trajectory(new Vector(8000,8000,0), new Vector(50000,0,0),1000,10);
        for (int i = 0; i < n.length; i++) {
         System.out.println(n[i].toString());
        }
    }
    private PIDtest closedControllerX = new PIDtest(0,0,0,0,0,0);
    private PIDtest closedControllerY = new PIDtest(0.0001,0.0001,0.0001,0,0,0);

    public Vector[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {

        closedControllerY.BoundedLimits(1,1);

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
        double  u = this.closedControllerY.calculateOutput(p0.getY(),v0.getY(),h);
        // TODO: add the controller here, to calculate v and u;
        n.step(0,u,h);
        return n;
    }
}
