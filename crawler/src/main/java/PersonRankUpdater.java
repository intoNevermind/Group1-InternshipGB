import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sitefetcher.Downloader;

import java.util.ArrayList;

/**
 * Created by ferney on 01.11.17.
 */
public class PersonRankUpdater {

    public static void updatePersonRanks(Page page, DBWrapper dbWrapper) {

        String[] keywordEndings = {" ", ",",".",";","-"};

        ArrayList<Integer> peronsIds = null;
        ArrayList<String> keywords = null;

        peronsIds = dbWrapper.getPersonIDs();

        Downloader downloader = new Downloader();
        String pageHTML = "";

        try {
            pageHTML = downloader.download(page.getPageUrl()) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Document document = Jsoup.parse(pageHTML);

        int pageRank = 0;
        String pageText = document.body().text();

        for (int i = 0; i < peronsIds.size(); i++) {
            //LogWrapper.info("Looking for person ID " + peronsIds.get(i) + " in url " + page.getPageUrl());
            pageRank = 0;
            keywords = dbWrapper.getPersonKeywords(peronsIds.get(i));
            for (int j = 0; j < keywords.size(); j++) {
                for (int k = 0; k < keywordEndings.length; k++) {
                    //LogWrapper.info("Looking for keyword ID " + keywords.get(j) + keywordEndings[k] + " in url " + page.getPageUrl());
                    pageRank += StringUtils.countMatches(pageText, keywords.get(j) + keywordEndings[k]);
                }
            }
            LogWrapper.info("Writing rank " + pageRank + " for person ID " + peronsIds.get(i) + " and page " + page.getPageUrl()
                    + " into the database");
            //dbWrapper.updatePersonPageRating(pageRank, peronsIds.get(i), page);
        }


    }

}
