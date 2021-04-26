package GenBody;

import interfaces.*;


public class RungeKutta implements ODESolverInterface {
    private double stepSize;

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double[] ts) {
        return new StateInterface[0];
    }

    @Override
    public StateInterface[] solve(ODEFunctionInterface f, StateInterface y0, double tf, double h) {

        StateInterface[] solarSystemOverCourseOfTime = new StateInterface[(int)Math.ceil(tf/h)+1];
        solarSystemOverCourseOfTime[0] = y0;

        double time = 0;
        for(int i = 1 ; i< solarSystemOverCourseOfTime.length; i++){
            double left_time = tf - time;
            if( left_time < h ) {
                //System.out.println("time left!");
                h = left_time;
            }
            solarSystemOverCourseOfTime[i] = step(f,time,solarSystemOverCourseOfTime[i-1], h);
            time += h;
        }

        return solarSystemOverCourseOfTime;
    }


    public StateOfSolarSystem update(StateInterface y, Vector3dInterface[] a1, Vector3dInterface[] v1, double step) {
        StateOfSolarSystem s = new StateOfSolarSystem((StateOfSolarSystem) y);
        for(int i = 0; i < 12; i++) {
            s.p[i] = s.previousP[i].addMul(step, v1[i]);
            s.v[i] = s.previousV[i].addMul(step, a1[i]);
        }
        return s;
    }

    public StateInterface step(ODEFunctionInterface f, double t, StateInterface y, double h) {
        //step
        NewtonsLawofGravity n = (NewtonsLawofGravity) f;
        RateInterface a1 = n.call(t,y);
        StateOfSolarSystem y1 = (StateOfSolarSystem) y;
        StateOfSolarSystem yn1 = update(y, ((Change)a1).getA(), y1.getVelocityOfPlanets(), h/6);

        //step2
        StateOfSolarSystem y2 = update(y, ((Change)a1).getA(), y1.getVelocityOfPlanets(), h/2);
        RateInterface a2 = (n.call(t+h/2,y2));
        StateOfSolarSystem yn2 = update(yn1, ((Change)a2).getA(), y2.getVelocityOfPlanets(), h/3);

        //step3
        StateOfSolarSystem y3 = update(y, ((Change)a2).getA(), y2.getVelocityOfPlanets(), h/2);
        RateInterface a3 = (n.call(t+h/2,y3));
        StateOfSolarSystem yn3 = update(yn2, ((Change)a3).getA(), y3.getVelocityOfPlanets(), h/3);

        //step4
        StateOfSolarSystem y4 = update(y, ((Change)a3).getA(), y3.getVelocityOfPlanets(), h);
        RateInterface a4 = (n.call(t+h,y4));
        StateOfSolarSystem yn4 = update(yn3, ((Change)a4).getA(), y4.getVelocityOfPlanets(), h/6);

        return yn4;
    }

}