/**
 * Created by ferney on 01.11.17.
 */
public class SiteStructureFetcher {

    public static void updateSiteStructure(String url){
        String[] sitemapUrls = fetchSitemaps(url);

        if (sitemapUrls.length==0){
            crawlPage(url);
        }
        else crawlSitemaps(sitemapUrls);

    }

    private static String[] fetchSitemaps(String url){
        //проверяет есть ли robots.txt и если он есть, то есть ли ссылки на sitemap
        //если нет sitemap то возвращает пустой массив, иначе записывает в массив все найденные ссылки
        //нет robots.txt возвращаем так же пустой массив
    }

    //2 разных метода, потому что у некоорых сайтов есть sitemap и все ссылки можно получить из него, а у некоторых нет :(
    //и все ссылки придется получать разбирая HTML страниц

    private static void crawlSitemaps(String[] urls){
        //находим все ссылки на странице
        //запускаем для каждой crawlSitemap
        //записываем в базу страницу с ссылкой на текущий sitemap
        //вопрос oграничивать ли глубину и проверять ли повторы
    }

    private static void crawlPage(String url){
        //находим все ссылки на странице
        //запускаем для каждой crawlPage
        //записываем в базу адрес страницы
        //вопрос oграничивать ли глубину и проверять ли повторы
    }

    //TODO классы обертки для базы - добавление страницы, проверка на наличие страницы
}
