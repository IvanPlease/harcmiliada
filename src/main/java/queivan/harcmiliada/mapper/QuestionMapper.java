package queivan.harcmiliada.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.harcmiliada.domain.Question;
import queivan.harcmiliada.domain.QuestionDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    List<QuestionDto> mapToQuestionDtoList(List<Question> all);
    QuestionDto mapToQuestionDto(Question fetched);
    Question mapToQuestion(QuestionDto dto);
}
