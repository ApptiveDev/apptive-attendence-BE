package apptive.attendence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @NotNull
    @Builder.Default
    private Boolean attendence = false;

    private String absentReason;

    private String evidenceURL;
}
