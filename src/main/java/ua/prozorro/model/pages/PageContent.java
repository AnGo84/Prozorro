package ua.prozorro.model.pages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnGo on 30.10.2016.
 */
public class PageContent {
    private URL url;
    private Page nextPage;
    private List<PageElement> pageElementList;

    public PageContent(URL url) {
        this.url = url;
        this.nextPage = null;
        this.pageElementList = new ArrayList<>();
    }

    public PageContent(URL url, Page nextPage) {
        this.url = url;
        this.nextPage = nextPage;
        this.pageElementList = new ArrayList<>();
    }

    public Page getNextPage() {
        return nextPage;
    }

    public URL getUrl() {
        return url;
    }

    public List<PageElement> getPageElementList() {
        return pageElementList;
    }

    public String getPageHeader(){
        final StringBuilder sb = new StringBuilder("PageContent{");
        sb.append("url=").append(url);
        sb.append(", nextPage=").append(nextPage);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageContent{");
        sb.append("url=").append(url).append('\n');
        sb.append(", nextPage=").append(nextPage).append('\n');
        sb.append(", pageElementList=").append(pageElementList).append('\n');
        sb.append('}');
        return sb.toString();
    }
}
