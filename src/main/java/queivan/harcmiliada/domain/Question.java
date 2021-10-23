package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.*;

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
            cascade = CascadeType.ALL
    )
    private List<Answer> answers;
    @NotNull
    @Builder.Default
    private boolean current = false;

    public void setCurrent(boolean current) {
        this.current = current;
    }

}
