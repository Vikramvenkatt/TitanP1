package GenBody;

import interfaces.*;

/**
 *
 *
 * Euler function
 */

public class EulerSolver implements ODESolverInterface {

    StateOfSolarSystem d = null;

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {

        return new StateInterface[0];
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double tf, double h) {

        StateInterface[] solarSystemOverCourseOfTime = new StateInterface[(int)(tf/h)+1];

        StateInterface yn = null;

        solarSystemOverCourseOfTime[0] = y0;

        double time = 0;

        int day = -1;

        for(int i =1 ; i< solarSystemOverCourseOfTime.length; i++){


            solarSystemOverCourseOfTime[i] = step(f,time,solarSystemOverCourseOfTime[i-1], h);

            time+=h;

            d = (StateOfSolarSystem)  solarSystemOverCourseOfTime[i];

            if((int) time/86400 > day) {
                day = (int) time / 86400;
                System.out.println("Day: "+day);
                d.print();
            }

        }


        return solarSystemOverCourseOfTime;
    }

    @Override
    /**return state of the solar system at the time t
     *
     *
     */
    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {

        NewtonsLawofGravity n = (NewtonsLawofGravity) f;

        RateInterface q = n.call(h,y);

        StateOfSolarSystem solarSystem = new StateOfSolarSystem((StateOfSolarSystem) y);

        StateInterface Tn =  solarSystem.addMul(h,q);

        return Tn;
    }

}
