import java.util.ArrayList;

/**
 * Created by ferney on 05.11.17.
 */
public class CrawlerMainLoop implements Runnable {
    final long TIME_UPDATE = 7000;

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

    public void run() {

        while (true) {
            try {
                if (!linksFromDb.isEmpty()){
                    for (String s: linksFromDb) {
                        siteStructureFetcher.updateSiteStructure(s);
                    }
                }
                if (!linksFromDbPages.isEmpty()){
                    personRankUpdater.updatePersonRanks(linksFromDbPages, keyWords);
                }

                Thread.sleep(TIME_UPDATE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
