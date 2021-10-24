package queivan.harcmiliada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.harcmiliada.domain.AnswerDto;
import queivan.harcmiliada.facade.AnswerFacade;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/questions/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerFacade facade;

    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public AnswerDto toggleChecked(@PathVariable String id){
        return facade.toggleChecked(id);
    }

}
