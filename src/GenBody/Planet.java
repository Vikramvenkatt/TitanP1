package GenBody;

import java.awt.*;

public class Planet extends Bdata {

    public static double zoomFactor = 0.95;
    public static double sizeFactor = 0.805;

    //TODO:CHANGE
    public double dryRocketMass;
    public double massOfLander;
    public double exhaustVelocity;
    //in newton?
    public double maximumThrust;

    //Thrust : thrust = m * exhaust velocity < maximumThrust

    public Planet(Bdata orbitsAround, String name, double mass, Vector initialPosition, Vector initialVelocity,
                  double radius, java.awt.Color Color) {
        super(orbitsAround, name, mass, initialPosition, initialVelocity, radius, Color);


    }

    public void initializeRocketVariables()
    {
        dryRocketMass = 7.8e4;
        massOfLander = 6e3;
        exhaustVelocity = 2e4;
        maximumThrust = 3e7;
    }

    public void setX(double x) {
        this.initialPosition.setX(x);
    }

    public void setY(double y) {
        this.initialPosition.setY(y);
    }

    public void draw(Graphics g) {

        double scale = 1e8;
        double radius = getRadius() * 2e2;
        int sunSize = 35;
        int venusEarthSize = 9;
        int mercuryMarsMoonTitanSize = 5;
        int jupiterSaturnSize = 24;
        int uranusNeptuneSize = 25;
        double spaceshipSize = 4;

        Font myFont = new Font("Aerial", 1, 8);
        g.setFont(myFont);


        if (this.getName().equals("Sun")) {
            radius /= 35;
            g.setColor(this.getColor());
            g.fillOval((int) (changePosX(initialPosition) - (int) (radius / scale / 2) + 10), (int) (changePosY(initialPosition) - (int) (radius / scale / 2) + 10), (int) (sunSize * sizeFactor), (int) (sunSize * sizeFactor));
            g.drawString(getName(), (int) (changePosX(initialPosition) - (int) (radius / scale / 2) + 10), (int) (changePosY(initialPosition) - (int) (radius / scale / 2) + 10));

            if (isInside(this.initialPosition.getX(), this.initialPosition.getY(), this.getRadius(), spaceshipX(), spaceshipY()))
                System.out.println("Inside Sun");
            else
                System.out.println("");

        } else if (this.getName().equals("Mercury") || this.getName().equals("Moon")) {
            radius /= 2;
            g.setColor(this.getColor());
            g.fillOval((int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) (changePosY(initialPosition) - (int) (radius / scale / 2)), (int) (venusEarthSize * sizeFactor), (int) (venusEarthSize * sizeFactor));
            g.drawString(getName(), (int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) changePosY(initialPosition) - (int) (radius / scale / 2));

            if (isInside(this.initialPosition.getX(), this.initialPosition.getY(), this.getRadius(),spaceshipX(), spaceshipY()))
                System.out.println("In Mercury/Moon");
            else
                System.out.println("");

        } else if (this.getName().equals("Venus") || this.getName().equals("Mars") || this.getName().equals("Earth") || this.getName().equals("Titan")) {
            radius /= 2;
            g.setColor(this.getColor());
            g.fillOval((int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) (changePosY(initialPosition) - (int) (radius / scale / 2)), (int) (mercuryMarsMoonTitanSize * sizeFactor), (int) (mercuryMarsMoonTitanSize * sizeFactor));
            g.drawString(getName(), (int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) changePosY(initialPosition) - (int) (radius / scale / 2));

            if (isInside(this.initialPosition.getX(), this.initialPosition.getY(), this.getRadius(), spaceshipX(), spaceshipY()))
                System.out.println("In Venus/Mars/Earth/Titan");
            else
                System.out.println("");

        } else if (this.getName().equals("Jupiter") || this.getName().equals("Saturn")) {
            radius /= 7;
            g.setColor(this.getColor());
            g.fillOval((int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) (changePosY(initialPosition) - (int) (radius / scale / 2)), (int) (jupiterSaturnSize * sizeFactor), (int) (jupiterSaturnSize * sizeFactor));
            g.drawString(getName(), (int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) changePosY(initialPosition) - (int) (radius / scale / 2));

            if (isInside(this.initialPosition.getX(), this.initialPosition.getY(), this.getRadius(), spaceshipX(), spaceshipY()))
                System.out.println("In Jupiter/Saturn");
            else
                System.out.println("");

        } else if (this.getName().equals("Uranus") || this.getName().equals("Neptune")) {
            radius /= 5;
            g.setColor(this.getColor());
            g.fillOval((int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) (changePosY(initialPosition) - (int) (radius / scale / 2)), (int) (uranusNeptuneSize * sizeFactor), (int) (uranusNeptuneSize * sizeFactor));
            g.drawString(getName(), (int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) changePosY(initialPosition) - (int) (radius / scale / 2));

            if (isInside(this.initialPosition.getX(), this.initialPosition.getY(), this.getRadius(), spaceshipX(), spaceshipY()))
                System.out.println("In Uranus/Neptune");
            else
                System.out.println("");

        } else if (this.getName().equals("spaceship")) {
            g.setColor(this.getColor());
            g.fillOval((int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) (changePosY(initialPosition) - (int) (radius / scale / 2)), (int) (spaceshipSize * sizeFactor), (int) (spaceshipSize * sizeFactor));
            g.drawString(getName(), (int) (changePosX(initialPosition) - (int) (radius / scale / 2)), (int) changePosY(initialPosition) - (int) (radius / scale / 2));
        }
    }


    public double changePosX(Vector x) {
        return 750 + this.initialPosition.getX() * 1500 / 4.382692942729203e+12 / zoomFactor;
    }

    public double changePosY(Vector y) {
        return 500 + this.initialPosition.getY() * 1000 / 1.744450959214586e+12 / zoomFactor;
    }


    public static boolean isInside(double circle_x, double circle_y,
                                   double rad, double x, double y) {

        return (x - circle_x) * (x - circle_x) +
                (y - circle_y) * (y - circle_y) <= rad * rad;
    }

    public  double spaceshipX() {
        double spaceshipXCoordinates = Planets.getSpaceshipX();
        System.out.println(spaceshipXCoordinates);
        return spaceshipXCoordinates;
    }

    public  double spaceshipY() {

        double spaceshipYCoordinates = Planets.getSpaceshipY();
        System.out.println(spaceshipYCoordinates);
        return spaceshipYCoordinates;
    }

}