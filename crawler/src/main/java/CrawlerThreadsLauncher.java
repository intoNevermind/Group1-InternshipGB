import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by ferney on 08.11.17.
 */
public class CrawlerThreadsLauncher {

    final int BUCKET_SIZE = 2;

    private Semaphore semaphore;
    private int threadsCount;

    public CrawlerThreadsLauncher(int number){
        semaphore = new Semaphore(number);
        threadsCount = number;
    }

    private ArrayList<String> linksFromDb = new ArrayList<String>();
    private ArrayList<String> linksFromDbPages = new ArrayList<String>();
    private ArrayList<String> keyWords = new ArrayList<String>();

    SiteStructureFetcher siteStructureFetcher = new SiteStructureFetcher();
    PersonRankUpdater personRankUpdater = new PersonRankUpdater();

    public void startThreads(){

        for (int i = 0; i < threadsCount; i++) {
            new Thread(() -> {

                try {

                    semaphore.acquire();

                    LogWrapper.info(Thread.currentThread().getName() + " starting structure fetching");

                    DBWrapper dbWrapper = new DBWrapper();

                    ArrayList<Page> sites = null;

                    do {
                        sites = dbWrapper.getSiteBucketFromDB(BUCKET_SIZE);

                        for (int a = 0; a < sites.size(); a++) {
                            LogWrapper.info(Thread.currentThread().getName() + " - " + sites.get(a));
                        }

                        if (sites.size() > 0) {
                            for (int j = 0; j < sites.size(); j++) {
                                LogWrapper.info(Thread.currentThread().getName() + " is processing site " + sites.get(j));
                                siteStructureFetcher.updateSiteStructure(sites.get(j), dbWrapper);
                                Thread.sleep(1000);
                                dbWrapper.unlockSite(sites.get(j));
                            }
                        } else {
                            LogWrapper.info(Thread.currentThread().getName() + " - Nothing to do, exiting structure fetching");
                        }

                    } while (sites.size() > 0);

                    LogWrapper.info(Thread.currentThread().getName() + " starting ranks updating");

                    ArrayList<Page> pages = null;

                    do {
                       pages = dbWrapper.getPageBucketFromDB(BUCKET_SIZE);
                       for (int a = 0; a < pages.size(); a++) {
                           LogWrapper.info(Thread.currentThread().getName() + " - " + pages.get(a));
                       }
                        if (pages.size() > 0) {
                            for (int j = 0; j < pages.size(); j++) {
                                LogWrapper.info(Thread.currentThread().getName() + " is processing page " + pages.get(j));
                                PersonRankUpdater.updatePersonRanks(pages.get(j), dbWrapper);
                                //Thread.sleep(1000);
                                dbWrapper.unlockPage(pages.get(j));
                            }
                        } else {
                            LogWrapper.info(Thread.currentThread().getName() + " - Nothing to do, exiting rank updating");
                        }
                    } while (pages.size() > 0);

                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                } finally {
                    LogWrapper.info("Закончила работать " + Thread.currentThread().getName());
                    semaphore.release();
                }
            }).start();
        }
    }

}
