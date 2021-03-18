package GenBody;
import interfaces.ProbeSimulatorInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

public class TestPhysic implements ProbeSimulatorInterface {

        public static void main(String[] args){

            Planets planets = new Planets();

            EulerSolver solve = new EulerSolver();

            NewtonsLawofGravity n = new NewtonsLawofGravity();

            StateOfSolarSystem state=  new StateOfSolarSystem();

            state.addOrigin(planets.getPlanets());

            double[] time = new double[30];

            for(int z = 0 ; z< 30;z++){
                time[z] = z*86.400;
            }

           StateInterface[] arr = (solve.solve(n,state, 31536000, 1000));

            StateOfSpaceShip launchData = new StateOfSpaceShip();

            launchData.addLaunchData((Vector3dInterface) new Vector(-1.471922101663588e+11+6371e3,-2.860995816266412e+10,8.278183193596080e+06),new Vector(5.427193405797901e+03,-2.931056622265021e+04,6.575428158157592e-01));

           StateInterface[] positionOfShip =  solve.solveShip(n,arr, launchData,31536000, 1000);

           StateOfSolarSystem[] arr2 = new StateOfSolarSystem[arr.length];

            StateOfSpaceShip[] arr3 = new StateOfSpaceShip[arr.length];

            for (int m = 0; m < arr.length; m++) {
                arr2[m] = (StateOfSolarSystem) arr[m];
                arr3[m] = (StateOfSpaceShip) positionOfShip[m];
            }

            Vector3dInterface[] vectorShip = new Vector3dInterface[arr.length];

            for (int g = 0; g < arr3.length; g++) {
                arr3[g].print();
                vectorShip[g] =  arr3[g].getP();
            }


        }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double[] ts) {
        return new Vector3dInterface[0];
    }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) { // h needs to be 1000 and tf 31536000

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
