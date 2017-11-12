/**
 * Created by Alexander V. Prigorovsky 09.11.17.
 */

package sitefetcher;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.IOException;

import static sitefetcher.PagesBufferUpdater.fetchedPagesBuffer;
import static sitefetcher.ReScanPages.newPagesBuffer;

public class ThreadedPageFetcher implements Runnable{

    private static final String SITEMAP = "Sitemap: ";
    private static final String ROBOTS_TXT = "/robots.txt";
    private static final String XML = ".xml";

    private void crawlRobotsTxt (String url) {
        Downloader downloader = new Downloader();

        // Пытаемся скачать сайт по ссылке url
        try {
            String robotsContent = downloader.download(url);

            // Если файл скачан успешно, приступаем к поиску Sitemap
            for (String str : robotsContent.split(System.getProperty("line.separator"))) {
                str.trim();

                // Sitemap найден
                if (str.startsWith(SITEMAP)) {
                    str = str.replaceAll(SITEMAP, "");

                    // Добавляем в список не обработанных ссылок
                    newPagesBuffer.offer(str);
                }
            }
        } catch (Exception e) {
            System.out.println("File " + url + " not found!");
            // Если не успешно - ссылка битая, ничего не делаем
            // По хорошему надо записать ее обратно в newPagesBuffer
        }
    }

    private void crawlSitemap (String url) {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        try {
            // Скачиваем XML по ссылке
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(url);
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
                newPagesBuffer.offer(titleNode.getNodeValue());
            }
        } catch (Exception e) {
            System.out.println("XML error in file " + url);
            // Если не успешно - ссылка битая, ничего не делаем
        }
    }

    private void crawlPage (String url) {
        try {
            // Скачиваем HTML документ
            org.jsoup.nodes.Document doc = Jsoup.connect(url).get();

            // Если успешно - выбираем все ссылки по маске a href
            Elements links = doc.select("a[href]");

            for (org.jsoup.nodes.Element link : links) {
                // Получаем абсолютное значение ссылки
                String linkUrl = link.attr("abs:href");

                // Записываем все найденные ссылки в массив обработанных
                fetchedPagesBuffer.offer(linkUrl);
            }

        } catch (IOException e) {
            System.out.println("URL " + url + " is broken!");
            // Если не успешно - ссылка битая, ничего не делаем
        }
    }

    public void run() {
        while (!newPagesBuffer.isEmpty()) {
            String url = newPagesBuffer.poll();

            if (url.endsWith(ROBOTS_TXT)) {
                crawlRobotsTxt(url);
            }
            else if (url.endsWith(XML)) {
                crawlSitemap(url);
            }
            else {
                crawlPage(url);
            }
        }

        System.out.println("ThreadedPageFetcher finished!");
    }
}
