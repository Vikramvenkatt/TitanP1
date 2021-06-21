package LinearAlgebra;

public class findstartingVector {

    public static void main(String[] args) {
        long start = System.nanoTime();
        NewtonRaphson Newton = new NewtonRaphson();
        System.out.println("Starting Velocity: "+Newton.findstartingVelocity().toString());
        long end = System.nanoTime();
        long exec = end - start;
        double inSeconds = (double)exec / 1_000_000_000.0;
        System.out.println("The program takes "+exec+" nanoseconds that is "+inSeconds+" seconds to execute.");

    }
}
