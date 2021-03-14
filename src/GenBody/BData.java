package GenBody;
import java.awt.*;
import java.util.Vector;

public class BData {
    /*
    defining parameters of this thingy, which will be used in the Body class duh
    IMP:can and should be used for both body and spaceship!
    Vikram
     */
    private String Name;//name of object
    private Vector initialVelocity;//initial for both
    private Vector initialPosition;//initial for stationary as well as for spaceship in that vector position
    private double mass;//mass of object
    private double radius;
    private Color Color;//les go with neon gray lol
    /*
    Defining the methods that can be called when we make more complex methods
    Vikram
     */
    public String getName(){
        return Name;
    }
    public Vector getInitialVelocity(){
        return initialVelocity;
    }
    public Vector getInitialPosition(){
        return initialPosition;
    }
    public double getMass(){
        return mass;
    }
    public double getRadius(){
        return radius;
    }
    public Color getColor(){
        return Color;
    }

    //Body Data Constructor assignments-Sahil
    public BData(){
        BData parent;
    }
}
