package landing;

import GenBody.Vector;

public class Main {

    public static void main(String[] args) {
        SimulationLanding k = new SimulationLanding();
        Vector[] n = k.trajectory(new Vector(8000,30000,0), new Vector(-500,0,0),10000,5);
        for (int i = 0; i < n.length; i++) {
            System.out.println(n[i].toString());
        }
    }
}
