package queivan.harcmiliada.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.harcmiliada.domain.AnswerDto;
import queivan.harcmiliada.service.AnswerService;

@Component
@RequiredArgsConstructor
public class AnswerFacade {
    private final AnswerService service;

    public AnswerDto toggleChecked(String id) {
        return service.toggleChecked(id);
    }
}
