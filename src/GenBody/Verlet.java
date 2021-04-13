package GenBody;

import interfaces.Vector3dInterface;

public class Verlet {
    public static double verlet(double currentPos, double acceleration, double changeT) {

        // Note that we are using a temp variable for the previous position
        double prevPos, temp_pos, time;
        prevPos =currentPos;
        time = 0;

        while (currentPos > 0) {
            time += changeT;
            temp_pos = currentPos;
            currentPos = currentPos*2 - prevPos + acceleration * changeT * changeT;
            prevPos = temp_pos;
        }

        return time;
    }

   public static BasicVerlet calculations(double pos, double acc, double dt) {

        
        double prev_pos, temporaryPos, time, vel;
        prev_pos = pos;
        vel = 0;
        time = 0;
        while (pos > 0) {
            time += dt;
            temporaryPos = pos;
            pos = pos*2 - prev_pos + acc * dt * dt;
            prev_pos = temporaryPos;

            // The acceleration is constant, so the velocity is straightforward
            vel += acc*dt;
        }

        return new BasicVerlet(time, vel);
    }

    public static BasicVerlet velocitycalc(double position, double acc, double dt) {

        //i used a strategy to assign temp switching!
        double time, vel;
        vel = 0;
        time = 0;
        while (position > 0) {
            time += dt;
            position += vel*dt + 0.5*acc * dt * dt;
            vel += acc*dt;
        }
        return new BasicVerlet(time, vel);
    }

    public static void main(String[] args) {

        double verletT = verlet(5.0, -10, 0.01);
        System.out.println("Time for integration in statesolar class: " + verletT);

        BasicVerlet calculateVerlet = calculations(5.0, -10, 0.01);
        System.out.println("Time for Stormer Verlet integration is: " + calculateVerlet.time);
        System.out.println("Velocity for Stormer Verlet integration is: " + calculateVerlet.vel);

        BasicVerlet vVerlet = velocitycalc(5.0, -10, 0.01);
        System.out.println("Time for velocity Verlet integration is: " + vVerlet.time);
        System.out.println("Velocity for velocity Verlet integration is: " + vVerlet.vel);
    }
}

