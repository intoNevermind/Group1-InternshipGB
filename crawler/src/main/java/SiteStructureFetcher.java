import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import sitefetcher.Downloader;
import sitefetcher.LinkChecker;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;


/**
 * Created by ferney on 01.11.17.
 * Edited by Alexander V. Prigorovsky 05.11.17.
 */
class SiteStructureFetcher {

    private static final String ROBOTS_TXT = "/robots.txt";
    private static final String SITEMAP = "Sitemap: ";
    private static final String XML = ".xml";
    private static final String XML_GZ = ".xml.gz";
    private static final int MAX_ATTEMPTS = 3;
    private static final int RETRY_DELAY = 1000;

    // Буфер обработанных ссылок из newPagesBuffer
    private Queue<String> fetchedPagesBuffer = new PriorityBlockingQueue<String>();
    // Буфер новых записей в таблице Pages, заполняется не обработанными ссылками
    private Queue<String> newPagesBuffer = new PriorityBlockingQueue<String>();

    void dumpPagesToDB(Page site, DBWrapper dbWrapper) {
        String str;
        // Пишем все полученные в fetchedPagesBuffer ссылки в базу
        while ((str = fetchedPagesBuffer.poll()) != null) {
            LogWrapper.info("Crawled page " + str);
            if (dbWrapper != null)
                dbWrapper.addSitePage(site, str);
        }
    }

    void updateSiteStructure(Page site, DBWrapper dbWrapper) {

        // Проверяем robots.txt и пишем ссылки в буфер
        checkRobotsTxt(site.getPageUrl());

        // Обрабатываем все ссылки из массиве newPagesBuffer
        String str;
        while ((str = newPagesBuffer.poll()) != null) {

            if (str.endsWith(ROBOTS_TXT)) {
                crawlRobotsTxt(str);
            }
            else if (str.endsWith(XML) || str.endsWith(XML_GZ)) {
                crawlSitemap(str);
            }
            else {
                crawlPage(str);
            }
        }

        dumpPagesToDB(site, dbWrapper);
    }

    // Добавлены новые версии методов.

