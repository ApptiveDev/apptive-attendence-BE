package apptive.attendence.dto.response;

public record PenaltyDTO (
        Integer penalty,
        String reason,
        String notes
) {}
