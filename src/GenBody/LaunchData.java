package GenBody;

import java.util.Calendar;
import GenBody.Body;
public class LaunchData implements Comparable<LaunchData>{
    public Vector position;
    public String name;
    public Body target;
    public Calendar launchDate;
    public long launchTimeMillis;

    public LaunchData(Body target, String name, Vector position, int year, int month, int day) {
        this.name = name;
        this.target = target;
        this.position = position;
        launchDate = Calendar.getInstance();
        launchDate.set(Calendar.YEAR, year);
        launchDate.set(Calendar.MONTH, month);
        launchDate.set(Calendar.DAY_OF_MONTH, day);
        launchTimeMillis = launchDate.getTimeInMillis();
    }
    public LaunchData(Vector startPosition)
    {
        this.position = startPosition;
    }


    public void setPosition(Vector position) {
        this.position = position;
    }



    @Override
    public int compareTo(LaunchData o) {
        return launchDate.compareTo(o.launchDate);
    }
    public Vector getPosition(){
        return position;
    }
    public String getName(){
        return name;

    }
    public Body getTarget(){
        return target;
    }
    public Calendar getLaunchDate(){
        return launchDate;
    }
}