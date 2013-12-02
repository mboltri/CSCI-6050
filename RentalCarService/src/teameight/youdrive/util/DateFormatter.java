package teameight.youdrive.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter {

    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(dateString);
        return new Date(date.getTime());
    }
    
    public static Timestamp stringToDatetime(String datetimeString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        java.util.Date date = sdf.parse(datetimeString);
        return new Timestamp(date.getTime());
    }
    
    public static String dateToDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }
    
    public static String dateToTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
        String dateString = sdf.format(date);
        return dateString;
    }
}
