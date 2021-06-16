package GenBody;

import interfaces.ProbeSimulatorInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

public class SimulationVerlet implements ProbeSimulatorInterface {

    public StateInterface[] positionOfPlanets = null;

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double[] ts) {
        return new Vector3dInterface[0];
    }

    /**
     *Simulates trajectory of spaceship and planets.
     *
     * @param p0 start position of spaceship
     * @param v0 start velocity
     * @param tf final time: 1 year
     * @param h stepsize
     * @return coordinates in 3dvector form at each step
     */

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {

        Planets planets = new Planets();

       StartVel s = new StartVel((Vector) v0);

       Engine e = new Engine(null,0);

       e.setBack();

        VerletSolver solve = new VerletSolver();

        NewtonsLawofGravity n = new NewtonsLawofGravity();

        StateOfSolarSystem state =  new StateOfSolarSystem();

        state.addOrigin(planets.getPlanets(), p0,v0);

        StateInterface[] arr = solve.solve(n,state, tf,h);// contains position of all planets every 1000 secs

       /* int  normalSteps = 31550;

        int smallerSteps = 600;

        double smallerStepsize = 10;

        double time =0;

        double [] ts = new double[normalSteps + smallerSteps];

        for (int i = 0; i < normalSteps; i++) {
            ts[i]=time;
            time+=h;
        }

        time = 0;

        for (int k = normalSteps; k < smallerSteps; k++) {
            ts[k] = time;
            time += smallerStepsize;
        }*/

       double[] ts = createArray(tf,h,10,10);

       // StateInterface[] arr = solve.solve(n,state,ts);

        positionOfPlanets = arr;

        StateOfSolarSystem[] arr2 =  new StateOfSolarSystem[arr.length];

        Vector3dInterface[] vectorShip = new Vector3dInterface[arr.length];

        for (int g = 0; g < arr.length; g++) {
            arr2[g] = (StateOfSolarSystem) arr[g];
            vectorShip[g] =  arr2[g].getP();

        }
        System.out.println("Distance: "+arr2[arr.length-1].p[8].sub(vectorShip[vectorShip.length-1]).norm());
        // System.out.println("mass: "+arr2[arr.length-1].mass[11]);
        return vectorShip;
    }

    public StateInterface[] getPositionOfPlanets() {
        return positionOfPlanets;
    }

    /*public Vector3dInterface getLastTitan(){
        return positionOfPlanets.p.
    }*/

    /**
     *
     *
     * @param tf final time
     * @param h normal step
     * @param adaptedStep needs to divide h without a rest
     * @param stepsToReplace the number of big steps to replace by small steps
     * @return array where ts[i]-ts[i-1] = stepsize
     */
    public double[] createArray(double tf,double h, double adaptedStep, int stepsToReplace){

        int length = (int) Math.ceil(tf/h);
        int normalSteps = length-stepsToReplace;
        int stepPerH = (int) Math.ceil(h/adaptedStep);
        int numberOfSmallSteps = stepPerH*stepsToReplace;
        double[] ts = new double[normalSteps+numberOfSmallSteps];
        double time =0;
        for (int i = 0; i < normalSteps; i++) {
            ts[i]=time;
            time+=h;
        }
        time += adaptedStep;
        for (int k = normalSteps; k < numberOfSmallSteps; k++) {
            ts[k] = time;
            time += adaptedStep;
        }
        return ts;
    }
}
