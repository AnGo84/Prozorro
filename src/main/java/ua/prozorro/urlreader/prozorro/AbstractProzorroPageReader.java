package ua.prozorro.urlreader.prozorro;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ua.prozorro.entity.mappers.prozorro.ProzorroPageContentToDataPageMapper;
import ua.prozorro.entity.mappers.prozorro.ProzorroPageToDataURLMapper;
import ua.prozorro.source.DataPage;
import ua.prozorro.source.DataURL;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.prozorro.ProzorroPage;
import ua.prozorro.source.prozorro.ProzorroPageContent;
import ua.prozorro.urlreader.AbstractPageReader;

import java.io.IOException;

@Log4j2
@Getter
public abstract class AbstractProzorroPageReader extends AbstractPageReader {
    private ProzorroPageToDataURLMapper prozorroPageToDataURLMapper = new ProzorroPageToDataURLMapper();
    private ProzorroPageContentToDataPageMapper prozorroPageContentToDataPageMapper =
            new ProzorroPageContentToDataPageMapper();
    private Gson gson = new Gson();

    public AbstractProzorroPageReader(SourceLink sourceLink, DataPage dataPage) {
        super(sourceLink, dataPage);
    }

    public void updateDataPageTypes() {
        dataPage.getCurrentDataURL().setType(sourceLink.getType());
        dataPage.getCurrentDataURL().setDateFormat(sourceLink.getPageDateFormat());
        dataPage.getPrevDataURL().setType(sourceLink.getType());
        dataPage.getPrevDataURL().setDateFormat(sourceLink.getPageDateFormat());
        dataPage.getNextDataURL().setType(sourceLink.getType());
        dataPage.getNextDataURL().setDateFormat(sourceLink.getPageDateFormat());
        if (pageHasContent()) {
            dataPage.getPageContentData().parallelStream().forEach(contentData -> {
                contentData.getDataURL().setType(sourceLink.getType());
                contentData.getDataURL().setDateFormat(sourceLink.getPageDateFormat());
                contentData.getDataURL().setUrl(sourceLink.getStartPage() + "/" + contentData.getId());
            });
        }
    }

    public DataPage readProzorroPage(DataURL dataURL) throws IOException {
        ProzorroPage currentProzorroPage = prozorroPageToDataURLMapper.convertToObject(dataURL);
        log.info("Read: {}", currentProzorroPage);
        ProzorroPageContent pageContent = readPage(currentProzorroPage);
        return prozorroPageContentToDataPageMapper.convertToEntity(pageContent);
    }

    public ProzorroPageContent readPage(ProzorroPage currentPage) throws IOException {
        String stringJSON = urlSourceReader.read(currentPage.getUri());
        ProzorroPageContent prozorroPageContent = gson.fromJson(stringJSON, ProzorroPageContent.class);
        prozorroPageContent.setCurrentPage(currentPage);
        return prozorroPageContent;
    }
}
