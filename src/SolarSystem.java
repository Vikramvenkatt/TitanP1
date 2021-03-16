import interfaces.ODEFunctionInterface;
import interfaces.RateInterface;
import interfaces.StateInterface;

public class SolarSystem implements StateInterface, ODEFunctionInterface {

    private Planets planets;
    @Override
    public StateInterface addMul(double step, RateInterface rate) {
        return null;
    }

    @Override
    public RateInterface call(double t, StateInterface y) {
        return null;
    }

    //TODO : figure out what to do with this
    interface RateInterface {
    }
}
