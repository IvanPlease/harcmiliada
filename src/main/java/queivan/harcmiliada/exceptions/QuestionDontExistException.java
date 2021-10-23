package queivan.harcmiliada.exceptions;

import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class QuestionDontExistException extends RuntimeException {
    public QuestionDontExistException(UUID id) {
        super(String.format("Pytanie o numerze id: %s nie istnieje w bazie danych", id));
    }
}