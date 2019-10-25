package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.PageElement;
import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.LotDTO;
import ua.prozorro.prozorro.model.pages.ProzorroPage;
import ua.prozorro.prozorro.model.pages.ProzorroPageElement;
import ua.prozorro.prozorro.model.tenders.Lot;

import java.util.ArrayList;
import java.util.List;

public class ProzorroPageElementListMapper extends AbstractListMapper<ProzorroPageElement, PageElement> {
    private ProzorroPageElementMapper prozorroPageElementMapper = new ProzorroPageElementMapper();
    @Override
    public List<PageElement> convertToEntitiesList(List<ProzorroPageElement> prozorroPageElements) {
        if (prozorroPageElements == null || prozorroPageElements.isEmpty()) {
            return null;
        }
        List<PageElement> pageElements = new ArrayList<>();

        for (ProzorroPageElement prozorroPageElement : prozorroPageElements) {
            PageElement pageElement = prozorroPageElementMapper.convertToEntity(prozorroPageElement);
            if (pageElement != null) {
                pageElements.add(pageElement);
            }
        }

        return pageElements;
    }

    @Override
    public List<ProzorroPageElement> convertToObjectsList(List<PageElement> entities) {
        return null;
    }
}
