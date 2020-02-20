package ua.prozorro.service.nburate;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.SessionFactory;
import ua.prozorro.dataparser.nburate.NBURateParser;
import ua.prozorro.entity.mappers.nburate.NBURateDTOListToNBURateListMapper;
import ua.prozorro.entity.nburate.NBURate;
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
	private NBURateRepository nbuRateRepository;
	private NBURateParser nbuRateParser = new NBURateParser();
	private NBURateDTOListToNBURateListMapper mapper = new NBURateDTOListToNBURateListMapper();
	
	public NBURateService(SessionFactory sessionFactory) {
		this.nbuRateRepository = new NBURateRepository(sessionFactory);
	}
	
	@Override
	public List<ActionResult> saveOrUpdate(List<ContentData> data) throws IOException {
		if (data == null) {
			log.error("Empty content data list");
			return Arrays.asList(ActionResult.builder().resultType(ResultType.ERROR)
											 .resultDescription("Empty content data list").build());
		}
		
		ContentData contentData =
				data.stream().filter(content -> (content != null && !Strings.isBlank(content.getDataJSON())))
					.findFirst().orElse(null);
		if (contentData == null || ResultType.ERROR.equals(contentData.getReadResult().getResultType())) {
			log.error("Not found data for saving {}", data);
			return Arrays.asList(ActionResult.builder().resultType(ResultType.ERROR)
											 .resultDescription("Not found data for saving").build());
		}
		
		//NBURatesPageDTO nbuRatesPageDTO =
		List<NBURateDTO> nbuRateDTOList = nbuRateParser.parse(contentData.getDataJSON());
		List<NBURate> nbuRatesList = mapper.convertToEntity(nbuRateDTOList);
		
		List<ActionResult> actionResults = nbuRateRepository.saveOrUpdateAll(nbuRatesList);
		/*
		ActionResult actionResult =
				ActionResult.builder().resultType(ResultType.ADD).dataURL(contentData.getDataURL()).build();
		
		ExportResult exportResult;
		try {
			nbuRateRepository.saveOrUpdateAll(nbuRatesList);
			exportResult = ExportResult.builder().resultType(ResultType.ADD).dataURL(contentData.getDataURL()).build();
			log.info("Saved NBU Rates: {}", nbuRatesList);
		} catch (IOException e) {
			log.error("Error on save {}", data, e);
			exportResult = ExportResult.builder().resultType(ResultType.ERROR).dataURL(contentData.getDataURL())
									   .description(e.getMessage()).build();
		}
		return Arrays.asList(actionResult);
		 */
		return actionResults;
	}
}
