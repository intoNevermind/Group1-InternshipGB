import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sitefetcher.Downloader;

import java.util.ArrayList;

public class PersonRankUpdater {

    public static void updatePersonRanks(Page page, DBWrapper dbWrapper) {

          String pageText = getTextFromHtml(downloadPage(page));
          countPersonRanks(pageText, dbWrapper, page);

    }

     private static String downloadPage(Page page) {

            Downloader downloader = new Downloader();
            String pageHTML = "";

            try {
                pageHTML = downloader.download(page.getPageUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return pageHTML;
        }

        private static String getTextFromHtml(String html) {

            Document document = Jsoup.parse(html);
            String pageText = document.body().text();
            return pageText;
        }

        private static void countPersonRanks(String pageText, DBWrapper dbWrapper, Page page) {
            ArrayList<Integer> personsIds = null;
            ArrayList<String> keywords = null;

            personsIds = dbWrapper.getPersonIDs();
            int pageRank = 0;


            for (int i = 0; i < personsIds.size(); i++) {
                //LogWrapper.info("Looking for person ID " + peronsIds.get(i) + " in url " + page.getPageUrl());
                pageRank = 0;
                keywords = dbWrapper.getPersonKeywords(personsIds.get(i));
                for (int j = 0; j < keywords.size(); j++) {
                    pageRank += countKeyRangs(keywords.get(j), pageText);
                }
                LogWrapper.info("Writing rank " + pageRank + " for person ID " + personsIds.get(i) + " and page " + page.getPageUrl()
                        + " into the database");
                dbWrapper.updatePersonPageRating(pageRank, personsIds.get(i), page);
            }
       }

        public static int countKeyRangs(String keyWord, String pageText){
            String[] keywordEndings = {" ", ",",".", ":", ";","-"};
            int result = 0;
            for (int k = 0; k < keywordEndings.length; k++) {
                //LogWrapper.info("Looking for keyword ID " + keywords.get(j) + keywordEndings[k] + " in url " + page.getPageUrl());
                 result += StringUtils.countMatches(pageText, keyWord + keywordEndings[k]);
            }
            return result;
        }

}
