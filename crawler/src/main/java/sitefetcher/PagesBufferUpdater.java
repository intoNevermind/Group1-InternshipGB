/**
 * Created by Alexander V. Prigorovsky 09.11.17.
 */

package sitefetcher;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class PagesBufferUpdater implements Runnable{

    // Буфер обработанных ссылок из newPagesBuffer, заполняется в процессе работы ThreadedPageFetcher
    public static Queue<String> fetchedPagesBuffer = new PriorityBlockingQueue<String>();

    public void run() {
        // Подключаемся к базе данных
        // Записываем в базу все ссылки из fetchedPagesBuffer с обновленеим даты, если ссылка присутсвовала в базе
        // Закрываем подключение к базе

        // Удаляем из очереди все записанные в базу ссылки
        while (!fetchedPagesBuffer.isEmpty()) {
            System.out.println("Crawled page " + fetchedPagesBuffer.poll());
        }

        System.out.println("PagesBufferUpdater finished!");
    }
}
