package apptive.attendence.dto.request;

import java.util.Date;

public record PenaltyRequestDTO(
   Long userId,
   Long scheduleId,
   Integer penaltyRuleId
) {}
