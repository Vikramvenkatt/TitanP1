package landing;

import GenBody.Vector;

public class Main {

    public static void main(String[] args) {
        SimulationLanding k = new SimulationLanding();
        //Vector[] n = k.trajectory(new Vector(-100000+500,30000,1.57079633), new Vector(1000,0,0),199,1); for x controll
        Vector[] n = k.trajectory(new Vector(-100000+500,30000,1.57079633), new Vector(1000,0,0),500,1);
        // Vector[] n = k.trajectory(new Vector(3095.200318907835,3095.200318907835,0), new Vector(1000,-269.04799681092186,0),23.00853643659009,1);
        for (int i = 0; i < n.length; i++) {
            System.out.println(n[i].toString());
        }
    }
}
