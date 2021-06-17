package landing;

import GenBody.Vector;

public class Main {

    public static void main(String[] args) {
        SimulationLanding k = new SimulationLanding();
        Vector[] n = k.trajectory(new Vector(8000,8000,0), new Vector(50000,0,0),1000,1);
        for (int i = 0; i < n.length; i++) {
            System.out.println(n[i].toString());
        }
    }
}
