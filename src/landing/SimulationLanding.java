package landing;
import GenBody.Vector;
import interfaces.ProbeSimulatorInterface;
import interfaces.Vector3dInterface;

public class SimulationLanding  {

    public static void main(String[] args) {
        Vector[] n = trajectory(new Vector(8000,8000,90), new Vector(50000,0,90),1000,10);
        for (int i = 0; i < n.length; i++) {
         System.out.println(n[i].toString());
        }
    }


    public static Vector[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {

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

    private static EulerLanding step(Vector3dInterface p0,Vector3dInterface v0, double h) {
        EulerLanding n = new EulerLanding(p0,v0);
        // TODO: add the controller here, to calculate v and u;
        n.step(1,1,h);
        return n;
    }
}
