package test;

import static org.junit.jupiter.api.Assertions.*;

import GenBody.EulerSolver;
import GenBody.NewtonsLawofGravity;
import test.Derivative;
import GenBody.Vector;
import interfaces.ODEFunctionInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;
import org.junit.jupiter.api.Test;


class TestEulerSolver
{
    public static Vector3dInterface[] p;//position of titan is in this list and of spaceship
    public static Vector3dInterface[] v;
    double[] masses = {1.988500e30, 3.302e23};//i will be calculating our test cases for 2 planets in the list																								//Masses of first two planets
    StateInterface initial = new Derivative(v,p);

    double tf = 5.0;//starting time
    double[] ts = {0, 10, 40, 100, 1000, 20000};//different time intervals/divisions stored in the array that can be tesed with
    double t = 1;
    double step = 1.0;


    //SETTING TESTING CO-ORDINATES
    public static void init()
    {

        //Adding initial velocity vectors.
        v[0]= (new Vector(-1.420511669610689e+01, -4.954714716629277e+00, 3.994237625449041e-01));//velocity of sun
        v[1]=(new Vector( 3.892585189044652e+04,  2.978342247012996e+03, -3.327964151414740e+03));	//velocity of mercury

        p[0]=(new Vector( -6.806783239281648e+08,   1.080005533878725e+09,   6.564012751690170e+06));//position of sun
        p[1]=(new Vector(  6.047855986424127e+06,  -6.801800047868888e+10,  -5.702742359714534e+09));//position of mercury
    }

    @Test
    public void testSingleStep()
    {
        ODEFunctionInterface function1 = new NewtonsLawofGravity();//we have to pass the array containing the masses! for the calculations!																			//Create ODEFunction object
        EulerSolver test1 = new EulerSolver();
        StateInterface resultsStep1 = test1.step(function1, t, initial, step);
        Derivative resultsConverted = (Derivative)resultsStep1;
        System.out.println("A first test with single step: "+ resultsStep1.toString());


        assertEquals(new Vector(-14.205116696061488, -4.954714721197586,  0.39942376216744196), resultsConverted.velocity.get(0));//compare cases for sun
        assertEquals(new Vector(-6.806783381332816E8, 1.0800055289240103E9, 6564013.151113932), resultsConverted.position.get(0));

        //Assertions for planet 2
        assertEquals(new Vector(38925.85161703165, 2978.3697578652873, -3327.961878297275), resultsConverted.velocity.get(1));
        assertEquals(new Vector(6086781.838314573, -6.8017997500346634E10, -5.702745687678685E9), resultsConverted.position.get(1));

    }


    @Test
    public void testStep2()
    {
        ODEFunctionInterface function2 = new NewtonsLawofGravity();//check line 39

        EulerSolver test2 = new EulerSolver();
        StateInterface[] resultsStep2 = test2.solve(function2, initial,tf, step);
        Derivative finalResultConverted = (Derivative) resultsStep2[resultsStep2.length-1];
        System.out.println("Test2: ");
        for(int i=0; i< resultsStep2.length; i++)
        {
            System.out.print(resultsStep2[i].toString() + ", ");
        }


        //Determine final standing of both sun and mercury for assert test cases
        assertEquals(new Vector(-14.205116695879855, -4.954714739470827, 0.399423760657591), finalResultConverted.velocity.get(0));//Sun
        assertEquals(new Vector(-6.806783949537486E8, 1.0800055091051512E9, 6564014.748808979), finalResultConverted.position.get(0));
        assertEquals(5.0, finalResultConverted.time);
        assertEquals(new Vector(38925.850523216846, 2978.47980129011, -3327.952785811886), finalResultConverted.velocity.get(1));//mercury
        assertEquals(new Vector(6242485.243142055, -6.801798558670253E10, -5.702758999512559E9), finalResultConverted.position.get(1));
        assertEquals(5.0, finalResultConverted.time);
    }


    @Test
    public void testStep3()
    {
        ODEFunctionInterface function3 = new NewtonsLawofGravity();//CHECK LINE 39!!!

        EulerSolver test3 = new EulerSolver();
        StateInterface[] resultsStep3 = test3.solve(function3, initial,ts);
        System.out.println("Test3: ");
        for(int i=0; i< resultsStep3.length; i++)
        {
            System.out.print(resultsStep3[i].toString() + ", ");
        }


        //testing with time stamp 100
        Derivative interimRes = (Derivative) resultsStep3[2];
        assertEquals(new Vector(-14.20511669155974, -4.954715173460928,  0.3994237247979979), interimRes.velocity.get(0));
        assertEquals(new Vector(-6.806797444398345E8, 1.080005038407241E9, 6564052.694065405), interimRes.position.get(0));
        assertEquals(100.0, interimRes.time);

        //testing with time stamp 2000
        Derivative finalRes = (Derivative) resultsStep3[2];
        assertEquals(new Vector(38920.087035855984, 3528.58897741389, -3282.4721719566264), finalRes.velocity.get(1));
        assertEquals(new Vector(7.845596461740923E8, -6.795790837453871E10, -5.769258240054541E9), finalRes.position.get(1));
        assertEquals(20000.00, finalRes.time);
    }

}
