package windowGUI.options.workSQL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PagesTable {
    private static final String PAGES_NAME = "pages";

    private static final String ID_COLUMN = "ID";
    private static final String URL_COLUMN = "URL";
    private static final String SITE_ID_COLUMN = "SiteID";
    private static final String FOUND_DATE_TIME_COLUMN = "FoundDateTime";
    private static final String LAST_SCAN_DATE_COLUMN = "LastScanDate";

    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listURL = new ArrayList<>();
    private static final ArrayList<Integer> listSiteID = new ArrayList<>();
    private static final ArrayList<Date> listFoundDateTime = new ArrayList<>();
    private static final ArrayList<Date> listLastScanDate = new ArrayList<>();

    public ArrayList<Integer> getListID(){
        listID.add(1);
        listID.add(2);
        listID.add(3);
        listID.add(4);
        listID.add(5);
        listID.add(6);
        return listID;
    }

    public ArrayList<String> getListURL(){
        listURL.add("https://lenta.ru/news/2017/10/31/okueva/");
        listURL.add("https://lenta.ru/news/2017/10/25/malakhov_navalny/");
        listURL.add("https://lenta.ru/news/2017/10/26/navalny/");
        listURL.add("http://www.rbc.ru/politics/30/10/2017/59f726639a794714c553b09e");
        listURL.add("http://www.rbc.ru/politics/27/10/2017/59f345049a79475ffd648fec");
        listURL.add("http://www.rbc.ru/politics/27/10/2017/59f345049a79475ffd648fec");
        return listURL;
    }

    public ArrayList<Integer> getListSiteID(){
        listSiteID.add(1);
        listSiteID.add(1);
        listSiteID.add(1);
        listSiteID.add(2);
        listSiteID.add(2);
        listSiteID.add(2);
        return listSiteID;
    }

    public  ArrayList<Date> getListFoundDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2017-10-01 09:43:00");
            Date date1 = sdf.parse("2017-10-02 09:43:00");
            Date date2 = sdf.parse("2017-10-03 09:43:00");
            Date date3 = sdf.parse("2017-10-04 09:43:00");
            Date date4 = sdf.parse("2017-10-05 09:43:00");
            Date date5 = sdf.parse("2017-10-06 09:43:00");


            listFoundDateTime.add(date);
            listFoundDateTime.add(date1);
            listFoundDateTime.add(date2);
            listFoundDateTime.add(date3);
            listFoundDateTime.add(date4);
            listFoundDateTime.add(date5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listFoundDateTime;
    }

    public  ArrayList<Date> getListLastScanDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2017-10-06 09:43:00");
            Date date1 = sdf.parse("2017-10-06 09:43:00");
            Date date2 = sdf.parse("2017-10-06 09:43:00");
            Date date3 = sdf.parse("2017-10-06 09:43:00");
            Date date4 = sdf.parse("2017-10-06 09:43:00");
            Date date5 = sdf.parse("2017-10-06 09:43:00");


            listLastScanDate.add(date);
            listLastScanDate.add(date1);
            listLastScanDate.add(date2);
            listLastScanDate.add(date3);
            listLastScanDate.add(date4);
            listLastScanDate.add(date5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listLastScanDate;
    }

    public static String getPagesName() {
        return PAGES_NAME;
    }

    public static String getIdColumn() {
        return ID_COLUMN;
    }

    public static String getUrlColumn() {
        return URL_COLUMN;
    }

    public static String getSiteIdColumn() {
        return SITE_ID_COLUMN;
    }

    public static String getFoundDateTimeColumn() {
        return FOUND_DATE_TIME_COLUMN;
    }

    public static String getLastScanDateColumn() {
        return LAST_SCAN_DATE_COLUMN;
    }
}
