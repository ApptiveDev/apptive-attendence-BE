package apptive.attendence.entity;

import apptive.attendence.dto.request.ScheduleRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private LocalDate scheduleDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer permissionPenalty;

    private Integer nonPermissionPenalty;

    public void updateScheduleInfo(ScheduleRequestDTO scheduleRequestDTO) {
        this.title = scheduleRequestDTO.title();
        this.scheduleDate = scheduleRequestDTO.scheduleDate();
        this.startTime = scheduleRequestDTO.startTime();
        this.endTime = scheduleRequestDTO.endTime();
        this.permissionPenalty = scheduleRequestDTO.permissionPenalty();
        this.nonPermissionPenalty = scheduleRequestDTO.nonPermissionPenalty();
    }
}
