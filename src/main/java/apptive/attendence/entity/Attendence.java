package apptive.attendence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.context.annotation.Primary;

@Entity
public class Attendence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule scheduleId;

    @NotNull
    private Boolean attendence = false;

    private String absentReason;

    private String evidenceURL;
}
