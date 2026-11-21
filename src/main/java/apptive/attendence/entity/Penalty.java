package apptive.attendence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
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
}
