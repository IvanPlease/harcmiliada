package queivan.harcmiliada.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.harcmiliada.domain.QuestionDto;
import queivan.harcmiliada.service.QuestionService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionFacade {
    private final QuestionService service;

    public List<QuestionDto> getAll() {
        return service.getAll();
    }

    public QuestionDto getById(String id) {
        return service.getById(id);
    }

    public QuestionDto create(QuestionDto dto) {
        return service.create(dto);
    }

    public QuestionDto update(QuestionDto dto) {
        return service.update(dto);
    }

    public QuestionDto getCurrent() {
        return service.getCurrent();
    }

    public QuestionDto setCurrent(String id) {
        return service.setCurrent(id);
    }

    public void delete(String id) {
        service.delete(id);
    }
}
