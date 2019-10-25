package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.DocumentDTO;
import ua.prozorro.prozorro.model.tenders.Document;

public class DocumentMapper extends AbstractMapper<Document, DocumentDTO> {
    @Override
    public DocumentDTO convertToEntity(Document document) {

        if (document == null) {
            return null;
        }
        DocumentDTO documentDTO = new DocumentDTO();

        documentDTO.setId(document.getId());
        documentDTO.setHash(document.getHash());
        documentDTO.setDescription(document.getDescription());
        documentDTO.setTitle(document.getTitle());
        documentDTO.setTitleRu(document.getTitleRu());
        documentDTO.setTitleEn(document.getTitleEn());
        documentDTO.setUrl(document.getUrl());
        documentDTO.setFormat(document.getFormat());
        documentDTO.setDocumentOf(document.getDocumentOf());
        documentDTO.setDatePublished(document.getDatePublished());
        documentDTO.setAuthor(document.getAuthor());
        documentDTO.setDocumentType(document.getDocumentType());
        documentDTO.setDateModified(document.getDateModified());

        return documentDTO;
    }

    @Override
    public Document convertToObject(DocumentDTO entity) {
        return null;
    }
}
