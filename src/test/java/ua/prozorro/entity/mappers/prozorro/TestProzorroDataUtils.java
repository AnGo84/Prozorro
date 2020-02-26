package ua.prozorro.entity.mappers.prozorro;

import ua.prozorro.source.ContentData;
import ua.prozorro.source.DataURL;
import ua.prozorro.source.prozorro.ProzorroPage;
import ua.prozorro.source.prozorro.ProzorroPageElement;

public class TestProzorroDataUtils {
	public static boolean isContentDataEqualProzorroPageElement(ContentData contentData,
																ProzorroPageElement prozorroPageElement) {
		if (contentData == null || prozorroPageElement == null) {
			return false;
		}
		return contentData.getId().equals(prozorroPageElement.getId()) && contentData.getDataURL().getDate().equals(
				prozorroPageElement.getDateModified());
	}
	
	public static boolean isDataURLEqualProzorroPage(DataURL dataURL, ProzorroPage prozorroPage) {
		if (dataURL == null || prozorroPage == null) {
			return false;
		}
		return dataURL.getUrl().equals(prozorroPage.getUri()) && dataURL.getDate().equals(prozorroPage.getOffset());
	}
}
