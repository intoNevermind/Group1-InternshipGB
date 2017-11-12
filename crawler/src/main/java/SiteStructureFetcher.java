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

    private static final String ROBOTS_TXT = "robots.txt";
    private static final String SITEMAP = "Sitemap: ";

    public void updateSiteStructure(String url, DBWrapper dbWrapper) {
        String host = url.trim().replaceAll("(https|http)://", "");

        Set<String> sitemapUrls = fetchSitemaps(url, dbWrapper);

        if (sitemapUrls.isEmpty()) {
            LogWrapper.info("Captain, sitemaps are empty!!!111");
            crawlPage(url, host, dbWrapper);
        } else crawlSitemaps(url, sitemapUrls, dbWrapper);

    }

    private Set<String> fetchSitemaps(String url, DBWrapper dbWrapper) {

        // Trying to download correspondent robots.txt
        Downloader downloader = new Downloader();

        try {
            String robotsString = downloader.download(url + "/" + ROBOTS_TXT);
            // If file exists and not empty
            if (robotsString != null) {

                dbWrapper.addSitePage(url, url + "/" + ROBOTS_TXT);

                Set<String> setOfSitemaps = new HashSet<String>();

                LogWrapper.info(robotsString);

                // If "Sitemap:" found, parsing url.
                for (String str : robotsString.split(System.getProperty("line.separator"))) {
                    LogWrapper.info(str);
                    str.trim();
                    if (str.startsWith(SITEMAP)) {
                        str = str.replaceAll(SITEMAP, "");
                        setOfSitemaps.add(str);
                        dbWrapper.addSitePage(url, str);
                    }
                }
                return setOfSitemaps;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogWrapper.info("Going to return empty sitemaps array");
            return new HashSet<String>();
        }

        return new HashSet<String>();
    }

    private void crawlSitemaps(String siteUrl, Set<String> urls, DBWrapper dbWrapper) {

        LogWrapper.info("Crawling sitemap!..");
        LogWrapper.info("Sitemap list: " + urls.toString());

        try {
            for (String url : urls) {
                DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
                DocumentBuilder b = f.newDocumentBuilder();
                Document doc = b.parse(url);
                System.out.println(doc.getDocumentURI());
                doc.getDocumentElement().normalize();

                // Loop through each item
                NodeList items = doc.getElementsByTagName("loc");
                for (int i = 0; i < items.getLength(); i++) {
                    Node n = items.item(i);
                    if (n.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    Element e = (Element) n;

                    // Get the "text node" in the loc (only one)
                    Node titleNode = e.getChildNodes().item(0);
                    LogWrapper.info(titleNode.getNodeValue());
                    dbWrapper.addSitePage(siteUrl, titleNode.getNodeValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crawlPage(String url, String host, DBWrapper dbWrapper) {

        Set<String> linkSet = new HashSet<String>();

        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(url).get();

            // Extract all href links from url
            Elements links = doc.select("a[href]");

            for (org.jsoup.nodes.Element link : links) {
                String linkUrl = link.attr("abs:href");

                if (linkUrl.contains(host)) {
                    LogWrapper.info("Crawling " + linkUrl);
                    linkSet.add(linkUrl);
                    dbWrapper.addSitePage(url, linkUrl);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
