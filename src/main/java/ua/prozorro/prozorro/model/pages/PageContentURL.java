package ua.prozorro.prozorro.model.pages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PageContentURL {

    private URL url;

    private PageURL nextPage;

    private List<ProzorroPageElement> pageElementList;

    public PageContentURL() {
    }

    public PageContentURL(URL url) {
        this.url = url;
        this.nextPage = null;
        this.pageElementList = new ArrayList<>();
    }

    public PageContentURL(URL url, PageURL nextPage) {
        this.url = url;
        this.nextPage = nextPage;
        this.pageElementList = new ArrayList<>();
    }

    public PageURL getNextPage() {
        return nextPage;
    }

    public URL getUrl() {
        return url;
    }

    public List<ProzorroPageElement> getPageElementList() {
        return pageElementList;
    }

    public String getPageHeader() {
        final StringBuilder sb = new StringBuilder("PageContentURL{");
        sb.append("url=").append(url);
        sb.append(", nextPage=").append(nextPage);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageContentURL{");
        sb.append("url=").append(url).append('\n');
        sb.append(", nextPage=").append(nextPage).append('\n');
        sb.append(", pageElementList=").append(pageElementList).append('\n');
        sb.append('}');
        return sb.toString();
    }
}
