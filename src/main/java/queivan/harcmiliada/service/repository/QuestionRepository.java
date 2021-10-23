package queivan.harcmiliada.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.harcmiliada.domain.Question;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    boolean existsByContent(String content);
    Question findByCurrent(Boolean current);
}
