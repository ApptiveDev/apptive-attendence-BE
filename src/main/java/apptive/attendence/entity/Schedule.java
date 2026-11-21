package apptive.attendence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;
import java.util.Date;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Date scheduleDate;

    private Time startTime;

    private Time endTime;

    private Integer permissionPenalty;

    private Integer nonPermissionPenalty;
}
