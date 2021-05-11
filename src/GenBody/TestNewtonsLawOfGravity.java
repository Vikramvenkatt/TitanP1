package GenBody;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import interfaces.RateInterface;
import interfaces.StateInterface;
import org.junit.jupiter.api.Test;

class TestNewtonGravityFunction extends StateOfSolarSystem
{

    //class testCases extends NewtonsLawofGravity {

        @Test
        void testNewtonGravityFunc() {
            //Setup of co-ordinates
            ArrayList<Vector> vel = new ArrayList<Vector>();//making array list of velocity vectors, starting out with only 2 planets
            vel.add(new Vector(-1.420511669610689e+01, -4.954714716629277e+00, 3.994237625449041e-01));
            vel.add(new Vector(3.892585189044652e+04, 2.978342247012996e+03, -3.327964151414740e+03));
            ArrayList<Vector> pos = new ArrayList<Vector>();   //making array list of the position vectors
            pos.add(new Vector(-6.806783239281648e+08, 1.080005533878725e+09, 6.564012751690170e+06));
            pos.add(new Vector(6.047855986424127e+06, -6.801800047868888e+10, -5.702742359714534e+09));

            StateInterface test = new Derivative(vel,pos);

            //Generate the current state
            //System.out.println(test.toString());//show it , commented out for now


           // double[] masses = {mass[0], mass[2]};//only use 2 planets for test cases

            /*Calculation*/
            NewtonsLawofGravity testing = new NewtonsLawofGravity();//
            //StateOfSolarSystem

            RateInterface res = testing.call(1, test);
            Change outcome = (Change) res;
            System.out.println("Outcome:");
            System.out.println(outcome.toString());

            /*Testing*/
            //position and veocity changes
            assertEquals(new Vector(4.5401856113667494E-11, -4.568309492895859E-9, -3.7746210063492234E-10), outcome.velocityChange.get(0));
            assertEquals(new Vector(-14.20511669610689, -4.954714716629277, 0.3994237625449041), outcome.positionChange.get(0));
        }
    }
//}
