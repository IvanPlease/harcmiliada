package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import queivan.harcmiliada.domain.Answer;
import queivan.harcmiliada.domain.AnswerDto;
import queivan.harcmiliada.exceptions.AnswerDontExistException;
import queivan.harcmiliada.mapper.AnswerMapper;
import queivan.harcmiliada.service.repository.AnswerRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerService {
    private final AnswerRepository repository;
    private final AnswerMapper mapper;

    public AnswerDto toggleChecked(String id) {
        try{
            UUID answerId = UUID.fromString(id);
            isAnswerNotExisting(answerId);
            Answer fetched = repository.findById(answerId).orElseThrow(() -> new AnswerDontExistException(answerId));
            fetched.toggleChecked();
            Answer saved = repository.save(fetched);
            return mapper.mapToAnswerDto(saved);
        } catch (AnswerDontExistException e) {
            log.error(e.getMessage());
        }
        return AnswerDto.builder().build();
    }

    private void isAnswerNotExisting(UUID id) {
        if(!repository.existsById(id)){
            throw new AnswerDontExistException(id);
        }
    }
}
