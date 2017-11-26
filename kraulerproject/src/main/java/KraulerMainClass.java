/**
 * Created by Benutzer on 01.11.2017.
 */

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KraulerMainClass {
    public static void main(String[] args) throws IOException {

        String url = "http://www.lenta.ru";

        Document doc = Jsoup.connect(url).get();
        //Elements persons = doc.select(".entrytitle");
        Elements persons = doc.getElementsContainingOwnText("Путин");

        //print all titles in main page
        int count = 0;
        for (Element e : persons) {
            System.out.println("Title: " + e.text());
            count++;
        }
        System.out.println("Количество упоминаний: "+count);

        /*print all available links on page
        Elements links = doc.select("a[href]");
        for (Element l : links) {
            System.out.println("link: " + l.attr("abs:href"));
        }*/

    }
}
