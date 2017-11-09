/**
 * Created by Alexander V. Prigorovsky 09.11.17.
 */

package sitefetcher;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class SitesBufferUpdater implements Runnable{

    // Буфер новых записей в таблице Sites

    public static Queue<String> sitesBuffer = new PriorityBlockingQueue<String>();

    public void run() {
        // Соединяется с базой данных
        // Получает список Sites, для которых отсутствуют Pages
        // Закрывает соединение с базой данных
        // Записывает все найденные Sites в sitesBuffer

        // Для тестирования добавим пару сайтов в очередь
        sitesBuffer.offer("https://ria.ru");
        sitesBuffer.offer("https://meduza.io");
    }
}
