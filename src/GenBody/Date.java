package GenBody;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Date extends GregorianCalendar{
    /**
     * setting the first time when the space ship departs, apparently in some julian format
     * "For example, the Julian day number for the day starting at 12:00 UT (noon) on January 1, 2000, was 2 451 545.
     * The Julian period is a chronological interval of 7980 years; year 1 of the Julian Period was 4713 BC (−4712)."
     * https://en.wikipedia.org/wiki/Julian_day
     */
    public static final double Julian = 2451545.0;

    public Date() {
        /**
        *empty constructor
         */
        super();
    }
    public Date(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public Date(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        super(year, month, dayOfMonth, hourOfDay, minute);
    }

    public Date(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
        super(year, month, dayOfMonth, hourOfDay, minute, second);
    }

    /**
     * use defensive copying,from SE
     * @param date that is copied
     */
    public Date(Date date){
        //TAKEN FROM SE SLIDES, GO THERE FOR MORE REFERENCE
        this.setTimeInMillis(date.getTimeInMillis());
    }


    public Date(java.util.Date date){
        this.setTimeInMillis(date.toInstant().toEpochMilli());
    }
    /**
     * https://landweb.modaps.eosdis.nasa.gov/browse/calendar.html
     * @return time in julian date
     * http://scienceworld.wolfram.com/astronomy/JulianDate.html
     * "The java. lang. Math. floor() is used to find the largest integer value which is less than or equal to the
     * argument and is equal to the mathematical integer of a double value."
     */
    /*
    public double julianConversion() {
        /**
         * create variables for hour,minute, month, day and time
         * why 60D? i think to convert into julian format

        double H = this.get(Calendar.HOUR_OF_DAY);
        H = H + (this.get(Calendar.MINUTE)/60D);
        int D = this.get(Calendar.DAY_OF_MONTH);
        int M = this.get(Calendar.MONTH) +1;
        int Y = this.get(Calendar.YEAR);


        return 365 * Y - Math.floor(7 * (Y + Math.floor((M + 9) / 12D)) / 4D)
                - Math.floor(3 * (Math.floor((Y + (M - 9) / 7D) / 100D) + 1) / 4D)
                + Math.floor(275 * M / 9D) + D + 1721028.5 + (H / 24D);
    }
    */

    public String getDateString() {
        return get(Calendar.YEAR) + "/" + calcGeneral(get(Calendar.MONTH)) + "/" + calcGeneral(get(Calendar.DATE));
    }

    public String calcGeneral(int n) {
        String str = "" + n;
        if(n < 10) {
            str = "0" + str;
        }
        return str;
    }
    public String toDateString() {
        return  this.get(Calendar.DAY_OF_MONTH) + "/" +
                (this.get(Calendar.MONTH) + 1) + "/" +
                this.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return "Date{" +
                this.get(Calendar.YEAR) + "-" +
                (this.get(Calendar.MONTH) + 1) + "-" +
                this.get(Calendar.DAY_OF_MONTH) + " " +
                this.get(Calendar.HOUR_OF_DAY) + ":" +
                this.get(Calendar.MINUTE) + ":" +
                this.get(Calendar.SECOND) +
                '}';
    }
}
