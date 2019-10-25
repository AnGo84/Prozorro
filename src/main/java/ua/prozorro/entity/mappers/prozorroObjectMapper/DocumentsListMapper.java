package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.DocumentDTO;
import ua.prozorro.prozorro.model.tenders.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentsListMapper extends AbstractListMapper<Document, DocumentDTO> {
    private DocumentMapper documentMapper = new DocumentMapper();

    @Override
    public List<DocumentDTO> convertToEntitiesList(List<Document> documentList) {
        if (documentList == null || documentList.isEmpty()) {
            return null;
        }
        Map<String, Document> documentsMap = new HashMap<>();
        for (Document document : documentList) {
            documentsMap.put(document.getId(), document);
        }

        List<DocumentDTO> documentDTOList = new ArrayList<>();

        for (Map.Entry<String, Document> entry : documentsMap.entrySet()) {
            DocumentDTO documentDTO = documentMapper.convertToEntity(entry.getValue());
            if (documentDTO != null) {
                documentDTOList.add(documentDTO);
            }
        }
        /*
        List<DocumentDTO> documentDTOList = new ArrayList<>();
        for (Document document : documentList) {
            DocumentDTO documentDTO = getDocumentDTO(document);
            documentDTOList.add(documentDTO);
        }*/

        return documentDTOList;
    }

    @Override
    public List<Document> convertToObjectsList(List<DocumentDTO> entities) {
        return null;
    }
}
