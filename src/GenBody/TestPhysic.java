package GenBody;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

public class TestPhysic {

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

            launchData.addLaunchData((Vector3dInterface) new Vector(-1.471922101663588e+11,-2.860995816266412e+10,8.278183193596080e+06),new Vector(5.427193405797901e+03,-2.931056622265021e+04,6.575428158157592e-01));

           StateInterface[] positionOfShip =  solve.solveShip(n,arr, launchData,31536000, 1000);

           StateOfSolarSystem[] arr2 = new StateOfSolarSystem[arr.length];

        StateOfSpaceShip[] arr3 = new StateOfSpaceShip[arr.length];

            for (int m = 0; m < arr.length; m++) {
                arr2[m] = (StateOfSolarSystem) arr[m];
                arr3[m] = (StateOfSpaceShip) positionOfShip[m];
            }

            for (int i = 0; i < arr.length; i++) {
                arr3[i].print();
                System.out.println("      ");
            }

        }
}
