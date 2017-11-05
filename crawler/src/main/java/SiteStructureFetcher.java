import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ferney on 01.11.17.
 * Edited by Alexander V. Prigorovsky 05.11.17.
 */
public class SiteStructureFetcher {

    public static final String ROBOTS_TXT = "robots.txt";
    public static final String SITEMAP = "Sitemap: ";

    public static void updateSiteStructure(String url){
        String host = url.trim().replaceAll("(https|http)://", "");

        Set<String> sitemapUrls = fetchSitemaps(url);

        if (sitemapUrls.isEmpty()){
            crawlPage(url, host);
        }
        else crawlSitemaps(sitemapUrls);

    }

    private static Set<String> fetchSitemaps(String url){
        //проверяет есть ли robots.txt и если он есть, то есть ли ссылки на sitemap
        //если нет sitemap то возвращает пустой массив, иначе записывает в массив все найденные ссылки
        //нет robots.txt возвращаем так же пустой массив

        // Trying to download correspondent robots.txt
        Downloader downloader = new Downloader();
        try {
            String robotsString = downloader.download(url + "/" + ROBOTS_TXT);
            // If file exists and no empty
            if (robotsString != null) {
                Set<String> setOfSitemaps = new HashSet<String>();

                System.out.println(robotsString);

                // If "Sitemap:" found, parsing url.
                for (String str : robotsString.split(System.getProperty("line.separator"))) {
                    System.out.println(str);
                    str.trim();
                    if (str.startsWith(SITEMAP)) {
                        str = str.replaceAll(SITEMAP, "");
                        setOfSitemaps.add(str);
                    }
                }
                return setOfSitemaps;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new HashSet<String>();
        }

        return new HashSet<String>();
    }

    //2 разных метода, потому что у некоорых сайтов есть sitemap и все ссылки можно получить из него, а у некоторых нет :(
    //и все ссылки придется получать разбирая HTML страниц

    private static void crawlSitemaps(Set<String> urls){
        //находим все ссылки на странице
        //запускаем для каждой crawlSitemap
        //записываем в базу страницу с ссылкой на текущий sitemap
        //вопрос oграничивать ли глубину и проверять ли повторы
        System.out.println("Crawling sitemap!..");
        System.out.println("Sitemap list: " + urls.toString());

        try {
            for (String url : urls) {
                DocumentBuilderFactory f =
                        DocumentBuilderFactory.newInstance();
                DocumentBuilder b = f.newDocumentBuilder();
                Document doc = b.parse(url);
                doc.getDocumentElement().normalize();

                // Loop through each item
                NodeList items = doc.getElementsByTagName("url");
                for (int i = 0; i < items.getLength(); i++)
                {
                    Node n = items.item(i);
                    if (n.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    Element e = (Element) n;

                    // Get the "loc elem" in this url (only one)
                    NodeList locList =
                            e.getElementsByTagName("loc");
                    Element titleElem = (Element) locList.item(0);

                    // Get the "text node" in the loc (only one)
                    Node titleNode = titleElem.getChildNodes().item(0);
                    System.out.println(titleNode.getNodeValue());

                    // TODO Save urls`s to database
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void crawlPage(String url, String host){
        //находим все ссылки на странице
        //запускаем для каждой crawlPage
        //записываем в базу адрес страницы
        //вопрос oграничивать ли глубину и проверять ли повторы
        //System.out.println("Sitemap not found!");

        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(url).get();

            // Extract all href links from url
            Elements links = doc.select("a[href]");

            for (org.jsoup.nodes.Element link : links) {
                String linkUrl = link.attr("abs:href");

                if (linkUrl.contains(host))
                System.out.println("Crawling " + linkUrl);

                // Crawl deep inside;
                // crawlPage(link.attr("abs:href"));

                // TODO Save urls`s to database
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO классы обертки для базы - добавление страницы, проверка на наличие страницы
}
