import java.util.logging.Logger;

/**
 * Created by ferney on 27.10.17.
 */


public class Main {

    final static Logger logger = Logger.getLogger("Crawler");

    public static DBWrapper dbWrapper = null;

    public static void main(String[] args) {

        LogWrapper.info("Hello, I am Crawler.");

        CrawlerMainLoop crawlerMainLoop = new CrawlerMainLoop();
        crawlerMainLoop.run();

        /*DBWrapper dbWrapper = new DBWrapper();
        SiteStructureFetcher siteStructureFetcher = new SiteStructureFetcher();
        siteStructureFetcher.crawlPage("http://meduza.io/", "meduza.io", dbWrapper);*/

    }
}
