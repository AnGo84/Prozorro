package ua.prozorro.temp;

import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import ua.prozorro.Prozorro;
import ua.prozorro.model.pages.PageContent;
import ua.prozorro.model.pages.PageContentURL;
import ua.prozorro.model.tenders.TenderData;
import ua.prozorro.prozorro.PageService;
import ua.prozorro.prozorro.TenderDataService;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;


public class Test {

	public static void main(String[] args) {
		String url = "https://public.api.openprocurement.org/api/2.4/tenders";

		//JSONPareser(url);
		GSONPareser(url);

	}

	private static void JSONPareser(String url) {
		try {
			String genreJson = getStringFromURL(url);
			//JSONObject genreJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
			PageContentURL pageContent = Prozorro.getPageContent(new URL(url), genreJson);
			System.out.println(pageContent);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static void GSONPareser(String url) {
		try {
			String getUrl = url;
			PageService pageService = new PageService();
			System.out.println("Read from URL: " + getUrl);
			//String genreJson = getStringFromURL(getUrl);

			PageContent pageContent =  pageService.getPageContentFromURL(getUrl);
			System.out.println(pageContent);

			System.out.println("Read from next URL: " + pageContent.getNextPage().getUri());
			//genreJson =  IOUtils.toString(new URL(pageContent.getNextPage().getUri()), Charset.forName("UTF-8"));

			pageContent = pageService.getPageContentFromURL(pageContent.getNextPage().getUri());

			System.out.println(pageContent);

			//
			TenderDataService tenderDataService = new TenderDataService();
			//getUrl = url +"/" +  pageContent.getPageElementList().get(0).getId();
			getUrl = "https://public.api.openprocurement.org/api/2.4/tenders/8689aed656e34ece8420559e50edaacb";
			System.out.println("Get Tender URL: " + getUrl);
			TenderData tenderData = tenderDataService.getPageContentFromURL(getUrl);
			System.out.println("Tender:");
			System.out.println(tenderData);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getStringFromURL(String url) throws IOException {
		return IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
	}


}
