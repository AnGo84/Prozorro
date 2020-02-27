package ua.prozorro.urlreader.nburate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.prozorro.source.DataPage;
import ua.prozorro.source.SourceLink;
import ua.prozorro.source.SourceLinkFactory;
import ua.prozorro.source.SourceType;
import ua.prozorro.temp.TestPropertyFieldsFactory;
import ua.prozorro.utils.DateUtils;
import ua.prozorro.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileUtils.class})
class NBURatePageReaderTest {
    private NBURatePageReader nbuRatePageReader;
    private SourceLink sourceLink =
            SourceLinkFactory.getSourceLink(SourceType.NBU_RATE, TestPropertyFieldsFactory.getStartProperties());


    @Test
    public void readDataPage() throws IOException, ParseException {
        File inputFile = new File(this.getClass().getResource("/test_data/NBURates_2020.02.20.txt").getFile());
        System.out.println(inputFile.getAbsolutePath());
        String fileContent = FileUtils.readFile(inputFile);
        //System.out.println(fileContent);
        Date date = DateUtils.parseDateFromString("20.02.2020", "dd.MM.yyyy");
        String dateTxt = DateUtils.parseDateToString(date, sourceLink.getPageDateFormat());
        DataPage dataPage = DataPage.builder().currentDataURL(sourceLink.getDataURL(sourceLink, dateTxt)).build();
        nbuRatePageReader = new NBURatePageReader(sourceLink, dataPage);
        nbuRatePageReader.readDataPage();


        mockStatic(FileUtils.class);
        when(FileUtils.getStringFromURL(anyString())).thenReturn("5");
        nbuRatePageReader.readPageContent();

        System.out.println(nbuRatePageReader.getDataPage().getPageContentData());

    }

    @Test
    public void readPageContent() {
    }

    @Test
    public void nextPage() {
    }
}