package apptive.attendence.dto.response;

import apptive.attendence.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleResponseDTO (
        Long scheduleId,
        String title,
        LocalDate scheduleDate,
        LocalTime startTime,
        LocalTime endTime,
        Integer permissionPenalty,
        Integer nonPermissionPenalty
) {
    public static ScheduleResponseDTO from(Schedule schedule) {
        return new ScheduleResponseDTO(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getScheduleDate(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getPermissionPenalty(),
                schedule.getNonPermissionPenalty()
        );
    }
}
