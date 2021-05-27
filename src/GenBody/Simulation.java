package GenBody;

import interfaces.ProbeSimulatorInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

public class Simulation implements ProbeSimulatorInterface {

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

       // VerletSolver solve = new VerletSolver();

        EulerSolver solve = new EulerSolver();

       //RungeKutta solve = new RungeKutta();

        NewtonsLawofGravity n = new NewtonsLawofGravity();

        StateOfSolarSystem state =  new StateOfSolarSystem();

        state.addOrigin(planets.getPlanets(), p0,v0);

        StateInterface[] arr = solve.solve(n,state, tf,h);// contains position of all planets every 1000 secs



        positionOfPlanets = arr;

        StateOfSolarSystem[] arr2 =  new StateOfSolarSystem[arr.length];

        Vector3dInterface[] vectorShip = new Vector3dInterface[arr.length];

        for (int g = 0; g < arr.length; g++) {
            arr2[g] = (StateOfSolarSystem) arr[g];
            vectorShip[g] =  arr2[g].getP();
        }
        System.out.println(arr2[arr.length-1].p[8].toString());
        System.out.println(arr2[arr.length-1].p[11].toString());
        System.out.println(arr2[arr.length-1].p[8].sub(arr2[arr.length-1].p[11]).norm());
       // System.out.println("Vector: "+vectorShip[vectorShip.length-1].toString());
        return vectorShip;
    }


    public StateInterface[] getPositionOfPlanets() {
        return positionOfPlanets;
    }
}
