package landing;

import GenBody.Vector;

/**
 *
 * The formulae we used:
 * First calculate the acceleration value
 * a=Ve*mass flow rate/M
 *
 * Second calculate fuel usage time
 * t = (v1 - v0)/a
 *
 * Third calculate the fuel cost
 * fuel = mass flow rate*fuel burning time
 *
 *
 */

public class FuelCost {

    //velocity
    private Vector currentVelocity;//velocity before use of fuel
    private Vector postVelocity;//velocity after use of fuel
    private Vector changeOfVelocity;//delta vel

    //engine
    private final double effectiveExhaustVelocity;//(m/s)
    private final double thrust;//(N)

    //1. Combustion fuel
    private static final double ex_Vel_Combustion=4e3;//4000m/s
    private static final double thrustCombustion=3e7;//30000000N

    //2. Ion fuel
    private static final double ex_Vel_Iron=2e4;//20000m/s
    private static final double thrustIron=1e-1;//0.1N

    //3. Magnetoplasmadynamic
    //private static final double ex_Vel_Magneto=Math.random()*(60000-15000+1)+15000;//15000-60000 m/s
    //private static final double thrustMagneto=Math.random()*(25-2.5+1)+2.5;//2.5-25N

    private static final double ex_Vel_Magneto=60000;//15000-60000 m/s
    private static final double thrustMagneto=25;//2.5-25N


    private final double massFlowRate;//(kg/s)

    private double fuelBurningTime;

    private double accValue;//acceleration.norm()

    //mass
    private static final double massOfSpaceShip = 7.8e4;//Mass of spaceship inkg
    private static final double massOfLander = 6e3;//kg
    private static double currentMassOfFuel = 1e4;//Assumption

    private static double currentMassTotal = massOfSpaceShip + currentMassOfFuel + massOfLander ;//Mass of spaceship + fuel
    private double costOfFuel;


    //constructor
    public FuelCost(Vector currentVelocity, Vector postVelocity, int n) throws Exception {
        //initial velocity before
        this.currentVelocity=currentVelocity;

        //velocity after
        this.postVelocity=postVelocity;

        if(currentMassOfFuel<0) {
            throw new RuntimeException("There is no fuel left! ");
        }

        switch(n)
        {
            case 1:
                //Combustion fuel
                this.effectiveExhaustVelocity =ex_Vel_Combustion;
                this.thrust =thrustCombustion;
                this.massFlowRate=thrust/effectiveExhaustVelocity;
                break;

            case 2:
                //Ion fuel
                this.effectiveExhaustVelocity=ex_Vel_Iron;
                this.thrust=thrustIron;
                this.massFlowRate=thrust/effectiveExhaustVelocity;
                break;

            case 3:
                //Magnetoplasmadynamic
                this.effectiveExhaustVelocity=ex_Vel_Magneto;
                this.thrust=thrustMagneto;
                this.massFlowRate=thrust/effectiveExhaustVelocity;
                break;

            default:
                throw new Exception("Please input a valid number from one to three");

        }


    }

    public Vector calDeltaOfVelocity(){

        //delta of velocity
        changeOfVelocity=(Vector) postVelocity.sub(currentVelocity);

        return changeOfVelocity;

    }

    public double calAccValue() {

        accValue= effectiveExhaustVelocity *massFlowRate/ massOfSpaceShip;

        return accValue;

    }

    public double calcTime(){

        fuelBurningTime =(changeOfVelocity.norm())/accValue;

        return fuelBurningTime;
    }


    //calculate the acceleration
    public Vector calAcc() {
        //acceleration
        return (Vector) changeOfVelocity.mul(1D / fuelBurningTime);
    }



    //calculate the fuel cost
    //fuel cost=usage time * mass flow rate
    public void fuelCost() throws Exception {

        calAccValue();
        calDeltaOfVelocity();
        calcTime();
        calAcc();

        costOfFuel= fuelBurningTime *massFlowRate;//the mass cost

        currentMassOfFuel=(currentMassOfFuel)-(costOfFuel);
        if(currentMassOfFuel<0) {
            throw new Exception("Not enough fuel left");

        }

    }

    public double getFuelCost(){
        return costOfFuel;
    }

    public double getCurrentMassOfFuel(){
        return currentMassOfFuel;
    }

    public void setCurrentMassOfFuel(double newMass) {
        currentMassOfFuel=newMass;
        currentMassTotal = massOfSpaceShip +currentMassOfFuel+ massOfLander; //current mass of fuel + Spaceship + lander

    }

    public double getFuelBurningTime() {
        return fuelBurningTime;
    }

    public static void main(String[] args) throws Exception {

        //take-off
        Vector v0=new Vector(0, 0, 0);//Velocity before burning of fuel
        Vector vt=new Vector(0, 60000, 0);//Velocity after burning of fuel

        FuelCost conbustion=new FuelCost(v0,vt,1);
        conbustion.setCurrentMassOfFuel(1e11);
        conbustion.fuelCost();
        double fuelCost1=conbustion.getFuelCost();
        Vector acc1=conbustion.calAcc();
        double fuelLeft1=conbustion.getCurrentMassOfFuel();
        double time1 = conbustion.getFuelBurningTime();

        System.out.println("COMBUSTION FUEL TEST\n");
        System.out.println("Fuel consumption: "+fuelCost1+" kg");
        System.out.println("The acc after burning of fuel is :"+ acc1);
        System.out.println("Mass of fuel left: "+fuelLeft1+" kg");
        System.out.println("The time required: "+time1 +"s");
        System.out.println();

        //We only use fuel for the take Off, we don't have any in-flight corrections,
        //Cannot use iron fuel thruster

        /*Vector v0=new Vector(0,0,0);//Velocity before burning of fuel
        Vector vt=new Vector(0,60000,0);//Velocity after burning of fuel

        FuelCost comb=new FuelCost(v0,vt,2);
        comb.fuelCost();
        double fuelCost2=comb.getFuelCost();
        Vector acc2=comb.calAcc();
        double fuelLeft2=comb.getCurrentMassOfFuel();
        double time2 = comb.getFuelBurningTime();

        System.out.println("COMBUSTION FUEL TEST\n");
        System.out.println("Fuel Consumption: "+fuelCost2+" kg");
        System.out.println("The acc after burning of fuel is :"+ acc2);
        System.out.println("Mass of fuel left: "+fuelLeft2+" kg");
        System.out.println("The time required: "+time2 +"s");
        System.out.println();



        Vector v0=new Vector(50000,50000,50000);
        Vector vt=new Vector(40000,40000,40000);
        FuelCost magneto=new FuelCost(v0,vt,3);
        magneto.setCurrentMassOfFuel(1e7);
        magneto.fuelCost();
        double fuelCost3=magneto.getFuelCost();
        Vector acc3=magneto.calAcc();
        double fuelLeft3=magneto.getCurrentMassOfFuel();
        double time3 = magneto.getFuelBurningTime();

        System.out.println("Magnetoplasmadynamic TEST\n");
        System.out.println("Fuel Consumption: "+fuelCost3+" kg");
        System.out.println("The acc after is :"+ acc3);
        System.out.println("Mass of fuel left: "+fuelLeft3+" kg");
        System.out.println("The time required: "+time3 +"s");*/

    }

}
