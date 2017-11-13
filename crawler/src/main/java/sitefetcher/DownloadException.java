package sitefetcher;

/**
 * Created by ferney on 26.10.17.
 */
public class DownloadException extends RuntimeException{

    private String url;
    private int returnCode;

    public DownloadException(String url, int returnCode) {
        this.url = url;
        this.returnCode = returnCode;
    }

    @Override
    public String toString(){
        return "При обращении к " + url + " был получен неожиданный код ответа " + returnCode + ".";
    }
}
