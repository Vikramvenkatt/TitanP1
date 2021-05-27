package geneticAlgorithm;

import GenBody.*;
import interfaces.ProbeSimulatorInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

public class MiniSimulation {
    public StateInterface[] positionOfPlanets = null;


    public Vector trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {
        Planets planets = new Planets();
        EulerSolver solve = new EulerSolver();
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
        return (Vector) arr2[arr.length-1].p[11];
    }

}
