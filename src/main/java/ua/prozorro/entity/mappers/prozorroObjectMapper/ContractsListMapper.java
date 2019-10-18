package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.ContractDTO;
import ua.prozorro.prozorro.model.tenders.Contract;

import java.util.ArrayList;
import java.util.List;

public class ContractsListMapper extends AbstractListMapper<Contract, ContractDTO> {
    private TenderContractMapper contractMapper = new TenderContractMapper();

    @Override
    public List<ContractDTO> convertToEntitiesList(List<Contract> contractList) {
        if (contractList == null || contractList.isEmpty()) {
            return null;
        }

        List<ContractDTO> contractDTOList = new ArrayList<>();

        for (Contract contract : contractList) {
            ContractDTO contractDTO = contractMapper.convertToEntity(contract);
            if (contractDTO != null) {
                contractDTOList.add(contractDTO);
            }
        }

        return contractDTOList;
    }

    @Override
    public List<Contract> convertToObjectsList(List<ContractDTO> entities) {
        return null;
    }
}
