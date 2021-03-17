package GenBody;

import java.awt.Graphics;
import java.awt.Color;

public class Spaceship extends Body{
    //create simple spaceship with no mass, only vectors
    public Spaceship(Vector position, Vector velocity)
    {
        super(position, velocity);
    }

    //draw a rocket at position x,y
    public void draw(Graphics g, int x, int y)
    {
        g.setColor(Color.red);
        g.fillRect(x,y, 20,40);
    }
}
