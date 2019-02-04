package ua.prozorro.prozorro.model.pages;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProzorroPageContent {
	
	@SerializedName("next_page")
	private ProzorroPage nextPage;
	
	@SerializedName("data")
	private List<ProzorroPageElement> pageElementList;
	
	@SerializedName("prev_page")
	private ProzorroPage prevPage;
	
	public ProzorroPage getNextPage() {
		return nextPage;
	}
	
	public void setNextPage(ProzorroPage nextPage) {
		this.nextPage = nextPage;
	}
	
	public List<ProzorroPageElement> getPageElementList() {
		return pageElementList;
	}
	
	public void setPageElementList(List<ProzorroPageElement> pageElementList) {
		this.pageElementList = pageElementList;
	}
	
	public ProzorroPage getPrevPage() {
		return prevPage;
	}
	
	public void setPrevPage(ProzorroPage prevPage) {
		this.prevPage = prevPage;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ProzorroPageContent{");
		sb.append("nextPage=").append(nextPage);
		sb.append(", pageElementList=").append(pageElementList);
		sb.append(", prevPage=").append(prevPage);
		sb.append('}');
		return sb.toString();
	}
}
