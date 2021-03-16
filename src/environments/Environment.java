package environments;
import GenBody.Body;
import GenBody.Planet;
import GenBody.Planets;
import GenBody.Spaceship;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Environment {
    List<Spaceship> spaceShip=new ArrayList<>();//contains the list of spaceships
    List<Body> all=new ArrayList<>();

    boolean running=false;
    Thread runner=null;
    Calendar startDate;
    long start;
    Calendar current;
    private Planets planetsList;

    public void paintComponent(Graphics g){
        planetsList.addPlanets();
        planetsList.draw(g);
        //What is completed till now
    }
}
