import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
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
public class SiteStructureFetcher {

    private static final String ROBOTS_TXT = "/robots.txt";
    private static final String SITEMAP = "Sitemap: ";
    private static final String XML = ".xml";
    private static final String XML_GZ = ".xml.gz";

    // Буфер обработанных ссылок из newPagesBuffer
    public Queue<String> fetchedPagesBuffer = new PriorityBlockingQueue<String>();
    // Буфер новых записей в таблице Pages, заполняется не обработанными ссылками
    public Queue<String> newPagesBuffer = new PriorityBlockingQueue<String>();

    public void updateSiteStructure(Page site, DBWrapper dbWrapper) {

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

        // Пишем все полученные в fetchedPagesBuffer ссылки в базу
        while ((str = fetchedPagesBuffer.poll()) != null) {
            LogWrapper.info("Crawled page " + str);
            if (dbWrapper != null)
                dbWrapper.addSitePage(site, str);
        }

        // По итогу все обработанные поптоками страницы перетекают в fetchedPagesBuffer

        /*
        String host = url.trim().replaceAll("(https|http)://", "");

        Set<String> sitemapUrls = fetchSitemaps(url, dbWrapper);

        if (sitemapUrls.isEmpty()) {
            LogWrapper.info("Captain, sitemaps are empty!!!111");
            crawlPage(url, host, dbWrapper);
        } else crawlSitemaps(url, sitemapUrls, dbWrapper);
        */
    }
/*
*
* Убрать, больше не используются
* */

/*    private Set<String> fetchSitemaps(Page site, DBWrapper dbWrapper) {

        // Trying to download correspondent robots.txt
        Downloader downloader = new Downloader();

        try {
            String robotsString = downloader.download(site.getPageUrl() + ROBOTS_TXT);
            // If file exists and not empty
            if (robotsString != null) {

                dbWrapper.addSitePage(site, site.getPageUrl() + ROBOTS_TXT);

                Set<String> setOfSitemaps = new HashSet<String>();

                LogWrapper.info(robotsString);

                // If "Sitemap:" found, parsing url.
                for (String str : robotsString.split(System.getProperty("line.separator"))) {
                    LogWrapper.info(str);
                    str.trim();
                    if (str.startsWith(SITEMAP)) {
                        str = str.replaceAll(SITEMAP, "");
                        setOfSitemaps.add(str);
                        dbWrapper.addSitePage(site, str);
                    }
                }
                return setOfSitemaps;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogWrapper.info("Going to return empty sitemaps array");
            return new HashSet<String>();
        }

        return new HashSet<String>();
    }

    private void crawlSitemaps(Page site, Set<String> urls, DBWrapper dbWrapper) {

        LogWrapper.info("Crawling sitemap!..");
        LogWrapper.info("Sitemap list: " + urls.toString());

        try {
            for (String url : urls) {
                DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
                DocumentBuilder b = f.newDocumentBuilder();
                Document doc = b.parse(url);
                LogWrapper.info(doc.getDocumentURI());
                doc.getDocumentElement().normalize();

                // Loop through each item
                NodeList items = doc.getElementsByTagName("loc");
                for (int i = 0; i < items.getLength(); i++) {
                    Node n = items.item(i);
                    if (n.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    Element e = (Element) n;

                    // Get the "text node" in the loc (only one)
                    Node titleNode = e.getChildNodes().item(0);
                    LogWrapper.info(titleNode.getNodeValue());
                    dbWrapper.addSitePage(site, titleNode.getNodeValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crawlPage(Page site, String pageUrl, DBWrapper dbWrapper) {

        Set<String> linkSet = new HashSet<String>();

        String host = site.getPageUrl().trim().replaceAll("(https|http)://", "");

        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(pageUrl).get();

            // Extract all href links from url
            Elements links = doc.select("a[href]");

            for (org.jsoup.nodes.Element link : links) {
                String linkUrl = link.attr("abs:href");

                if (linkUrl.contains(host)) {
                    LogWrapper.info("Crawling " + linkUrl);
                    linkSet.add(linkUrl);
                    LogWrapper.info("Adding page " + linkUrl + " for url " + site.getPageUrl());
                    dbWrapper.addSitePage(site, linkUrl);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    // Добавлены новые версии методов.

    private void checkRobotsTxt(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length()-1);
        }
        String robotsUrl = url + ROBOTS_TXT;

        // Попытка загрузить robots.txt
        Downloader downloader = new Downloader();
        try {
            String robotsContent = downloader.download(robotsUrl);

            // Если файл скачан успешно, записываем ссылку robotsUrl в newPagesBuffer
            if (!newPagesBuffer.contains(robotsUrl)) {
                newPagesBuffer.offer(robotsUrl);
                LogWrapper.info("Added new URL: " + robotsUrl);
            }
        } catch (Exception e) {
            LogWrapper.info("File " + robotsUrl + " not found!");
            // Если не успешно - записываем ссылку url в newPagesBuffer
        }
        if (!newPagesBuffer.contains(url)) {
            newPagesBuffer.offer(url);
            LogWrapper.info("Added new URL: " + url);
        }
    }

    private void crawlRobotsTxt (String url) {
        Downloader downloader = new Downloader();

        // Пытаемся скачать сайт по ссылке url
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
        } catch (Exception e) {
            LogWrapper.info("File " + url + " not found!");
            // Если не успешно - ссылка битая, ничего не делаем
            // По хорошему надо записать ее обратно в newPagesBuffer
        }
    }

    private void crawlSitemap (String url) {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setNamespaceAware(true);
        try {
            // Скачиваем XML по ссылке
            DocumentBuilder b = f.newDocumentBuilder();

            Document doc = null;

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
            for (int i = 0; i < items.getLength(); i++)
            {
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
        } catch (Exception e) {
            LogWrapper.info("XML error in file " + url);
            // Если не успешно - ссылка битая, ничего не делаем
        }
    }

    private void crawlPage (String url) {
        if (LinkChecker.isAllowed(url) && !fetchedPagesBuffer.contains(url))
            fetchedPagesBuffer.offer(url);
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

        } catch (IOException e) {
            LogWrapper.info("URL " + url + " is broken!");
            // Если не успешно - ссылка битая, ничего не делаем
        }
    }

}
