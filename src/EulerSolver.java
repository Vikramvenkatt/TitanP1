import interfaces.*;

public class EulerSolver implements ODESolverInterface {

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {
        return new StateInterface[0];
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double tf, double h) {
        return new StateInterface[0];
    }

    @Override
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {

        NewtonsLawofGravity n = (NewtonsLawofGravity) f;

        RateInterface q = n.call(h,y);

        StateOfSolarSystem solarSystem = (StateOfSolarSystem) y;

        Vector3dInterface[] formerPositions = solarSystem.formerPositionOfPlanets;

        VelocityOfPlanets k = (VelocityOfPlanets) q;

        Vector3dInterface[] velocity = k.velocityOfPlanets;

        Vector3dInterface[] positions = new Vector3dInterface[11];

        for(int i =0; i < velocity.length;i++)
            positions[i] = formerPositions[i].addMul(t,velocity[i]);


        return new StateOfSolarSystem(positions);
    }

}
