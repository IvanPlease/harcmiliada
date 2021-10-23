package queivan.harcmiliada.exceptions;

@SuppressWarnings("SpellCheckingInspection")
public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(String id){
        super(String.format("Pytanie o numerze id: %s nie istnieje w bazie danych", id));
    }
}