    private void checkRobotsTxt(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length()-1);
        }
        String robotsUrl = url + ROBOTS_TXT;

        // Попытка загрузить robots.txt
        Downloader downloader = new Downloader();

        int attempts = 0;
        do {

            try {
                String robotsContent = downloader.download(robotsUrl);

                // Если файл скачан успешно, записываем ссылку robotsUrl в newPagesBuffer
                if (!newPagesBuffer.contains(robotsUrl)) {
                    newPagesBuffer.offer(robotsUrl);
                    LogWrapper.info("Added new URL: " + robotsUrl);
                }
                break;
            } catch (Exception e) {
                LogWrapper.info("File " + robotsUrl + " not found! Retying in " + RETRY_DELAY + " milliseconds.");
                try {
                    Thread.sleep(RETRY_DELAY);
                } catch (InterruptedException e1) {
                    attempts = MAX_ATTEMPTS;
                }
            }
        } while (attempts++ < MAX_ATTEMPTS);

        // Если не успешно c MAX_ATTEMPTS попыток - записываем ссылку url в newPagesBuffer
        if (attempts >= MAX_ATTEMPTS)
            LogWrapper.info("File " + robotsUrl + " not found!");

        if (!newPagesBuffer.contains(url)) {
            newPagesBuffer.offer(url);
            LogWrapper.info("Added new URL: " + url);
        }
    }

    private void crawlRobotsTxt (String url) {
        Downloader downloader = new Downloader();

        // Пытаемся скачать сайт по ссылке url
        int attempts = 0;
        do {
            try {
                String robotsContent = downloader.download(url);

                // Если файл скачан успешно, приступаем к поиску Sitemap
                for (String str : robotsContent.split(System.getProperty("line.separator"))) {
                    str = str.trim();

                    // Sitemap найден
                    if (str.startsWith(SITEMAP)) {
                        str = str.replaceAll(SITEMAP, "");

                        // Добавляем в список не обработанных ссылок
                        if (!newPagesBuffer.contains(str)) {
                            newPagesBuffer.offer(str);
                            LogWrapper.info("Added new URL: " + str);
                        }
                    }
                }
                break;
            } catch (Exception e) {
                LogWrapper.info("File " + url + " not found! Retying in " + RETRY_DELAY + " milliseconds.");
                try {
                    Thread.sleep(RETRY_DELAY);
                } catch (InterruptedException e1) {
                    attempts = MAX_ATTEMPTS;
                }
            }
        } while (attempts++ < MAX_ATTEMPTS);

        if (attempts >= MAX_ATTEMPTS)
            LogWrapper.info("File " + url + " not found!");
    }

    private void crawlSitemap (String url) {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setNamespaceAware(true);

        int attempts = 0;
        do {
            try {
                // Скачиваем XML по ссылке
                DocumentBuilder b = f.newDocumentBuilder();

                Document doc;

                // Если фалй упакован в GZ, сперва распаковываем.
                if (url.endsWith(XML_GZ)) {
                    Downloader downloader = new Downloader();
                    String unzipedXML = downloader.downloadGz(url);
                    doc = b.parse(new InputSource(new StringReader(unzipedXML)));
                } else
                    doc = b.parse(url);
                doc.getDocumentElement().normalize();

                // Перебираем все элементы с тегами <loc></loc>
                NodeList items = doc.getElementsByTagName("loc");
                for (int i = 0; i < items.getLength(); i++) {
                    Node n = items.item(i);
                    if (n.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    Element e = (Element) n;

                    // Выделяем текстовое поле и добавляем в список не обработанных ссылок
                    Node titleNode = e.getChildNodes().item(0);
                    String node = titleNode.getNodeValue();
                    if (!newPagesBuffer.contains(node)) {
                        newPagesBuffer.offer(node);
                        LogWrapper.info("Added new URL: " + node);
                    }
                }
                break;
            } catch (Exception e) {
                LogWrapper.info("XML error in file " + url + ". Retying in " + RETRY_DELAY + " milliseconds.");
                try {
                    Thread.sleep(RETRY_DELAY);
                } catch (InterruptedException e1) {
                    attempts = MAX_ATTEMPTS;
                }
            }
        } while (attempts++ < MAX_ATTEMPTS);

        if (attempts >= MAX_ATTEMPTS) {
            LogWrapper.info("XML error in file " + url);
            if (url.startsWith("http://")) {
                url = url.replaceFirst("http://", "https://");
                if (!newPagesBuffer.contains(url)) {
                    newPagesBuffer.offer(url);
                    LogWrapper.info("Added new URL: " + url);
                }
            }
        }
    }

    public void crawlPage (String url) {
        if (LinkChecker.isAllowed(url) && !fetchedPagesBuffer.contains(url))
            fetchedPagesBuffer.offer(url);

        int attempts = 0;
        do {
            try {
                // Скачиваем HTML документ
                org.jsoup.nodes.Document doc = Jsoup.connect(url).get();

                // Если успешно - выбираем все ссылки по маске a href
                Elements links = doc.select("a[href]");

                for (org.jsoup.nodes.Element link : links) {
                    // Получаем абсолютное значение ссылки
                    String linkUrl = link.attr("abs:href");

                    // Записываем все найденные ссылки в массив обработанных
                    if (LinkChecker.isAllowed(linkUrl) && !fetchedPagesBuffer.contains(linkUrl)) {
                        fetchedPagesBuffer.offer(linkUrl);
                        LogWrapper.info("Added new URL: " + linkUrl);
                    }
                }
                break;
            } catch (IOException e) {
                LogWrapper.info("URL " + url + " is broken! Retying in " + RETRY_DELAY + " milliseconds.");
                try {
                    Thread.sleep(RETRY_DELAY);
                } catch (InterruptedException e1) {
                    attempts = MAX_ATTEMPTS;
                }
            }
        } while (attempts++ < MAX_ATTEMPTS);

        if (attempts >= MAX_ATTEMPTS) {
            LogWrapper.info("URL " + url + " is broken!");
            if (url.startsWith("http://")) {
                url = url.replaceFirst("http://", "https://");
                if (!newPagesBuffer.contains(url)) {
                    newPagesBuffer.offer(url);
                    LogWrapper.info("Added new URL: " + url);
                }
            }
        }
    }
}
