package GenBody;

public class Planet extends Bdata {

	public Planet(Bdata orbitsAround, String name, double mass, Vector initialPosition, Vector initialVelocity,
			double radius, java.awt.Color Color) {
		super(orbitsAround, name, mass, initialPosition, initialVelocity, radius, Color);
		
			
	}
	    public Color getColor(){
        return Color;
    }
	    public void draw(Graphics g){
        g.setColor(this.getColor());
        g.fillOval((int)initialPosition.getX(),(int)initialPosition.getY(),(int)getRadius()*2,(int)getRadius()*2);
    }




}
 
