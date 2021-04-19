package GenBody;

public class Verlet {
    private double stepSize = 100; //TODO: implement good initial step size

    public void setStepSize(double stepSize)
    {
        this.stepSize = stepSize;
    }
    //TODO: - add comments
    //      - Verlet requires the information from spaceship's and planet's Body variable, ideally we should create a class
    //      celestialBody so we can use that as parameter instead of calling the getBody function of every different class
    //      -Kai
    // Here's how the verlet solver is supposed to work copy pasted
    //      The method initially calculates the previous position as follows:
    //     *      previousPosition = currentPosition - currentVelocity * step + 1/2 * currentAcceleration * step^2
    //     * Then, in a loop, the next position and the currentVelocity are calculated as follows:
    //     *      nextPosition = 2 * currentPosition - previousPosition + currentAcceleration * step^2
    //     *      currentVelocity = (nextPosition + previousPosition) / (2 * step)
    //     * After which, in the same loop, the previousPosition and currentPosition are updated:
    //     *      previousPosition = currentPosition
    //     *      currentPosition = nextPosition
    //     *
    //     * The velocity is always one step behind, because it is calculated using the Centered Difference formula.
    //     * To get the current velocity in the end, one extra step is performed.
    //     * In this step only the velocity is updated and not the position.
    public void verletSolve(Body body)
    {
        Vector currentPos = body.getPosition();
        Vector currentVel = body.getVelocity();

        //TODO: is it okay for substract to be of type Vector and not Vector3d? If it is of type vector, may not be shown in simulations
        // I need to cast it here to Vector, this may cause problems somewhere but I'm not sure if it does or not
        Vector previousPos =  currentPos.substract((Vector)currentVel.mul(stepSize));
    }

    public void calculateAcceleration(Vector pos, Vector vel)
    {//formula for acceleration is dv/dt, velocity can be calculated/given
        //how do i find the time at that position? do we need a method
        

    }
}
