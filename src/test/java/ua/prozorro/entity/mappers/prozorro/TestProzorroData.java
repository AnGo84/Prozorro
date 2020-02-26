package ua.prozorro.entity.mappers.prozorro;

import ua.prozorro.source.ContentData;
import ua.prozorro.source.DataPage;
import ua.prozorro.source.DataURL;
import ua.prozorro.source.prozorro.ProzorroPage;
import ua.prozorro.source.prozorro.ProzorroPageContent;
import ua.prozorro.source.prozorro.ProzorroPageElement;

import java.util.Arrays;

public class TestProzorroData {
	
	public static DataURL testDataURL() {
		DataURL dataURL = DataURL.builder().url(
				"https://public.api.openprocurement.org/api/2.5/tenders?offset=2019-06-20T00%3A02%3A43.300277%2B03%3A00")
								 .date("2019-06-20T00:02:43.300277+03:00").build();
		return dataURL;
	}
	
	
	public static ProzorroPage testProzorroPage() {
		ProzorroPage prozorroPage = ProzorroPage.builder().uri(
				"https://public.api.openprocurement.org/api/2.5/tenders?offset=2019-06-20T00%3A02%3A43.300277%2B03%3A00")
												.path("/api/2.5/tenders?offset=2019-06-20T00%3A02%3A43.300277%2B03%3A00")
												.offset("2019-06-20T00:02:43.300277+03:00").build();
		return prozorroPage;
	}
	
	public static ProzorroPageElement testTenderProzorroPageElement() {
		ProzorroPageElement prozorroPageElement = new ProzorroPageElement();
		prozorroPageElement.setId("5300942d18ff46d1ae9a62f0847a0125");
		prozorroPageElement.setDateModified("2019-06-19T21:42:50.864694+03:00");
		return prozorroPageElement;
	}
	
	public static ProzorroPageElement testTenderProzorroPageElementWithWrongDate() {
		ProzorroPageElement prozorroPageElement = new ProzorroPageElement();
		prozorroPageElement.setId("5300942d18ff46d1ae9a62f0847a0125");
		prozorroPageElement.setDateModified("2019-06-19T21:42:50+03:00");
		return prozorroPageElement;
	}
	
	public static ProzorroPageContent testTenderProzorroPageContent() {
		ProzorroPageContent prozorroPageContent = new ProzorroPageContent();
		
		ProzorroPage prozorroPage = ProzorroPage.builder().uri(
				"https://public.api.openprocurement.org/api/2.5/tenders?offset=2019-06-19T21%3A00%3A03.340891%2B03%3A00")
												.offset("2019-06-19T21:00:03.340891+03:00").build();
		prozorroPageContent.setCurrentPage(prozorroPage);
		prozorroPage = ProzorroPage.builder().uri(
				"https://public.api.openprocurement.org/api/2.5/tenders?offset=2019-06-20T00%3A02%3A43.300277%2B03%3A00")
								   .path("/api/2.5/tenders?offset=2019-06-20T00%3A02%3A43.300277%2B03%3A00").offset(
						"2019-06-20T00:02:43.300277+03:00").build();
		prozorroPageContent.setNextPage(prozorroPage);
		prozorroPage = ProzorroPage.builder().uri(
				"https://public.api.openprocurement.org/api/2.5/tenders?descending=1&offset=2019-06-19T21%3A00%3A03.340891%2B03%3A00")
								   .path("/api/2.5/tenders?descending=1&offset=2019-06-19T21%3A00%3A03.340891%2B03%3A00")
								   .offset("2019-06-19T21:00:03.340891+03:00").build();
		prozorroPageContent.setPrevPage(prozorroPage);
		
		ProzorroPageElement prozorroPageElement = new ProzorroPageElement();
		prozorroPageElement.setId("5300942d18ff46d1ae9a62f0847a0125");
		prozorroPageElement.setDateModified("2019-06-19T21:00:03.340891+03:00");
		
		prozorroPageContent.setPageElementList(Arrays.asList(prozorroPageElement));
		
		return prozorroPageContent;
	}
	
	public static DataPage testTenderDataPage() {
		DataURL currentDataURL = DataURL.builder().date("2019-06-19T21:00:03.340891+03:00").url(
				"https://public.api.openprocurement.org/api/2.5/tenders?offset=2019-06-19T21%3A00%3A03.340891%2B03%3A00")
										.build();
		DataURL nextDataURL = DataURL.builder().date("2019-06-20T00:02:43.300277+03:00").url(
				"https://public.api.openprocurement.org/api/2.5/tenders?offset=2019-06-20T00%3A02%3A43.300277%2B03%3A00")
									 .build();
		DataURL prevDataURL = DataURL.builder().date("2019-06-19T21:00:03.340891+03:00").url(
				"https://public.api.openprocurement.org/api/2.5/tenders?descending=1&offset=2019-06-19T21%3A00%3A03.340891%2B03%3A00")
									 .build();
		
		ContentData contentData = new ContentData();
		
		contentData.setId("5300942d18ff46d1ae9a62f0847a0125");
		DataURL dataURL = DataURL.builder().url("5300942d18ff46d1ae9a62f0847a0125").date(
				"2019-06-19T21:00:03.340891+03:00").build();
		contentData.setDataURL(dataURL);
		DataPage dataPage = DataPage.builder().currentDataURL(currentDataURL).nextDataURL(nextDataURL).prevDataURL(
				prevDataURL).pageContentData(Arrays.asList(contentData)).build();
		
		return dataPage;
	}
	
	
}
