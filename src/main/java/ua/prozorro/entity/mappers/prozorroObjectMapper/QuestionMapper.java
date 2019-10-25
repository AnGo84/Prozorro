package ua.prozorro.entity.mappers.prozorroObjectMapper;

import ua.prozorro.entity.mappers.AbstractMapper;
import ua.prozorro.entity.tenders.QuestionDTO;
import ua.prozorro.prozorro.model.tenders.Question;

public class QuestionMapper extends AbstractMapper<Question, QuestionDTO> {
    private OrganizationMapper organizationMapper = new OrganizationMapper();

    @Override
    public QuestionDTO convertToEntity(Question question) {
        if (question == null) {
            return null;
        }
        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setId(question.getId());
        questionDTO.setAuthor(organizationMapper.convertToEntity(question.getAuthor()));
        //questionDTO.setTitle(question.getTitle());
        questionDTO.setTitle(question.getTitle() == null ? question.getTitle() :
                question.getTitle().substring(0, Math.min(question.getTitle().length(), 4000)));

        questionDTO.setDescription(question.getDescription());
        questionDTO.setDate(question.getDate());
        questionDTO.setDateAnswered(question.getDateAnswered());
        questionDTO.setAnswer(question.getAnswer());
        questionDTO.setQuestionOf(question.getQuestionOf());
        questionDTO.setRelatedItem(question.getRelatedItem());

        return questionDTO;
    }

    @Override
    public Question convertToObject(QuestionDTO entity) {
        return null;
    }
}
