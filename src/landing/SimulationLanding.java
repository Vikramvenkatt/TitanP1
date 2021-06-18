package landing;
import GenBody.Vector;
import interfaces.ProbeSimulatorInterface;
import interfaces.Vector3dInterface;

public class SimulationLanding  {

    private PIDtest closedControllerX = new PIDtest(0.0001,0.003,0.195,0,0,0,8000);
    private PIDtest closedControllerY = new PIDtest(0.00000001,0.00000001,0.1997,0,0,0,30000);

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
        double  u = this.closedControllerY.calculateOutput(p0.getY(),h);
        // TODO: add the controller here, to calculate v and u;
        n.step(0,u,h);
        return n;
    }
}
