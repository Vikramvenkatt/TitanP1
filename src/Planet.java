import java.awt.*;
import java.util.Vector;
import GenBody.Bdata;
public class Planet extends Bdata {

	public Planet(Bdata orbitsAround, String name, double mass, Vector initialPosition, Vector initialVelocity,
			double radius, Color color) {
		super(orbitsAround, name, mass, initialPosition, initialVelocity, radius, color);
	}

}
 
