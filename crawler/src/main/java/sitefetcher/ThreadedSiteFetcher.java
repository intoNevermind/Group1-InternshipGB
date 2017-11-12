/**
 * Created by Alexander V. Prigorovsky 08.11.17.
 */

package sitefetcher;

import static sitefetcher.ReScanPages.newPagesBuffer;
import static sitefetcher.SitesBufferUpdater.sitesBuffer;

public class ThreadedSiteFetcher implements Runnable {

    private static final String ROBOTS_TXT = "/robots.txt";

    private void checkRobotsTxt(String url) {
        String robotsUrl = url + ROBOTS_TXT;

        // Попытка загрузить robots.txt
        Downloader downloader = new Downloader();
        try {
            String robotsContent = downloader.download(robotsUrl);

            // Если файл скачан успешно, записываем ссылку robotsUrl в newPagesBuffer
            newPagesBuffer.offer(robotsUrl);
        } catch (Exception e) {
            System.out.println("File " + robotsUrl + " not found!");
            // Если не успешно - записываем ссылку url в newPagesBuffer
            newPagesBuffer.offer(url);
        }
    }

    public void run() {
        while (!sitesBuffer.isEmpty()) {
            // Получаем ссылку на сайт
            String url = sitesBuffer.poll();
            checkRobotsTxt(url);
        }

        System.out.println("ThreadedSiteFetcher finished!");
    }
}
