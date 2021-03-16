import interfaces.*;

public class SolarSystem implements StateInterface, ODEFunctionInterface, ProbeSimulatorInterface, ODESolverInterface {

    private Planets planets;
    @Override
    public StateInterface addMul(double step, RateInterface rate) {
        return null;
    }

    @Override
    public RateInterface call(double t, StateInterface y) {
        return null;
    }

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
        return null;
    }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double[] ts) {
        return new Vector3dInterface[0];
    }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {
        return new Vector3dInterface[0];
    }

    @Override
    public StateInterface addMul(double step, interfaces.RateInterface rate) {
        return null;
    }

    //TODO : figure out what to do with this
    interface RateInterface {
    }
}
