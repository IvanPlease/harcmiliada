package queivan.harcmiliada.exceptions;

import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class QuestionExistsException extends RuntimeException {
    public QuestionExistsException(String content){
        super(String.format("Pytanie o treści: %s istnieje w bazie danych", content));
    }
    public QuestionExistsException(UUID id){
        super(String.format("Pytanie o numerze id: %s istnieje w bazie danych", id));
    }
}
