import java.util.Date;

/**
 * Created by ferney on 12.11.17.
 */
public class LogWrapper {
    public static void info(String message) {
        Date date = new Date();
        System.out.println((date.toString()) + " " + message);
    }
}
