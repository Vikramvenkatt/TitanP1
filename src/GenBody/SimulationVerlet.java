package GenBody;

import interfaces.ProbeSimulatorInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

public class SimulationVerlet implements ProbeSimulatorInterface {
    private StateInterface[] positionOfPlanets;

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double[] ts) {
        return new Vector3dInterface[0];
    }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {

        Planets planets = new Planets();

        VerletSolver solve = new VerletSolver();

        NewtonsLawofGravity n = new NewtonsLawofGravity();

        StateVerlet state =  new StateVerlet();

        state.addOrigin(planets.getPlanets(), p0,v0);

        StateInterface[] arr = (solve.solve(n,state, tf,h));// contains position of all planets every 1000 secs

        positionOfPlanets = arr;

        StateOfSolarSystem[] arr2 =  new StateOfSolarSystem[arr.length];

        Vector3dInterface[] vectorShip = new Vector3dInterface[arr.length];

        for (int g = 0; g < arr.length; g++) {
            arr2[g] = (StateOfSolarSystem) arr[g];
            vectorShip[g] =  arr2[g].getP();
        }

        return vectorShip;
    }
}

