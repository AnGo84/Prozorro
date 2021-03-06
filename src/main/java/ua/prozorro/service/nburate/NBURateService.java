package ua.prozorro.service.nburate;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.SessionFactory;
import ua.prozorro.entity.mappers.nburate.NBURateDTOListToNBURateListMapper;
import ua.prozorro.entity.nburate.NBURate;
import ua.prozorro.gson.nburate.NBURateParser;
import ua.prozorro.repositories.nburate.NBURateRepository;
import ua.prozorro.service.ResultType;
import ua.prozorro.service.Service;
import ua.prozorro.source.ContentData;
import ua.prozorro.source.nburate.NBURateDTO;
import ua.prozorro.urlreader.ActionResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class NBURateService implements Service {
	public static final String NOT_FOUND_DATA_FOR_SAVING = "Not found data for saving";
	private NBURateRepository nbuRateRepository;
	private NBURateParser nbuRateParser = new NBURateParser();
	private NBURateDTOListToNBURateListMapper mapper = new NBURateDTOListToNBURateListMapper();
	
	public NBURateService(SessionFactory sessionFactory) {
		this.nbuRateRepository = new NBURateRepository(sessionFactory);
	}
	
	@Override
	public List<ActionResult> saveOrUpdate(List<ContentData> data) throws IOException {
		if (data == null) {
			log.error("Empty NBU Rates content data list");
			return Arrays.asList(ActionResult.builder().resultType(ResultType.ERROR)
											 .resultDescription("Empty NBU Rates content data list").build());
		}
		
		ContentData contentData = data.stream().filter(content -> (content != null && !Strings.isBlank(
				content.getDataJSON()))).findFirst().orElse(null);
		if (contentData == null || ResultType.ERROR.equals(contentData.getReadResult().getResultType())) {
			log.error(NOT_FOUND_DATA_FOR_SAVING + ": {}", data);
			return Arrays.asList(ActionResult.builder().resultType(ResultType.ERROR)
											 .resultDescription(NOT_FOUND_DATA_FOR_SAVING).build());
		}
		List<NBURateDTO> nbuRateDTOList = nbuRateParser.parse(contentData.getDataJSON());
		List<NBURate> nbuRatesList = mapper.convertToEntity(nbuRateDTOList);
		
		List<ActionResult> actionResults = nbuRateRepository.saveOrUpdateAll(nbuRatesList);
		
		return actionResults;
	}
}
