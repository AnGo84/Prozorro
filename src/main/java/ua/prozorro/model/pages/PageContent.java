package ua.prozorro.model.pages;

import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnGo on 30.10.2016.
 */
public class PageContent {

    @SerializedName("next_page")
    private Page nextPage;

    @SerializedName("data")
    private List<PageElement> pageElementList;

    @SerializedName("prev_page")
    private Page prevPage;

    public Page getNextPage() {
        return nextPage;
    }

    public void setNextPage(Page nextPage) {
        this.nextPage = nextPage;
    }

    public List<PageElement> getPageElementList() {
        return pageElementList;
    }

    public void setPageElementList(List<PageElement> pageElementList) {
        this.pageElementList = pageElementList;
    }

    public Page getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(Page prevPage) {
        this.prevPage = prevPage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageContent{");
        sb.append("nextPage=").append(nextPage);
        sb.append(", pageElementList=").append(pageElementList);
        sb.append(", prevPage=").append(prevPage);
        sb.append('}');
        return sb.toString();
    }
}
