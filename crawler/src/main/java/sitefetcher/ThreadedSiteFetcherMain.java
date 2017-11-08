/**
 * Created by Alexander V. Prigorovsky 08.11.17.
 */

package sitefetcher;

import java.util.Date;

public class ThreadedSiteFetcherMain {

    public static void main(String[] args) {
        ThreadedSiteFetcher threadedSiteFetcher = new ThreadedSiteFetcher(new Date(), "https://yandex.ru");
        threadedSiteFetcher.run();
    }
}
