package GenBody;

public class TestPhysic {

        public static void main(String[] args){

            Planets planets = new Planets();

            EulerSolver solve = new EulerSolver();

            NewtonsLawofGravity n = new NewtonsLawofGravity();

            StateOfSolarSystem state=  new StateOfSolarSystem();

            state.addOrigin(planets.getPlanets());

           StateOfSolarSystem[] arr = (StateOfSolarSystem[]) solve.solve(n,state,100,  10);

            for (int i = 0; i < arr.length; i++) {
                arr[i].print();
            }

        }
}
