package ua.prozorro.urlreader.prozorro;

import lombok.extern.log4j.Log4j2;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.prozorro.ProzorroPage;
import ua.prozorro.source.prozorro.ProzorroPageContent;

import java.io.IOException;

@Deprecated
@Log4j2
public class ProzorroContentReader extends AbstractProzorroReader<ProzorroPageContent> {
    public ProzorroContentReader(Class<? extends ProzorroPageContent> theClass, SourceLink sourceLink) {
        super(theClass, sourceLink);
    }

    public ProzorroPageContent readPage(ProzorroPage currentPage) throws IOException {
        ProzorroPageContent prozorroPageContent = getObjectFromURL(currentPage.getUri());
        prozorroPageContent.setCurrentPage(currentPage);
        return prozorroPageContent;
    }

}
