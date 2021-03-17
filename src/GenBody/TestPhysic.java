package GenBody;
import interfaces.StateInterface;

public class TestPhysic {

        public static void main(String[] args){

            Planets planets = new Planets();

            EulerSolver solve = new EulerSolver();

            NewtonsLawofGravity n = new NewtonsLawofGravity();

            StateOfSolarSystem state=  new StateOfSolarSystem();

            state.addOrigin(planets.getPlanets());

            double[] time = {0,100000,2000,3000,4000,5000,6000,7000,80,90,100};

           StateInterface[] arr = (solve.solve(n,state,time));

           StateOfSolarSystem[] arr2 = new StateOfSolarSystem[arr.length];

            for (int m = 0; m < arr.length; m++) {
                arr2[m] = (StateOfSolarSystem) arr[m];
            }

            for (int i = 0; i < arr.length; i++) {
                arr2[i].print();
                System.out.println("      ");
            }

        }
}
