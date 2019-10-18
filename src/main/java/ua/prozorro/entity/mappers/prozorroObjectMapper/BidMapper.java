package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.BidDTO;
import ua.prozorro.prozorro.model.tenders.Bid;

public class BidMapper extends AbstractMapper<Bid, BidDTO> {
    private DocumentsListMapper documentsListMapper = new DocumentsListMapper();
    private OrganizationsListMapper organizationsListMapper = new OrganizationsListMapper();

    @Override
    public BidDTO convertToEntity(Bid bid) {
        if (bid == null) {
            return null;
        }
        BidDTO bidDTO = new BidDTO();

        bidDTO.setId(bid.getId());
        bidDTO.setStatus(bid.getStatus());
        bidDTO.setDate(bid.getDate());
        bidDTO.setParticipationUrl(bid.getParticipationUrl());
        if (bid.getValue() != null) {
            bidDTO.setCurrency(bid.getValue().getCurrency());
            bidDTO.setAmount(bid.getValue().getAmount());
            bidDTO.setValueAddedTaxIncluded(bid.getValue().getValueAddedTaxIncluded());
        }
        bidDTO.setSelfEligible(bid.isSelfEligible());
        bidDTO.setSelfQualified(bid.isSelfQualified());
        bidDTO.setDocuments(documentsListMapper.convertToEntitiesList(bid.getDocuments()));
        bidDTO.setTenderers(organizationsListMapper.convertToEntitiesList(bid.getTenderers()));


        return bidDTO;
    }

    @Override
    public Bid convertToObject(BidDTO entity) {
        return null;
    }
}
