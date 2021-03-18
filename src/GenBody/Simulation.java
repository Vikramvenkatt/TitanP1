package GenBody;

import interfaces.ProbeSimulatorInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

public class Simulation implements ProbeSimulatorInterface {

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

        EulerSolver solve = new EulerSolver();

        NewtonsLawofGravity n = new NewtonsLawofGravity();

        StateOfSolarSystem state=  new StateOfSolarSystem();

        state.addOrigin(planets.getPlanets());

        StateInterface[] arr = (solve.solve(n,state, tf,h)); // contains position of all planets every 1000 secs

        StateOfSpaceShip launchData = new StateOfSpaceShip();

        launchData.addLaunchData(p0,v0);

        StateInterface[] positionOfShip =  solve.solveShip(n,arr, launchData,31536000, 1000); // contains position of spaceship every 1000 sec

        StateOfSpaceShip[] arr3 = new StateOfSpaceShip[arr.length];

        for (int m = 0; m < arr.length; m++) {                  // cast into vectorArray
            arr3[m] = (StateOfSpaceShip) positionOfShip[m];
        }

        Vector3dInterface[] vectorShip = new Vector3dInterface[arr3.length];

        for (int g = 0; g < arr3.length; g++) {
            vectorShip[g] =  arr3[g].getP();
        }

        return vectorShip;
    }
}
