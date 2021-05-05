package GenBody;

import interfaces.Vector3dInterface;

import java.awt.*;

public class Spaceship extends Body{
    //create simple spaceship with no mass, only vectors
    public Spaceship(Vector position, Vector3dInterface velocity) {
        super(position, (Vector) velocity);
    }
    //variable for fuel
    //StateOfSolarSystem fuell; should it be of type spaceship?

    /*
    HOW TO CALCULATE POSITION AND TRAJECTORY OF SPACESHIP:
        CALL ON CHANGE CLASS, WHICH CALCULATES ACCELERATION USING GRAVITATIONAL FORCES
     */

    private LaunchData launchData;

    public void draw(Graphics g, int x, int y)
    {
        g.setColor(Color.red);
        g.fillRect(x,y,20,40);
    }

    public void setLaunchData(Vector startPosition)
    {
        this.launchData = new LaunchData(startPosition);
    }
}
