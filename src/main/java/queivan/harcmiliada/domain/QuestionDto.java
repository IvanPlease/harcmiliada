package queivan.harcmiliada.domain;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {
    private UUID id;
    private String content;
    private boolean current;
    private List<AnswerDto> answers;
}
