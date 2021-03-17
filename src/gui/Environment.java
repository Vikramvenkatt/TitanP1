package gui;
import GenBody.Planet;
import GenBody.Planets;
import GenBody.Spaceship;
import GenBody.Vector;
import GenBody.LaunchData;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Environment {
    private Spaceship ship = new Spaceship(new Vector(,2,3),new Vector(1,2,3));
    private Planets planetsList;
    boolean running=false;
    Thread runner=null;
    Calendar startDate;
    long start;
    Calendar current;

    public void paintComponent(Graphics g){
        planetsList.addPlanets();
        planetsList.draw(g);
        ship.draw(g,(int) ship.getPosition().getX(), (int) ship.getPosition().getY());
    }


    public void setLaunchInfo(Vector startPosition)
    {
        ship.setLaunchData((int) startPosition.getX(),(int) startPosition.getY());
    }


}
