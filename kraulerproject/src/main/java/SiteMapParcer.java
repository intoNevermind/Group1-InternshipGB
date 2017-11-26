import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


/**
 * Created by Benutzer on 01.11.2017.
 */
public class SiteMapParcer {

    public static void main(String[] args) throws Exception {


        SiteMapParcer smp = new SiteMapParcer();
        Document doc = Jsoup.connect(String.valueOf(smp)).get();
        // как передать список robots.txt?

         //print all available links on page
        Elements links = doc.select("a[href]");
        for (Element l : links) {
            System.out.println("link: " + l.attr("abs:href"));
        }
    }

}
