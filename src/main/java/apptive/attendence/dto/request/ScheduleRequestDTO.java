package apptive.attendence.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleRequestDTO (
        String title,
        LocalDate scheduleDate,
        LocalTime startTime,
        LocalTime endTime,
        Integer permissionPenalty,
        Integer nonPermissionPenalty
) {}
