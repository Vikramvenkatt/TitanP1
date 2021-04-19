package GenBody;

import interfaces.ODEFunctionInterface;
import interfaces.ODESolverInterface;
import interfaces.RateInterface;
import interfaces.StateInterface;

public class VerletSolver implements ODESolverInterface {
    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {
        return new StateInterface[0];
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double tf, double h) {

        StateInterface[] solarSystemOverCourseOfTime = new StateInterface[(int)(tf/h)+1];

        solarSystemOverCourseOfTime[0] = y0;

        //solarSystemOverCourseOfTime[1] = RungeKutta bootstrap

        double time = 0;

        for (int i = 2; i < solarSystemOverCourseOfTime.length; i++) {

            solarSystemOverCourseOfTime[i] = step(f,time,solarSystemOverCourseOfTime[i-1], h);

            time+=h;

        }


        return new StateInterface[0];
    }

    @Override
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {

        NewtonsLawofGravity n = (NewtonsLawofGravity) f;

        RateInterface q = n.callVerlet(h,y);

        StateVerlet solarSystem = new StateVerlet((StateVerlet) y);

        StateInterface Tn =  solarSystem.addMul(h,q);

        return Tn;
    }
}
