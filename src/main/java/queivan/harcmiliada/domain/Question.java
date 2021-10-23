package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "_questions")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @Id
    @NotNull
    @GeneratedValue
    private UUID id;
    @NotNull
    private String content;
    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.PERSIST
    )
    private List<Answer> answers;
}
