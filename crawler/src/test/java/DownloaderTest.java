import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ferney on 28.10.17.
 */
public class DownloaderTest {


    Downloader downloader = new Downloader();

    @Test (expected = DownloadException.class)
    public void downloadExc() throws Exception {
        downloader.download("http://ya.ru/java/java");
    }

    @Test
    public void download() throws Exception {
        String keyWord = "Яндекс1";
        Assert.assertTrue(downloader.download("http://ya.ru").matches(".*" + keyWord + ".*"));
    }

}