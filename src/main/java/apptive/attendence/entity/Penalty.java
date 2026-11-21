package apptive.attendence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private Date penaltyDate;

    @ManyToOne
    @JoinColumn(name = "penaltyrule_id")
    private PenaltyRule penaltyRule;

    private LocalDateTime createAt = LocalDateTime.now();
}
