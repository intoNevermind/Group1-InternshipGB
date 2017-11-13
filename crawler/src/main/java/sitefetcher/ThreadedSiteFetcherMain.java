/**
 * Created by Alexander V. Prigorovsky 08.11.17.
 */

package sitefetcher;

import java.util.Date;

import static java.lang.Thread.sleep;

public class ThreadedSiteFetcherMain {

    public static void main(String[] args) throws InterruptedException {
        SitesBufferUpdater sitesBufferUpdater = new SitesBufferUpdater();
        LinkChecker linkChecker = new LinkChecker();
        ThreadedSiteFetcher threadedSiteFetcher = new ThreadedSiteFetcher();
        PagesBufferUpdater pagesBufferUpdater = new PagesBufferUpdater();
        ThreadedPageFetcher threadedPageFetcher = new ThreadedPageFetcher();
        ReScanPages reScanPages = new ReScanPages(new Date());

        sitesBufferUpdater.run();

        linkChecker.run();

        threadedSiteFetcher.run();

        threadedPageFetcher.run();

        pagesBufferUpdater.run();
    }
}
