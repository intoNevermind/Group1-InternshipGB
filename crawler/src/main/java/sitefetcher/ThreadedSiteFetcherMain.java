/**
 * Created by Alexander V. Prigorovsky 08.11.17.
 */

package sitefetcher;

import static java.lang.Thread.sleep;

public class ThreadedSiteFetcherMain {

    public static void main(String[] args) throws InterruptedException {
        SitesBufferUpdater sitesBufferUpdater = new SitesBufferUpdater();
        ThreadedSiteFetcher threadedSiteFetcher = new ThreadedSiteFetcher();
        PagesBufferUpdater pagesBufferUpdater = new PagesBufferUpdater();
        ThreadedPageFetcher threadedPageFetcher = new ThreadedPageFetcher();

        sitesBufferUpdater.run();
        sleep(10000);
        threadedSiteFetcher.run();
        sleep(10000);
        threadedPageFetcher.run();
        sleep(10000);
        pagesBufferUpdater.run();
    }
}
