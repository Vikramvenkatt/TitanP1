package GenBody;

import interfaces.*;

public class EulerSolver implements ODESolverInterface {

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {



        StateInterface[] solarSystemOverCourseOfTime = new StateInterface[ts.length];

        double h = ts[1]-ts[0];

        for(int i =0 ; i< solarSystemOverCourseOfTime.length; i++){

            solarSystemOverCourseOfTime[i] = step(f,ts[i],y0,h);

        }

        return solarSystemOverCourseOfTime;
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double tf, double h) {


        return new StateInterface[0];
    }

    @Override
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {

        NewtonsLawofGravity n = (NewtonsLawofGravity) f;

        RateInterface q = n.call(h,y);

        StateOfSolarSystem solarSystem = new StateOfSolarSystem((StateOfSolarSystem) y);

        StateInterface Tn =  solarSystem.addMul(h,q);

        return Tn;
    }


}
