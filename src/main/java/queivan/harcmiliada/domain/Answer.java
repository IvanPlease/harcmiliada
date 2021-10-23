package queivan.harcmiliada.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "_answers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
    @Id
    @NotNull
    @GeneratedValue
    private UUID id;
    @NotNull
    private String content;
    @NotNull
    private Long points;
    @NotNull
    @Builder.Default
    private boolean checked = false;
    @ManyToOne
    private Question question;
}
