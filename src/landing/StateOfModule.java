package landing;

import GenBody.State;
import GenBody.Vector;
import interfaces.RateInterface;
import interfaces.StateInterface;
import interfaces.Vector3dInterface;

public class StateOfModule{
    private double dist;
    //set titan's position to all zero for now
    private Vector3dInterface titanPos = new Vector(0,0,0);
    private Vector3dInterface gravity;
    private WindModel module;

    public Vector getGravitationalVector()
    {
        return module.gravitationalForces((Vector) titanPos);
    }

    public void applyWind()
    {
        module.windFirstInitialization();
        module.windForceOnLander();
    }

    public void updateModule()
    {
        updatePosition();
    }

    //needs timestep as parameter?
    public void updatePosition()
    {
        //applies wind to module
        updateVelocity();
        System.out.println(module.getPosition().toString());
    }
    public void updateVelocity()
    {
        applyWind();
    }
}
