package GenBody;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;


public class Planets  {
	private Planet sun;
	private Planet venus;
	private Planet mercury;
	private Planet jupiter;
	private Planet mars;
	private Planet earth;
	private Planet uranus;
	private Planet saturn;
	private Planet titan;
	private Planet moon;
	private Planet neptune;
	private  Planet spaceship;

	private ArrayList<Planet> planets = new ArrayList<Planet>();

	public Planets(){
		addPlanets();
		/*for (int i = 0; i < planets.size() ; i++) {
			planets.get(i).setRadius(20);
		}*/
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

		sun = new Planet(null, "Sun", 1.9891e30, new Vector(-6.806783239281648e+08,1.080005533878725e+09,6.564012751690170e+06), new Vector(-1.420511669610689e+01,-4.954714716629277e+00,3.994237625449041e-01), 6.957e8, Color.yellow);
		venus = new Planet(null, "Venus", 4.8685e24, new Vector(-9.435345478592035e+10,5.350359551033670e+10,6.131453014410347e+09), new Vector(-1.726404287724406e+04,-3.073432518238123e+04,5.741783385280979e-04), 6.0518e6, Color.white);
		mercury = new Planet(null, "Mercury", 3.302e23, new Vector(6.047855986424127e+06,-6.801800047868888e+10,-5.702742359714534e+09), new Vector(3.892585189044652e+04,2.978342247012996e+03,-3.327964151414740e+03), 2.44e6, Color.GRAY);
		jupiter = new Planet(null, "Jupiter", 1.89813e27, new Vector(1.781303138592153e+11,-7.551118436250277e+11,-8.532838524802327e+08), new Vector(1.255852555185220e+04,3.622680192790968e+03,-2.958620380112444e+02), 6.99e7, Color.orange);
		mars = new Planet(null, "Mars", 6.4171e23, new Vector(-3.615638921529161e+10,-2.167633037046744e+11,-3.687670305939779e+09), new Vector(2.481551975121696e+04,-1.816368005464070e+03,-6.467321619018108e+02), 3.39e6,Color.red);
		earth = new Planet(null, "Earth", 5.97219e24, new Vector(-1.471922101663588e+11,-2.860995816266412e+10,8.278183193596080e+06), new Vector(5.427193405797901e+03,-2.931056622265021e+04,6.575428158157592e-01), 6371e3, Color.BLUE);
		uranus = new Planet(null, "Uranus", 8.6813e25, new Vector(2.395195786685187e+12,1.744450959214586e+12,-2.455116324031639e+10), new Vector(-4.059468635313243e+03,5.187467354884825e+03,7.182516236837899e+01), 2.54e7, Color.green);
		saturn = new Planet(null, "Saturn", 5.6834e26, new Vector(6.328646641500651e+11,-1.358172804527507e+12,-1.578520137930810e+09), new Vector(8.220842186554890e+03,4.052137378979608e+03,-3.976224719266916e+02), 5.82e7, Color.yellow);
		titan = new Planet(null, "Titan", 1.34553e23, new Vector(6.332873118527889e+11,-1.357175556995868e+12,-2.134637041453660e+09), new Vector(3.056877965721629e+03,6.125612956428791e+03,-9.523587380845593e+02), 2.575e6, Color.orange);
		moon = new  Planet(null, "Moon", 7.349e22, new Vector(-1.472343904597218e+11,-2.822578361503422e+10,1.052790970065631e+07), new Vector(4.433121605215677e+03,-2.948453614110320e+04,8.896598225322805e+01 ), 1.737e6, Color.GRAY);
		neptune = new Planet(null, "Neptune", 1.02413e26, new Vector(4.382692942729203e+12,-9.093501655486243e+11,-8.227728929479486e+10), new Vector(1.068410720964204e+03,5.354959501569486e+03,-1.343918199987533e+02), 2.46e7, Color.blue);
		spaceship = new Planet(null, "spaceship", 1500, new Vector(-1.471922101663588e+11,-2.860995816266412e+10,8.278183193596080e+06),new Vector(5.427193405797901e+03,-2.931056622265021e+04,6.575428158157592e-01), 50, Color.pink);


		planets.add(sun);
		planets.add(venus);
		planets.add(mercury);
		planets.add(jupiter);
		planets.add(mars);
		planets.add(earth);
		planets.add(uranus);
		planets.add(saturn);
		planets.add(titan);
		planets.add(moon);
		planets.add(neptune);
		planets.add(spaceship);

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
