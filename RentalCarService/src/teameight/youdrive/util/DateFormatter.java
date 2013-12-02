package teameight.youdrive.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter {

    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(dateString);
        return new Date(date.getTime());
    }
    
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }
}
