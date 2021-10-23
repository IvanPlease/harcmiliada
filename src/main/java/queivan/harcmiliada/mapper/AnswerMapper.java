package queivan.harcmiliada.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.harcmiliada.domain.Answer;
import queivan.harcmiliada.domain.AnswerDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    AnswerDto mapToAnswerDto(Answer saved);
}
