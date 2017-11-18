package sitefetcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static sitefetcher.SitesBufferUpdater.sitesBuffer;

public class LinkChecker implements Runnable{

    private static final String ROBOTS_TXT = "/robots.txt";
    private static final String DISALLOW = "Disallow: ";

    // Список запрщещенных масок ссылок, ключ - host, значенте - набор запрещающих масок
    public static Map<String, HashSet<String>> disallowedLinks = new HashMap<String, HashSet<String>>();

    public static synchronized void addSite (String host) {
        String robotsUrl = host + ROBOTS_TXT;
        host = host.trim().replaceAll("(https|http)://", "").replaceAll("/", "");
        System.out.println("Found host: " + host);

        // Если ключ уже есть в базе - выход
        if (disallowedLinks.containsKey(host))
            return;

        // Попытка загрузить robots.txt
        Downloader downloader = new Downloader();
        try {
            String robotsContent = downloader.download(robotsUrl);

            // Если файл скачан успешно, записываем ссылки Disallow в disallowedLinks
            disallowedLinks.put(host, new HashSet<String>());
            for (String str : robotsContent.split(System.getProperty("line.separator"))) {
                str.trim();

                // Disallow найден
                if (str.startsWith(DISALLOW)) {
                    str = str.replaceAll(DISALLOW, "");

                    // Добавляем в список не разрешенных ссылок
                    disallowedLinks.get(host).add(str);
                }
            }
        } catch (Exception e) {
            System.out.println("File " + robotsUrl + " not found!");
            // Если не успешно - список disallowedLinks для данного host пуст.
            disallowedLinks.put(host, new HashSet<String>());
        }
        System.out.println(disallowedLinks.get(host));
    }

    public static boolean isAllowed(String url) {
        boolean result = false;
        // Если url содержит Host
        for (String host : disallowedLinks.keySet()) {
            if (url.contains(host)) {
                result = true;
                // И если не удовлетворяет ни одной маске, значит нормальная ссылка.
                for (String mask : disallowedLinks.get(host)) {
                    result = result && (!url.contains(mask));
                }
                return result;
            }
        }
        return result;
    }

    public void run() {
        // Подключается к базе
        // Заправшивает спиок всех Sites
        // Строит DisallowedLinks

        // Для целей тестирования пока берем ссылки из SitesBuffer
        for (String host : sitesBuffer) {
            addSite(host);
        }
    }
}
