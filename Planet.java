package GenBody;

import java.awt.Graphics;

public class Planet extends Bdata {


    public Planet(Bdata orbitsAround, String name, double mass, Vector initialPosition, Vector initialVelocity,
                  double radius, java.awt.Color Color) {
        super(orbitsAround, name, mass, initialPosition, initialVelocity, radius, Color);


    }

    public void draw(Graphics g) {

        double scale = 1e8;
        double radius = getRadius()*2e2;

        if(this.getName().equals("Sun")){
            radius/=2e1;
        }else{

        }

        g.setColor(this.getColor());
        g.fillOval((int) (changePosX(initialPosition)-radius/scale) ,(int) (changePosY(initialPosition)-radius/scale) , (int) (radius/scale), (int) (radius/scale));
        //(int) (changePosX(initialPosition)+getRadius())
        //(int) (changePosY(initialPosition)-getRadius())
    }

    public double changePosX(Vector x) {

        double newPosX = 0;

        double pointOnScreenX = this.initialPosition.getX() * 1500 / 4.382692942729203e+12;

        if (pointOnScreenX > 0) {

            newPosX = 750 + pointOnScreenX;

        } else {

            newPosX = 750 - pointOnScreenX;

        }
        return newPosX;
    }

    public double changePosY(Vector y) {

        double newPosY = 0;

        double pointOnScreenY = this.initialPosition.getY() * 1000 / 1.744450959214586e+12;

        if (pointOnScreenY > 0) {
            newPosY = 500-pointOnScreenY ;
        } else {
            newPosY = 500 + pointOnScreenY  ;
        }
        return newPosY;
    }

}
 
