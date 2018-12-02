package ua.prozorro.temp;

import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import ua.prozorro.Prozorro;
import ua.prozorro.model.pages.PageContent;
import ua.prozorro.model.pages.PageContentURL;
import ua.prozorro.model.pages.PageElement;
import ua.prozorro.model.tenders.TenderData;
import ua.prozorro.prozorro.PageService;
import ua.prozorro.prozorro.TenderDataService;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;


public class Test {

    public static void main(String[] args) {
        String url = "https://public.api.openprocurement.org/api/2.4/tenders";
        String url2 =
                "https://public.api.openprocurement.org/api/2.4/tenders?offset=2018-11-30T21%3A00%3A03" +
                ".340891%2B03%3A00";
        //"https://public.api.openprocurement.org/api/2.4/tenders?descending=1&offset=2018-11-30T21%3A00%3A03.340891%2B03%3A00";

        //JSONParser(url);
        GSONPareser(url2);

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
            TenderDataService tenderDataService = new TenderDataService();

            System.out.println("Read from URL: " + getUrl);
            //String genreJson = getStringFromURL(getUrl);

            long startTime = System.nanoTime();
            long startTime2 = startTime;

            long endTime;
            PageContent pageContent = pageService.getPageContentFromURL(getUrl);
            System.out.println("Read from URL: " + getUrl);
            int pageCount=0;
            int tenderCount=0;
            while (pageContent.getPageElementList() != null && !pageContent.getPageElementList().isEmpty()&& pageCount<10) {
                pageCount++;
                System.out.println("Read from URL: " + getUrl);

                for (PageElement element : pageContent.getPageElementList()) {
                    tenderCount++;
                    String tenderURL = "https://public.api.openprocurement.org/api/2.4/tenders/" + element.getId();
                    System.out.println("Get Tender URL: "+ tenderURL);
                    TenderData tenderData = tenderDataService.getPageContentFromURL(tenderURL);
                    System.out.println("Tender "+tenderCount+": " + tenderData);
                }
                endTime = System.nanoTime();

                System.out.println("Time: " + (endTime - startTime)/1000000);

                startTime = System.nanoTime();

                pageContent = pageService.getPageContentFromURL(getUrl);
                getUrl = pageContent.getNextPage().getUri();
                System.out.println("Read from URL: " + getUrl);
            }
            System.out.println(new Date() + ": Total page count: " + pageContent + " with tenders count: " + tenderCount);
            System.out.println("Total time: " + ((startTime2-System.nanoTime())/1000000000)+ " seconds");
            /*System.out.println(pageContent);

            System.out.println("Read from next URL: " + pageContent.getNextPage().getUri());
            //genreJson =  IOUtils.toString(new URL(pageContent.getNextPage().getUri()), Charset.forName("UTF-8"));

            pageContent = pageService.getPageContentFromURL(pageContent.getNextPage().getUri());

            System.out.println(pageContent);

            //

            //getUrl = url +"/" +  pageContent.getPageElementList().get(0).getId();
            getUrl = "https://public.api.openprocurement.org/api/2.4/tenders/8689aed656e34ece8420559e50edaacb";
            System.out.println("Get Tender URL: " + getUrl);
            TenderData tenderData = tenderDataService.getPageContentFromURL(getUrl);
            System.out.println("Tender:");
            System.out.println(tenderData);*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getStringFromURL(String url) throws IOException {
        return IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
    }


}
