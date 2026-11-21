package apptive.attendence.dto.request;

import java.sql.Time;
import java.util.Date;

public record ScheduleRequestDTO (
        String title,
        Date scheduleDate,
        Time startTime,
        Time endTime,
        Integer permissionPenalty,
        Integer nonPermissionPenalty
) {}
