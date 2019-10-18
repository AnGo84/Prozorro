package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractListMapper;
import ua.prozorro.entity.tenders.QuestionDTO;
import ua.prozorro.prozorro.model.tenders.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListMapper extends AbstractListMapper<Question, QuestionDTO> {
    private QuestionMapper questionMapper = new QuestionMapper();

    @Override
    public List<QuestionDTO> convertToEntitiesList(List<Question> questionList) {
        if (questionList == null || questionList.isEmpty()) {
            return null;
        }
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            QuestionDTO questionDTO = questionMapper.convertToEntity(question);
            if (questionDTO != null) {
                questionDTOList.add(questionDTO);
            }
        }

        return questionDTOList;
    }

    @Override
    public List<Question> convertToObjectsList(List<QuestionDTO> entities) {
        return null;
    }
}
