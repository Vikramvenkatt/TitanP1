import java.awt.*;
import java.util.ArrayList;

public class Planets {

	private Planet sun;
	private Planet venus;
	private Planet mercury;
	private Planet jupiter;
	private Planet mars;
	private Planet earth;
	private Planet uranus;
	private Planet saturn;
	private Planet titan;

	private ArrayList<Planet> planets;

	public void updateVelocity()
	{
		for(Planet p : planets)
		{
			//TODO: write the formula
		}
	}
	

	public ArrayList<Planet> getPlanets() {
		return planets;
	}

	public void addPlanets() {
		// Create a new "planet" (Sun is a star but who cares) with the given parameters
		// of the text file given to us
		// the vectors need to be added later so set them all to null for now and set
		// appropriate colors for the planets
		// for the GUI team, so Color.blue for earth
		sun = new Planet(null, "Sun", 1.9891e30, null, null, 6.957e8, Color.yellow);
		venus = new Planet(null, "Venus", 4.8685e24, null, null, 6.0518e6, Color.white);
		mercury = new Planet(null, "Mercury", 3.302e23, null, null, 2.44e6, Color.GRAY);
		jupiter = new Planet(null, "Jupiter", 1.89813e27, null, null, 6.99e7, Color.orange);
		mars = new Planet(null, "Mars", 6.4171e23, null, null, 3.39e6, Color.red);
		earth = new Planet(null, "Earth", 5.97219e24, null, null, 6371e3, Color.BLUE);
		uranus = new Planet(null, "Uranus", 8.6813e25, null, null, 2.54e7, Color.green);
		saturn = new Planet(null, "Saturn", 5.6834e26, null, null, 5.82e7, Color.yellow);
		titan = new Planet(null, "Titan", 1.34553e23, null, null, 2.575e6, Color.orange);

		planets.add(sun);
		planets.add(venus);
		planets.add(mercury);
		planets.add(jupiter);
		planets.add(mars);
		planets.add(earth);
		planets.add(uranus);
		planets.add(saturn);
		planets.add(titan);

	}

	public Planet getSun() {
		return sun;
	}

	public Planet getVenus() {
		return venus;
	}

	public Planet getMercury() {
		return mercury;
	}

	public Planet getJupiter() {
		return jupiter;
	}

	public Planet getMars() {
		return mars;
	}

	public Planet getEarth() {
		return earth;
	}

	public Planet getUranus() {
		return uranus;
	}

	public Planet getSaturn() {
		return saturn;
	}

	public Planet getTitan() {
		return titan;
	}

}
