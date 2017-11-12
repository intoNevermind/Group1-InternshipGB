package sitefetcher;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class ReScanPages implements Runnable {

    // Дата обрабатываемых записей
    private Date scanDate = new Date();

    // Буфер новых записей в таблице Pages, заполняется не обработанными ссылками
    public static Queue<String> newPagesBuffer = new PriorityBlockingQueue<String>();

    public ReScanPages(Date scanDate) {
        this.scanDate = scanDate;
    }

    private String[] getReScanPages() {
        // Подкючается к базе данных
        // Получает спсиок всех страниц со временем обновления меньше заданного
        // Возвращает Массив ссылок на страницы, которые нужно обработать
        String[] result = new String[0];
        return result;
    }

    public void run() {
        for (String url : getReScanPages()) {
            newPagesBuffer.offer(url);
        }
    }
}
