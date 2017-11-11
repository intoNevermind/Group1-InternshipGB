package windowGUI.component.workDB.Tables;

import retrofit2.Response;
import windowGUI.component.workDB.ConnectServer;
import windowGUI.component.workDB.RestApi.RestApiForPagesTable;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class PagesTable extends ConnectServer {
/*
<РЕАЛ>
Часть кода которая будет использоваться с реальными данными из базы
*/
    private RestApiForPagesTable restApiForPagesTable = getRetrofit().create(RestApiForPagesTable.class);

    private ArrayList<Integer> getListIDReal() {
        try {
            Response<ArrayList<Integer>> response = restApiForPagesTable.getListIDFromPagesTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    private ArrayList<String> getListURLReal(){
        try {
            Response<ArrayList<String>> response = restApiForPagesTable.getListURLFromPagesTable().execute();
            return response.body();
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    public ArrayList<Integer> getListSateIDReal(){
        try {
            Response<ArrayList<Integer>> response = restApiForPagesTable.getListSiteIDFromPagesTable().execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    public ArrayList<Date> getListFoundDateTimeReal(){
        try {
            Response<ArrayList<Date>> response = restApiForPagesTable.getListFoundDateTimeFromPagesTable().execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    public ArrayList<Date> getListLastScanDateReal(){
        try {
            Response<ArrayList<Date>> response = restApiForPagesTable.getListLastScanDateFromPagesTable().execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }
/*
</РЕАЛ>
*/

/*
<ФЕЙК>
Часть кода для проверки работоспособности обработки данных из БД
*/
    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listURL = new ArrayList<>();
    private static final ArrayList<Integer> listSiteID = new ArrayList<>();
    private static final ArrayList<Date> listFoundDateTime = new ArrayList<>();
    private static final ArrayList<Date> listLastScanDate = new ArrayList<>();
    private static final LinkedHashMap<Integer,Date> listIDAndFoundDateTime = new LinkedHashMap<>();

    public  LinkedHashMap<Integer,Date> getListIDAndFoundDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2017-11-01 09:43:00");
            Date date1 = sdf.parse("2017-11-02 09:43:00");
            Date date2 = sdf.parse("2017-11-03 09:43:00");
            Date date3 = sdf.parse("2017-11-04 09:43:00");
            Date date4 = sdf.parse("2017-11-05 09:43:00");
            Date date5 = sdf.parse("2017-11-06 09:43:00");

            listIDAndFoundDateTime.put(1, date);
            listIDAndFoundDateTime.put(2, date1);
            listIDAndFoundDateTime.put(3, date2);
            listIDAndFoundDateTime.put(4, date3);
            listIDAndFoundDateTime.put(5, date4);
            listIDAndFoundDateTime.put(6, date5);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return listIDAndFoundDateTime;
    }

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
            Date date = sdf.parse("2017-11-01 09:43:00");
            Date date1 = sdf.parse("2017-11-02 09:43:00");
            Date date2 = sdf.parse("2017-11-03 09:43:00");
            Date date3 = sdf.parse("2017-11-04 09:43:00");
            Date date4 = sdf.parse("2017-11-05 09:43:00");
            Date date5 = sdf.parse("2017-11-06 09:43:00");


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
            Date date = sdf.parse("2017-11-06 09:43:00");
            Date date1 = sdf.parse("2017-11-06 09:43:00");
            Date date2 = sdf.parse("2017-11-06 09:43:00");
            Date date3 = sdf.parse("2017-11-06 09:43:00");
            Date date4 = sdf.parse("2017-11-06 09:43:00");
            Date date5 = sdf.parse("2017-11-06 09:43:00");


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
/*
</ФЕЙК>
*/
}
