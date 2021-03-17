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
    private Spaceship ship;
    private Planets planetsList;
    boolean running=false;
    Thread runner=null;
    Calendar startDate;
    long start;
    Calendar current;

    public void paintComponent(Graphics g){
        //Adds all our planets and calls every individual draw from each planet
        planetsList.addPlanets();
        planetsList.draw(g);
        //draw the ship at the initial position
        ship.draw(g,(int) ship.getPosition().getX(), (int) ship.getPosition().getY());
    }

    //this method should be called before paintComponent as it sets the position of our ship on earth
    public void setLaunchInfo(Vector startPosition)
    {
        ship.setLaunchData((int) startPosition.getX(),(int) startPosition.getY());
    }


}
