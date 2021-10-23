package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import queivan.harcmiliada.domain.Question;
import queivan.harcmiliada.domain.QuestionDto;
import queivan.harcmiliada.exceptions.QuestionDontExistException;
import queivan.harcmiliada.exceptions.QuestionExistsException;
import queivan.harcmiliada.exceptions.QuestionNotFoundException;
import queivan.harcmiliada.mapper.QuestionMapper;
import queivan.harcmiliada.service.repository.QuestionRepository;
import queivan.harcmiliada.service.sorter.PointSorter;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository repository;
    private final QuestionMapper mapper;

    public List<QuestionDto> getAll() {
        return mapper.mapToQuestionDtoList(repository.findAll());
    }

    public QuestionDto getCurrent() {
        try {
            Question fetched = repository.findCurrent().orElseThrow(() -> new QuestionNotFoundException("current"));
            fetched.getAnswers().sort(new PointSorter());
            return mapper.mapToQuestionDto(fetched);
        } catch (QuestionNotFoundException e) {
            log.error(e.getMessage());
        }
        return QuestionDto.builder().build();
    }

    public QuestionDto getById(String id) {
        try {
            UUID queryId = UUID.fromString(id);
            Question fetched = repository.findById(queryId).orElseThrow(() -> new QuestionNotFoundException(queryId.toString()));
            fetched.getAnswers().sort(new PointSorter());
            return mapper.mapToQuestionDto(fetched);
        } catch (QuestionNotFoundException e) {
            log.error(e.getMessage());
        }
        return QuestionDto.builder().build();
    }

    public QuestionDto create(QuestionDto dto) {
        try{
            isQuestionExisting(dto.getContent());
            Question entity = mapper.mapToQuestion(dto);
            Question returned = repository.save(entity);
            returned.getAnswers().forEach(answer -> answer.setQuestion(Question.builder().id(returned.getId()).build()));
            Question saved = repository.save(returned);
            return mapper.mapToQuestionDto(saved);
        }catch(QuestionExistsException e){
            log.error(e.getMessage());
        }
        return QuestionDto.builder().build();
    }

    public QuestionDto update(QuestionDto dto) {
        try{
            isQuestionExisting(dto.getId());
            Question entity = mapper.mapToQuestion(dto);
            Question fetched = repository.save(entity);
            return mapper.mapToQuestionDto(fetched);
        } catch (QuestionDontExistException e){
            log.error(e.getMessage());
        }
        return QuestionDto.builder().build();
    }

    @Transactional
    public QuestionDto setCurrent(String id) {
        try{
            UUID questionId = UUID.fromString(id);
            isQuestionNotExisting(questionId);
            repository.clearCurrent();
            Question fetched = repository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException(questionId));
            fetched.setCurrent(true);
            Question result = repository.saveAndFlush(fetched);
            return mapper.mapToQuestionDto(result);
        } catch (QuestionDontExistException e){
            log.error(e.getMessage());
        }
        return QuestionDto.builder().build();
    }

    public void delete(String id) {
        try {
            UUID questionId = UUID.fromString(id);
            isQuestionNotExisting(questionId);
            repository.deleteById(questionId);
        } catch (QuestionDontExistException e) {
            log.error(e.getMessage());
        }
    }

    private void isQuestionExisting(String content) {
        if(repository.existsByContent(content)){
            throw new QuestionExistsException(content);
        }
    }

    private void isQuestionNotExisting(UUID id) {
        if(!repository.existsById(id)){
            throw new QuestionDontExistException(id);
        }
    }

    private void isQuestionExisting(UUID id) {
        if(repository.existsById(id)){
            throw new QuestionExistsException(id);
        }
    }
}
