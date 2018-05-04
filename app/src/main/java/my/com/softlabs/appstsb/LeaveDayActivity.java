package my.com.softlabs.appstsb;

/**
 * Created by Muhammad Akmal on 3/3/2017.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class LeaveDayActivity{
    public static void main(String[] args) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = df.parse("2017-03-03");
        Date date2 = df.parse("2017-03-13");
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        int numberOfDays = 0;
        while (cal1.before(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))&&(Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
                cal1.add(Calendar.DATE,1);
            }else {
                cal1.add(Calendar.DATE,1);
            }
        }
        numberOfDays=numberOfDays+1;
        System.out.println(numberOfDays);
    }
}