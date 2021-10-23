package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import queivan.harcmiliada.domain.Answer;
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
    private final QuestionRepository qRepository;
    private final QuestionMapper qMapper;

    public List<QuestionDto> getAll() {
        return qMapper.mapToQuestionDtoList(qRepository.findAll());
    }

    public QuestionDto getCurrent() {
        try {
            Question fetched = qRepository.findCurrent().orElseThrow(() -> new QuestionNotFoundException("current"));
            fetched.getAnswers().sort(new PointSorter());
            return qMapper.mapToQuestionDto(fetched);
        } catch (QuestionNotFoundException e) {
            log.error(e.getMessage());
        }
        return QuestionDto.builder().build();
    }

    public QuestionDto getById(String id) {
        try {
            UUID queryId = UUID.fromString(id);
            Question fetched = qRepository.findById(queryId).orElseThrow(() -> new QuestionNotFoundException(queryId.toString()));
            fetched.getAnswers().sort(new PointSorter());
            return qMapper.mapToQuestionDto(fetched);
        } catch (QuestionNotFoundException e) {
            log.error(e.getMessage());
        }
        return QuestionDto.builder().build();
    }

    public QuestionDto create(QuestionDto dto) {
        try{
            isQuestionExisting(dto.getContent());
            Question entity = qMapper.mapToQuestion(dto);
            Question returned = qRepository.save(entity);
            returned.getAnswers().forEach(answer -> answer.setQuestion(Question.builder().id(returned.getId()).build()));
            Question saved = qRepository.save(returned);
            return qMapper.mapToQuestionDto(saved);
        }catch(QuestionExistsException e){
            log.error(e.getMessage());
        }
        return QuestionDto.builder().build();
    }

    public QuestionDto update(QuestionDto dto) {
        try{
            isQuestionExisting(dto.getId());
            Question entity = qMapper.mapToQuestion(dto);
            Question fetched = qRepository.save(entity);
            return qMapper.mapToQuestionDto(fetched);
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
            qRepository.clearCurrent();
            Question fetched = qRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException(questionId));
            fetched.setCurrent(true);
            Question result = qRepository.saveAndFlush(fetched);
            return qMapper.mapToQuestionDto(result);
        } catch (QuestionDontExistException e){
            log.error(e.getMessage());
        }
        return QuestionDto.builder().build();
    }

    private void isQuestionExisting(String content) {
        if(qRepository.existsByContent(content)){
            throw new QuestionExistsException(content);
        }
    }

    private void isQuestionNotExisting(UUID id) {
        if(!qRepository.existsById(id)){
            throw new QuestionDontExistException(id);
        }
    }

    private void isQuestionExisting(UUID id) {
        if(qRepository.existsById(id)){
            throw new QuestionExistsException(id);
        }
    }
}
