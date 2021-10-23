package queivan.harcmiliada.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import queivan.harcmiliada.domain.Question;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    boolean existsByContent(String content);
    @Query(nativeQuery = true, value = "SELECT * FROM _questions WHERE _questions.current = true")
    Question findCurrent();
}
