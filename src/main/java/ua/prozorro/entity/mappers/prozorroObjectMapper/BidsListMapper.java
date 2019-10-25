package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.BidDTO;
import ua.prozorro.prozorro.model.tenders.Bid;

import java.util.ArrayList;
import java.util.List;

public class BidsListMapper extends AbstractListMapper<Bid, BidDTO> {
    private BidMapper bidMapper = new BidMapper();
    @Override
    public List<BidDTO> convertToEntitiesList(List<Bid> bidList) {
        if (bidList == null || bidList.isEmpty()) {
            return null;
        }
        List<BidDTO> bidDTOList = new ArrayList<>();

        for (Bid bid : bidList) {
            BidDTO bidDTO = bidMapper.convertToEntity(bid);
            if (bidDTO != null) {
                bidDTOList.add(bidDTO);
            }
        }

        return bidDTOList;
    }

    @Override
    public List<Bid> convertToObjectsList(List<BidDTO> entities) {
        return null;
    }
}
