/**
 * Created by ferney on 19.11.17.
 */
public class Page {

    private long pageId;
    private String pageUrl;
    private long siteId;
    private String siteUrl;


    Page(long id, String url){
        pageId = id;
        pageUrl = url;
    }

    Page(long id, String url, long initialSiteId, String initialSiteUrl){
        pageId = id;
        pageUrl = url;
        siteId = initialSiteId;
        siteUrl = initialSiteUrl;
    }


    public long getPageId() {
        return pageId;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public String getSiteUrl() { return siteUrl;  }

    public long getSiteId() { return siteId; }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void setSiteUrl(String siteUrl) { this.siteUrl = siteUrl; }

    public void setSiteId(long siteId) { this.siteId = siteId; }
}
