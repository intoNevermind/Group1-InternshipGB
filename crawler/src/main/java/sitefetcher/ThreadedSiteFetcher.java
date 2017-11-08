/**
 * Created by Alexander V. Prigorovsky 08.11.17.
 */

package sitefetcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.*;

public class ThreadedSiteFetcher implements Runnable {

    private static final String ROBOTS_TXT = "/robots.txt";
    private static final String SITEMAP = "Sitemap: ";
    private static final String DISALLOW = "Disallow: ";

    // Обрабатывает страницы, созданные до указанной даты
    private Date scanDate;
    // База данных
    private Object dataBaseInstance;
    // Локальное хранилище ссылок, K = URL, V = Host
    private Map<String, String> foundPages = new HashMap<String, String>();;
    // Хранилище не разрешенных ссылок
    private Set<String> disallowRegex = new HashSet<String>();


    public ThreadedSiteFetcher(Date scanDate, Object dataBaseInstance) {
        this.scanDate = scanDate;
        this.dataBaseInstance = dataBaseInstance;
    }



    private void findNewSites() {
        // Соединяется с базой данных
        // Получает список Sites, для которых отсутствуют Pages
        // Закрывает соединение с базой данных

        // Дл целей тестирования положим
        Set<String> newSitesUrls = new HashSet<String>();
        newSitesUrls.add(dataBaseInstance.toString());

        // Перебираем новые сайты
        for (String siteUrl : newSitesUrls) {
            String robotsUrl = siteUrl + ROBOTS_TXT;

            // Попытка загрузить robots.txt
            Downloader downloader = new Downloader();
            String robotsContent = new String();
            try {
                robotsContent = downloader.download(robotsUrl);
            } catch (Exception e) {
                System.out.println("File " + robotsUrl + " not found!");
            }

            // Если файл скачан успешно, выбираем все Sitemap и Disallow
            if (robotsContent != "") {
                System.out.println(robotsContent);
            }
        }
    }

    public void run() {
        this.findNewSites();
    }
}
