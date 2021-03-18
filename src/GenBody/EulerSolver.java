package GenBody;

import interfaces.*;

/**
 *
 *
 * Euler function
 */

public class EulerSolver implements ODESolverInterface {

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

        for(int i =1 ; i< solarSystemOverCourseOfTime.length; i++){


            solarSystemOverCourseOfTime[i] = step(f,time,solarSystemOverCourseOfTime[i-1], h);

            time+=h;

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

    /**
     *
     * @param f newtons gravity law
     * @param t time at which position of spaceship is calculated
     * @param y state of solar system at that before that point
     * @param ship state of ship before that time
     * @param h stepsize
     * @return postion of spaceship at time t
     */
    public StateInterface stepShip(ODEFunctionInterface f, double t, StateInterface y, StateInterface ship, double h) {

        NewtonsLawofGravity n = (NewtonsLawofGravity) f;

        RateInterface q = n.callSpaceShip(ship,y,h);

        StateOfSpaceShip spaceship = new StateOfSpaceShip((StateOfSpaceShip) ship);

        StateInterface Tn =  spaceship.addMul(h,q);

        return Tn;
    }

    /**
     *
     * @param f newtons gravitylaw
     * @param y0 intitial state of solar system
     * @param launchData initial position of spaceship
     * @param tf final time: 1 year
     * @param h stepsize
     * @return array containing the position of the ship at each step size
     */
    public StateInterface[] solveShip(ODEFunctionInterface f, StateInterface[] y0, StateInterface launchData, double tf, double h) {



        StateInterface[] spaceShipOverCourseOfTime = new StateInterface[y0.length];

        StateInterface yn = null;

        spaceShipOverCourseOfTime[0] = launchData;

        double time = 0;

        for(int i =1 ; i< spaceShipOverCourseOfTime.length; i++){


            spaceShipOverCourseOfTime[i] = stepShip(f,time,y0[i-1], spaceShipOverCourseOfTime[i-1], h);

            time+=h;

        }

        return spaceShipOverCourseOfTime;
    }




}
