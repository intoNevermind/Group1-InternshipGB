/**
 * Created by ferney on 05.11.17.
 */
public class CrawlerMainLoop implements Runnable {
    final long TIME_UPDATE = 7000;
    final int NUMBERS_OF_THREADS = 4;



    public void run() {
        CrawlerThreadsLauncher crawlerThreadsLauncher = new CrawlerThreadsLauncher(NUMBERS_OF_THREADS);

        while (true) {
            try {
                System.out.println("Стартанул большой цикл");
                crawlerThreadsLauncher.startThreads();
                System.out.println("Время поспать");
                Thread.sleep(TIME_UPDATE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
