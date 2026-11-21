package apptive.attendence.dto.response;

import apptive.attendence.entity.Schedule;

import java.sql.Time;
import java.util.Date;

public record ScheduleResponseDTO (
        Long scheduleId,
        String title,
        Date scheduleDate,
        Time startTime,
        Time endTime,
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
