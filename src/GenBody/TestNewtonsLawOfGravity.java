package GenBody;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import interfaces.RateInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;
import org.junit.jupiter.api.Test;

class TestNewtonGravityFunction
{

    @Test
    void testNewtonGravityFunc()
    {
        /*Setup*/
        Vector3dInterface[] vel = new Vector3dInterface[2];																//Initialize arrayList of velocity vectors (First two planets)
        vel[0] = new Vector(-1.420511669610689e+01, -4.954714716629277e+00,  3.994237625449041e-01));
        vel[1]= (new Vector( 3.892585189044652e+04,  2.978342247012996e+03, -3.327964151414740e+03));
        Vector3dInterface[] pos = new Vector3dInterface[2];																//Initialize arrayList of position vectors (First two planets)
        pos[0] = (new Vector( -6.806783239281648e+08,   1.080005533878725e+09,   6.564012751690170e+06));
        pos[1] = ( new Vector(  6.047855986424127e+06,  -6.801800047868888e+10,  -5.702742359714534e+09));

         StateInterface test = new Derivative(vel, pos);																			//Generate the current state
        //System.out.println(test.toString());																				//Display
        //System.out.println("------");

        double[] masses = {1.988500e30, 3.302e23};																			//Array of masses (first two planets)

        /*Calculation*/
        NewtonsLawofGravity testing = new NewtonsLawofGravity();
        RateInterface res = testing.call(1, test);
        Change outcome = (Change) res;
        System.out.println("Outcome:");
        System.out.println(outcome.toString());

        /*Testing*/
        //position and veocity changes
        //assertEquals(new Vector(4.5401856113667494E-11, -4.568309492895859E-9, -3.7746210063492234E-10), outcome.velocityChange.get(0));
        //assertEquals(new Vector(-14.20511669610689, -4.954714716629277, 0.3994237625449041), outcome.positionChange.get(0));
    }
}