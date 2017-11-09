/**
 * Created by Alexander V. Prigorovsky 08.11.17.
 */

package sitefetcher;

public class ThreadedSiteFetcherMain {

    public static void main(String[] args) {
        SitesBufferUpdater sitesBufferUpdater = new SitesBufferUpdater();
        sitesBufferUpdater.run();
        ThreadedSiteFetcher threadedSiteFetcher = new ThreadedSiteFetcher();
        threadedSiteFetcher.run();
    }
}
