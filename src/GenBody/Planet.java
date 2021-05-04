package GenBody;
import java.awt.Graphics;
public class Planet extends Bdata {

	public Planet(Bdata orbitsAround, String name, double mass, Vector initialPosition, Vector initialVelocity,
			double radius, java.awt.Color Color) {
		super(orbitsAround, name, mass, initialPosition, initialVelocity, radius, Color);
		
			
	}


	public void setX(double x)
	{
		this.initialPosition.setX(x);
	}

	public void setY(double y)
	{
		this.initialPosition.setY(y);
	}

	public void draw(Graphics g) {

		double scale = 1e8;
		double radius = getRadius()*2e2;

		if(this.getName().equals("Sun")){
			radius/=2e1;
			//g.fillOval((int) (changePosX(initialPosition)-radius/scale) ,(int) (changePosY(initialPosition)-radius/scale) , (int) (radius/scale), (int) (radius/scale));
		}else {

		}

			g.setColor(this.getColor());
			g.fillOval((int) (changePosX(initialPosition) - radius / scale), (int) (changePosY(initialPosition) - radius / scale), (int) (radius / scale), (int) (radius / scale));
			//(int) (changePosX(initialPosition)+getRadius())
			//(int) (changePosY(initialPosition)-getRadius())


	}

	public double changePosX(Vector x) {

		double newPosX = 0;

		double pointOnScreenX =750 + this.initialPosition.getX() * 1500 / 4.382692942729203e+12;

		/*if (pointOnScreenX > 0) {

			newPosX = 750 - pointOnScreenX;

		} else {

			newPosX = 750 + pointOnScreenX;

		}*/
		return pointOnScreenX;
	}

	public double changePosY(Vector y) {

		double newPosY = 0;

		double pointOnScreenY = 500 + this.initialPosition.getY() * 1000 / 1.744450959214586e+12;

		/*if (pointOnScreenY > 0) {
			newPosY = 500+pointOnScreenY ;
		} else {
			newPosY = 500 - pointOnScreenY  ;
		}*/
		return pointOnScreenY;
	}
	/*public boolean inRange(StateofSolarSystem spaceship){//make sure that spaceship is the elemnt being called

		MAKE AN IF STATEMENT THAT CHECKS THAT SPACESHIP IS THE ELEMENT BEING CALLED(IT IS THE LAST ONE)
		get position of current planets
		add 100 and 300 km, 2 variables, outside and inside range of 300 and 100 km
		use circle formula whatever to calculate cos its circular
		 */


	}




}
 
