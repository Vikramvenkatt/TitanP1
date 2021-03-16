
import java.util.Calendar;
import java.util.Vector;
import GenBody.Body;
public class LaunchData implements Comparable<LaunchData>{
    public Vector position;
    public String name;
    public Body target;
    public final Calendar launchDate;
    public final long launchTimeMillis;

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