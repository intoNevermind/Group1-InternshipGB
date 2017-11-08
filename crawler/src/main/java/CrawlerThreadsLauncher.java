import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by ferney on 08.11.17.
 */
public class CrawlerThreadsLauncher {

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

    public void setLinksFromDb(ArrayList<String> linksFromDb) {
        this.linksFromDb = linksFromDb;
    }

    public void setLinksFromDbPages(ArrayList<String> linksFromDbPages) {
        this.linksFromDbPages = linksFromDbPages;
    }

    public void setKeyWords(ArrayList<String> keyWords) {
        this.keyWords = keyWords;
    }

    public void startThreads(){

        for (int i = 0; i < threadsCount; i++) {
            new Thread(() -> {

                try {

                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(7100);
                    /*if (!linksFromDb.isEmpty()) {
                        for (String s : linksFromDb) {
                            siteStructureFetcher.updateSiteStructure(s);
                        }
                    }
                    if (!linksFromDbPages.isEmpty()) {
                        personRankUpdater.updatePersonRanks(linksFromDbPages, keyWords);
                    }*/
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                } finally {
                    System.out.println("Закончила работать " + Thread.currentThread().getName());
                    semaphore.release();
                }
            }).start();
        }
    }

}
