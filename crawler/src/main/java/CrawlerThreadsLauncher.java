import sitefetcher.LinkChecker;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by ferney on 08.11.17.
 */
public class CrawlerThreadsLauncher {

    final int BUCKET_SIZE = 100;

    private Semaphore semaphore;
    private int threadsCount;

    public CrawlerThreadsLauncher(int number) {
        semaphore = new Semaphore(number);
        threadsCount = number;
    }

    private ArrayList<String> linksFromDb = new ArrayList<String>();
    private ArrayList<String> linksFromDbPages = new ArrayList<String>();
    private ArrayList<String> keyWords = new ArrayList<String>();

    //SiteStructureFetcher siteStructureFetcher = new SiteStructureFetcher();
    PersonRankUpdater personRankUpdater = new PersonRankUpdater();

    public void startThreads() {

        for (int i = 0; i < threadsCount; i++) {
            new Thread(() -> {

                DBWrapper dbWrapper = new DBWrapper();
                SiteStructureFetcher siteStructureFetcher = new SiteStructureFetcher();

                ArrayList<Page> pages = null;
                ArrayList<Page> sites = null;

                try {

                    semaphore.acquire();

                    LogWrapper.info(Thread.currentThread().getName() + " starting structure fetching");

                    do {
                        sites = dbWrapper.getSiteBucketFromDB(BUCKET_SIZE);

                        for (int a = 0; a < sites.size(); a++) {
                            LogWrapper.info(Thread.currentThread().getName() + " - " + sites.get(a));
                            LinkChecker.addSite(sites.get(a).getPageUrl());
                        }

                        if (sites.size() > 0) {
                            for (int j = 0; j < sites.size(); j++) {
                                LogWrapper.info(Thread.currentThread().getName() + " is processing site " + sites.get(j));
                                siteStructureFetcher.updateSiteStructure(sites.get(j), dbWrapper);
                                dbWrapper.unlockSite(sites.get(j));

                                }
                            } else{
                                LogWrapper.info(Thread.currentThread().getName() + " - Nothing to do, exiting structure fetching");
                            }

                        } while (sites.size() > 0) ;

                        LogWrapper.info(Thread.currentThread().getName() + " starting ranks updating");

                        do {
                            pages = dbWrapper.getPageBucketFromDB(BUCKET_SIZE);
                            for (int a = 0; a < pages.size(); a++) {
                                LogWrapper.info(Thread.currentThread().getName() + " - " + pages.get(a).getPageUrl());
                            }
                            if (pages.size() > 0) {
                                for (int j = 0; j < pages.size(); j++) {
                                    LogWrapper.info(Thread.currentThread().getName() + " is processing page " + pages.get(j).getPageUrl());
                                    PersonRankUpdater.updatePersonRanks(pages.get(j), dbWrapper);
                                    LogWrapper.info(Thread.currentThread().getName() + " is updating page " + pages.get(j).getPageUrl() + " structure");
                                    siteStructureFetcher.crawlPage(pages.get(j).getPageUrl());
                                    siteStructureFetcher.dumpPagesToDB(
                                            new Page(pages.get(j).getSiteId(), pages.get(j).getSiteUrl()),
                                            dbWrapper
                                    );
                                    dbWrapper.unlockPage(pages.get(j));
                                }
                            } else {
                                LogWrapper.info(Thread.currentThread().getName() + " - Nothing to do, exiting rank updating");
                            }
                        } while (pages.size() > 0);

                    } catch(InterruptedException ex){
                        Thread.currentThread().interrupt();
                    } finally{
                        LogWrapper.info("Закончила работать " + Thread.currentThread().getName());
                        semaphore.release();
                        if (sites.size() > 0) {
                            for (int j = 0; j < sites.size(); j++) {
                                dbWrapper.unlockSite(sites.get(j));
                            }
                        }
                        if (pages.size() > 0) {
                            for (int j = 0; j < pages.size(); j++) {

                                dbWrapper.unlockPage(pages.get(j));
                            }
                        }
                    }
                }).start();
            }
        }

    }
