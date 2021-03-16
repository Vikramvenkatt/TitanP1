package GenBody;
import java.util.Calendar;

public class Launch implements Comparable<Launch>{
    //creating the local variables
    public Body target;
    public String name;
    public double toSunVelocity;
    public double gradeVelocity;
    public double perpenVelocity;
    public Calendar Date;
    public long launchMillis;
    public Launch(
          Body target,
          String name,
          double gradeVelocity,
          double perpenVelocity,
          double toSunVelocity,
          int year, int month, int day,
          int hour,int minute,int second
    ){
        this.target=target;
        this.name=name;
        this.gradeVelocity=gradeVelocity;
        this.perpenVelocity=perpenVelocity;
        this.toSunVelocity=toSunVelocity;
        Date=Calendar.getInstance();
        Date.set(Calendar.YEAR,year);
        Date.set(Calendar.MONTH,month);
        Date.set(Calendar.DAY_OF_MONTH,day);
        Date.set(Calendar.HOUR_OF_DAY,hour);
        Date.set(Calendar.MINUTE,minute);
        Date.set(Calendar.SECOND,second);
        Date.set(Calendar.MILLISECOND,0);

        launchMillis=Date.getTimeInMillis();
    }
    @Override
    public int compareTo(Launch o) {
        return Date.compareTo(o.Date);
    }
}
