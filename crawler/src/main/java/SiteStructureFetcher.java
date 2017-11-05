import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ferney on 01.11.17.
 * Edited by Alexander V. Prigorovsky 05.11.17.
 */
public class SiteStructureFetcher {

    public static final String ROBOTS_TXT = "robots.txt";
    public static final String SITEMAP = "Sitemap: ";

    public static void updateSiteStructure(String url){
        Set<String> sitemapUrls = fetchSitemaps(url);

        if (sitemapUrls.isEmpty()){
            crawlPage(url);
        }
        else crawlSitemaps(sitemapUrls);

    }

    private static Set<String> fetchSitemaps(String url){
        //проверяет есть ли robots.txt и если он есть, то есть ли ссылки на sitemap
        //если нет sitemap то возвращает пустой массив, иначе записывает в массив все найденные ссылки
        //нет robots.txt возвращаем так же пустой массив

        // Trying to download correspondent robots.txt
        Downloader downloader = new Downloader();
        try {
            String robotsString = downloader.download(url + "/" + ROBOTS_TXT);
            // If file exists and no empty
            if (robotsString != null) {
                Set<String> arrayOfSitemaps = new HashSet<String>();

                System.out.println(robotsString);

                // If "Sitemap:" found, parsing url.
                for (String str : robotsString.split(System.getProperty("line.separator"))) {
                    System.out.println(str);
                    str.trim();
                    if (str.startsWith(SITEMAP)) {
                        str = str.replaceAll(SITEMAP, "");
                        arrayOfSitemaps.add(str);
                    }
                }
                return arrayOfSitemaps;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new HashSet<String>();
        }

        return new HashSet<String>();
    }

    //2 разных метода, потому что у некоорых сайтов есть sitemap и все ссылки можно получить из него, а у некоторых нет :(
    //и все ссылки придется получать разбирая HTML страниц

    private static void crawlSitemaps(Set<String> urls){
        //находим все ссылки на странице
        //запускаем для каждой crawlSitemap
        //записываем в базу страницу с ссылкой на текущий sitemap
        //вопрос oграничивать ли глубину и проверять ли повторы
        System.out.println("Crawling sitemap!..");
        System.out.println("Sitemap list: " + urls.toString());
    }

    private static void crawlPage(String url){
        //находим все ссылки на странице
        //запускаем для каждой crawlPage
        //записываем в базу адрес страницы
        //вопрос oграничивать ли глубину и проверять ли повторы
        System.out.println("Sitemap not found!");
    }

    //TODO классы обертки для базы - добавление страницы, проверка на наличие страницы
}
