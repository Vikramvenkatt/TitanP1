package GenBody;

import java.awt.Color;

public abstract class Bdata{
    /*
    defining parameters of this thingy, which will be used in the Body class duh
    IMP:can and should be used for both body and spaceship!
    Vikram
     */
    protected final String Name;//name of object
    protected final Vector initialVelocity;//final velocity??
    protected final Vector initialPosition;//initial for stationary as well as for spaceship in that vector position
    protected final double mass;//mass of object
    protected final double radius;
    protected final Color Color;//les go with neon gray lol
	protected final Bdata orbitsAround;
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

    //Body Data Constructor assignments-Jan
    public Bdata(Bdata orbitsAround,String name, double mass,Vector initialPosition, Vector initialVelocity, double radius, Color Color){
		this.orbitsAround = orbitsAround;
		this.Name = name;
		this.initialVelocity = initialVelocity;
		this.initialPosition = initialPosition;
		this.mass = mass;
		this.radius= radius;
		this.Color = Color;
        
    }
	

}
