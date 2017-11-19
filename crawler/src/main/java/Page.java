/**
 * Created by ferney on 19.11.17.
 */
public class Page {

    private long pageId;
    private String pageUrl;


    Page(long id, String url){
        pageId = id;
        pageUrl = url;
    }

    public long getPageId() {
        return pageId;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
