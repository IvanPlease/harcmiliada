package queivan.harcmiliada.exceptions;

import java.util.UUID;

@SuppressWarnings("SpellCheckingInspection")
public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(String id){
        super(String.format("Pytanie o numerze id: %s nie istnieje w bazie danych", id));
    }

    public QuestionNotFoundException(UUID questionId) {
        super(String.format("Pytanie o numerze id: %s nie istnieje w bazie danych", questionId));
    }
}
