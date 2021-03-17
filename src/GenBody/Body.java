package GenBody;

import java.awt.Color;


public class Body {
    /*so we are gonna need some global variables that define the parameters of the body of the spaceship
    such as name, mass,color etc
    idk how many we need tho and whether stuff like colour is important but i think it prob is
    Vikram
     */
    //so this is just copied from what I wrote from the Data body class
    public String bodyName;//name of the body, like in general, we have spaceship defined in another class
    public double radius;
    public double mass;
    public Vector position;//is of type GenBody.Vector class, which was created drawing from the interface provided in API
    public Vector velocity;//same thing I guess
    public Color color;

    /*now we have to create methods that simply return all of the above
    Vikram
     */

    public double getRadius(){
        return radius;
    }
    public double getMass(){
        return mass;
    }
    public Vector getVelocity(){
        return velocity;
    }
    public Vector getPosition(){
        return position;
    }

    //simple constructor which requires less parameters for testing phase 1
    public Body(double mass, Vector position, Vector velocity)
    {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
    }

    public Body(Vector position, Vector velocity)
    {
        this.position = position;
        this.velocity = velocity;
    }

    //complete constructor
    public Body(String bodyName, double radius, double mass, Vector position, Vector velocity, Color color)
    {
        this.bodyName = bodyName;
        this.radius = radius;
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.color = color;
    }
    /*now we need a few more methods but idk what they are
    Both Vikram and Sahil will work on this method
     */
    public Body(Bdata x){

        this.bodyName = x.getName();
        this.radius = x.getRadius();
        this.mass = x.getMass();
        this.position = x.getInitialPosition();
        this.velocity = x.getInitialVelocity();
        this.color = x.getColor();
    }



}
