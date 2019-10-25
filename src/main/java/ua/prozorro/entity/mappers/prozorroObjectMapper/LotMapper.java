package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.LotDTO;
import ua.prozorro.prozorro.model.tenders.Lot;

public class LotMapper extends AbstractMapper<Lot, LotDTO> {

    @Override
    public LotDTO convertToEntity(Lot lot) {
        if (lot == null) {
            return null;
        }
        LotDTO lotDTO = new LotDTO();

        lotDTO.setId(lot.getId());
        if (lot.getTitle().length() <= 4000) {
            lotDTO.setTitle(lot.getTitle());
        } else {
            lotDTO.setTitle(lot.getTitle().substring(0, 4000));
        }
        lotDTO.setDescription(lot.getDescription());
        if (lot.getValue() != null) {
            lotDTO.setCurrency(lot.getValue().getCurrency());
            lotDTO.setAmount(lot.getValue().getAmount());
            lotDTO.setAddedTaxIncluded(lot.getValue().getValueAddedTaxIncluded());
        }
        if (lot.getGuarantee() != null) {
            lotDTO.setGuaranteeCurrency(lot.getGuarantee().getCurrency());
            lotDTO.setGuaranteeAmount(lot.getGuarantee().getAmount());
        }
        lotDTO.setDate(lot.getDate());
        if (lot.getMinimalStep() != null) {
            lotDTO.setMinimalStepAmount(lot.getMinimalStep().getAmount());
            lotDTO.setMinimalStepCurrency(lot.getMinimalStep().getCurrency());
            lotDTO.setMinimalStepValueAddedTaxIncluded(lot.getMinimalStep().getValueAddedTaxIncluded());
        }
        if (lot.getAuctionPeriod() != null) {
            lotDTO.setAuctionPeriodStartDate(lot.getAuctionPeriod().getStartDate());
            lotDTO.setAuctionPeriodClarificationsUntil(lot.getAuctionPeriod().getClarificationsUntil());
            lotDTO.setAuctionPeriodEndDate(lot.getAuctionPeriod().getEndDate());
            lotDTO.setAuctionPeriodInvalidationDate(lot.getAuctionPeriod().getInvalidationDate());
        }
        lotDTO.setAuctionUrl(lot.getAuctionUrl());
        lotDTO.setStatus(lot.getStatus());

        return lotDTO;
    }

    @Override
    public Lot convertToObject(LotDTO entity) {
        return null;
    }
}
