package landing;

import GenBody.Vector;

/**
 * class calculate the fuel cost
 *
 * NOT USED FOR NOW!
 *
 * The formulae we used:
 * First calculate the acceleration
 * a=Ve*mass flow rate/M
 *
 * Second calculate fuel usage time
 * t = (v1 - v0)/a
 *
 * Third calculate the fuel cost
 *
 */
public class FuelCost {

    //mass
    private static final double massOfCraft = 7.8e4;//Mass of craft in kg
    private static final double massOfFuel = 100e4;//Assume we bring 7.8e4 kg fuel
    private static double massTotal =massOfCraft+massOfFuel;//Mass of craft + fuel
    private static double costOfFuel;// mass that lost this time
    private static double currentMassOfFuel;

    //velocity
    private Vector currentVelocity;//vel before use of fuel
    private Vector finalVelocity;//vel after use of fuel
    private Vector changeOfVelocity;//delta vel

    //engine
    private static final double exhaustVelocity = 2e3;//(m/s)
    private static final double massFlowRate = 500;//(kg/s)
    private static double maxThrust = 3e7;//(N)

    private static double fuelUsageTime;

    //acceleration
    private Vector acceleration;
    private double accValue;

    //constructor
    public FuelCost(Vector currentVelocity, Vector finalVelocity) {
        //initial velocity before combustion
        this.currentVelocity=currentVelocity;

        //velocity in the end
        this.finalVelocity=finalVelocity;

        if(currentMassOfFuel<0) {
            throw new RuntimeException("There is no fuel left! ");
        }
    }

    public Vector calDeltaOfVelocity(){

        //delta of velocity
        changeOfVelocity=(Vector)finalVelocity.sub(currentVelocity);
        //System.out.println(finalVelocity);
        //System.out.println(currentVelocity);
        return changeOfVelocity;
    }

    public double calAccValue() {

        accValue=exhaustVelocity*massFlowRate/massOfCraft;

        return accValue;

    }

    public double calcTime(){


        fuelUsageTime=(changeOfVelocity.norm())/accValue;

        return fuelUsageTime;
    }


    //calculate the acceleration
    public Vector calAcc() {
        acceleration=(Vector)changeOfVelocity.mul(1/fuelUsageTime);
        return acceleration;
    }



    //calculate the fuel cost
    //fuel cost=usage time * mass flow rate
    public void fuelCost() {

        calAccValue();
        calDeltaOfVelocity();
        calcTime();
        calAcc();

        //prints
        System.out.println("exhaust velocity: "+exhaustVelocity);
        System.out.println("max thrust: "+maxThrust);
        System.out.println("mass flow rate: "+massFlowRate);

        costOfFuel=fuelUsageTime*massFlowRate;//the mass cost

        currentMassOfFuel=(currentMassOfFuel)-(costOfFuel);
        if(currentMassOfFuel<0) {
            System.out.println("Not enough fuel! ");
        }


    }

    public double getFuelCost(){
        return costOfFuel;
    }

    public double getCurrentMassOfFuel(){
        return currentMassOfFuel;
    }

    public void setMassOfFuel(double newMass) {
        currentMassOfFuel=newMass;
        massTotal=massOfCraft+currentMassOfFuel; //current mass of fuel + craft

        System.out.println("The mass of fuel is set to: "+newMass+" kg");
    }

    public static void main(String[] args){
        Vector v0=new Vector(0,0,0);//Velocity before combustion of fuel
        Vector v1=new Vector(0,50000,0);//Velocity after combustion of fuel

        FuelCost c=new FuelCost(v0,v1);
        c.setMassOfFuel(2500000);
        c.fuelCost();
        double fuelCost=c.getFuelCost();
        Vector acc=c.calAcc();
        double fuelLeft=c.getCurrentMassOfFuel();

        System.out.println("TEST FUEL USE\n");
        System.out.println("FuelCost: "+fuelCost+" kg");
        System.out.println("The acceleration after thrust is :"+ acc);
        System.out.println("Mass of fuel left: "+fuelLeft+" kg");
        System.out.println("The time required to speed up: "+fuelUsageTime +"s");
    }

}
