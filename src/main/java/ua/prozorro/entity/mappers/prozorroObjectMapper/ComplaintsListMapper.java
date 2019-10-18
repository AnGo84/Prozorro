package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.ComplaintDTO;
import ua.prozorro.prozorro.model.tenders.Complaint;

import java.util.ArrayList;
import java.util.List;

public class ComplaintsListMapper extends AbstractListMapper<Complaint, ComplaintDTO> {
    private ComplaintMapper complaintMapper = new ComplaintMapper();
    @Override
    public List<ComplaintDTO> convertToEntitiesList(List<Complaint> complaintList) {
        if (complaintList == null || complaintList.isEmpty()) {
            return null;
        }
        List<ComplaintDTO> complaintDTOList = new ArrayList<>();

        for (Complaint complaint : complaintList) {
            ComplaintDTO complaintDTO = complaintMapper.convertToEntity(complaint);
            if (complaintDTO != null) {
                complaintDTOList.add(complaintDTO);
            }
        }

        return complaintDTOList;
    }

    @Override
    public List<Complaint> convertToObjectsList(List<ComplaintDTO> entities) {
        return null;
    }
}
