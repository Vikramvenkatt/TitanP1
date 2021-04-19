package GenBody;

public class BasicVerlet {
    //base class for verlet solver

    public double time;
    public double vel;

    public BasicVerlet(double time, double vel) {
        this.time = time;
        this.vel = vel;
    }
}
