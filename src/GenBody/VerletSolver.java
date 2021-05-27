package GenBody;

import interfaces.ODEFunctionInterface;
import interfaces.ODESolverInterface;
import interfaces.RateInterface;
import interfaces.StateInterface;

//TO-DO:BOOTSTRAPPING-RUNGE KUTTA, TESTING PATH TRAJECTORY WITHIN 1, 10, 1000 M ETC

public class VerletSolver implements ODESolverInterface {
    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {
        return new StateInterface[0];
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double tf, double h) {

        StateInterface[] solarSystemOverCourseOfTime = new StateInterface[(int)Math.ceil(tf/h)+1];

        solarSystemOverCourseOfTime[0] = y0;

        double time = 0;

        for (int i = 1; i < solarSystemOverCourseOfTime.length; i++) {

            solarSystemOverCourseOfTime[i] = step(f,time,solarSystemOverCourseOfTime[i-1], h);

            time+=h;

        }

        return solarSystemOverCourseOfTime;
    }

    @Override
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {

        NewtonsLawofGravity n = (NewtonsLawofGravity) f;

        RateInterface q = n.call(h,y);

        StateOfSolarSystem solarSystem = new StateOfSolarSystem((StateOfSolarSystem) y);

        StateInterface Tn =  solarSystem.addMulVerlet(h,q);

        return Tn;
    }
}
