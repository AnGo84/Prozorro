package ua.prozorro.service.prozorro;

import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import ua.prozorro.entity.mappers.prozorro.ContentDataToContractJSONMapper;
import ua.prozorro.entity.prozorro.ContractJSON;
import ua.prozorro.repositories.prozorro.ContractJSONRepository;
import ua.prozorro.service.ResultType;
import ua.prozorro.service.Service;
import ua.prozorro.source.ContentData;
import ua.prozorro.urlreader.ActionResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class ContractService implements Service {
	private ContractJSONRepository contractJSONRepository;
	private ContentDataToContractJSONMapper contentDataToContractJSONMapper = new ContentDataToContractJSONMapper();
	
	public ContractService(SessionFactory sessionFactory) {
		this.contractJSONRepository = new ContractJSONRepository(sessionFactory);
	}
	
	@Override
	public List<ActionResult> saveOrUpdate(List<ContentData> data) throws IOException {
		if (data == null) {
			log.error("Empty Contracts content data list");
			return Arrays.asList(ActionResult.builder().resultType(ResultType.ERROR)
											 .resultDescription("Empty Contracts content data list").build());
		}
		
		List<ContractJSON> contractList =
				data.parallelStream().map(contentData -> contentDataToContractJSONMapper.convertToEntity(contentData))
					.collect(Collectors.toList());
		
		List<ActionResult> actionResults = contractJSONRepository.saveOrUpdateAll(contractList);
		
		return actionResults;
	}
	
}
