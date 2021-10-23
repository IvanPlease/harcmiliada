package queivan.harcmiliada.exceptions;

import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class AnswerDontExistException extends RuntimeException {
    public AnswerDontExistException(UUID id) {
        super(String.format("Pytanie o numerze id: %s nie istnieje w bazie danych", id));
    }
}