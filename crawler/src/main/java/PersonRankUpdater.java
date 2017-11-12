import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * Created by ferney on 01.11.17.
 */
public class PersonRankUpdater {

    public static void updatePersonRanks(String url, DBWrapper dbWrapper) {

        String[] keywordEndings = {" ", ",",".",";","<"};

        ArrayList<Integer> peronsIds = null;
        ArrayList<String> keywords = null;

        peronsIds = dbWrapper.getPersonIDs();

        Downloader downloader = new Downloader();
        String pageContent = "";

        try {
            pageContent = downloader.download(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int pageRank = 0;

        for (int i = 0; i < peronsIds.size(); i++) {
            LogWrapper.info("Looking for person ID " + peronsIds.get(i) + " in url " + url);
            pageRank = 0;
            keywords = dbWrapper.getPersonKeywords(peronsIds.get(i));
            for (int j = 0; j < keywords.size(); j++) {
                for (int k = 0; k < keywordEndings.length; k++) {
                    LogWrapper.info("Looking for keyword ID " + keywords.get(j) + keywordEndings[k] + " in url " + url);
                    pageRank += StringUtils.countMatches(pageContent, keywords.get(j) + keywordEndings[k]);
                }
            }
            LogWrapper.info("Writing rank " + pageRank + " for person ID " + peronsIds.get(i) + " and page " + url
                    + " into the database");
            dbWrapper.updatePersonPageRating(pageRank, peronsIds.get(i), url);
        }


        //проходим по массиву ссылок
        //к каждой странице применяем downloader
        //ищем и считаем ключевые слова
        //записываем результат в базу
    }

}
