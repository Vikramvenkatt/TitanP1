package GenBody;

import interfaces.Vector3dInterface;

public class RungeKutta {

    private NewtonsLawofGravity calculateA = new NewtonsLawofGravity();

    /* Copy pasting an example of how RungeKutta works method wise:
    Processes the shot using the Classical 4th-order Runge-Kutta Method. This method takes four samples of the
     * velocity and acceleration at different time-points in a time-step. To get the next position and velocity, a
     * weighted average of these four samples is calculated and multiplied with a time-step, which is added to the
     * current position.
     *
     * The formulas to calculate these samples are:
     *      k1 = v(t)                               k3 = v(t) + l2 * 1/2 * Δt
     *      l1 = a(p(t), v(t))                      l3 = a(p(t) + k2 * 1/2 * Δt, k3)
     *      k2 = v(t) + l1 * 1/2 * Δt               k4 = v(t) + l3 * Δt
     *      l2 = a(p(t) + k1 * 1/2 * Δt, k2)        l4 = a(p(t) + k2 * Δt, k4)
     * (where p = position, v = velocity, a = acceleration, t = time,
     *      and Δt = step size = change in time from current position)
     *
     * And to calculate the next position and next velocity with these samples, the formulas are:
     *      p(t+Δt) = p(t) + 1/6 * Δt * (k1 + 2*k2 + 2*k3 + k4)
     *      v(t+Δt) = v(t) + 1/6 * Δt * (l1 + 2*l2 + 2*l3 + l4)
     */
    private double stepSize;
    //needs to solve from startX to finalX

    //needs initial condition

    //for loop iterating through the startX to the finalX by stepSize


   /* public ShotInfo process(double dtime, StateOfSolarSystem formerstate) {
        Vector3dInterface[] formerPosition = formerstate.getPositionOfPlanets(); //p1
        Vector3dInterface[] formerVelocity = formerstate.getVelocityOfPlanets(); //v1

        Change v2 = (Change) calculateA.call(dtime, formerstate);

        Vector3dInterface[] a = v2.getA();

        for (int i = 0; i < formerPosition.length; i++) {
            //STEP 1
            ; //a1

            //STEP 2
            Vector3dInterface intermediatePosition1 = formerPosition[i].add(formerVelocity[i].mul(step / 2.0)); //p2 = p1 + v1 * 1/2 * step
            Vector3dInterface intermediateVelocity1 = formerVelocity[i].add(a[i].mul(step / 2.0)); //v2 = v1 + a1 * 1/2 * step
            Vector3dInterface intermediateAcceleration1 = acceleration(intermediatePosition1, intermediateVelocity1); //a2 = acceleration(p2,v2)

            //STEP 3
            Vector3dInterface intermediatePosition2 = formerPosition[i].add(intermediateVelocity1.mul(step / 2.0)); //p3 = p1 + v2 * 1/2 * step
            Vector3dInterface intermediateVelocity2 = formerVelocity[i].add(intermediateAcceleration1.mul(step / 2.0)); //v3 = v1 + a2 * 1/2 * step
            Vector3dInterface intermediateAcceleration2 = acceleration(intermediatePosition2, intermediateVelocity2); //a3 = acceleration(p3,v3)

            //STEP 4
            Vector3dInterface endPosition = formerPosition[i].add(intermediateVelocity2.mul(step)); //p4 = p1 + v3 * step
            Vector3dInterface endVelocity = formerVelocity[i].add(intermediateAcceleration2.mul(step)); //v4 = v1 + a3 * step
            Vector3dInterface endAcceleration = acceleration(endPosition, endVelocity); //a4 = acceleration(p4,v4)

            //positionStep = 1/6 * step * (v1 + 2*v2 + 2*v3 + v4);
            Vector3dInterface positionStep = formerVelocity[i].add(intermediateVelocity1.mul(2.0)).add(intermediateVelocity2.mul(2.0)).add(endVelocity).mul(step / 6.0);
            //velocityStep = 1/6 * step (a1 + 2*a2 + 2*a3 + a4)
            Vector3dInterface velocityStep = a[i].add(intermediateAcceleration1.mul(2.0)).add(intermediateAcceleration2.mul(2.0)).add(endAcceleration).mul(step / 6.0);

            //Calculate next position and velocity and update current position and velocity
            formerPosition[i] = formerPosition[i].add(positionStep);
            formerVelocity[i] = formerVelocity[i].add(velocityStep);

        }

        return new ShotInfo(shotInfo);
    }*/

}