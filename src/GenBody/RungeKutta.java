package GenBody;

import interfaces.*;

public class RungeKutta implements ODESolverInterface {

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
            h = Math.min(tf, h);
            solarSystemOverCourseOfTime[i] = step(f,time,solarSystemOverCourseOfTime[i-1], h);
            tf -= h;
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
        Change a1 = (Change) n.call(t,y);
        StateOfSolarSystem y1 = (StateOfSolarSystem) y;
        StateOfSolarSystem yn1 = update(y1, a1.getA(), y1.getVelocityOfPlanets(), h/6);

        //step2
        StateOfSolarSystem y2 = update(y1, a1.getARG(), y1.getVelocityOfPlanets(), h/2);
        StateOfSolarSystem yn2 = update(yn1, a1.getARG(), y2.getVelocityOfPlanets(), h/3);

        //step3
        StateOfSolarSystem y3 = update(y1, a1.getARG(), y2.getVelocityOfPlanets(), h/2);
        StateOfSolarSystem yn3 = update(yn2, a1.getARG(), y3.getVelocityOfPlanets(), h/3);

        //step4
        StateOfSolarSystem y4 = update(y1, a1.getARG(), y3.getVelocityOfPlanets(), h);
        StateOfSolarSystem yn4 = update(yn3, a1.getARG(), y4.getVelocityOfPlanets(), h/6);

        return yn4;
    }

}