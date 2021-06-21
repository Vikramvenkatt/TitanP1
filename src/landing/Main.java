package landing;

import GenBody.Vector;

public class Main {

    public static void main(String[] args) {
        SimulationLanding k = new SimulationLanding();
        Vector[] n = k.trajectoryOpenloop(new Vector(-100000+500,30000,1.57079633), new Vector(1000,0,0),230,1);
        Vector[] j = k.trajectoryCLosedloop(new Vector(-100000+500,30000,1.57079633), new Vector(1000,0,0),230,1);
        for (int i = 0; i < n.length; i++) {
            System.out.println(n[i].toString());
            //System.out.println(j[i].toString());
        }
    }
}
